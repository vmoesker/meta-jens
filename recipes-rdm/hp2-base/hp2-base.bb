DESCRIPTION = "RDM HP2 system base" 
HOMEPAGE = "http://www.rademacher.de"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://${THISDIR}/files/license.txt;md5=3ebe3464e841ddbf115af1f7019017c5"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

inherit useradd

SRC_URI = "file://hp2.volatiles"

DEPENDS = "daemontools ledctrl"
RDEPENDS_${PN} = "daemontools ledctrl check-connectivity"

HOMEPILOT_USER = "homepilot"
HOMEPILOT_USER_HOME = "/home/homepilot"

do_install() {
    # create homepilot user dir
    install -o homepilot -g users -m 0755 -d ${D}${HOMEPILOT_USER_HOME}

    # create volatiles base dir
    install -d ${D}${sysconfdir}/default/volatiles

    install -m 644 ${WORKDIR}/hp2.volatiles ${D}${sysconfdir}/default/volatiles/98_hp2
}

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "-u 800 -d ${HOMEPILOT_USER_HOME} -g users -G dialout,svcctrl,errhlp -r -m -s /bin/sh ${HOMEPILOT_USER}"

FILES_${PN} += "\
    ${HOMEPILOT_USER_HOME} \
    ${sysconfdir} \
"
