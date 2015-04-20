DESCRIPTION = "XBMC HomePilot addon"

LICENSE = "GPLv2"
DEPENDS += "xbmc"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=85f28f254857486f13360c0e8b34f38b"

PR = "r0"
PV = "0.9.13.1"
SRC_URI = "git://github.com/rdm-dev/xbmc-rdm-hp-addon;rev=d216b776791cf88f4f6a8c6d136677388b1b07e1"

DEPENDS += "xbmc-startup"
RDEPENDS_${PN} += "xbmc-startup"

XBMC_USER_HOME = "/home/xbmc"
XBMC_APPDIR = "${XBMC_USER_HOME}/${XBMC_APPLICATION_DIRECTORY_BASE}"
XBMC_ADDONS = "${XBMC_APPDIR}/addons"

S = "${WORKDIR}/git/script.homepilot/"
do_install() {
        install -o xbmc -g users -d ${D}${XBMC_USER_HOME}
        install -o xbmc -g users -d ${D}${XBMC_APPDIR}
        install -o xbmc -g users -d ${D}${XBMC_ADDONS}
        cp -axr ${S} ${D}${XBMC_ADDONS}
	chown -R xbmc:users ${D}${XBMC_ADDONS}
}

FILES_${PN} += "${XBMC_USER_HOME}"
