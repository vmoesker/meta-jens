#!/bin/sh

# Enable LED
echo 255 > /sys/devices/platform/leds-gpio/leds/wifi/brightness
