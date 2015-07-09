# Copyright (C) 2014-2015 Shanghai Zhixing Information Technology Co.Ltd

DESCRIPTION = "Bootloader for Curie Board"
require recipes-bsp/u-boot/u-boot.inc

PROVIDES += "u-boot"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRCREPO ?= "rdm-dev"
SRC_URI = "git://github.com/${SRCREPO}/uboot-curie.git;branch=${SRCBRANCH};rev=${REV} \
	  "
S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(curie)"

BBCLASSEXTEND = "native nativesdk"
