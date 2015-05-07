LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

MAINTAINER = "HP2 Dev Team <verteiler.hp2dev.team@rademacher.de>"

SRC_URI = "file://thermaldetails \
           file://thermaldetails.run \
           file://thermaldetails.json \
"

RDEPENDS_${PN} += "config-any-perl"
RDEPENDS_${PN} += "file-slurp-tiny-perl"
RDEPENDS_${PN} += "daemontools"
RDEPENDS_${PN} += "perl-modules"
RDEPENDS_${PN} += "unix-statgrab-perl"
RDEPENDS_${PN} += "text-csv-xs-perl"

SERVICE_ROOT = "${sysconfdir}/daemontools/service"
SYSUPDT_SERVICE_DIR = "${SERVICE_ROOT}/thermaldetails"

do_install() {
    install -d -m 755 ${D}${bindir}
    install -m 0755 ${WORKDIR}/thermaldetails ${D}${bindir}

    install -d -m 755 ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/thermaldetails.json ${D}${sysconfdir}

    install -d ${D}${SYSUPDT_SERVICE_DIR}
    install -m 0755 ${WORKDIR}/thermaldetails.run ${D}${SYSUPDT_SERVICE_DIR}/run
}

FILES_${PN} += "${sysconfdir} ${bindir}"
