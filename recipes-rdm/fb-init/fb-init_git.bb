DESCRIPTION = "This software will copy a splash screen in Framebuffer (/dev/fb0) how to Install XBMC if it isn't installed."
HOMEPAGE = "http://www.rademacher.de"

LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://${THISDIR}/files/license.txt;md5=3ebe3464e841ddbf115af1f7019017c5"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

RDEPENDS_${PN} = "fbset imagemagick liberation-fonts"

SRCREV="3be362581931a117831f3d236bb6a8bb86ac3428"
PV = "0.1+git${SRCPV}"

SRC_URI = "git://git@bitbucket.org/rdm-dev/IEBF.git;protocol=ssh;branch=jethro"
S = "${WORKDIR}/git/src"

inherit update-rc.d

ALTERNATIVE_PRIORITY = "100"
ALTERNATIVE_LINK_NAME[init_fb] = "${D}${sysconfdir}/init.d/init_fb"

do_install() {
	#create init.d directory
	install -d ${D}${sysconfdir}/init.d/
	
	#install init.d script and make it executable
	install -m 0755 ${S}/init_fb ${D}${sysconfdir}/init.d/init_fb
	
	#create directory for source
	install -d ${D}/opt/rdm/fb

	#copy scripts and make them executable
	install -m 0755 ${S}/*.sh ${D}/opt/rdm/fb/
	
	#copy inital pictures. BE CAREFUL HERE: Pictures have to be in BGR-Colorspace for the Framebuffer, you maybe have to convert it first. See Readme in the src/baseimage folder in the SVN Repository
	install ${S}/*.jpg ${D}/opt/rdm/fb/
}

FILES_${PN} += "/opt/rdm/fb"

INITSCRIPT_NAME = "init_fb"
INITSCRIPT_PARAMS = "start 99 5 . stop 20 0 1 6 ."
