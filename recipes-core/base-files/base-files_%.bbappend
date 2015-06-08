FILESEXTRAPATHS_prepend := "${THISDIR}/base-files:"

hostname := "homepilot"
volatiles := ""

do_install_append () {
    BOOT_DEV="mmcblk${KERNEL_MMC_DEV}p"
    test ${USBSTICK_IMAGE} -eq 1 && BOOT_DEV="sda"
    sed -i -e "s,@BOOT_DEV@,${BOOT_DEV},g" \
         -e "s,@overlayfs@,${OVERLAYFS},g" -e "s,@unionfs@,${UNIONFS},g" \
        ${D}/${sysconfdir}/fstab

    install -d ${D}/data
    rm -f ${D}/var/log ${D}/var/tmp
    install -d ${D}/var/log
    install -d ${D}/var/tmp
}
