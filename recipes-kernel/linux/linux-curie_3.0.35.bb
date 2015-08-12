DESCRIPTION = "Linux kernel for Curie"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
DEPENDS += "lzop-native u-boot-curie"
require recipes-kernel/linux/linux-imx.inc

SRCREV = "681175b964d4f84755776260fa670f7aa20d13c7"
#SRCREV = "0c58d0f15879856dd750223abeeb0410a0891ca2"
LOCALVERSION = "-4.1.0+curie"

COMPATIBLE_MACHINE = "(curie)"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:${THISDIR}/linux-imx-3.0.35:"

SRC_URI = "git://github.com/rdm-dev/linux-curie;branch=curie-3.0.101 \
           file://defconfig \
"

# patch for the hardware of "Marie Curie" board
SRC_URI += "file://physeries.scc \
            file://physeries.cfg \
            file://physeries-user-config.cfg \
            file://physeries-user-patches.scc \
            file://Merge-patches-for-CEC-issues-from-wolfgar.patch \
            file://use-dma-pool.patch \
	    file://0001-Patch-to-tune-mx6-HDMI-parameters.patch \
            file://bootscript \
            file://bootscript.nfs \
           "

do_install_append () {
    rm -f ${D}/boot/uImage
    cp ${D}/boot/uImage-*  ${D}/boot/uImage

    sed -i -e "s/@UBOOT_LOADADDRESS[@]/${UBOOT_LOADADDRESS}/g" -e "s/@UBOOT_MMC_DEV[@]/${UBOOT_MMC_DEV}/g" \
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
