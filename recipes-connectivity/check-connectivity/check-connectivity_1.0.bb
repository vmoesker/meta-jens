# Copyright (C) 2016 Jens Rehsack <sno@netbsd.org>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "HomePilot Connectivity Check"
HOMEPAGE = "https://www.homepilot.de/"
LICENSE = "GPL-2.0"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

DEPENDS = "errhlp"
RDEPENDS_${PN} = "errhlp"

SRC_URI = "\
    file://check-connectivity.init \
"

do_compile () {
    cp ${WORKDIR}/check-connectivity.init ${B}/
    sed -i -e "s,@BINDIR[@],${bindir},g" ${B}/*.init
}

do_install() {
    # install and setup SysV init scripts
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${B}/check-connectivity.init ${D}${sysconfdir}/init.d/check-connectivity.sh

    update-rc.d -r ${D} check-connectivity.sh start 75 2 3 4 5 .
}
