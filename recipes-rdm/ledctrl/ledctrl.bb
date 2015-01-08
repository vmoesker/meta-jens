FILESEXTRAPATHS_prepend := "${THISDIR}/ledplay:"

LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "file://ledplay_s.sh \
	file://ledplay_m.sh \
	file://ledreadyonce.sh \
	file://ledbootup.sh \
	file://ledgodown.sh \
	file://ledbootdown.sh \
"

RDEPENDS_${PN} += "daemontools"

SERVICE_ROOT = "${sysconfdir}/daemontools/service"
LEDREADY_SERVICE_DIR = "${SERVICE_ROOT}/ledready"

do_install () {
	install -d ${D}${sysconfdir}/init.d

	sed -i -e "s,@LEDREADY_SERVICE_DIR@,${LEDREADY_SERVICE_DIR},g" ${WORKDIR}/ledreadyonce.sh

	install -m 0755 ${WORKDIR}/ledplay_s.sh ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/ledplay_m.sh ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/ledreadyonce.sh ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/ledgodown.sh ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/ledbootdown.sh ${D}${sysconfdir}/init.d

	update-rc.d -r ${D} ledplay_s.sh start 03 S .
	update-rc.d -r ${D} ledplay_m.sh start 06 2 3 4 5 .
	update-rc.d -r ${D} ledreadyonce.sh start 95 5 .
	update-rc.d -r ${D} ledgodown.sh stop 99 6 .
	update-rc.d -r ${D} ledbootdown.sh stop 15 0 .

	#create daemontools service directory
	install -d ${D}${LEDREADY_SERVICE_DIR}
	
	#install svc run script and make it executable
	install -m 0755 ${WORKDIR}/ledbootup.sh ${D}${LEDREADY_SERVICE_DIR}/run
	touch ${D}${LEDREADY_SERVICE_DIR}/down
}

FILES_${PN} += "${SERVICE_ROOT} \
"
