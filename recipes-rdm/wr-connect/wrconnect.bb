DESCRIPTION = "This recipe provides WR-Connect production steps" 
HOMEPAGE = "http://www.rademacher.de"
LICENSE = "commercial"
LIC_FILES_CHKSUM = "file://${THISDIR}/files/license.txt;md5=3ebe3464e841ddbf115af1f7019017c5"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

#XXX add ssh dependency
PV = "0.1"
SRC_URI += "file://wr-connect.sh"

inherit update-rc.d

do_install() {
    	install -d ${D}${sysconfdir}/init.d/
	install -m 0755 ${WORKDIR}/wr-connect.sh ${D}${sysconfdir}/init.d/wr-connect.sh
	
	#create known_hosts for homepilot.de
	mkdir -p ${D}/home/root/.ssh
	touch ${D}/home/root/.ssh/known_hosts
	echo "homepilot.de ssh-rsa AAAAB3NzaC1yc2EAAAABIwAAAQEAu0P5LndV3zxpL+TL69OJ4PUIRM7WiiPKe9byskWw8EohHC0QvfnbjJmR9G2y74tDzl6biBtXHP2n3J6kg4EJ/qS85ewo08Ks1665I1MfZITArvdybc1oH7/Jh4tgIBHr4jZF9QaC3Z/hPPd7caCkOKTMPE6xhcf+rpI/3C1ZVP+gatVo3AwCnVEW7zOKtpbcXBpgT3S6VLh+tlXWOXAcnkmez2DAU5p7g5jCnHSrjqk8/AD/WEwUKIm+AQksEPki5QviD1HoXIop2d7J6jXju8IUiqyu4tyVvS3539v3I/3DuDV3As95L6U6TPMRva8MhF206aRH4KYxRxOxmLxoNQ==" >> ${D}/home/root/.ssh/known_hosts
chmod 700 -R ${D}/home/root/.ssh/known_hosts
	chmod 600 ${D}/home/root/.ssh/known_hosts 
}
FILES_${PN} += "/home/root/.ssh"
INITSCRIPT_NAME = "wr-connect.sh"
INITSCRIPT_PARAMS = "start 99 5 S. stop 20 0 1 6 ."

	
