#!/bin/sh

test -x /etc/network/if-pre-up.d/wpa-supplicant && /etc/network/if-pre-up.d/wpa-supplicant stop
ifup wlan0
