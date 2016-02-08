# Copyright (C) 2016 Jens Rehsack <sno@netbsd.org>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Init Update Stick"
HOMEPAGE = "http://www.homepilot.de/"

COMPATIBLE_MACHINE = "(bohr-update)"

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

RDEPENDS_${PN} = "\
    bohr-update-image \
    parted \
"

FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"
SRC_URI = "\
    file://init-update-stick.sh \
    file://init-update-stick-volatiles.conf \
"

do_compile () {
    cp ${WORKDIR}/init-update-stick-volatiles.conf ${B}
    sed -i -e "s,@DATADIR[@],${datadir},g" ${B}/init-update-stick-volatiles.conf
}

do_install () {
    install -d ${D}${sysconfdir}/init.d
    install -d ${D}${sysconfdir}/default/volatiles

    install -m 0644 ${B}/init-update-stick-volatiles.conf ${D}${sysconfdir}/default/volatiles/05_init-update-stick

    install -m 0755 ${WORKDIR}/init-update-stick.sh ${D}${sysconfdir}/init.d
    update-rc.d -r ${D} init-update-stick.sh start 30 S .
}
