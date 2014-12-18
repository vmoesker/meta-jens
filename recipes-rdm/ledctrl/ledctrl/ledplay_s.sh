#!/bin/sh
### BEGIN INIT INFO
# Provides:             ledplay_s
# Required-Start:       $local_fs
# Required-Stop:      $local_fs
# Default-Start:
# Default-Stop:
# Short-Description:  Enables/Disables each LED once at boot
### END INIT INFO

for led in user2 user1
do
    echo 255 >/sys/class/leds/${led}/brightness
    sleep 1
    echo 0 >/sys/class/leds/${led}/brightness
done

exit 0
