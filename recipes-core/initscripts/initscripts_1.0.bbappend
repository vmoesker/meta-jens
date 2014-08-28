FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://mountunion.sh \
	file://umountunion.sh \
"

do_install_append () {
    install -d ${D}/data

    install -m 0755 ${WORKDIR}/mountunion.sh ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/umountunion.sh ${D}${sysconfdir}/init.d

    update-rc.d -r ${D} umountunion.sh start 30 0 1 6 .
    update-rc.d -r ${D} mountunion.sh start 16 2 3 4 5 S .
}

FILES_${PN} += "/data"
