#!/bin/sh
#
# Called from udev
#
# Attempt to symlink radio tty's for DuoFern sticks

if [ "$ACTION" = "add" ] && [ -n "$DEVNAME" ]
then
    ttyid=`basename ${DEVPATH}`
    tty=`basename /sys${DEVPATH}/${ttyid}*/ttyUSB*`
    echo "DF ADD at $tty on $(date)" >> /tmp/update_tty
        echo "$tty" >> /tmp/update_tty
    ln -s $tty /dev/ttyDuoFern
    echo 255 >/sys/class/leds/user2/brightness
elif [ "$ACTION" = "remove" ] && [ -n "$DEVNAME" ]
then
    echo "DF REM from $DEVNAME on $(date)" >> /tmp/update_tty
    test ! -c /dev/ttyDuoFern && rm /dev/ttyDuoFern
fi
