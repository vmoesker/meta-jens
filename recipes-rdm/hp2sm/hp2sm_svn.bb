DESCRIPTION = "This software will proide the HomePilot2 Service Monitor e.g :81 Interface"
HOMEPAGE = "http://www.rademacher.de"

LICENSE = "commercial"
LIC_FILES_CHKSUM = "file://${THISDIR}/files/license.txt;md5=3ebe3464e841ddbf115af1f7019017c5"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

RDEPENDS_${PN} += "dancer2-perl yaml-libyaml-perl"
RDEPENDS_${PN} += "perl-modules"

PV = "0.1"

SRC_URI = "svn://192.168.1.186/svn/EW_Prj/trunk/;protocol=http;module=hp2sm;rev=3551"
S = "${WORKDIR}/hp2sm/src"

inherit update-rc.d

ALTERNATIVE_PRIORITY = "100"
ALTERNATIVE_LINK_NAME[hp2sm] = "${D}${sysconfdir}/init.d/hp2sm"

do_install() {
	#create init.d directory
	install -d ${D}${sysconfdir}/init.d
	
	#install init.d script and make it executable
	install -m 0755 ${THISDIR}/files/hp2sm ${D}${sysconfdir}/init.d/
	
	#create directory for source
	install -d ${D}/opt/rdm/hp2sm
	#copy all
	(cd ${S} && tar cf - .) | (cd ${D}/opt/rdm/hp2sm && tar xf -)
}

FILES_${PN} += "/opt/rdm/hp2sm"

INITSCRIPT_NAME = "hp2sm"
INITSCRIPT_PARAMS = "start 99 S . stop 20 0 1 6 ."
