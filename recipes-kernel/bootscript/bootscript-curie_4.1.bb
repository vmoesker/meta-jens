# Copyright (C) 2015 Jens Rehsack <sno@netbsd.org>
# Released under the GPLv2 license (see COPYING.MIT for the terms)

DESCRIPTION = "U-Boot BootScripts for linux-curie-4.1"
PN = "bootscript-curie-${WANTED_ROOT_DEV}"

inherit bootscript

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "u-boot-mkimage-native"
RDEPENDS_${PN} = "u-boot-curie"

FILESEXTRAPATHS_prepend := "${THISDIR}/bootscript-${MACHINE}-${PV}:"

SRC_URI = "file://bootscript.mmc \
           file://bootscript.nfs \
           file://bootscript.usb \
"

COMPATIBLE_MACHINE = "(curie)"

do_install () {
    set -x
    DEFAULT_BOOTSCRIPT="bootscript.mmc"
    test -f ${WORKDIR}/bootscript.${WANTED_ROOT_DEV} && DEFAULT_BOOTSCRIPT="bootscript.${WANTED_ROOT_DEV}"
    sed -i -e "s/@UBOOT_LOADADDRESS[@]/${UBOOT_LOADADDRESS}/g" -e "s/@UBOOT_FDTADDRESS[@]/${UBOOT_FDTADDRESS}/g" \
           -e "s/@UBOOT_MMC_DEV[@]/${UBOOT_MMC_DEV}/g" \
           -e "s/@ROOT_DEV_NAME[@]/${ROOT_DEV_NAME}/g" -e "s/@ROOT_DEV_SEP[@]/${ROOT_DEV_SEP}/g" \
           -e "s/@KERNEL_IMAGETYPE[@]/${KERNEL_IMAGETYPE}/g" -e "s/@KERNEL_DEVICETREE[@]/${KERNEL_DEVICETREE}/g" \
	   -e "s/@MACHINE[@]/${MACHINE}/g" -e "s/@BRANCH[@]/${METADATA_BRANCH}/g" \
	 ${WORKDIR}/${DEFAULT_BOOTSCRIPT}
    install -d ${D}/boot
    uboot-mkimage -T script -C none -n 'Curie Script' -d ${WORKDIR}/${DEFAULT_BOOTSCRIPT} ${D}/boot/bootscript.${WANTED_ROOT_DEV}
}

FILES_${PN} += "/boot/bootscript*"
