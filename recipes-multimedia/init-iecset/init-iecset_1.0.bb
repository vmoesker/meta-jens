FILESEXTRAPATHS_prepend := "${THISDIR}/init-iecset:"

LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

RDEPENDS_${PV} += "alsa-utils"

SRC_URI = "file://init-iecset.sh \
	file://iecset.defaults \
"

do_install () {
	install -d ${D}${sysconfdir}/init.d
	install -d ${D}${sysconfdir}/default

	install -m 0755 ${WORKDIR}/init-iecset.sh ${D}${sysconfdir}/init.d
	install -m 0644 ${WORKDIR}/iecset.defaults ${D}${sysconfdir}/default/iecset

	update-rc.d -r ${D} init-iecset.sh start 19 3 5 .
}
