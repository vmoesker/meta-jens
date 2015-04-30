FILESEXTRAPATHS_prepend := "${THISDIR}/base-files:"

hostname := "homepilot"
volatiles := ""

do_install_append () {
    PREFIX=""
    test ${SDCARD_IMAGE} -eq 1 && PREFIX="sd"
    LABEL="${PREFIX}${MACHINE}"
    sed -i -e "s,@LABEL@,${LABEL},g" -e "s,@KERNEL_MMC_DEV@,${KERNEL_MMC_DEV},g" \
         -e "s,@overlayfs@,${OVERLAYFS},g" -e "s,@unionfs@,${UNIONFS},g" \
        ${D}/${sysconfdir}/fstab

    install -d ${D}/data
    rm -f ${D}/var/log ${D}/var/tmp
    install -d ${D}/var/log
    install -d ${D}/var/tmp
}
