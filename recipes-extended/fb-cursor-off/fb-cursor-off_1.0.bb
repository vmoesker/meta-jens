DESCRIPTION = "Initscript to disable blinking cursor on framebuffer"

LICENSE = "GPL-2.0"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "file://fb-cursor-off.sh \
"

do_install () {
        install -d ${D}${sysconfdir}/init.d
        install -m 0755 ${WORKDIR}/fb-cursor-off.sh ${D}${sysconfdir}/init.d

        update-rc.d -r ${D} fb-cursor-off.sh start 05 2 3 4 5 .
}
