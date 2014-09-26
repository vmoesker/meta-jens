# Freescale imx extra configuration udev rules
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_mx6 = " file://10-imx-hdmi.rules"

do_install_prepend_mx6 () {
	install -d ${D}${sysconfdir}/udev/rules.d
	install -m 0644 ${WORKDIR}/10-imx-hdmi.rules ${D}${sysconfdir}/udev/rules.d
}

PACKAGE_ARCH_mx6 = "${MACHINE_ARCH}"
