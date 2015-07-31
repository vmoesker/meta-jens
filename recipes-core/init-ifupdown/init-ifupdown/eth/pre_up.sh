#!/bin/sh

ifdown wlan0
test -x /etc/network/if-pre-up.d/wpa-supplicant && MODE=stop IF_WPA_CONF=/etc/wpa_supplicant.conf IFACE=wlan0 IF_WPA_DRIVER=wext /etc/network/if-pre-up.d/wpa-supplicant

test -x /usr/sbin/ethtool && test -f /etc/ethtool-config && ETHTOOL_APPLY=/media/*/dont-apply-ethernet.settings && test $ETHTOOL_APPLY = "/media/*/dont-apply-ethernet.settings" && . /etc/ethtool-config && /usr/sbin/ethtool ${ETHTOOL_ARGS}

exit 0
