#!/bin/sh

test -x /etc/network/if-pre-up.d/wpa-supplicant && /etc/network/if-pre-up.d/wpa-supplicant

echo 0 > /sys/devices/platform/leds-gpio/leds/wifi/brightness
