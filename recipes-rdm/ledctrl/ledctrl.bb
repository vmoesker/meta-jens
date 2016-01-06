LICENSE = "GPL-2.0"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "file://ledplay_s.${MACHINE} \
	file://ledplay_m.${MACHINE} \
	file://ledbootup.${MACHINE} \
	file://ledgodown.${MACHINE} \
	file://ledbootdown.${MACHINE} \
	file://ledreadyonce.sh \
	file://ready-led-nonroot.sudoers \
"

RDEPENDS_${PN} += "daemontools"

inherit useradd

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM_${PN} = "-r ledctrl"

SERVICE_ROOT = "${sysconfdir}/daemontools/service"
LEDREADY_SERVICE_DIR = "${SERVICE_ROOT}/ledready"

PACKAGE_ARCH = "${MACHINE_ARCH}"

do_compile () {
	cp ${WORKDIR}/ledreadyonce.sh ${B}/ledreadyonce.sh
	sed -i -e "s,@LEDREADY_SERVICE_DIR@,${LEDREADY_SERVICE_DIR},g" ${B}/ledreadyonce.sh
}

do_install () {
	install -d ${D}${sysconfdir}/init.d

	install -m 0755 ${WORKDIR}/ledplay_s.${MACHINE} ${D}${sysconfdir}/init.d/ledplay_s.sh
	install -m 0755 ${WORKDIR}/ledplay_m.${MACHINE} ${D}${sysconfdir}/init.d/ledplay_m.sh
	install -m 0755 ${B}/ledreadyonce.sh ${D}${sysconfdir}/init.d/
	install -m 0755 ${WORKDIR}/ledgodown.${MACHINE} ${D}${sysconfdir}/init.d/ledgodown.sh
	install -m 0755 ${WORKDIR}/ledbootdown.${MACHINE} ${D}${sysconfdir}/init.d/ledbootdown.sh

	update-rc.d -r ${D} ledplay_s.sh start 03 S .
	update-rc.d -r ${D} ledplay_m.sh start 06 2 3 4 5 .
	update-rc.d -r ${D} ledreadyonce.sh start 95 5 .
	update-rc.d -r ${D} ledgodown.sh stop 99 6 .
	update-rc.d -r ${D} ledbootdown.sh stop 15 0 .

	#create daemontools service directory
	install -d ${D}${LEDREADY_SERVICE_DIR}
	
	#install svc run script and make it executable
	install -m 0755 ${WORKDIR}/ledbootup.${MACHINE} ${D}${LEDREADY_SERVICE_DIR}/run
	touch ${D}${LEDREADY_SERVICE_DIR}/down

	# allow %ledctrl to call ledgodown.sh and ledreadyonce.sh
	install -d ${D}${sysconfdir}/sudoers.d
	install -m 600 ${WORKDIR}/ready-led-nonroot.sudoers ${D}${sysconfdir}/sudoers.d/ready-led-nonroot
}

FILES_${PN} += "${SERVICE_ROOT} \
"
