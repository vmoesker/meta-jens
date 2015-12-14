DESCRIPTION = "RDM Zway Stick Updater"
HOMEPAGE = "http://www.rademacher.de"

LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://${THISDIR}/files/license.txt;md5=3ebe3464e841ddbf115af1f7019017c5"

FILESEXTRAPATHS_prepend := "${THISDIR}/z-way-stick-updater:"

PV = "0.4"

SRC_URI = "\
    git://git@bitbucket.org/rdm-dev/z-way-stick-updater.git;protocol=ssh;branch=import \
"
SRCREV = "031474999a20ee7b2199536700ef5eaf34afdbc6"

DEPENDS = "hp2-base"
RDEPENDS_${PN} += "hp2-base"
RDEPENDS_${PN} += "perl"
RDEPENDS_${PN} += "python-fcntl"
RDEPENDS_${PN} += "python-logging"
RDEPENDS_${PN} += "python-pyserial"

S = "${WORKDIR}/git"
INST_DEST_PREFIX="/opt/z-way-stick-updater"

do_install() {
    install -d ${D}${INST_DEST_PREFIX}
    install -d ${D}${INST_DEST_PREFIX}/ZStickUpdater
    install -m 755 zwave_update.pl ${D}${INST_DEST_PREFIX}
    install -m 755 ZStickUpdater/ZStickUpdater.py ${D}${INST_DEST_PREFIX}/ZStickUpdater
    install -m 644 ZStickUpdater/IntelHex.py ZStickUpdater/SerialAPI.py \
                   ZStickUpdater/ZLogging.py \
                   ZStickUpdater/Rademacher4.ehex ZStickUpdater/Rademacher5.ehex \
                   ${D}${INST_DEST_PREFIX}/ZStickUpdater
}

FILES_${PN} += "${INST_DEST_PREFIX}"
