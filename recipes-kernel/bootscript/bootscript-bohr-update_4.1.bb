# Copyright (C) 2015 Jens Rehsack <sno@netbsd.org>
# Released under the GPL-2.0 license (see COPYING.MIT for the terms)

DESCRIPTION = "U-Boot BootScripts for linux-bohr-4.1"
PN = "bootscript-bohr-update-${WANTED_ROOT_DEV}"

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

PACKAGE_ARCH = "${MACHINE_ARCH}-${WANTED_ROOT_DEV}"

DEPENDS = "u-boot-mkimage-native"
RDEPENDS_${PN} = "u-boot"

FILESEXTRAPATHS_prepend := "${THISDIR}/bootscript-${MACHINE}-${PV}:"

SRC_URI = "file://bootscript.usb \
"

COMPATIBLE_MACHINE = "(bohr-update)"

do_install () {
    set -x
    . ${DEPLOY_DIR}/images/bohr/.rdm-hp2-image-nand-${DISTRO_VERSION}-settings
    BOHR_DEVICE_TREE=`echo ${KERNEL_PREPARE} | awk -F '&&' '{print $3}' | awk '{print $3}'`
    BOHR_KERNEL_IMAGETYPE=`echo ${KERNEL_SANITIZE} | awk '{print $3}'`
    sed -i -e "s/@UBOOT_LOADADDRESS[@]/${UBOOT_LOADADDRESS}/g" -e "s/@UBOOT_FDTADDRESS[@]/${UBOOT_FDTADDRESS}/g" \
           -e "s/@KERNEL_IMAGETYPE[@]/${BOHR_KERNEL_IMAGETYPE}/g" -e "s/@KERNEL_DEVICETREE[@]/${BOHR_DEVICE_TREE}/g" \
	 ${WORKDIR}/bootscript.${WANTED_ROOT_DEV}
    install -d ${D}
    uboot-mkimage -T script -C none -n 'Bohr Script' -d ${WORKDIR}/bootscript.${WANTED_ROOT_DEV} ${D}/bootscript
}

FILES_${PN} += "/bootscript"
