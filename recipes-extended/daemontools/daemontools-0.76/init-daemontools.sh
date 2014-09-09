#!/bin/sh
### BEGIN INIT INFO
# Provides:             daemontools
# Required-Start:       
# Required-Stop:      
# Default-Start:
# Default-Stop:
# Short-Description:  runs svscanboot &
### END INIT INFO

case "$1" in
	start)
		/etc/init.d/svscanboot &
		;;
	*)
		echo "Usage $0 (start)" >&2
		exit 1
		;;
esac

: exit 0
