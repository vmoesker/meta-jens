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

waitfornetwork() {
	FOUND=0
	for l in $(seq 1 2); do
		for i in $(seq 0 3); do
			nslookup ${i}.de.pool.ntp.org 1>/dev/null 2>/dev/null && FOUND=1 && break
			sleep 1
		done

		test $FOUND -eq 1 && break
	done
}

# avoid punish users for broken wifi setup
started_wifi() {
	logger -s "starting wlan0 done."
	waitfornetwork
}

case "$1" in
start)
	waitfornetwork
	test -f /sys/class/net/eth0/address && logger -s "eth0 has address `cat /sys/class/net/eth0/address`"
	if test `cat /sys/class/net/eth0/carrier` -eq 0
	then
		if [ -e /sys/class/net/wlan0/address -a -f /data/.shadow/.etc/wpa_supplicant.enabled -a -f /data/.shadow/.etc/wpa_supplicant.conf ]
		then
			logger -s "eth0 got no-carrier, starting wlan0 instead."
			ifup wlan0 && started_wifi || logger -s "starting wlan0 failed."
		else
			logger -s "eth0 got no-carrier, but neither /etc/wpa_supplicant.conf to start wifi"
		fi
	else
   		logger -s "eth0 got a carrier, wifi start is not required."
	fi	
	;;

stop)
	;;

*)
	echo "Usage: /etc/init.d/wifi-fallback.sh {start|stop}"
	exit 1
	;;
esac

exit 0

