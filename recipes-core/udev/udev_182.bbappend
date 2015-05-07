FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://restart-udev.sh \
"

do_install_append () {
    install -m 0755 ${WORKDIR}/restart-udev.sh ${D}${sysconfdir}/init.d/restart-udev.sh
    update-rc.d -r ${D} restart-udev.sh start 03 3 5 .
}
