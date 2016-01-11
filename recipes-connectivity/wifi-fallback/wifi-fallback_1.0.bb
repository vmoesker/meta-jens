DESCRIPTION = "Initscripts for enabling WiFi at boot when no carrier is detected for wired phy."

LICENSE = "GPL-2.0"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "file://wifi-fallback.sh \
"

do_install () {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/wifi-fallback.sh ${D}${sysconfdir}/init.d

	update-rc.d -r ${D} wifi-fallback.sh start 22 2 3 4 5 .
}
