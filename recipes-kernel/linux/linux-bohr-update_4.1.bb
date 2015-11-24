# Copyright (C) 2015 Jens Rehsack <sno@netbsd.org>
# Released under the MIT license (see COPYING.MIT for the terms)

# Copyright (C) 2015 Jens Rehsack

SUMMARY = "Linux Kernel for Bohr Board"
DESCRIPTION = "Linux Kernel for Bohr Board"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
FILESEXTRAPATHS_prepend := "${THISDIR}/linux-bohr-${PV}:"

KERNEL_CLASSES := " kernel-uimage-dtb "

# Pick up shared functions
inherit kernel
require recipes-kernel/linux/linux-dtb.inc

DEPENDS += "lzop-native bc-native bootscript-${MACHINE}-${WANTED_ROOT_DEV}"

REV="27f1b7fed9c305ef46f8708f1bdde9cdb5f166bd"
SRCREPO="rdm-dev"
SRCBRANCH = "linux-4.1.y"

SRC_URI = "git://github.com/${SRCREPO}/linux-curie.git;branch=${SRCBRANCH};rev=${REV} \
	   file://defconfig \
	   file://defconfig.update \
	   file://omit-to-optimize-some-printf.patch \
	   file://no-chosen-bootargs.patch \
          "

# patches for hp
COMPATIBLE_MACHINE = "(bohr-update)"

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

	egrep -v '(CONFIG_ARM_APPENDED_DTB|CONFIG_ARM_ATAG_DTB_COMPAT|CONFIG_ARM_ATAG_DTB_COMPAT_CMDLINE_FROM_BOOTLOADER|CONFIG_ARM_ATAG_DTB_COMPAT_CMDLINE_EXTEND|CONFIG_CMDLINE|CONFIG_CMDLINE_FROM_BOOTLOADER|CONFIG_CMDLINE_EXTEND|CONFIG_CMDLINE_FORCE)' < '${WORKDIR}/defconfig' | sed -e "${CONF_SED_SCRIPT}"  >> '${B}/.config'
	sed -e "${CONF_SED_SCRIPT}" < '${WORKDIR}/defconfig.update' >> '${B}/.config'

	if [ "${SCMVERSION}" = "y" ]; then
		# Add GIT revision to the local version
		head=`git --git-dir=${S}/.git rev-parse --verify --short HEAD 2> /dev/null`
		printf "%s%s" +g $head > ${S}/.scmversion
	fi
}

do_install_append () {
    echo "blacklist cfg80211" >${WORKDIR}/blacklist-cfg80211.conf
    install -d ${D}${sysconfdir}/modprobe.d/
    install -m 644 ${WORKDIR}/blacklist-cfg80211.conf ${D}${sysconfdir}/modprobe.d/
}

FILES_kernel-module-cfg80211 += "${sysconfdir}/modprobe.d/blacklist-cfg80211.conf"