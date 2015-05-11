# Copyright (C) 2014 Shanghai Zhixing Information Technology Co.Ltd

SUMMARY = "Linux Kernel for Curie Board"
DESCRIPTION = "Linux Kernel for Curie Board"

require recipes-kernel/linux/linux-imx.inc
require recipes-kernel/linux/linux-dtb.inc

DEPENDS += "lzop-native bc-native u-boot-curie"

SRCBRANCH = "curie_3.10.17_1.0.0_ga"
LOCALVERSION = "+curie"

SRC_URI = "git://github.com/rdm-dev/linux-curie.git;branch=${SRCBRANCH};rev=b2807877623804f5620db5e59371ce92ca9c443a \
           file://0001-Importing-rtl8189es_4.3.0-driver.patch \
           file://0002-include-rtl8189es-driver-in-kernel-build.patch \
           file://0003-Add-platform-specific-modifications-for-Curie.patch \
           file://0004-don-t-printout-debug-message-when-DBG-is-off.patch \
	   file://unionfs-2.6_for_3.10.53.patch \
	   file://overlayfs-v18.patch \
	   file://defconfig \
	   file://bootscript \
	   file://bootscript.nfs \
          "

# patches for curie
COMPATIBLE_MACHINE = "(curie)"

do_install_append () {
    sed -i -e "s/@UBOOT_LOADADDRESS[@]/${UBOOT_LOADADDRESS}/g" -e "s/@UBOOT_FDTADDRESS[@]/${UBOOT_FDTADDRESS}/g" \
         -e "s/@UBOOT_MMC_DEV[@]/${UBOOT_MMC_DEV}/g" -e "s/@SDCARD_IMAGE[@]/${SDCARD_IMAGE}/g" \
         -e "s/@KERNEL_MMC_DEV[@]/${KERNEL_MMC_DEV}/g" \
	 ${WORKDIR}/bootscript ${WORKDIR}/bootscript.nfs
    uboot-mkimage -T script -C none -n 'Curie Script' -d ${WORKDIR}/bootscript ${D}/boot/bootscript
    uboot-mkimage -T script -C none -n 'Curie Script' -d ${WORKDIR}/bootscript.nfs ${D}/boot/bootscript.nfs

    echo "options 8189es rtw_power_mgnt=0" >${WORKDIR}/8189es.conf
    install -d ${D}${sysconfdir}/modprobe.d/
    install -m 644 ${WORKDIR}/8189es.conf ${D}${sysconfdir}/modprobe.d/
}

do_deploy_append () {
    cp ${D}/boot/bootscript ${DEPLOYDIR}/bootscript-${DATETIME}
    cp ${D}/boot/bootscript.nfs ${DEPLOYDIR}/bootscript.nfs-${DATETIME}
    ln -sf bootscript-${DATETIME} ${DEPLOYDIR}/bootscript
    ln -sf bootscript.nfs-${DATETIME} ${DEPLOYDIR}/bootscript.nfs
}

FILES_kernel-image += "/boot/bootscript /boot/bootscript.nfs"
