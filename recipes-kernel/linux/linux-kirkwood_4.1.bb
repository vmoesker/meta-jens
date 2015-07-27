# Copyright (C) 2015 Jens Rehsack

SUMMARY = "Linux Kernel for Bohr Board"
DESCRIPTION = "Linux Kernel for Bohr Board"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

# Pick up shared functions
inherit kernel
require recipes-kernel/linux/linux-dtb.inc

# u-boot-curie
DEPENDS += "lzop-native bc-native u-boot u-boot-mkimage-native"

REV="5cf9896dc5c72a6c68c36140568b755f697f7760"
SRCREPO="rehsack"
SRCBRANCH = "linux-4.1.y"

SRC_URI = "git://github.com/${SRCREPO}/linux-curie.git;branch=${SRCBRANCH};rev=${REV} \
	   file://defconfig \
	   file://bootscript.nand \
	   file://bootscript.nfs \
	   file://bootscript.usb \
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

do_install_append () {
    sed -i -e "s/@UBOOT_LOADADDRESS[@]/${UBOOT_LOADADDRESS}/g" -e "s/@UBOOT_FDTADDRESS[@]/${UBOOT_FDTADDRESS}/g" \
           -e "s/@KERNEL_IMAGETYPE[@]/${KERNEL_IMAGETYPE}/g" -e "s/@KERNEL_DEVICETREE[@]/${KERNEL_DEVICETREE}/g" \
	 ${WORKDIR}/bootscript.nand ${WORKDIR}/bootscript.nfs ${WORKDIR}/bootscript.usb
    uboot-mkimage -T script -C none -n 'Bohr Script' -d ${WORKDIR}/bootscript.nand ${D}/boot/bootscript.nand
    uboot-mkimage -T script -C none -n 'Bohr Script' -d ${WORKDIR}/bootscript.nfs ${D}/boot/bootscript.nfs
    uboot-mkimage -T script -C none -n 'Bohr Script' -d ${WORKDIR}/bootscript.usb ${D}/boot/bootscript.usb

    echo "blacklist cfg80211" >${WORKDIR}/blacklist-cfg80211.conf
    install -d ${D}${sysconfdir}/modprobe.d/
    install -m 644 ${WORKDIR}/blacklist-cfg80211.conf ${D}${sysconfdir}/modprobe.d/
}

do_deploy_append () {
    set -x
    cp ${D}/boot/bootscript.nand ${DEPLOYDIR}/bootscript.nand-${DATETIME}
    cp ${D}/boot/bootscript.nfs ${DEPLOYDIR}/bootscript.nfs-${DATETIME}
    cp ${D}/boot/bootscript.usb ${DEPLOYDIR}/bootscript.usb-${DATETIME}
    ln -sf bootscript.nand-${DATETIME} ${DEPLOYDIR}/bootscript.nand
    ln -sf bootscript.nfs-${DATETIME} ${DEPLOYDIR}/bootscript.nfs
    ln -sf bootscript.usb-${DATETIME} ${DEPLOYDIR}/bootscript.usb

    ln -sf bootscript.${WANTED_ROOT_DEV}-${DATETIME} ${DEPLOYDIR}/bootscript
    : # exit 0
}

FILES_kernel-module-cfg80211 += "${sysconfdir}/modprobe.d/blacklist-cfg80211.conf"
FILES_kernel-image += "/boot/bootscript.nand /boot/bootscript.nfs /boot/bootscript.usb"
