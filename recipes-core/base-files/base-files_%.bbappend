FILESEXTRAPATHS_prepend := "${THISDIR}/base-files:"

hostname := "homepilot"
volatiles := ""

do_install_append () {
    sed -i -e "s,@MACHINE@,${MACHINE},g" ${D}/${sysconfdir}/fstab

    install -d ${D}/data
    rm -f ${D}/var/log ${D}/var/tmp
    install -d ${D}/var/log
    install -d ${D}/var/tmp
}
