#!/bin/sh
### BEGIN INIT INFO
# Provides:             ledreadydelay.sh
# Required-Start:       $local_fs
# Required-Stop:      $local_fs
# Default-Start:
# Default-Stop:
# Short-Description:  Waits measured time to allow homepilot/xbmc etc. come up
### END INIT INFO

# Frank measured 45 seconds between LED turned into ready state and every application is runnning
# sleep 60 seconds just to be sure
sleep 60

exit 0
