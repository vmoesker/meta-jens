DESCRIPTION = "/legal"

LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://${THISDIR}/files/license.txt;md5=3ebe3464e841ddbf115af1f7019017c5"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

DEPENDS = "nginx"
RDEPENDS_${PN} = "nginx"

PV = "0.2"

SRC_URI = "http://internal.rdm.local/blobs/${PN}-${PV}.tar.gz;name=legal"
SRC_URI[legal.md5sum] = "ccb221827f2bcd827cca8db6a3d14d70"
SRC_URI[legal.sha256sum] = "f8643b50b34b5cf5ce483bf01012f97dc35b2d0a5b79adbe2d73042ca47edf1e"

do_install() {
    install -o www -g www-data -m 0755 -d ${D}${localstatedir}/www/localhost/legal
    install -o www -g www-data -m 0755 -t ${D}${localstatedir}/www/localhost/legal/ ${WORKDIR}/legal/*
}
