DESCRIPTION = "libCEC allows you in combination with the right hardware to control your device with your TV remote control. Utilising your existing HDMI cabling"
LICENSE = "GPL-2.0+"
LIC_FILES_CHKSUM = "file://COPYING;md5=e296fd6027598da75a7516ce1ae4f56a"

inherit autotools pkgconfig

SRC_URI = "git://github.com/xbmc-imx6/libcec.git"
SRCREV = "5e00ba12d63b6f9240c2b6ee914b35a7735262aa"

PV = "2.2.0-1+git${SRCPV}"
PR = "r2"

EXTRA_OECONF="--enable-imx6"

S = "${WORKDIR}/git"

# cec-client and xbmc may need the .so present to work :(
FILES_${PN} += "${libdir}/*.so"
