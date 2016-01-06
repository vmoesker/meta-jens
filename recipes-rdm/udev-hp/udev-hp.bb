SUMMARY = "Extra HP2 specific udev configuration files"
DESCRIPTION = "Extra HP2 radio specific configuration files for udev"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PR = "r0"

SRC_URI = "file://hp2radio.rules \
           file://update_tty_df.sh \
           file://update_tty_zwave.sh \
"

DUOFERN_LED_curie = "user2"
DUOFERN_LED_bohr = "guruplug:green:health"
ZWAVE_LED_curie = "user1"
ZWAVE_LED_bohr = "guruplug:green:wmode"

PACKAGE_ARCH = "${MACHINE_ARCH}"

do_compile() {
    cp ${WORKDIR}/update_tty_df.sh ${B}/update_tty_df.sh
    sed -i -e "s,@DUOFERN_LED@,${DUOFERN_LED},g" ${B}/update_tty_df.sh

    cp ${WORKDIR}/update_tty_zwave.sh ${B}/update_tty_zwave.sh
    sed -i -e "s,@ZWAVE_LED@,${ZWAVE_LED},g" ${B}/update_tty_zwave.sh

    cp ${WORKDIR}/hp2radio.rules ${B}/hp2radio.rules
}

do_install() {
    install -d ${D}${sysconfdir}/udev/rules.d
    install -d ${D}${sysconfdir}/udev/scripts

    install -m 0644 ${B}/hp2radio.rules ${D}${sysconfdir}/udev/rules.d/

    install -m 0755 ${B}/update_tty_df.sh ${D}${sysconfdir}/udev/scripts/
    install -m 0755 ${B}/update_tty_zwave.sh ${D}${sysconfdir}/udev/scripts/
}
