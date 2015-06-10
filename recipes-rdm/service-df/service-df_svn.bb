DESCRIPTION = "RDM DF Service"
HOMEPAGE = "http://www.rademacher.de"

LICENSE = "commercial"
LIC_FILES_CHKSUM = "file://${THISDIR}/files/license.txt;md5=3ebe3464e841ddbf115af1f7019017c5"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

DEPENDS = "libxml2 libftdi"

PV = "1.2.0"

SRC_URI = "svn://192.168.1.186/svn/EW_Prj/001/HP_DuoFern_Service/trunk/;protocol=http;module=DuoFern_Service;rev=4648"

S = "${WORKDIR}/DuoFern_Service"

inherit autotools pkgconfig

EXTRA_OECONF="--prefix=/opt/homepilot"

do_configure() {
    autoreconf -i
    ${S}/configure	--build=${BUILD_SYS} \
			--host=${HOST_SYS} \
			--target=${TARGET_SYS} \
			${EXTRA_OECONF}

}

FILES_${PN} += "/opt/homepilot"
FILES_${PN}-dbg += "/opt/homepilot/bin/.debug"
