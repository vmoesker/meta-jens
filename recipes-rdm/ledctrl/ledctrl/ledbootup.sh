#!/bin/sh
### BEGIN INIT INFO
# Provides:             ledplay
# Required-Start:       $local_fs
# Required-Stop:      $local_fs
# Default-Start:
# Default-Stop:
# Short-Description:  Enables/Disables each LED once at boot
### END INIT INFO

echo none >/sys/class/leds/boot/trigger
echo 0 >/sys/class/leds/boot/brightness
echo 255 >/sys/class/leds/boot/brightness

exit 0
