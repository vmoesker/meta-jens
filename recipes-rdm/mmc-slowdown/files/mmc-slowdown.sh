#!/bin/sh
### BEGIN INIT INFO
# Provides:             mmc-slowdown
# Required-Start:       
# Required-Stop:      
# Default-Start:
# Default-Stop:
# Short-Description:  
### END INIT INFO

case "$1" in
        start)
                echo "39000000" >/sys/kernel/debug/mmc@KERNEL_MMC_DEV@/clock
                ;;
        stop)
                ;;
        *)
                echo "Usage $0 (start|stop)" >&2
                exit 1
                ;;
esac

: exit 0
