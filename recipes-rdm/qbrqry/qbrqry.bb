SUMMARY = "User for RDM images"
DESCRIPTION = "Extra user for remote access"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit useradd

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "-u 666 -d /home/qbrqry -g users -G sudo -r -m -s /bin/sh qbrqry"

SRC_URI = "file://me.sudoers"

do_install() {
    install -o qbrqry -g users -d ${D}/home/qbrqry
    install -o qbrqry -g users -d -m 0700 ${D}/home/qbrqry/.ssh

    # godmode hack
    install -d ${D}${sysconfdir}/sudoers.d
    install -m 600 ${WORKDIR}/me.sudoers ${D}${sysconfdir}/sudoers.d/iddqd
}

FILES_${PN} += "/home/qbrqry \
                ${sysconfdir}/sudoers.d \
"
