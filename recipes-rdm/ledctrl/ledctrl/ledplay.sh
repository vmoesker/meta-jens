#!/bin/sh
### BEGIN INIT INFO
# Provides:             ledplay
# Required-Start:       $local_fs
# Required-Stop:      $local_fs
# Default-Start:
# Default-Stop:
# Short-Description:  Enables/Disables each LED once at boot
### END INIT INFO

for led in `cd /sys/class/leds/ && echo *`
do
    echo 255 >/sys/class/leds/${led}/brightness
done

sleep 2

for led in `cd /sys/class/leds/ && echo *`
do
    echo 0 >/sys/class/leds/${led}/brightness
done

echo 255 >/sys/class/leds/boot/brightness
echo heartbeat >/sys/class/leds/boot/trigger

exit 0
