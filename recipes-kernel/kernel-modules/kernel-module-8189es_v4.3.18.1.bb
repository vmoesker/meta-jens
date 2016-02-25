# Copyright (C) 2015 Jens Rehsack <sno@netbsd.org>

SUMMARY = "Kernel loadable module for RTL8189ES WiFi chipset"
DESCRIPTION = "Provides flexibility to switch wifi drivers between different kernels"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

inherit module

REV="cef931fc0614acbba15f6b08402fe253629411db"
SRCREPO="rehsack"
SRCBRANCH = "master"

SRC_URI = "git://github.com/${SRCREPO}/rtl8189ES_linux.git;branch=${SRCBRANCH};rev=${REV}"

S = "${WORKDIR}/git"

do_compile_append () {
    echo "options 8189es rtw_power_mgnt=0" >${WORKDIR}/8189es.conf
}

do_install_append () {
    install -d ${D}${sysconfdir}/modprobe.d/
    install -m 644 ${WORKDIR}/8189es.conf ${D}${sysconfdir}/modprobe.d/
}
