FILESEXTRAPATHS_prepend := "${THISDIR}/base-files:"

hostname := "homepilot"
volatiles := ""
DEV_PFX="${ROOT_DEV_NAME}${ROOT_DEV_SEP}"

do_install_append () {
    sed -i -e "s,@DEV_PFX@,${DEV_PFX},g" \
         -e "s,@overlay@,${OVERLAY},g" -e "s,@overlayfs@,${OVERLAYFS},g" -e "s,@unionfs@,${UNIONFS},g" \
        ${D}${sysconfdir}/fstab
    test "${MACHINE}" = "bohr" && sed -i -e "s,ext[24],ubifs,g" \
        ${D}${sysconfdir}/fstab

    install -d ${D}/data
    rm -f ${D}/var/log ${D}/var/tmp
    install -d ${D}/var/log
    install -d ${D}/var/tmp
}
