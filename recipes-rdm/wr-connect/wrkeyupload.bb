DESCRIPTION = "This recipe provides WR-Connect production steps" 
HOMEPAGE = "http://www.rademacher.de"
LICENSE = "commercial"
LIC_FILES_CHKSUM = "file://${THISDIR}/wrkeyupload/license.txt;md5=3ebe3464e841ddbf115af1f7019017c5"
FILESEXTRAPATHS_prepend := "${THISDIR}/wrkeyupload:"

#XXX add ssh dependency
PV = "0.1"
SRC_URI += "file://wrkeyupload.sh \
	    file://hpsrvfingerprint "

inherit update-rc.d

do_install() {
    	install -d ${D}${sysconfdir}/init.d/
	install -m 0755 ${WORKDIR}/wrkeyupload.sh ${D}${sysconfdir}/init.d/wrkeyupload.sh
	
	#create known_hosts for homepilot.de
	install -d -m 0700 ${D}/home/root/.ssh
	touch ${D}/home/root/.ssh/known_hosts
	cat ${WORKDIR}/hpsrvfingerprint >> ${D}/home/root/.ssh/known_hosts
	chmod 600 ${D}/home/root/.ssh/known_hosts 
}
FILES_${PN} += "/home/root/.ssh"
INITSCRIPT_NAME = "wrkeyupload.sh"
INITSCRIPT_PARAMS = "start 99 3 5 . stop 20 0 1 6 ."
