FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://mountunion.sh \
	file://cleanunion.sh \
	file://default-cleanunion.conf \
	file://umountunion.sh \
	file://wifi-fallback.sh \
	file://fb-cursor-off.sh \
"

do_install_append () {
    install -m 0755 ${WORKDIR}/mountunion.sh ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/umountunion.sh ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/wifi-fallback.sh ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/fb-cursor-off.sh ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/cleanunion.sh ${D}${sysconfdir}/init.d

	install -d ${D}${sysconfdir}/default
	install -m 0644 ${WORKDIR}/default-cleanunion.conf ${D}${sysconfdir}/default/cleanunion.conf

    update-rc.d -r ${D} umountunion.sh start 30 0 1 6 .
    update-rc.d -r ${D} mountunion.sh start 16 2 3 4 5 S .

    update-rc.d -r ${D} fb-cursor-off.sh start 05 2 3 4 5 .
    update-rc.d -r ${D} wifi-fallback.sh start 07 2 3 4 5 .

    # mount by-* requires udev being started
    mv -f ${D}/${sysconfdir}/rcS.d/S03mountall.sh ${D}/${sysconfdir}/rcS.d/S04mountall.sh 
}
