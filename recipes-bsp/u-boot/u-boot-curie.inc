# Copyright (C) 2014-2015 Shanghai Zhixing Information Technology Co.Ltd

DESCRIPTION = "Bootloader for Curie Board"
require recipes-bsp/u-boot/u-boot.inc

PROVIDES += "u-boot"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI = "git://50.116.15.14/mx6/uboot-curie.git;protocol=ssh;user=git;branch=${SRCBRANCH} \
		   "
S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(curie)"

