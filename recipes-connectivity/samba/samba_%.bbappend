FILESEXTRAPATHS_prepend := "${THISDIR}/samba:"
SRC_URI += "file://smb.conf"
SRC_URI += "file://samba-logrotate.conf"
SRC_URI += "file://volatiles.03_samba"

#EXTRA_OECONF += " --with-nmbdsocketdir=/run/nmbd "

SYSVINITTYPE := "lsb"

do_install_append() {
    install -m 755 -d ${D}${sysconfdir}/logrotate.d
    install -m 644 ${WORKDIR}/samba-logrotate.conf ${D}${sysconfdir}/logrotate.d/samba

    install -m 755 -d ${D}${sysconfdir}/samba
    install -m644 ${WORKDIR}/smb.conf ${D}${sysconfdir}/samba/smb.conf

    install -m 755 -d ${D}${sysconfdir}/default/volatiles
    install -m644 ${WORKDIR}/volatiles.03_samba ${D}${sysconfdir}/default/volatiles/03_samba
}
