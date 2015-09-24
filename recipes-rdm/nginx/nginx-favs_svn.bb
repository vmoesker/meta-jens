DESCRIPTION = "/favs"

LICENSE = "commercial"
LIC_FILES_CHKSUM = "file://${THISDIR}/files/license.txt;md5=3ebe3464e841ddbf115af1f7019017c5"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

DEPENDS = "nginx"
RDEPENDS_${PN} = "nginx"

PV = "0.1"

SRC_URI = "svn://192.168.1.186/svn/EW_Prj/001/HP_Whitepages/trunk;protocol=http;module=HP_View_Status;rev=5013"
S = "${WORKDIR}/HP_View_Status/src"

do_install() {
    install -o www -g www-data -m 0755 -d ${D}${localstatedir}/www/localhost/favs
    install -o www -g www-data -m 0755 -t ${D}${localstatedir}/www/localhost/favs/ ${S}/index.html ${S}/jquery-1.7.1.min.js
}
