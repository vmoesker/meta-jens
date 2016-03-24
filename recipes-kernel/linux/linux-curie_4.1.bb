# Copyright (C) 2014 Shanghai Zhixing Information Technology Co.Ltd

SUMMARY = "Linux Kernel for Curie Board"
DESCRIPTION = "Linux Kernel for Curie Board"

require recipes-kernel/linux/linux-imx.inc
require recipes-kernel/linux/linux-dtb.inc

DEPENDS += "lzop-native bc-native bootscript-${MACHINE}-${WANTED_ROOT_DEV}"
RDEPENDS_kernel-image += "bootscript-${MACHINE}-${WANTED_ROOT_DEV}"

REV="6614871bbb6cb2b866723dbd3798392095e17c84"
SRCREPO="rdm-dev"
SRCBRANCH = "curie_4.1.18"
LOCALVERSION = "+curie"

SRC_URI = "git://github.com/${SRCREPO}/linux-curie.git;branch=${SRCBRANCH};rev=${REV}"
SRC_URI_append = " \
   file://defconfig \
"

# patches for curie
COMPATIBLE_MACHINE = "(curie)"

do_install_append () {
    echo "blacklist ahci_imx" >${WORKDIR}/blacklist-ahci_imx.conf
    install -m 644 ${WORKDIR}/blacklist-ahci_imx.conf ${D}${sysconfdir}/modprobe.d/
}

FILES_kernel-module-ahci-imx += "${sysconfdir}/modprobe.d/blacklist-ahci_imx.conf"
