DESCRIPTION = "RDM DF Service"
HOMEPAGE = "http://www.rademacher.de"

LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://${THISDIR}/files/license.txt;md5=3ebe3464e841ddbf115af1f7019017c5"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

DEPENDS = "libxml2 libftdi"

PV = "1.2.0"

SRC_URI = "svn://192.168.1.186/svn/EW_Prj/001/HP_DuoFern_Service/trunk/;protocol=http;module=DuoFern_Service;rev=4865 \
	  file://use-ftdi1-too.patch \
	  file://clean-up.patch \
	  "

S = "${WORKDIR}/DuoFern_Service"

inherit autotools pkgconfig

prefix="/opt/homepilot"
exec_prefix="/opt/homepilot"
PKG_CONFIG_DIR .= ":${STAGING_DIR_HOST}/usr/lib/pkgconfig"

FILES_${PN} += "/opt/homepilot"
FILES_${PN}-dbg += "/opt/homepilot/bin/.debug"
