DESCRIPTION = "Use this script and have KSM enabled during boot-time"
HOMEPAGE = "https://github.com/dnaeon/ksm-init.d-debian"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

DEPENDS = "ncurses"

PV = "0.91"

FILESEXTRAPATHS_prepend := "${THISDIR}/patches:"

SRCREV = "c0c39a9c29c6da38b844af840079e89af70994ee"
SRC_URI = "git://github.com/dnaeon/ksm-init.d-debian.git \
          "

S = "${WORKDIR}/git"

do_install_append () {
    install -d ${D}${sysconfdir}/init.d

    install -m 0755 ${S}/ksm ${D}${sysconfdir}/init.d
}

INITSCRIPT_NAME = "ksm"
INITSCRIPT_PARAMS = "start 04 S . stop 20 0 1 6 ."
