DESCRIPTION = "sysdetails should help getting details of last minutes when \
a system dies"

SECTION = "libs"
LICENSE = "Artistic-1.0 | GPL-2.0"
PR = "r0"

MAINTAINER=     "HP2 Dev Team <verteiler.hp2dev.team@rademacher.com>"
HOMEPAGE=       "http://www.i-scream.org/libstatgrab/"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Artistic-1.0;md5=cda03bbdc3c1951996392b872397b798 \
file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "file://sysdetails \
           file://sysdetails.run \
           file://sysdetails.json \
"

RDEPENDS_${PN} += "config-any-perl"
RDEPENDS_${PN} += "daemontools"
RDEPENDS_${PN} += "perl-modules"
RDEPENDS_${PN} += "unix-statgrab-perl"

SERVICE_ROOT = "${sysconfdir}/daemontools/service"
SYSUPDT_SERVICE_DIR = "${SERVICE_ROOT}/sysdetails"

do_install() {
    install -d -m 755 ${D}${bindir}
    install -m 0755 ${WORKDIR}/sysdetails ${D}${bindir}

    install -d -m 755 ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/sysdetails.json ${D}${sysconfdir}

    install -d ${D}${SYSUPDT_SERVICE_DIR}
    install -m 0755 ${WORKDIR}/sysdetails.run ${D}${SYSUPDT_SERVICE_DIR}/run

	# disable start
    touch ${D}${SYSUPDT_SERVICE_DIR}/down
}

FILES_${PN} += "${sysconfdir} ${bindir}"
