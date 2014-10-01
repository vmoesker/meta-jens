#!/bin/sh
### BEGIN INIT INFO
# Provides:          restart-udev
# Required-Start:    
# Required-Stop: 
# Default-Start:     3 5
# Default-Stop:
# Short-Description: restart udev to catch missing signals
# Description:
### END INIT INFO

/etc/init.d/udev restart
