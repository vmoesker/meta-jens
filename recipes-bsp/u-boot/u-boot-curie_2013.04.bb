# Copyright (C) 2014 Shanghai Zhixing Information Technology Co.Ltd

DESCRIPTION = "Bootloader for Curie Board"
require recipes-bsp/u-boot/u-boot.inc

PROVIDES += "u-boot"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=1707d6db1d42237583f50183a5651ecb"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRCBRANCH = "curie_v2013.04_3.10.17"
SRC_URI = "git://github.com/rdm-dev/uboot-curie.git;branch=${SRCBRANCH};rev=f3b5656dc2b31d3d2044f18495b34b9e521f86d9 \
           file://bootsettings.patch \
	   "

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(curie)"

