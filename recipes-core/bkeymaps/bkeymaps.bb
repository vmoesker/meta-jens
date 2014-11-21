DESCRIPTION = "bkeymaps and initscript"

HOMEPAGE = "http://dev.alpinelinux.org/bkeymaps"
LICENSE = "GPL"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-1.0;md5=e9e36a9de734199567a4d769498f743d"

PV = "1.13"

SRC_URI = "http://dev.alpinelinux.org/bkeymaps/bkeymaps-${PV}.tar.gz \
           file://bkeymap.sh"

SRC_URI[sha256sum] = "59d41ddb0c7a92d8ac155a82ed2875b7880c8957ea4308afa633c8b81e5b8887"

KEYMAPS_DEST_PREFIX = "/usr/share/bkeymaps"

do_install() {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/bkeymap.sh ${D}${sysconfdir}/init.d

	update-rc.d -r ${D} bkeymap.sh start 53 S .

	install -d ${D}${KEYMAPS_DEST_PREFIX}
	cp -r ${WORKDIR}/bkeymaps-1.13/bkeymaps/* ${D}${KEYMAPS_DEST_PREFIX}
}
