# Copyright (C) 2015 Jens Rehsack <sno@netbsd.org>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Recipe to place the image to install from update USB stick in file system updater"
HOMEPAGE = "http://www.homepilot.de/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
PR = "r0"

FILESEXTRAPATHS_prepend := "${DEPLOY_DIR}/images/bohr:"

SRC_URI = "\
    file://rdm-hp2-image-nand-${DISTRO_VERSION}-complete.cpi \
"

COMPATIBLE_MACHINE = "(bohr-update)"

do_install () {
    set -x
    # expect bohr/rdm-hp2-image-nand has been build already
    rm -rf ${D}/data/flashimg/rdm-hp2-nand-image-complete
    install -d ${D}/data/flashimg/rdm-hp2-nand-image-complete
    (cd ${D}/data/flashimg/rdm-hp2-nand-image-complete && tar xvjf ${WORKDIR}/rdm-hp2-image-nand-${DISTRO_VERSION}-complete.cpi)
    rm -rf ${D}/boot
    install -d ${D}/boot
    . ${D}/data/flashimg/rdm-hp2-nand-image-complete/.settings
    (cd ${D}/data/flashimg/rdm-hp2-nand-image-complete && tar cf - ${KERNEL}) | (cd ${D}/boot && tar xf - && chown -R root:root . && eval ${KERNEL_PREPARE} && eval ${KERNEL_SANITIZE})
    chown -R root:root ${D}/data/flashimg/rdm-hp2-nand-image-complete
}

FILES_${PN} = "\
    /data/flashimg/rdm-hp2-nand-image-complete \
    /boot \
"
