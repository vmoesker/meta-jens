FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

RRECOMMENDS_${PN} += "ethtool"

SRC_URI += "file://eth/pre_up.sh \
	file://eth/post_up.sh \
	file://eth/pre_down.sh \
	file://eth/post_down.sh \
	file://wifi/pre_up.sh \
        file://wifi/post_up.sh \
        file://wifi/pre_down.sh \
        file://wifi/post_down.sh \
        file://wifi/defaults \
	"

do_install_append () {
    	install -d ${D}${sysconfdir}/network/eth
	install -d ${D}${sysconfdir}/network/wifi

	install -m 0755 ${WORKDIR}/eth/pre_up.sh ${D}${sysconfdir}/network/eth
	install -m 0755 ${WORKDIR}/eth/pre_down.sh ${D}${sysconfdir}/network/eth
	install -m 0755 ${WORKDIR}/eth/post_up.sh ${D}${sysconfdir}/network/eth
	install -m 0755 ${WORKDIR}/eth/post_down.sh ${D}${sysconfdir}/network/eth

	install -m 0644 ${WORKDIR}/wifi/defaults ${D}${sysconfdir}/network/wifi
	install -m 0755 ${WORKDIR}/wifi/pre_up.sh ${D}${sysconfdir}/network/wifi
	install -m 0755 ${WORKDIR}/wifi/pre_down.sh ${D}${sysconfdir}/network/wifi
	install -m 0755 ${WORKDIR}/wifi/post_up.sh ${D}${sysconfdir}/network/wifi
	install -m 0755 ${WORKDIR}/wifi/post_down.sh ${D}${sysconfdir}/network/wifi
}

FILES_${PN} += "/etc/network"
