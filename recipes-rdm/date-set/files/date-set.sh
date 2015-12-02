#!/bin/sh
### BEGIN INIT INFO
# Provides:		date-set
# Required-Start:
# Required-Stop:
# Default-Start:
# Default-Stop:
# Short-Description:
### END INIT INFO

case "$1" in
        start)
		# Set the system clock from hardware clock
		# If the timestamp is more recent than the current time,
		# use the timestamp instead.
		test -x /etc/init.d/hwclock.sh && /etc/init.d/hwclock.sh start
		if test -e /etc/timestamp
		then
		        SYSTEMDATE=`date -u +%4Y%2m%2d%2H%2M`
		        read TIMESTAMP < /etc/timestamp
		        if [ ${TIMESTAMP} -gt $SYSTEMDATE ]; then
		                date -u ${TIMESTAMP#????}${TIMESTAMP%????????}
		                test -x /etc/init.d/hwclock.sh && /etc/init.d/hwclock.sh stop
		        fi
		fi
                ;;
        stop)
                ;;
        *)
                echo "Usage $0 (start|stop)" >&2
                exit 1
                ;;
esac

: exit 0
