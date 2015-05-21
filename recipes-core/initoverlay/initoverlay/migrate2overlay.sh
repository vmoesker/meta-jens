#!/bin/sh

set -x

test -f /etc/fstab && (

#
#	Read through fstab line by line and mount overlay file systems
#
cat /etc/fstab | sed -E 's/#.*//g' | while read device mountpt fstype options freq passno
do
    case "$fstype" in
    unionfs)
        lowerdir=`echo "$options" | sed -E 's/.*dirs=([^=]+)=rw.*/\1/g'`
        ;;
    overlayfs)
        lowerdir=`echo "$options" | sed -E 's/.*upperdir=([^,]+).*/\1/g'`
        ;;
    *)
        # skip
        ;;
    esac

    test -n "$lowerdir" || continue
    mkdir -p "${lowerdir}"

    if [ -z "$ROOTDEV" ]
    then
        ROOTDEV=`mount | grep "on / type" | sed -e 's/ on.*//'`
    fi

    if [ $(echo ${ROOTDEV} | egrep 'p3$') ]
    then
	if [ -z "${PRIMDEV}" ]
	then
	    PRIMDEV=`echo $ROOTDEV | awk -F/ '{print $3}' | sed -E 's/p3$/p2/g'`
	    if [ ! -d /var/volatile/media ]
	    then
		mkdir -p "/var/volatile/media"
		chmod 0755 /var/volatile/media
	    fi

	    if [ ! -d /var/volatile/media/${PRIMDEV} ]
	    then
		mkdir /var/volatile/media/${PRIMDEV}
		mount -o ro /dev/${PRIMDEV} /var/volatile/media/${PRIMDEV}
	    fi
	fi
    else
        continue
    fi

    (
    cat /var/volatile/media/${PRIMDEV}/etc/fstab | sed -E 's/#.*//g' | while read pri_dev pri_mnt pri_fs pri_opt pri_freq pri_pno
    do
        case "${pri_fs}->${fstype}" in
        "unionfs->overlayfs")
            pri_lower=`echo "$pri_opt" | sed -E 's/.*dirs=([^=]+)=rw.*/\1/g'`
            if [ "$pri_lower" = "$lowerdir" ]
            then
                # yeah, we want to migrate
                (
		find $lowerdir | while read path_entry
                do
                    bn=`basename "$path_entry"`
                    dn=`dirname "$path_entry"`
                    case "$bn" in
                    .wh.__dir_opaque)
                        rm -rf "${dn}/.wh.__dir_opaque"
                        setfattr -n "trusted.overlay.opaque" -v "y" "${dn}"
                        ;;
                    .wh.*)
                        opaque="n"
                        if [ -d "${path_entry}" -a -e "${path_entry}/.wh.__dir_opaque" ]
                        then
                            opaque="y"
                        fi
                        rm -rf "$path_entry"
                        pure_bn=`echo $bn | sed -e 's/.wh.//'`
                        ln -sf "(overlay-whiteout)" "${dn}/${pure_bn}"
                        setfattr -n "trusted.overlay.whiteout" -v "y" "${dn}/${pure_bn}"
                        test "${opaque}" = "y" && setfattr -n "trusted.overlay.opaque" -v "y" "${dn}/${pure_bn}"
                        ;;
                    esac
                done
		)
            fi
            ;;
        esac
    done
    )
done
)

if [ -n "$PRIMDEV" -a -d /var/volatile/media/${PRIMDEV} ]
then
    busybox umount /var/volatile/media/${PRIMDEV}
    rmdir /var/volatile/media/${PRIMDEV}
fi

: exit 0
