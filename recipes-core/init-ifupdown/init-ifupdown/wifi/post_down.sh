#!/bin/sh

. @LEDCTRL@/ledctrl

silence_wifi
test `cat /sys/class/net/eth0/carrier` -ne 1 || @BINDIR@/enable-error no-carrier
