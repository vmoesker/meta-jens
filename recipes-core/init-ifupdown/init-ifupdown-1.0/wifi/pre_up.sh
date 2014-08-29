#!/bin/sh

test -r /etc/network/wifi/defaults && source /etc/network/wifi/defaults

test -x /etc/network/if-pre-up.d/wpa-supplicant && /etc/network/if-pre-up.d/wpa-supplicant stop
test -x /etc/network/if-pre-up.d/wpa-supplicant && /etc/network/if-pre-up.d/wpa-supplicant start

if [ $(lsmod | grep $wifi_module | wc -l) -eq 0 ]; then
	modprobe $wifi_module
fi

