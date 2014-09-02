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

for i in `seq 5`; do
    if grep unknown /sys/class/net/eth0/operstate
    then
	sleep 1
    fi
done

case "$1" in
start)
	if test `cat /sys/class/net/eth0/carrier` -eq 0
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

