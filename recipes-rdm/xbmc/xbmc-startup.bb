DESCRIPTION = "XBMC customization"
 
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
DEPENDS = "xbmc"

PR = "r0"
PV = "0.1-${XBMC_APPLICATION_NAME}"

SRC_URI = "file://xbmc \
           file://xbmc-log \
           file://xbmc-volatile.conf \
           file://clear-page-cache \
           file://51-tty.rules \
"

inherit useradd

RDEPENDS_${PN} += "daemontools xbmc"

SERVICE_ROOT = "${sysconfdir}/daemontools/service"
XBMC_SERVICE_DIR = "${SERVICE_ROOT}/xbmc"
XBMC_LOG_DIR = "/var/log/daemontools/xbmc"
CLEAR_PAGE_CACHE_SERVICE_DIR = "${SERVICE_ROOT}/clear-page-cache"

XBMC_USER_HOME = "/home/xbmc"

USERADD_PACKAGES = "${PN}"

USERADD_PARAM_${PN} = "-u 888 -d ${XBMC_USER_HOME} -g users -G tty,audio,video,input,disk -r -m -s /bin/sh xbmc"

do_install () {
        install -o xbmc -g users -d ${D}${XBMC_USER_HOME}
	
	install -d ${D}${XBMC_SERVICE_DIR}
        install -d ${D}${CLEAR_PAGE_CACHE_SERVICE_DIR}

	install -m 0755 ${WORKDIR}/xbmc ${D}${XBMC_SERVICE_DIR}/run
	install -m 0755 ${WORKDIR}/clear-page-cache ${D}${CLEAR_PAGE_CACHE_SERVICE_DIR}/run

	install -d ${D}${sysconfdir}/udev/rules.d
	install -m 0644 ${WORKDIR}/51-tty.rules ${D}${sysconfdir}/udev/rules.d/

	install -d ${D}${XBMC_SERVICE_DIR}/log
	install -m 0755 ${WORKDIR}/xbmc-log ${D}${XBMC_SERVICE_DIR}/log/run

	install -d ${D}${sysconfdir}/default/volatiles
	install -m 0644 ${WORKDIR}/xbmc-volatile.conf ${D}${sysconfdir}/default/volatiles/99_xbmc

	sed -i -e "s,@APPLICATION_DIRECTORY@,${XBMC_APPLICATION_DIRECTORY_BASE},g" -e "s,@APPLICATION_BIN@,${XBMC_APPLICATION_BIN},g" ${D}${XBMC_SERVICE_DIR}/run
}

FILES_${PN} += "${SERVICE_ROOT} ${XBMC_USER_HOME}"
