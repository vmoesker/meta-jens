DESCRIPTION = "Initscript for flashing images at boot"

FILESEXTRAPATHS_prepend := "${THISDIR}/prd-flash:"

LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "file://flash-device.sh \
"

SRC_URI_append_curie = "\
	file://init.ssd \
	file://init.curie \
	file://hw.curie \
"

SRC_URI_append_bohr = "\
	file://init.mtd \
	file://init.ssd \
	file://init.bohr \
	file://hw.bohr \
	file://ubinize.cfg \
"

SRC_URI_append_bohr-update = "\
	file://init.mtd \
	file://init.bohr-update \
	file://hw.bohr-update \
	file://ubinize.cfg \
"

def all_root_dev_names (d) :
    rc = []
    root_dev_names = d.getVar("AVAIL_ROOT_DEVS", True).split(" ")
    for dev in root_dev_names:
        devnm = "ROOT_DEV_NAME-" + dev
        rc.append("s,@" + devnm + "[@]," + d.getVar(devnm, True) + ",g")
    return " ".join(rc)

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
	    -e "s/@MACHINE[@]/${MACHINE}/g" -e "s,@AVAIL_ROOT_DEVS[@],${AVAIL_ROOT_DEVS},g" -e "s,@WANTED_ROOT_DEV[@],${WANTED_ROOT_DEV},g" \
            -e "s,@ROOT_DEV_TYPE[@],${ROOT_DEV_TYPE},g" -e "s,@ROOT_DEV_SEP[@],${ROOT_DEV_SEP},g" \
            $ALL_ROOT_DEV_NAMES \
	    ${WORKDIR}/flash-device.sh ${WORKDIR}/hw.${MACHINE} ${WORKDIR}/init.*
}

do_install () {
	install -d ${D}${sysconfdir}/init.d
	install -d ${D}${libexecdir}/${MACHINE}

	install -m 0755 ${WORKDIR}/flash-device.sh ${D}${sysconfdir}/init.d
	install -m 0644 ${WORKDIR}/hw.${MACHINE} ${D}${libexecdir}/${MACHINE}/hw
	install -m 0644 ${WORKDIR}/init.${MACHINE} ${D}${libexecdir}/${MACHINE}/init.${MACHINE}

	update-rc.d -r ${D} flash-device.sh start 25 3 5 .
}

do_install_append_curie () {
	install -d ${D}${libexecdir}/${MACHINE}
	install -m 0644 ${WORKDIR}/init.ssd ${D}${libexecdir}/${MACHINE}
}

do_install_append_bohr () {
	install -d ${D}${libexecdir}/${MACHINE}
	install -m 0644 ${WORKDIR}/init.mtd ${D}${libexecdir}/${MACHINE}
	install -m 0644 ${WORKDIR}/init.ssd ${D}${libexecdir}/${MACHINE}
	install -m 0644 ${WORKDIR}/ubinize.cfg ${D}${libexecdir}/${MACHINE}
}

FILES_${PN} += "${libexecdir}/${MACHINE}"
