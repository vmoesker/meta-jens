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

waitfornetwork(){
	for i in $(seq 1 10); do
		nslookup 0.de.pool.ntp.org 1>/dev/null 2>/dev/null && break
		sleep 1
	done
}

case "$1" in
start)
	waitfornetwork
	if test `cat /sys/class/net/eth0/carrier` -eq 0
	then
    		echo "eth0 got no-carrier, starting wifi instead .."
		ifup wlan0
		echo "done."
		waitfornetwork
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

