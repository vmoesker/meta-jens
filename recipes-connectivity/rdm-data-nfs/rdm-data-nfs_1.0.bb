# Copyright (C) 2015 Jens Rehsack <sno@netbsd.org>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Initscripts for Development Data"
HOMEPAGE = "http://www.rademacher.de"

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

DEPENDS = "nfs-utils"
PR = "r0"

SRC_URI = "file://mount-rdm-data.sh"

do_install () {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/mount-rdm-data.sh ${D}${sysconfdir}/init.d

    update-rc.d -r ${D} mount-rdm-data.sh start 13 3 5 .
}
