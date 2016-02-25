FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

RRECOMMENDS_${PN} += "ethtool"

PACKAGE_ARCH = "${MACHINE_ARCH}"

RDEPENDS_${PN}_append = "\
    file-slurp-tiny-perl \
    ledctrl \
    perl \
    netaddr-ip-perl \
"

SRC_URI += "\
    file://activate.pl \
    file://wifi/pre_up.sh \
    file://wifi/post_up.sh \
    file://wifi/pre_down.sh \
    file://wifi/post_down.sh \
"

do_compile_append () {
    sed -i -e "s,@LEDCTRL[@],${libdir}/ledctrl,g" ${WORKDIR}/wifi/*.sh
}

do_install_append () {
    install -d ${D}${sysconfdir}/network/wifi
    install -d ${D}${sysconfdir}/network/mapping

    install -m 0755 ${WORKDIR}/activate.pl ${D}${sysconfdir}/network/mapping/activate
    install -m 0755 ${WORKDIR}/wifi/pre_up.sh ${D}${sysconfdir}/network/wifi
    install -m 0755 ${WORKDIR}/wifi/pre_down.sh ${D}${sysconfdir}/network/wifi
    install -m 0755 ${WORKDIR}/wifi/post_up.sh ${D}${sysconfdir}/network/wifi
    install -m 0755 ${WORKDIR}/wifi/post_down.sh ${D}${sysconfdir}/network/wifi
}

FILES_${PN} += "/etc/network"
