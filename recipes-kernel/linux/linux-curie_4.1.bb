# Copyright (C) 2014 Shanghai Zhixing Information Technology Co.Ltd

SUMMARY = "Linux Kernel for Curie Board"
DESCRIPTION = "Linux Kernel for Curie Board"

require recipes-kernel/linux/linux-imx.inc
require recipes-kernel/linux/linux-dtb.inc

DEPENDS += "lzop-native bc-native bootscript-${MACHINE}-${WANTED_ROOT_DEV}"
RDEPENDS_kernel-image += "bootscript-${MACHINE}-${WANTED_ROOT_DEV}"

REV="252ec68fb7feea8afd00db17559069c57d70f7bb"
SRCREPO="rdm-dev"
SRCBRANCH = "curie_4.1"
LOCALVERSION = "+curie"

SRC_URI = "git://github.com/${SRCREPO}/linux-curie.git;branch=${SRCBRANCH};rev=${REV}"
SRC_URI_append = " \
   file://defconfig \
"

# patches for curie
COMPATIBLE_MACHINE = "(curie)"

do_install_append () {
    echo "blacklist cfg80211" >${WORKDIR}/blacklist-cfg80211.conf
    echo "blacklist ahci_imx" >${WORKDIR}/blacklist-ahci_imx.conf
    install -m 644 ${WORKDIR}/blacklist-cfg80211.conf ${D}${sysconfdir}/modprobe.d/
    install -m 644 ${WORKDIR}/blacklist-ahci_imx.conf ${D}${sysconfdir}/modprobe.d/
}

FILES_kernel-module-ahci-imx += "${sysconfdir}/modprobe.d/blacklist-ahci_imx.conf"
FILES_kernel-module-cfg80211 += "${sysconfdir}/modprobe.d/blacklist-cfg80211.conf"
