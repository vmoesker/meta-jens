#!/bin/sh
### BEGIN INIT INFO
# Provides:          restart-udev
# Required-Start:    
# Required-Stop: 
# Default-Start:     3 5
# Default-Stop:
# Short-Description: re-trigger udev add to catch missing signals
# Description:
### END INIT INFO

/usr/bin/udevadm trigger -c add
