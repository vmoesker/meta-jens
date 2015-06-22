#!/bin/sh

test -x /etc/network/if-pre-up.d/wpa-supplicant && /etc/network/if-pre-up.d/wpa-supplicant

echo 0 > /sys/class/leds/wifi/brightness
if test `cat /sys/class/net/eth0/carrier` -eq 0
then
	echo "eth0 got no-carrier ..."
	echo 255 > /sys/class/leds/error/brightness
fi
