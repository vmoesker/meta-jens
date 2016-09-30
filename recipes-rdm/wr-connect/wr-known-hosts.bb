DESCRIPTION = "This recipe provides wr known hosts"
HOMEPAGE = "http://www.rademacher.de"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://${THISDIR}/wr-known-hosts/license.txt;md5=3ebe3464e841ddbf115af1f7019017c5"
FILESEXTRAPATHS_prepend := "${THISDIR}/wr-known-hosts:"

PV = "0.1"
SRC_URI += "file://known_hosts"

do_install() {
    install -d -m 0700 ${D}/home/root/.ssh
    touch ${D}/home/root/.ssh/known_hosts
    cat ${WORKDIR}/known_hosts >> ${D}/home/root/.ssh/known_hosts
    chmod 600 ${D}/home/root/.ssh/known_hosts
}

FILES_${PN} += "/home/root/.ssh"
