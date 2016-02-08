#!/bin/sh
### BEGIN INIT INFO
# Provides:          mountoverlay
# Required-Start:    $local_fs
# Required-Stop: 
# Default-Start:     S
# Default-Stop:
# Short-Description: Mount all overlay filesystems.
# Description:
### END INIT INFO

set -x
set -e

. /etc/default/rcS

PATH=/sbin:/bin:/usr/sbin:/usr/bin

ROOTDEV=`mount | grep "on / type" | sed -e 's/ on.*//'`
BOOTDEV=`echo ${ROOTDEV} | sed -E -e 's/[0-9]+$//'`

DATADEV="${BOOTDEV}2"

if [ ! -e "${DATADEV}" ]
then
    STICK_SIZE=`fdisk -l "${BOOTDEV}" | grep "Disk ${BOOTDEV}" | awk '{print $5}'`
    STICK_SIZE=$(expr $STICK_SIZE \/ 1024)

    ROOT_SIZE=`fdisk -l "${BOOTDEV}" | grep "${ROOTDEV}" | awk '{print $3}'`
    ROOT_SIZE=$(expr $ROOT_SIZE \/ 2)

    parted -s ${BOOTDEV} unit KiB mkpart primary $(expr ${ROOT_SIZE} + 1) $(expr ${STICK_SIZE} - 1)
    mkfs.ext4 -L "data" ${DATADEV}

    mount ${DATADEV} /data

    mkdir -p /data/tmp /data/flashimg
fi

mount | grep -q "on /data type" || \
    mount ${DATADEV} /data

egrep -q "[[:space:]]/data" /etc/fstab || \
    echo "${DATADEV}	/data		ext4	defaults		1 2" >>/etc/fstab

:
