# Copyright (C) 2014 Shanghai Zhixing Information Technology Co.Ltd

SUMMARY = "Linux Kernel for Curie Board"
DESCRIPTION = "Linux Kernel for Curie Board"

require recipes-kernel/linux/linux-imx.inc
require recipes-kernel/linux/linux-dtb.inc

DEPENDS += "lzop-native bc-native u-boot-curie"

SRCBRANCH = "curie_3.10.17_1.0.0_ga"
LOCALVERSION = "+curie"

SRC_URI = "git://github.com/rdm-dev/linux-curie.git;branch=${SRCBRANCH};rev=318f8d4de37c0206f6fc0d8284c5c4b603dc5e9b \
           file://0001-Importing-rtl8189es_4.3.0-driver.patch \
           file://0002-include-rtl8189es-driver-in-kernel-build.patch \
           file://0003-Add-platform-specific-modifications-for-Curie.patch \
           file://0004-don-t-printout-debug-message-when-DBG-is-off.patch \
	   file://0099-handle-additional-cooling-info.patch \
	   file://unionfs-2.6_for_3.10.53.patch \
	   file://overlayfs-v18.patch \
	   file://defconfig \
	   file://bootscript.mmc \
	   file://bootscript.nfs \
	   file://bootscript.usb \
          "

# patches for curie
COMPATIBLE_MACHINE = "(curie)"

do_install_append () {
    sed -i -e "s/@UBOOT_LOADADDRESS[@]/${UBOOT_LOADADDRESS}/g" -e "s/@UBOOT_FDTADDRESS[@]/${UBOOT_FDTADDRESS}/g" \
         -e "s/@UBOOT_MMC_DEV[@]/${UBOOT_MMC_DEV}/g" -e "s/@SDCARD_IMAGE[@]/${SDCARD_IMAGE}/g" \
         -e "s/@KERNEL_MMC_DEV[@]/${KERNEL_MMC_DEV}/g" \
	 ${WORKDIR}/bootscript.mmc ${WORKDIR}/bootscript.nfs ${WORKDIR}/bootscript.usb
    uboot-mkimage -T script -C none -n 'Curie Script' -d ${WORKDIR}/bootscript.mmc ${D}/boot/bootscript.mmc
    uboot-mkimage -T script -C none -n 'Curie Script' -d ${WORKDIR}/bootscript.nfs ${D}/boot/bootscript.nfs
    uboot-mkimage -T script -C none -n 'Curie Script' -d ${WORKDIR}/bootscript.usb ${D}/boot/bootscript.usb

    echo "options 8189es rtw_power_mgnt=0" >${WORKDIR}/8189es.conf
    install -d ${D}${sysconfdir}/modprobe.d/
    install -m 644 ${WORKDIR}/8189es.conf ${D}${sysconfdir}/modprobe.d/
}

do_deploy_append () {
    set -x
    cp ${D}/boot/bootscript.mmc ${DEPLOYDIR}/bootscript.mmc-${DATETIME}
    cp ${D}/boot/bootscript.nfs ${DEPLOYDIR}/bootscript.nfs-${DATETIME}
    cp ${D}/boot/bootscript.usb ${DEPLOYDIR}/bootscript.usb-${DATETIME}
    ln -sf bootscript.mmc-${DATETIME} ${DEPLOYDIR}/bootscript.mmc
    ln -sf bootscript.nfs-${DATETIME} ${DEPLOYDIR}/bootscript.nfs
    ln -sf bootscript.usb-${DATETIME} ${DEPLOYDIR}/bootscript.usb

    ln -sf bootscript.mmc-${DATETIME} ${DEPLOYDIR}/bootscript
    test ${USBSTICK_IMAGE} -eq 1 && ln -sf bootscript.usb-${DATETIME} ${DEPLOYDIR}/bootscript
    : # exit 0
}

FILES_kernel-image += "/boot/bootscript.mmc /boot/bootscript.nfs /boot/bootscript.usb"
