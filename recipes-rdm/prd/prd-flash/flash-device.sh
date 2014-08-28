#!/bin/sh

BOOT_SPACE="8192"
IMAGE_ROOTFS_ALIGNMENT="4096"

BOOTFS_SIZE=$(expr 1024 \* 32)

SDCARD_DEVICE="/dev/mmcblk0"
test -e /dev/mmcblk1 && SDCARD_DEVICE="/dev/mmcblk1"

SDCARD_SIZE=`fdisk -l $SDCARD_DEVICE | grep "Disk $SDCARD_DEVICE" | awk '{print $5}'`
SDCARD_SIZE=$(expr $SDCARD_SIZE \/ 1024)

# use last image container
for c in /var/tmp/flashimg/*-complete.cpi /data/flashimg/*-complete.cpi
do
    IMAGE_CONTAINER="$c"
done

cd /var/tmp/
tar xjf "${IMAGE_CONTAINER}" .settings
. ./.settings

if test "$DEV" -eq 0
then
    ROOTFS_SIZE=$(expr 1024 \* 512)
    RECOVERY_SIZE=$(expr 1024 \* 128)
else
    ROOTFS_SIZE=$(expr 1024 \* 1024)
    RECOVERY_SIZE=$(expr 1024 \* 512)
fi

BOOT_SPACE_ALIGNED=$(expr ${BOOTFS_SIZE} + ${IMAGE_ROOTFS_ALIGNMENT} - 1)
BOOT_SPACE_ALIGNED=$(expr ${BOOT_SPACE_ALIGNED} - ${BOOT_SPACE_ALIGNED} % ${IMAGE_ROOTFS_ALIGNMENT})

BOOT_SPACE_START=${IMAGE_ROOTFS_ALIGNMENT}
BOOT_SPACE_END=$(expr ${IMAGE_ROOTFS_ALIGNMENT} \+ ${BOOT_SPACE_ALIGNED})

ROOTFS_SIZE_ALIGNED=$(expr ${ROOTFS_SIZE} + ${IMAGE_ROOTFS_ALIGNMENT} - 1)
ROOTFS_SIZE_ALIGNED=$(expr ${ROOTFS_SIZE_ALIGNED} - ${ROOTFS_SIZE_ALIGNED} % ${IMAGE_ROOTFS_ALIGNMENT})

ROOTFS_SPACE_START=${BOOT_SPACE_END}
ROOTFS_SPACE_END=$(expr ${ROOTFS_SPACE_START} \+ ${ROOTFS_SIZE_ALIGNED})

RECOVERY_SIZE_ALIGNED=$(expr ${RECOVERY_SIZE} + ${IMAGE_ROOTFS_ALIGNMENT} - 1)
RECOVERY_SIZE_ALIGNED=$(expr ${RECOVERY_SIZE_ALIGNED} - ${RECOVERY_SIZE_ALIGNED} % ${IMAGE_ROOTFS_ALIGNMENT})

RECOVERFS_SPACE_START=${ROOTFS_SPACE_END}
RECOVERFS_SPACE_END=$(expr ${RECOVERFS_SPACE_START} \+ ${RECOVERY_SIZE_ALIGNED})

parted -s ${SDCARD_DEVICE} mklabel msdos
parted -s ${SDCARD_DEVICE} unit KiB mkpart primary ${BOOT_SPACE_START} ${BOOT_SPACE_END}
parted -s ${SDCARD_DEVICE} unit KiB mkpart primary ${ROOTFS_SPACE_START} ${ROOTFS_SPACE_END}
parted -s ${SDCARD_DEVICE} unit KiB mkpart primary ${RECOVERFS_SPACE_START} ${RECOVERFS_SPACE_END}
parted -s ${SDCARD_DEVICE} unit KiB mkpart primary ${RECOVERFS_SPACE_END} $(expr ${SDCARD_SIZE} - 1)
parted ${SDCARD_DEVICE} print

mkdir -p /var/tmp/flashimg/root/boot /var/tmp/flashimg/root/data

mkfs.ext2 -I128 -L "boot-${MACHINE}" ${SDCARD_DEVICE}p1
mount ${SDCARD_DEVICE}p1 /var/tmp/flashimg/root/boot
mkfs.ext4 -L "data-${MACHINE}" ${SDCARD_DEVICE}p4
mount ${SDCARD_DEVICE}p4 /var/tmp/flashimg/root/data

tar xjf "${IMAGE_CONTAINER}" -O ${UBOOT_BIN} | dd of=${SDCARD_DEVICE} conv=notrunc seek=2 skip=${UBOOT_PADDING} bs=512
tar xjf "${IMAGE_CONTAINER}" -O ${ROOTIMG} | dd of=${SDCARD_DEVICE}p2 bs=1M
tar xjf "${IMAGE_CONTAINER}" -O ${RECOVERIMG} | dd of=${SDCARD_DEVICE}p3 bs=1M

(cd /var/tmp/flashimg/root/boot && tar xjf "${IMAGE_CONTAINER}" ${KERNEL} && chown -R root:root .)
mkdir -p /var/tmp/flashimg/root/data/tmp /var/tmp/flashimg/root/data/.shadow/.etc /var/tmp/flashimg/root/data/.shadow/.home /var/tmp/flashimg/root/data/.shadow/.var_lib

umount /var/tmp/flashimg/root/boot /var/tmp/flashimg/root/data
