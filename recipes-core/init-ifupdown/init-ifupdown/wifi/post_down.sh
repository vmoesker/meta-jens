#!/bin/sh

. @LEDCTRL@/ledctrl

silence_wifi
if test `cat /sys/class/net/eth0/carrier` -eq 0
then
	logger -s "eth0 got no-carrier ..."
	led_error
fi
