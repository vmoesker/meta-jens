DESCRIPTION = "This recipe provides WR-Connect production steps" 
HOMEPAGE = "http://www.rademacher.de"
LICENSE = "commercial"
LIC_FILES_CHKSUM = "file://${THISDIR}/wrkeyupload/license.txt;md5=3ebe3464e841ddbf115af1f7019017c5"
FILESEXTRAPATHS_prepend := "${THISDIR}/wrkeyupload:"

#XXX add ssh dependency
PV = "0.1"
SRC_URI += "file://hpsrvfingerprint"

do_install() {
	#create known_hosts for homepilot.de
	install -d -m 0700 ${D}/home/root/.ssh
	touch ${D}/home/root/.ssh/known_hosts
	cat ${WORKDIR}/hpsrvfingerprint >> ${D}/home/root/.ssh/known_hosts
	chmod 600 ${D}/home/root/.ssh/known_hosts 
}
FILES_${PN} += "/home/root/.ssh"
