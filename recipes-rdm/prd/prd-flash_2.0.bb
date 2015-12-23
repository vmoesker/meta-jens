DESCRIPTION = "Initscript for flashing images at boot"

OPN := "${PN}"
PN = "${OPN}-${WANTED_ROOT_DEV}"
FILESEXTRAPATHS_prepend := "${THISDIR}/${OPN}:"

include recipes-rdm/prd/${PN}.inc

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "file://flash-device.sh \
"

SRC_URI_append_curie = "\
    file://init.ssd \
    file://init.curie \
    file://hw.curie \
    file://post.curie \
"

SRC_URI_append_kirkwood = "\
    file://init.mtd \
    file://ubinize.cfg \
"

SRC_URI_append_bohr = "\
    file://init.ssd \
    file://init.bohr \
    file://hw.bohr \
    file://post.bohr \
"

SRC_URI_append_bohr-update = "\
    file://init.bohr-update \
    file://hw.bohr-update \
    file://post.bohr-update \
    file://algorithms \
"

RDEPENDS_${PN}_append = "\
    perl-module-version \
    util-linux \
"

RDEPENDS_${PN}_append_mx6 = "\
    u-boot-fw-utils-curie \
"

RDEPENDS_${PN}_append_kirkwood = "\
    mtd-utils \
    mtd-utils-jffs2 \
    mtd-utils-ubifs \
    mtd-utils-misc \
    u-boot-fw-utils \
"

RDEPENDS_${PN}_append_bohr-update = "\
    openssl \
"

def all_root_dev_names (d) :
    rc = []
    root_dev_names = d.getVar("AVAIL_ROOT_DEVS", True).split(" ")
    for dev in root_dev_names:
        devnm = "ROOT_DEV_NAME-" + dev
        rc.append("s,@" + devnm + "[@]," + d.getVar(devnm, True) + ",g")
    return " ".join(rc)

FINALIZE_FLASH = "reboot"
FINALIZE_FLASH_bohr-update = "poweroff"

do_compile () {
    set -x

    ALL_ROOT_DEV_NAMES=""
    ALL_ROOT_DEV_NAMES_S=""
    for d in ${@all_root_dev_names(d)}
    do
        ALL_ROOT_DEV_NAMES="${ALL_ROOT_DEV_NAMES}${ALL_ROOT_DEV_NAMES_S}-e ${d}"
        ALL_ROOT_DEV_NAMES_S=" "
    done

    sed -i -e "s,@ARGV0@,${sysconfdir}/init.d/flash-device.sh,g" -e "s,@LIBEXEC[@],${libexecdir}/${MACHINE},g" \
        -e "s/@MACHINE[@]/${MACHINE}/g" -e "s,@AVAIL_ROOT_DEVS[@],${AVAIL_ROOT_DEVS},g" \
        -e "s,@INTERNAL_ROOT_DEV[@],${INTERNAL_ROOT_DEV},g" -e "s,@WANTED_ROOT_DEV[@],${WANTED_ROOT_DEV},g" \
        -e "s,@ROOT_DEV_TYPE[@],${ROOT_DEV_TYPE},g" -e "s,@ROOT_DEV_SEP[@],${ROOT_DEV_SEP},g" \
	-e "s,@BOOTABLE_ROOT_DEVS[@],${BOOTABLE_ROOT_DEVS},g" -e "s,@FINALIZE_FLASH[@],${FINALIZE_FLASH},g" \
        $ALL_ROOT_DEV_NAMES \
        ${WORKDIR}/flash-device.sh ${WORKDIR}/hw.${MACHINE} ${WORKDIR}/init.*
}

do_install () {
    install -d ${D}${sysconfdir}/init.d
    install -d ${D}${libexecdir}/${MACHINE}

    install -m 0755 ${WORKDIR}/flash-device.sh ${D}${sysconfdir}/init.d
    install -m 0644 ${WORKDIR}/hw.${MACHINE} ${D}${libexecdir}/${MACHINE}/hw
    install -m 0644 ${WORKDIR}/init.${MACHINE} ${D}${libexecdir}/${MACHINE}/init

    update-rc.d -r ${D} flash-device.sh start 25 3 5 .
}

do_install_append_curie () {
    install -d ${D}${libexecdir}/${MACHINE}
    test "${WANTED_ROOT_DEV}" = "nfs" && install -m 0644 ${WORKDIR}/post.${MACHINE} ${D}${libexecdir}/${MACHINE}/post
    install -m 0644 ${WORKDIR}/init.ssd ${D}${libexecdir}/${MACHINE}
}

do_install_append_bohr () {
    install -d ${D}${libexecdir}/${MACHINE}
    install -m 0644 ${WORKDIR}/init.mtd ${D}${libexecdir}/${MACHINE}
    install -m 0644 ${WORKDIR}/init.ssd ${D}${libexecdir}/${MACHINE}
    test "${WANTED_ROOT_DEV}" = "nfs" && install -m 0644 ${WORKDIR}/post.${MACHINE} ${D}${libexecdir}/${MACHINE}/post
    install -m 0644 ${WORKDIR}/ubinize.cfg ${D}${libexecdir}/${MACHINE}
}

do_install_append_bohr-update () {
    install -d ${D}${libexecdir}/${MACHINE}
    install -m 0644 ${WORKDIR}/init.mtd ${D}${libexecdir}/${MACHINE}
    install -m 0644 ${WORKDIR}/post.${MACHINE} ${D}${libexecdir}/${MACHINE}/post
    install -m 0644 ${WORKDIR}/ubinize.cfg ${D}${libexecdir}/${MACHINE}
    install -m 0644 ${WORKDIR}/algorithms ${D}${libexecdir}/${MACHINE}
}

FILES_${PN} += "${libexecdir}/${MACHINE}"
