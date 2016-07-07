# Copyright (C) 2015 Jens Rehsack

SUMMARY = "Linux Kernel for Bohr Board"
DESCRIPTION = "Linux Kernel for Bohr Board"
LICENSE = "GPL-2.0"

LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

# Pick up shared functions
inherit kernel
require recipes-kernel/linux/linux-dtb.inc

DEPENDS += "lzop-native bc-native bootscript-${MACHINE}-${WANTED_ROOT_DEV}"
RDEPENDS_kernel-image += "bootscript-${MACHINE}-${WANTED_ROOT_DEV}"

REV="83fdace666f72dbfc4a7681a04e3689b61dae3b9"
SRCREPO="rdm-dev"
SRCBRANCH = "bohr_4.1.18"

SRC_URI = "git://github.com/${SRCREPO}/linux-curie.git;branch=${SRCBRANCH};rev=${REV} \
	   file://defconfig \
	   file://omit-to-optimize-some-printf.patch \
	   file://net-mv643xx-disable-tso-by-default.patch \
          "

# patches for hp
COMPATIBLE_MACHINE = "(bohr)"

# Taken from meta-fsl-arm
LOCALVERSION = "+bohr"
SCMVERSION ??= "y"

S = "${WORKDIR}/git"

# We need to pass it as param since kernel might support more then one
# machine, with different entry points
KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

kernel_conf_variable() {
	CONF_SED_SCRIPT="$CONF_SED_SCRIPT /CONFIG_$1[ =]/d;"
	if test "$2" = "n"
	then
		echo "# CONFIG_$1 is not set" >> ${B}/.config
	else
		echo "CONFIG_$1=$2" >> ${B}/.config
	fi
}

do_configure_prepend() {
	echo "" > ${B}/.config
	CONF_SED_SCRIPT=""

	kernel_conf_variable LOCALVERSION "\"${LOCALVERSION}\""
	kernel_conf_variable LOCALVERSION_AUTO y

	sed -e "${CONF_SED_SCRIPT}" < '${WORKDIR}/defconfig' >> '${B}/.config'

	if [ "${SCMVERSION}" = "y" ]; then
		# Add GIT revision to the local version
		head=`git --git-dir=${S}/.git rev-parse --verify --short HEAD 2> /dev/null`
		printf "%s%s" +g $head > ${S}/.scmversion
	fi
}

do_install_append() {
    echo "blacklist btmrvl" > ${WORKDIR}/blacklist-btmrvl.conf
    echo "blacklist btmrvl_sdio" > ${WORKDIR}/blacklist-btmrvl-sdio.conf
    install -m 644 ${WORKDIR}/blacklist-btmrvl.conf ${D}${sysconfdir}/modprobe.d/
    install -m 644 ${WORKDIR}/blacklist-btmrvl-sdio.conf ${D}${sysconfdir}/modprobe.d/
}

FILES_kernel-module-btmrvl += "${sysconfdir}/modprobe.d/blacklist-btmrvl.conf"
FILES_kernel-module-btmrvl-sdio += "${sysconfdir}/modprobe.d/blacklist-btmrvl-sdio.conf"
