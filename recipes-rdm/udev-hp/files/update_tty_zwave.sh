#!/bin/sh
#
# Called from udev
#
# Attempt to symlink radio tty's for Z-Wave sticks

zway_home="/opt/z-way"
tty_zwave="/dev/ttyZWave"

if [ "$ACTION" = "add" ] && [ -n "$DEVNAME" ]
then
    ttyid=`basename ${DEVPATH}`
    tty=`basename /sys${DEVPATH}/${ttyid}*/tty[AU]*`
    tty=$(cd $zway_home && env LD_LIBRARY_PATH="./libs:./libzway:./libzwayhttp:./libzwayjs:/opt/v8/lib" ./z-get-tty /dev/$tty)

    echo "test -n \"$tty\" -a ! -c $tty_zwave && ln -fs $tty $tty_zwave" >>/tmp/update_tty
    test -n "$tty" -a ! -c $tty_zwave && ln -fs $tty $tty_zwave
    echo 255 >/sys/class/leds/user1/brightness
elif [ "$ACTION" = "remove" ] && [ -n "$DEVNAME" ]
then
    if [ -e $tty_zwave ]
    then
	tty="`readlink $tty_zwave`"
	#if [ ! -c $tty_zwave ]; then
        # Z-Wave stick is not present, but maybe the symlink still exists...
	echo "test \"$DEVNAME\" = \"$tty\" && rm -f $tty_zwave" >>/tmp/update_tty
	test "x${DEVNAME}" = "x${tty}" && rm -f $tty_zwave
    fi
fi
