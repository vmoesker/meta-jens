#!/bin/sh

source /etc/network/wifi/defaults

if [ $(lsmod | grep $wifi_module | wc -l) -eq 0 ]; then
	modprobe $wifi_module
fi

