DESCRIPTION = "Initscipts for overlay filesystems"

LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "file://mountoverlay.sh \
	file://cleanoverlay.sh \
	file://migrate2overlay.sh \
	file://default-cleanoverlay.conf \
	file://umountoverlay.sh \
"

do_install () {
	install -d ${D}${sysconfdir}/default
	install -m 0644 ${WORKDIR}/default-cleanoverlay.conf ${D}${sysconfdir}/default/cleanoverlay.conf

	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/mountoverlay.sh ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/umountoverlay.sh ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/cleanoverlay.sh ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/migrate2overlay.sh ${D}${sysconfdir}/init.d

	update-rc.d -r ${D} umountoverlay.sh start 30 0 1 6 .
	# cleanoverlay is called at beginnof mountoverlay
	update-rc.d -r ${D} mountoverlay.sh start 16 2 3 4 5 S .
}
