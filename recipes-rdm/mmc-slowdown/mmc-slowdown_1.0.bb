DESCRIPTION = "This slows down mmc on startup"
HOMEPAGE = "http://www.rademacher.de"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

DEPENDS = "linux-curie"

SRC_URI = "file://mmc-slowdown.sh"

inherit update-rc.d

ALTERNATIVE_PRIORITY = "100"
ALTERNATIVE_LINK_NAME[mmc_slowdown] = "${D}${sysconfdir}/init.d/mmc-slowdown"

do_compile () {
	sed -i -e "s/@KERNEL_MMC_DEV[@]/${KERNEL_MMC_DEV}/g" \
	    ${WORKDIR}/mmc-slowdown.sh
}

do_install () {
	#create init.d directory
	install -d ${D}${sysconfdir}/init.d/
	
	#install init.d script and make it executable
	install -m 0755 ${WORKDIR}/mmc-slowdown.sh ${D}${sysconfdir}/init.d/mmc-slowdown
}

INITSCRIPT_NAME = "mmc-slowdown"
INITSCRIPT_PARAMS = "start 04 S ."
