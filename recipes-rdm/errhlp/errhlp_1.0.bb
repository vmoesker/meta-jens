# Copyright (C) 2016 Jens Rehsack <sno@netbsd.org>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Error Management Helper"
HOMEPAGE = "https://www.homepilot.de/"
LICENSE = "GPL-2.0"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

DEPENDS = "ledctrl"
RDEPENDS_${PN} = "ledctrl"

inherit useradd

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM_${PN} = "-r ${PN}"

SRC_URI = "\
    file://errhlp.sh \
    file://errhlp.sudoers \
    file://errhlp.volatiles \
"

do_compile () {
    cp ${WORKDIR}/errhlp.sh ${WORKDIR}/errhlp.sudoers ${WORKDIR}/errhlp.volatiles ${B}/
    sed -i -e "s,@LIBEXEC[@],${libexecdir},g" -e "s,@LEDCTRL[@],${libdir}/ledctrl,g" \
        -e "s,@BINDIR[@],${bindir},g" ${B}/*.sh ${B}/*.sudoers
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/errhlp.sh ${D}${bindir}/errhlp
    for call_id in enable-error disable-error enable-counted-error enable-counted-error
    do
        (cd ${D}${bindir} && ln -sf errhlp $call_id)
    done

    # allow %ledctrl to call ledctrl-helper
    install -d ${D}${sysconfdir}/sudoers.d
    install -m 600 ${B}/errhlp.sudoers ${D}${sysconfdir}/sudoers.d/errhlp

    # create volatiles base dir
    install -d ${D}${sysconfdir}/default/volatiles
    install -m 644 ${WORKDIR}/errhlp.volatiles ${D}${sysconfdir}/default/volatiles/41_errhlp
}
