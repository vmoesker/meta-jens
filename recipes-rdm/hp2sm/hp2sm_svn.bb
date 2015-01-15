DESCRIPTION = "This software will proide the HomePilot2 Service Monitor e.g :81 Interface"
MAINTAINER= "HP2 Dev Team <verteiler.hp2dev.team@rademacher.com>"
HOMEPAGE = "http://www.rademacher.de"

LICENSE = "commercial"
LIC_FILES_CHKSUM = "file://${THISDIR}/files/license.txt;md5=3ebe3464e841ddbf115af1f7019017c5"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

RDEPENDS_${PN} += "dancer2-perl yaml-libyaml-perl file-slurp-tiny-perl"
RDEPENDS_${PN} += "unix-statgrab-perl"
RDEPENDS_${PN} += "scalar-list-utils-perl"
RDEPENDS_${PN} += "perl-modules"
RDEPENDS_${PN} += "daemontools"

RDEPENDS_${PN} += "encode-perl"
RDEPENDS_${PN} += "devel-cycle-perl devel-leak-object-perl devel-stacktrace-perl"
RDEPENDS_${PN} += "test-memory-cycle-perl test-leaktrace-perl"

PV = "0.1"

SRC_URI = "svn://192.168.1.186/svn/EW_Prj/001/HP_ServiceMonitor/trunk;protocol=http;module=hp2sm;rev=4079"
SRC_URI += "file://hp2sm.run"
S = "${WORKDIR}/hp2sm/src"

SERVICE_ROOT = "${sysconfdir}/daemontools/service"
HP2SM_SERVICE_DIR = "${SERVICE_ROOT}/hp2sm"

do_install() {
	#create init.d directory
	install -d ${D}${HP2SM_SERVICE_DIR}
	
	#install svc run script and make it executable
	install -m 0755 ${WORKDIR}/hp2sm.run ${D}${HP2SM_SERVICE_DIR}/run
	
	#create directory for source
	install -d ${D}/opt/rdm/hp2sm
	#copy all
	(cd ${S} && tar cf - .) | (cd ${D}/opt/rdm/hp2sm && tar xf -)
}

FILES_${PN} += "/opt/rdm/hp2sm \
                ${SERVICE_ROOT} \
"
