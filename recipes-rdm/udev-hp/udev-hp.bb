SUMMARY = "Extra HP2 specific udev configuration files"
DESCRIPTION = "Extra HP2 radio specific configuration files for udev"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PR = "r0"

SRC_URI = "file://hp2radio.rules \
           file://update_tty_df.sh \
           file://update_tty_zwave.sh \
"

do_install() {
    install -d ${D}${sysconfdir}/udev/rules.d
    install -d ${D}${sysconfdir}/udev/scripts

    install -m 0644 ${WORKDIR}/hp2radio.rules     ${D}${sysconfdir}/udev/rules.d/

    install -m 0755 ${WORKDIR}/update_tty_df.sh ${D}${sysconfdir}/udev/scripts/
    install -m 0755 ${WORKDIR}/update_tty_zwave.sh ${D}${sysconfdir}/udev/scripts/
}
