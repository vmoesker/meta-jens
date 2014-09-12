DESCRIPTION = "RDM HP BLOB" 
HOMEPAGE = "http://www.rademacher.de"
LICENSE = "commercial"
LIC_FILES_CHKSUM = "file://${THISDIR}/files/license.txt;md5=3ebe3464e841ddbf115af1f7019017c5"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

DEPENDS = "tar "
PV = "4.0.0.0"
SRC_URI = "svn://192.168.1.186/svn/EW_Prj/trunk/;protocol=http;module=HomePilot_Blob;rev=3618"

S = "${WORKDIR}/HomePilot_Blob"

INST_DEST_PREFIX="/opt/homepilot"
TARBALL_NAME="hp-dist_${PV}.tar.gz"

inherit update-rc.d

do_install() {
	# create init.d directory
	install -d ${D}${sysconfdir}/init.d/
	
	# install init.d script and make it executable
	install -m 0755 ${S}/init.d/homepilot ${D}${sysconfdir}/init.d/homepilot
	
	# create INST_DEST_PREFIX folder
	install -d ${D}${INST_DEST_PREFIX}

	# Extract tarball into INST_DEST_PREFIX dir of target
	tar xzf ${S}/${TARBALL_NAME} -C ${D}${INST_DEST_PREFIX}

	# Clean-up messed up so-files from Jetty distribution ... 
	find ${D}${INST_DEST_PREFIX} -name '*.so' | xargs rm -f
	
	# fix rights of gpg keyfolder
	chmod -R 700 ${D}${INST_DEST_PREFIX}/etc/homepilot/2/gpg
}

FILES_${PN} += "/opt/homepilot \
		"

RDEPENDS_${PN} = "gnupg"

INITSCRIPT_NAME = "homepilot"
INITSCRIPT_PARAMS = "start 95 S . stop 20 0 1 6 ."
