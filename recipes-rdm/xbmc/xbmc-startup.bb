DESCRIPTION = "XBMC customization"
 
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
DEPENDS = "xbmc"

PR = "r0"

SRC_URI = "file://xbmc \
           file://clear-page-cache \
"

inherit useradd

RDEPENDS_${PN} += "daemontools xbmc"

SERVICE_ROOT = "${sysconfdir}/daemontools/service"
XBMC_SERVICE_DIR = "${SERVICE_ROOT}/xbmc"
CLEAR_PAGE_CACHE_SERVICE_DIR = "${SERVICE_ROOT}/clear-page-cache"

XBMC_USER_HOME = "/home/xbmc/"

USERADD_PACKAGES = "${PN}"

USERADD_PARAM_${PN} = "-u 888 -d ${XBMC_USER_HOME} -g users -G tty -r -m -s /bin/sh xbmc"

do_install () {
        install -d ${D}${XBMC_SERVICE_DIR}
        install -d ${D}${CLEAR_PAGE_CACHE_SERVICE_DIR}

	install -m 0755 ${WORKDIR}/xbmc ${D}${XBMC_SERVICE_DIR}/run
	install -m 0755 ${WORKDIR}/clear-page-cache ${D}${CLEAR_PAGE_CACHE_SERVICE_DIR}/run
}

FILES_${PN} += "${SERVICE_ROOT} ${XBMC_USER_HOME}"
