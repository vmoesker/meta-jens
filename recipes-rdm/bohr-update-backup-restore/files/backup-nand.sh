#!/bin/sh
 
set -x
set -e
 
WLAN_ADDR=$(cat /sys/class/net/[mw]lan0/address | sed -e 's/://g')
 
mkdir -p /data/.backup/${WLAN_ADDR}
cd /data/.backup/${WLAN_ADDR}
 
echo "Dumping mtd0"
nanddump -nof mtd0 /dev/mtd0
 
echo "Dumping mtd1"
nanddump -nof mtd1 /dev/mtd1
 
echo "Dumping mtd2"
nanddump -nof mtd2 /dev/mtd2
 
echo "done...."
