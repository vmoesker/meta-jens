#!/bin/sh

ifdown wlan0

test -x /usr/sbin/ethtool && test -f /etc/ethtool-config && ETHTOOL_APPLY=/run/media/*/dont-apply-ethernet.settings && test $ETHTOOL_APPLY = "/run/media/*/dont-apply-ethernet.settings" && . /etc/ethtool-config && /usr/sbin/ethtool ${ETHTOOL_ARGS}

exit 0
