FILESEXTRAPATHS_prepend := "${THISDIR}/samba:"
SRC_URI += "file://smb.conf"
SRC_URI += "file://samba-logrotate.conf"
SRC_URI += "file://volatiles.03_samba"

EXTRA_OECONF += "\
    --with-nmbdsocketdir=/run/nmbd \
    "

do_install_append() {
    rmdir "${D}/run/nmbd" "${D}/run"

    install -m 755 -d ${D}${sysconfdir}/logrotate.d
    install -m 644 ${WORKDIR}/samba-logrotate.conf ${D}${sysconfdir}/logrotate.d/samba
}
