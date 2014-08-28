#!/bin/sh -e
### BEGIN INIT INFO
# Provides:          start wlan0 if eth0 isn't up
# Required-Start:    mountvirtfs $local_fs
# Required-Stop:     $local_fs
# Should-Start:      ifup wlan0
# Should-Stop:       ifupdown
# Default-Start:     S
# Default-Stop:      0 6
# Short-Description: Raise network interfaces.
### END INIT INFO

PATH="/usr/local/sbin:/usr/local/bin:/sbin:/bin:/usr/sbin:/usr/bin"

[ -x /sbin/ifup ] || exit 0

case "$1" in
start)
	if ip link show | egrep "eth0.*NO-CARRIER"
	then
    		echo "eth0 got no-carrier, starting wifi instead .."
		ifup wlan0
		echo "done."
	else
   		echo "eth0 got a carrier,  wifi start is not required."
	fi	
	;;

stop)
	;;

*)
	echo "Usage: /etc/init.d/networking {start|stop}"
	exit 1
	;;
esac

exit 0

