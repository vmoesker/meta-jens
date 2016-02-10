#!/bin/sh
 
set -x
set -e
 
WLAN_ADDR=$(cat /sys/class/net/[mw]lan0/address | sed -e 's/://g')

cd /data/.backup/${WLAN_ADDR}
 
test -e mtd0
test -e mtd1
test -e mtd2
 
echo "Erase and flash mtd0"
flash_erase /dev/mtd0 0 0
nandwrite -no /dev/mtd0 mtd0
 
echo "Erase and flash mtd1"
flash_erase /dev/mtd1 0 0
nandwrite -no /dev/mtd1 mtd1
 
echo "Erase and flash mtd2"
flash_erase /dev/mtd2 0 0
nandwrite -no /dev/mtd2 mtd2
 
echo "done...."
