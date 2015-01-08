#!/bin/sh

# Frank measured 45 seconds between LED turned into ready state and every application is runnning
# sleep 60 seconds just to be sure

sleep 60s

echo none >/sys/class/leds/boot/trigger
echo 0 >/sys/class/leds/boot/brightness

exit 0
