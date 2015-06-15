# Freescale imx extra configuration udev rules
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_mx6 = " file://10-imx-hdmi.rules \
                       file://10-galcore.rules \
		       file://platform-gpio-keys.rules \
"

do_install_prepend_mx6 () {
	install -d ${D}${sysconfdir}/udev/rules.d
	install -m 0644 ${WORKDIR}/10-imx-hdmi.rules ${D}${sysconfdir}/udev/rules.d
	install -m 0644 ${WORKDIR}/10-galcore.rules ${D}${sysconfdir}/udev/rules.d
	install -m 0644 ${WORKDIR}/platform-gpio-keys.rules ${D}${sysconfdir}/udev/rules.d

        echo "/dev/mmcblk${KERNEL_MMC_DEV}p*" >> ${WORKDIR}/mount.blacklist
}

PACKAGE_ARCH_mx6 = "${MACHINE_ARCH}"
