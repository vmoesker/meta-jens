DESCRIPTION = "Utilities to collect and visualise system statistics"
HOMEPAGE = "http://www.i-scream.org/libstatgrab/"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = "ncurses"

PV = "0.91"

FILESEXTRAPATHS_prepend := "${THISDIR}/patches:"

SRCREV = "c39988855a9d1eb54adadb899b3865304da2cf84"
SRC_URI = "git://github.com/i-scream/libstatgrab.git \
           file://linux-proctbl-names-with-spaces.patch \
          "

S = "${WORKDIR}/git"

inherit autotools pkgconfig

# libstatgrab-client need the .so present to work :(
FILES_${PN} += "${libdir}/*.so"
INSANE_SKIP_${PN} = "dev-so"
