DESCRIPTION = "XBMC customization"
 
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS += "xbmc-startup"
RDEPENDS_${PN} = "xbmc-startup"

PR = "r0"

SRC_URI = "file://advancedsettings.xml \
           file://guisettings.xml \
"

XBMC_USER = "xbmc"
XBMC_HOME = "/home/xbmc/.xbmc"
XBMC_USERDATA = "${XBMC_HOME}/userdata"

do_install () {
        install -o xbmc -g users -d ${D}${XBMC_HOME}
        install -o xbmc -g users -d ${D}${XBMC_USERDATA}
        install -o xbmc -g users -m 0644 ${WORKDIR}/advancedsettings.xml ${D}${XBMC_USERDATA}
        install -o xbmc -g users -m 0644 ${WORKDIR}/guisettings.xml ${D}${XBMC_USERDATA}
}

FILES_${PN} += "${XBMC_HOME}"
