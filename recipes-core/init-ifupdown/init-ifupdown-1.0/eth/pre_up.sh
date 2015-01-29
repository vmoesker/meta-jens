#!/bin/sh

ifdown wlan0
test -x /etc/network/if-pre-up.d/wpa-supplicant && MODE=stop IF_WPA_CONF=/etc/wpa_supplicant.conf IFACE=wlan0 IF_WPA_DRIVER=wext /etc/network/if-pre-up.d/wpa-supplicant
