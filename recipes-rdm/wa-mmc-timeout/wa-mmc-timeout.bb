LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = " \
    file://wa-mmc-timeout.sh \
    file://wa-mmc-timeout-init.sh \
"

COMPATIBLE_MACHINE = "(curie)"
PACKAGE_ARCH = "${MACHINE_ARCH}"

do_install () {
    install -d ${D}${sbindir}
    install -m 0755 ${WORKDIR}/wa-mmc-timeout.sh ${D}${sbindir}/wa-mmc-timeout

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/wa-mmc-timeout-init.sh ${D}${sysconfdir}/init.d/wa-mmc-timeout

    update-rc.d -r ${D} wa-mmc-timeout start 98 5 .
}
