#!/bin/sh

# Frank measured >45 seconds between LED turned into ready state and every application is runnning
# sleep 120 seconds just to be sure

sleep 120s

echo none >/sys/class/leds/boot/trigger
echo 0 >/sys/class/leds/boot/brightness

exit 0
