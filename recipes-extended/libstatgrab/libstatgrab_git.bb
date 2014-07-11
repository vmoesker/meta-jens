DESCRIPTION = "Utilities to collect and visualise system statistics"
HOMEPAGE = "http://www.i-scream.org/libstatgrab/"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = "ncurses"

PV = "0.91"

SRCREV = "28151cc18d65bc0dc3568b2f48d1a528bb296e9d"
SRC_URI = "git://github.com/i-scream/libstatgrab.git"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

# libstatgrab-client need the .so present to work :(
FILES_${PN} += "${libdir}/*.so"
INSANE_SKIP_${PN} = "dev-so"
