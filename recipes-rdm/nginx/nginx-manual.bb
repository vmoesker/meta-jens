DESCRIPTION = "/manual"

LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://${THISDIR}/files/license.txt;md5=3ebe3464e841ddbf115af1f7019017c5"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

DEPENDS = "nginx"
RDEPENDS_${PN} = "nginx"

PV = "0.4"

SRC_URI = "http://internal.rdm.local/blobs/${PN}-${PV}.tar.gz;name=manual"
SRC_URI[manual.md5sum] = "d42c56ce54078c53bb8a4a151bb89eeb"
SRC_URI[manual.sha256sum] = "17e16a35206b2a0379d758a5b0f355e095db5064ee51e91d26cea3c527edc50c"

do_install() {
    install -o www -g www-data -m 0755 -d ${D}${localstatedir}/www/localhost/manual
    install -o www -g www-data -m 0755 -t ${D}${localstatedir}/www/localhost/manual/ ${WORKDIR}/manual/index.html ${WORKDIR}/manual/logo.png ${WORKDIR}/manual/*.pdf
}
