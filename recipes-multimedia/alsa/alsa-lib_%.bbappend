# look for files in the layer first
FILESEXTRAPATHS_prepend_mx6 := "${THISDIR}/${PN}:"

SRC_URI_append_mx6 = " \
        file://imx-hdmi-soc.conf \
        file://imx-spdif.conf \
"

BAD_PATCH="file://alsa-lib-1.0.24-fix_s24_3le_downmix.patch"

do_install_append_mx6 () {
    install -m 0644 ${WORKDIR}/imx-hdmi-soc.conf ${D}/${datadir}/alsa/cards
    install -m 0644 ${WORKDIR}/imx-spdif.conf ${D}/${datadir}/alsa/cards
}
