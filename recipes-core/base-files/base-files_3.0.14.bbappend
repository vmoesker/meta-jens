FILESEXTRAPATHS_prepend := "${THISDIR}/base-files:"

hostname := "homepilot"
volatiles := ""

do_install_append () {
    install -d ${D}/data
    rm -f ${D}/var/log ${D}/var/tmp
    install -d ${D}/var/log
    install -d ${D}/var/tmp
}
