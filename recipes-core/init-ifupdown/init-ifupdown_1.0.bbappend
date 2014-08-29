FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI += "file://eth/pre_up.sh \
	file://eth/post_up.sh \
	file://eth/pre_down.sh \
	file://eth/post_down.sh \
	file://wifi/pre_up.sh \
        file://wifi/post_up.sh \
        file://wifi/pre_down.sh \
        file://wifi/post_down.sh \
	"

do_install_append () {
    	install -d ${D}${sysconfdir}/network/eth
	install -d ${D}${sysconfdir}/network/wifi

	install ${WORKDIR}/eth/pre_up.sh ${D}${sysconfdir}/network/eth
	install ${WORKDIR}/eth/pre_down.sh ${D}${sysconfdir}/network/eth
	install ${WORKDIR}/eth/post_up.sh ${D}${sysconfdir}/network/eth
	install ${WORKDIR}/eth/post_down.sh ${D}${sysconfdir}/network/eth

	install ${WORKDIR}/wifi/pre_up.sh ${D}${sysconfdir}/network/wifi
	install ${WORKDIR}/wifi/pre_down.sh ${D}${sysconfdir}/network/wifi
	install ${WORKDIR}/wifi/post_up.sh ${D}${sysconfdir}/network/wifi
	install ${WORKDIR}/wifi/post_down.sh ${D}${sysconfdir}/network/wifi

	ls ${D}${sysconfdir}/network/eth
}

FILES_${PN} += "/etc/network"

