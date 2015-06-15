FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://alsa-utils.volatiles"

do_install_append () {
	install -d ${D}${sysconfdir}/default/volatiles
	install -m 644 ${WORKDIR}/alsa-utils.volatiles ${D}${sysconfdir}/default/volatiles/97_alsa-utils
}

FILES_alsa-utils-alsactl += "${sysconfdir}/default/volatiles"
