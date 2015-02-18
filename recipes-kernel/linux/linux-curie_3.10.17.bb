# Copyright (C) 2014 Shanghai Zhixing Information Technology Co.Ltd

SUMMARY = "Linux Kernel for Curie Board"
DESCRIPTION = "Linux Kernel for Curie Board"

require recipes-kernel/linux/linux-imx.inc
require recipes-kernel/linux/linux-dtb.inc

DEPENDS += "lzop-native bc-native"

SRCBRANCH = "curie_3.10.17_1.0.1_ga"
LOCALVERSION = "+curie"

SRC_URI = "git://github.com/rdm-dev/linux-curie.git;branch=${SRCBRANCH};rev=ca3c85c1e290dbc66c95bc1eea2fbc83293bc94a \
           file://0001-Importing-rtl8189es_4.3.0-driver.patch \
           file://0002-include-rtl8189es-driver-in-kernel-build.patch \
           file://0003-Add-platform-specific-modifications-for-Curie.patch \
           file://0004-don-t-printout-debug-message-when-DBG-is-off.patch \
	   file://0099-respect_config_pm_mxs.patch \
	   file://unionfs-2.6_for_3.10.53.patch \
	   file://overlayfs-v18.patch \
	   file://defconfig \
	   file://bootscript \
          "

# patches for curie
COMPATIBLE_MACHINE = "(curie)"

SDCARD_IMAGE ?= "0"
UBOOT_MMC_DEV = "${@${UBOOT_MMC_BASE_DEV}-${SDCARD_IMAGE}}"

do_install_append () {
    sed -i -e "s/@UBOOT_LOADADDRESS[@]/${UBOOT_LOADADDRESS}/g" -e "s/@UBOOT_FDTADDRESS[@]/${UBOOT_FDTADDRESS}/g" \
         -e "s/@UBOOT_MMC_DEV[@]/${UBOOT_MMC_DEV}/g" -e "s/@SDCARD_IMAGE[@]/${SDCARD_IMAGE}/g" \
	 ${WORKDIR}/bootscript
    uboot-mkimage -T script -C none -n 'Curie Script' -d ${WORKDIR}/bootscript ${D}/boot/bootscript

    echo "options 8189es rtw_power_mgnt=0" >${WORKDIR}/8189es.conf
    install -d ${D}${sysconfdir}/modprobe.d/
    install -m 644 ${WORKDIR}/8189es.conf ${D}${sysconfdir}/modprobe.d/
}

do_deploy_append () {
    cp ${D}/boot/bootscript ${DEPLOYDIR}/bootscript-${DATETIME}
    ln -sf bootscript-${DATETIME} ${DEPLOYDIR}/bootscript
}

FILES_kernel-image += "/boot/bootscript"
