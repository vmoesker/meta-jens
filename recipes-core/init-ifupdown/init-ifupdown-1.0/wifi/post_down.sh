#!/bin/sh

test -x /etc/network/if-pre-up.d/wpa-supplicant && /etc/network/if-pre-up.d/wpa-supplicant stop

echo 0 > /sys/devices/platform/leds-gpio/leds/wifi/brightness
