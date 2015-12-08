# Copyright (C) 2015 Jens Rehsack <sno@netbsd.org>
# Released under the MIT license (see COPYING.MIT for the terms)

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot-curie-${PV}:"
require recipes-bsp/u-boot/u-boot-fw-utils-curie.inc

SRCBRANCH = "curie_v2015.04_3.14.38_6ul_ga"
REV = "f84ba75401dd4777a59a9eaef683b829fbb03e93"

SRCREPO = "rdm-dev"

SRC_URI += "\
    file://bootsettings.patch \
    file://fw_env.config.patch \
"
