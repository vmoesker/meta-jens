#!/bin/sh
### BEGIN INIT INFO
# Provides:          fb_cursor_off
# Required-Start:
# Required-Stop:
# Default-Start:     3 5
# Default-Stop:
# Short-Description: Turn cursor of on fb console
# Description:       Turn cursor of on fb console
### END INIT INFO

echo 0 >  /sys/class/graphics/fbcon/cursor_blink
