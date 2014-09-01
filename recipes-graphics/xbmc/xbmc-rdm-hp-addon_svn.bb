DESCRIPTION = "XBMC HomePilot addon"

LICENSE = "GPLv2"
DEPENDS += "xbmc"
LICENSE = "GPLv3"
#LIC_FILES_CHKSUM = "file://script.homepilot/LICENSE.txt;md5=7226e01ff2d738e2246d467214c409b9"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891"

PR = "r0"
PV = "0.9.9"
SRC_URI = "svn://192.168.1.186/svn/EW_Prj/trunk/;protocol=http;module=XBMC_WR_Plugin_Ctrl;rev=3582"

S = "${WORKDIR}/XBMC_WR_Plugin_Ctrl/src/script.homepilot/"
do_install() {
        install -d ${D}/home/root/.xbmc/addons/
        cp -axr ${S} ${D}/home/root/.xbmc/addons/
}

FILES_${PN} += "/home/root/.xbmc/addons/script.homepilot"


