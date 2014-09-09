DESCRIPTION = "XBMC customization"
 
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

RDEPENDS_${PN} = "xbmc"

PR = "r0"

SRC_URI = "file://advancedsettings.xml \
           file://guisettings.xml \
"

XBMC_USER = "root"
XBMC_HOME = "/home/root/.xbmc"
XBMC_USERDATA = "${XBMC_HOME}/userdata"

do_install () {
        install -d ${D}${XBMC_USERDATA}
        install -m 0644 ${WORKDIR}/advancedsettings.xml ${D}/home/root/.xbmc/userdata/
        install -m 0644 ${WORKDIR}/guisettings.xml ${D}/home/root/.xbmc/userdata/
}

FILES_${PN} += "${XBMC_USERDATA}"
