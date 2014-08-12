DESCRIPTION = "RDM DF Service"
HOMEPAGE = "http://www.rademacher.de"

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891"


DEPENDS = "libxml2 libftdi"

PV = "1.2.0"

SRCREV = "3473"
SRC_URI = "svn://192.168.1.186/svn/EW_Prj/trunk/;protocol=http;module=DuoFern_Service"

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
FILES_${PN}-dbg += "/opt/homepilot"

