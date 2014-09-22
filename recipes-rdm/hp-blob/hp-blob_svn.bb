DESCRIPTION = "RDM HP BLOB" 
HOMEPAGE = "http://www.rademacher.de"
LICENSE = "commercial"
LIC_FILES_CHKSUM = "file://${THISDIR}/files/license.txt;md5=3ebe3464e841ddbf115af1f7019017c5"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

DEPENDS = "tar "
RDEPENDS_${PN} += "daemontools"

PV = "4.0.0.0"
SRC_URI = "svn://192.168.1.186/svn/EW_Prj/trunk/;protocol=http;module=HomePilot_Blob;rev=3655 \
		file://dfs \
		file://hp \
		file://jetty \
		file://z-way \
		"

S = "${WORKDIR}/HomePilot_Blob"

INST_DEST_PREFIX="/opt/homepilot"
TARBALL_NAME="hp-dist_${PV}.tar.gz"

SVC_SERVICES="${sysconfdir}/daemontools/service"

do_install() {
	# create INST_DEST_PREFIX folder
	install -d ${D}${INST_DEST_PREFIX}

	# Extract tarball into INST_DEST_PREFIX dir of target
	tar xzf ${S}/${TARBALL_NAME} -C ${D}${INST_DEST_PREFIX}

	# Clean-up messed up so-files from Jetty distribution ... 
	find ${D}${INST_DEST_PREFIX} -name '*.so' | xargs rm -f
	
	# fix rights of gpg keyfolder
	chmod -R 700 ${D}${INST_DEST_PREFIX}/etc/homepilot/2/gpg

	# Install all the init-scripts
	# 1 Create all the folders
	install -d ${D}${SVC_SERVICES}/dfs
	install -d ${D}${SVC_SERVICES}/hp
	install -d ${D}${SVC_SERVICES}/jetty
	install -d ${D}${SVC_SERVICES}/z-way

	# 2 Move all the run-files
	install -m 0755 ${WORKDIR}/dfs ${D}${SVC_SERVICES}/dfs/run
	install -m 0755 ${WORKDIR}/hp ${D}${SVC_SERVICES}/hp/run
	install -m 0755 ${WORKDIR}/jetty ${D}${SVC_SERVICES}/jetty/run
	install -m 0755 ${WORKDIR}/z-way ${D}${SVC_SERVICES}/z-way/run

	# 3 Disable all but hp
	touch ${D}${SVC_SERVICES}/dfs/down
	touch ${D}${SVC_SERVICES}/jetty/down
	touch ${D}${SVC_SERVICES}/z-way/down
}

FILES_${PN} += "/opt/homepilot \
		${SVC_SERVICES}/dfs \
		${SVC_SERVICES}/hp \
		${SVC_SERVICES}/jetty \
		${SVC_SERVICES}/z-way \
		"

RDEPENDS_${PN} += "at"
RDEPENDS_${PN} += "gnupg"
