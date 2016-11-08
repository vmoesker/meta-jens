#!/bin/sh
#
# Called from udev
#
# Attempt to symlink radio tty's for Z-Wave sticks
. @LEDCTRL@/ledctrl

zway_home="/opt/z-way"
tty_zwave="/dev/ttyZWave"

if [ "$ACTION" = "add" ] && [ -n "$DEVNAME" ]
then

    ttyid=`basename ${DEVPATH}`

    if [ "$ID_VENDOR_ID" = "0658" ] && [ "$ID_MODEL_ID" = "0200" ]  # this is the new Z-Wave Stick
    then
        tty=`basename /sys${DEVPATH}/${ttyid}*/tty/tty[A]*`
    elif [ "$ID_VENDOR_ID" = "0403" ] && [ "$ID_MODEL_ID" = "6001" ]  # this is the old FTDI based Z-Wave Stick
    then
        tty=`basename /sys${DEVPATH}/${ttyid}*/tty[U]*`
    else  # no stick fits
        exit 1
    fi

    tty=/dev/$tty

    echo "test -n \"$tty\" -a ! -c $tty_zwave && ln -fs $tty $tty_zwave" >>/tmp/update_tty
    test -n "$tty" -a ! -c $tty_zwave && ln -fs $tty $tty_zwave
    led_zwave

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
