#!/bin/sh

set -x
set -e

IP=`ip addr show dev eth0 | grep "\<inet\>" | awk '{print $2}' | sed -e 's,/.*$,,'`
SERVERIP=`echo $IP | sed -E 's,[^.]+$,1,'`
VLAN=`echo $IP | awk -F . '{print $3}'`

mount -t nfs -o rw ${SERVERIP}:/srv/nfs/dataprd_vlan${VLAN} /data
