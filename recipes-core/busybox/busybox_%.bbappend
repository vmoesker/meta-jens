FILESEXTRAPATHS_append := "${THISDIR}/files:"

SRC_URI += "\
    file://udhcpc-opts.cfg \
    file://netstat.cfg \
    file://nice.cfg \
    file://unix-local.cfg \
    file://udhcp.cfg \
    file://ifupdown.cfg \
    file://ifplugd.cfg \
    file://ifplugd/ifplugd.action \
    file://ifplugd/ifplugd.conf \
    file://ifplugd/ifplugd.init \
    file://pstree.cfg \
    \
    file://0001-ifupdown-improve-debian-compatibility-for-mapping.patch \
"

PACKAGES =+ "${PN}-ifplugd"
FILES_${PN}-ifplugd = "${sysconfdir}/init.d/busybox-ifplugd ${sysconfdir}/ifplugd ${sysconfdir}/rc*/*busybox-ifplugd"

do_install_append() {
    if grep -q "CONFIG_IFPLUGD=y" ${B}/.config; then
        install -m 755 ${WORKDIR}/ifplugd/ifplugd.init ${D}${sysconfdir}/init.d/busybox-ifplugd

        install -d ${D}${sysconfdir}/ifplugd
        install -m 755 ${WORKDIR}/ifplugd/ifplugd.action ${D}${sysconfdir}/ifplugd/ifplugd.action
        install -m 644 ${WORKDIR}/ifplugd/ifplugd.conf ${D}${sysconfdir}/ifplugd/ifplugd.conf

        update-rc.d -r ${D} busybox-ifplugd defaults 05
    fi
}
