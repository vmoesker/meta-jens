#!/bin/sh
### BEGIN INIT INFO
# Provides: wa-mmc-timeout
# Required-Start:
# Required-Stop:
# Default-Start:
# Default-Stop:
# Short-Description:
### END INIT INFO

NAME=wa-mmc-timeout
EXEC=/usr/sbin/wa-mmc-timeout
PIDFILE=/var/run/wa-mmc-timeout.pid

test -x $EXEC || exit 0

set -e

case "$1" in
    start)
        echo -n "Starting $NAME: "
        start-stop-daemon --start --quiet --pidfile $PIDFILE --make-pidfile --background --exec $EXEC --name $NAME
        echo "done."
        ;;
    stop)
        echo -n "Stopping $NAME: "
        start-stop-daemon --stop --quiet --pidfile $PIDFILE --name $NAME
        echo "done."
        ;;
    *)
        echo "Usage $0 (start|stop)" >&2
        exit 1
        ;;
esac

exit 0
