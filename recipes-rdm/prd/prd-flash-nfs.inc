RDEPENDS_${PN} = "\
    parted \
    openssl \
"

SRC_URI_append = "\
    file://algorithms \
"

do_install_append () {
    install -m 0644 ${WORKDIR}/algorithms ${D}${libexecdir}/${MACHINE}
}
