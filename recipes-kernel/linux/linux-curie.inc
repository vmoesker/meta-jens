DESCRIPTION = "Linux kernel for Curie"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
DEPENDS += "lzop-native"
require recipes-kernel/linux/linux-imx.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:${THISDIR}/linux-imx-3.0.35:"

SRC_URI = "git://github.com/rdm-dev/linux-curie;branch=curie-3.0.101 \
           file://defconfig \
"

SRC_URI += "file://defconfig"

# patch for the hardware of "Marie Curie" board
SRC_URI += "file://physeries.scc \
            file://physeries.cfg \
            file://physeries-user-config.cfg \
            file://physeries-user-patches.scc \
            file://Merge-patches-for-CEC-issues-from-wolfgar.patch \
            file://use-dma-pool.patch \
           "

do_install_append () {
    rm -f ${D}/boot/uImage
    cp ${D}/boot/uImage-*  ${D}/boot/uImage

    echo "options 8189es rtw_power_mgnt=0" >${WORKDIR}/8189es.conf
    install -d ${D}${sysconfdir}/modprobe.d/
    install -m 644 ${WORKDIR}/8189es.conf ${D}${sysconfdir}/modprobe.d/
}
