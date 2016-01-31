RDEPENDS_${PN} = "\
    parted \
    openssl \
"

SRC_URI_append = "\
    file://algorithms \
"

SRC_URI_append_curie = "\
    file://post.curie \
"

SRC_URI_append_bohr = "\
    file://post.bohr \
"

do_install_append () {
    install -m 0644 ${WORKDIR}/algorithms ${D}${libexecdir}
    install -m 0644 ${B}/post.${MACHINE} ${D}${libexecdir}/post
}
