# Copyright (C) 2014 Shanghai Zhixing Information Technology Co.Ltd

DESCRIPTION = "Bootloader for Curie Board"
require recipes-bsp/u-boot/u-boot.inc

PROVIDES += "u-boot"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=1707d6db1d42237583f50183a5651ecb"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRCBRANCH = "imx_v2013.04_3.10.17_1.0.0_ga"
SRC_URI = "git://git.freescale.com/imx/uboot-imx.git;protocol=git;branch=${SRCBRANCH} \
	   file://0001-add-new-board-for-mx6qcurie-The-board-is-copied-from.patch \
	   file://0002-Porting-BSP-to-curie-board.patch \
	   file://0003-add-imx6-duallite-curie-board.patch \
	  "
SRCREV = "ce0ea2507c492d43bbf88f8609482a171b2d2003"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(curie)"

