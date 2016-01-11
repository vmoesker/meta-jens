DESCRIPTION = "RDM DF Service"
HOMEPAGE = "http://www.rademacher.de/"

LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://${THISDIR}/files/license.txt;md5=3ebe3464e841ddbf115af1f7019017c5"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

DEPENDS = "libxml2 libftdi"

SRCREV="1ea1b1f6a9e54ef02de109516e0aadddc216dfe6"
PV = "1.2.0+git${SRCPV}"

SRC_URI = "git://git@bitbucket.org/rdm-dev/DuoFern-Service.git;protocol=ssh;branch=jethro"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

prefix="/opt/homepilot"
exec_prefix="/opt/homepilot"
PKG_CONFIG_DIR .= ":${STAGING_DIR_HOST}/usr/lib/pkgconfig"

FILES_${PN} += "/opt/homepilot"
FILES_${PN}-dbg += "/opt/homepilot/bin/.debug"
