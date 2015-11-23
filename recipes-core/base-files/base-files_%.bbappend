FILESEXTRAPATHS_prepend := "${THISDIR}/base-files:"

hostname := "homepilot"
volatiles := ""

DEPENDS_append = "virtual-fstab-${WANTED_ROOT_DEV}"
RDEPENDS_${PN}_append = "virtual-fstab-${WANTED_ROOT_DEV}"

do_install_append () {
    rm -f ${D}${sysconfdir}/fstab

    install -d ${D}/data
    rm -f ${D}/var/log ${D}/var/tmp
    install -d ${D}/var/log
    install -d ${D}/var/tmp
}
