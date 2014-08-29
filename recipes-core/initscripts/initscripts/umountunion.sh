#!/bin/sh
### BEGIN INIT INFO
# Provides:          umountunion
# Required-Start:
# Required-Stop:     umountnfs
# Should-Stop:
# Default-Start:
# Default-Stop:      0 6
# Short-Description: Unmount all union filesystems
### END INIT INFO

PATH=/sbin:/bin:/usr/sbin:/usr/bin

echo "Unmounting overlay filesystems..."

test -f /etc/fstab && (

#
#	Read through fstab line by line and unount network file systems
#
cat /etc/fstab | sed -E 's/#.*//g' | while read device mountpt fstype options
do
	if test "$fstype" = unionfs
	then
		umount -f $mountpt
	fi
done
)

: exit 0
