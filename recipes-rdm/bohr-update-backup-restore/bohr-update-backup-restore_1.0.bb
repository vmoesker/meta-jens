# Copyright (C) 2016 Jens Rehsack <sno@netbsd.org>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Init Update Stick"
HOMEPAGE = "http://www.homepilot.de/"

COMPATIBLE_MACHINE = "(bohr-update)"

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI = "\
    file://backup-nand.sh \
    file://restore-nand.sh \
"

do_install () {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/backup-nand.sh ${D}${bindir}/backup-nand
    install -m 0755 ${WORKDIR}/restore-nand.sh ${D}${bindir}/restore-nand
}
