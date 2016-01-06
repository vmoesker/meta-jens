#!/bin/sh
#
# Called from udev
#
# Attempt to symlink radio tty's for DuoFern sticks

tty_df="/dev/ttyDuoFern"

if [ "$ACTION" = "add" ] && [ -n "$DEVNAME" ]
then
    ttyid=`basename ${DEVPATH}`
    tty="/dev/`basename /sys${DEVPATH}/${ttyid}*/ttyUSB*`"
    echo "DF ADD: $DEVPATH on $(date)" >> /tmp/update_tty
    echo "DF ADD: $tty" >> /tmp/update_tty
    ln -sf $tty ${tty_df}
    echo 255 >/sys/class/leds/@DUOFERN_LED@/brightness
elif [ "$ACTION" = "remove" ] && [ -n "$DEVNAME" ]
then
    # echo "DF REM from $DEVNAME on $(date)" >> /tmp/update_tty
    # test ! -c ${tty_df} && rm ${tty_df}
    if [ -e $tty_df ]
    then
	tty="`readlink $tty_df`"
        # DF stick is not present, but maybe the symlink still exists...
	echo "test \"$DEVNAME\" = \"$tty\" && rm -f $tty_df" >>/tmp/update_tty
	test "x${DEVNAME}" = "x${tty}" && rm -f $tty_df
    fi
fi
