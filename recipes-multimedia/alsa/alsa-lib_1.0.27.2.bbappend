# look for files in the layer first
FILESEXTRAPATHS_prepend_mx6 := "${THISDIR}/${PN}:"

SRC_URI_append_mx6 = "file://alsa-lib-1.0.24-fix_s24_3le_downmix.patch \
        file://imx-hdmi-soc.conf \
        file://imx-spdif.conf \
"

do_install () {
    autotools_do_install
    install -m 0644 ${WORKDIR}/imx-hdmi-soc.conf ${D}/${datadir}/alsa/cards
    install -m 0644 ${WORKDIR}/imx-spdif.conf ${D}/${datadir}/alsa/cards
}
