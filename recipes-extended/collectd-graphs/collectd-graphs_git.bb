DESCRIPTION = "Collectd::Graphs creates the graphs from collectd rrds"

SECTION = "libs"
LICENSE = "Artistic-1.0 | GPL-2.0"
PR = "r0"

MAINTAINER=	"HP2 Dev Team <verteiler.hp2dev.team@rademacher.de>"
HOMEPAGE=	"https://github.com/rehsack/System-Image-Update"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Artistic-1.0;md5=cda03bbdc3c1951996392b872397b798 \
file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "git://github.com/rehsack/Collectd-Graphs.git;rev=30a84dd39e3fff7420e28b5fc4af85dbbe3f3971 \
"

RDEPENDS_${PN} += "fontconfig"
RDEPENDS_${PN} += "liberation-fonts"
RDEPENDS_${PN} += "collectd"

RDEPENDS_${PN} += "log-any-adapter-dispatch-perl"
RDEPENDS_${PN} += "experimental-perl"
RDEPENDS_${PN} += "file-find-rule-perl"
RDEPENDS_${PN} += "moo-perl"
RDEPENDS_${PN} += "moox-cmd-perl"
RDEPENDS_${PN} += "moox-configfromfile-perl"
RDEPENDS_${PN} += "moox-log-any-perl"
RDEPENDS_${PN} += "moox-options-perl"
RDEPENDS_${PN} += "moox-roles-pluggable-perl"
RDEPENDS_${PN} += "params-util-perl"
RDEPENDS_${PN} += "rrdtool-oo-perl"
RDEPENDS_${PN} += "scalar-list-utils-perl"

S = "${WORKDIR}/git"

inherit cpan

do_configure_append() {
    oe_runmake manifest
}

#SERVICE_ROOT = "${sysconfdir}/daemontools/service"
#SYSUPDT_SERVICE_DIR = "${SERVICE_ROOT}/sysimg_update"

#do_install_append() {
#}

#FILES_${PN} += "${sysconfdir}"

BBCLASSEXTEND = "native"
