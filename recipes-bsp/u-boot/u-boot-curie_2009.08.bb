# Copyright (C) 2011-2013 Freescale Semiconductor
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "bootloader for imx platforms"
require recipes-bsp/u-boot/u-boot.inc

PROVIDES += "u-boot"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=4c6cde5df68eff615d36789dc18edd3b"

PR = "r15"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

# Revision of imx_v2009.08_3.0.35_4.1.0
SRCREV = "aa2be38ed4a16a6c3c100ac2d0449d54360cb892"

SRCBRANCH = "imx_v2009.08_3.0.35_4.1.0"
SRC_URI = "git://git.freescale.com/imx/uboot-imx.git;branch=${SRCBRANCH}"
SRC_URI += "file://u-boot-curie.patch \
        file://0001-Back-port-the-DDR-calibration-parameters-for-iMX6Q-6.patch \
"

# UBOOT_MACHINE_imx53qsb = "mx53_loco_config"
# UBOOT_MACHINE_imx53ard = "mx53_ard_ddr3_config"
# UBOOT_MACHINE_imx51evk = "mx51_bbg_config"
# UBOOT_MACHINE_imx6qsabrelite = "mx6q_sabrelite_config"
# UBOOT_MACHINE_imx6qsabreauto = "mx6q_sabreauto_config"
# UBOOT_MACHINE_imx6qsabresd = "mx6q_sabresd_config"
# UBOOT_MACHINE_imx6dlsabresd = "mx6dl_sabresd_config"
# UBOOT_MACHINE_imx6slevk = "mx6sl_evk_config"
# UBOOT_MACHINE_imx28evk = "mx28_evk_config"

UBOOT_MAKE_TARGET = "u-boot.bin"

# Please, add the following variables to conf/local.conf
# in order to use this u-boot version
# UBOOT_SUFFIX = "bin"
# UBOOT_PADDING = "2"
# PREFERRED_PROVIDER_u-boot = "u-boot-imx"

S = "${WORKDIR}/git"
EXTRA_OEMAKE += 'HOSTSTRIP=true'

PACKAGE_ARCH = "${MACHINE_ARCH}"

do_compile_prepend() {
	if [ "${@base_contains('DISTRO_FEATURES', 'ld-is-gold', 'ld-is-gold', '', d)}" = "ld-is-gold" ] ; then
		sed -i 's/$(CROSS_COMPILE)ld/$(CROSS_COMPILE)ld.bfd/g' config.mk
	fi
}

COMPATIBLE_MACHINE = "(mx6curie)"
