FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

do_install_append () {
	mv ${D}${sysconfdir}/rcS.d/S02sysfs.sh ${D}${sysconfdir}/rcS.d/S01sysfs.sh
}
