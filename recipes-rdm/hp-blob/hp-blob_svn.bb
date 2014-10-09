DESCRIPTION = "RDM HP BLOB" 
HOMEPAGE = "http://www.rademacher.de"
LICENSE = "commercial"
LIC_FILES_CHKSUM = "file://${THISDIR}/files/license.txt;md5=3ebe3464e841ddbf115af1f7019017c5"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

DEPENDS = "tar zway-blob "
RDEPENDS_${PN} += "daemontools"
RDEPENDS_${PN} += "at"
RDEPENDS_${PN} += "gnupg"
RDEPENDS_${PN} += "service-df"
RDEPENDS_${PN} += "zway-blob"

inherit useradd

PV = "4.0.0.0"
SRC_URI = "svn://192.168.1.186/svn/EW_Prj/trunk/;protocol=http;module=HomePilot_Blob;rev=3738 \
                file://dfservice \
                file://homepilot \
                file://homepilot-network-manager \
                file://jetty \
                file://z-way \
                file://init_appdir.sh \
                file://gpg/pubring.gpg \
                file://gpg/random_seed \
                file://gpg/secring.gpg \
                file://gpg/trustdb.gpg \
"

S = "${WORKDIR}/HomePilot_Blob"

HOMEPILOT_USER_HOME = "/home/homepilot"

ZWAY_DEST_PREFIX="/opt/z-way"
INST_DEST_PREFIX="/opt/homepilot"
TARBALL_NAME="hp-dist_${PV}.tar.gz"

SVC_SERVICES="${sysconfdir}/daemontools/service"

do_install() {
        # create homepilot user dir
	install -o homepilot -g users -m 0755 -d ${D}${HOMEPILOT_USER_HOME}
	install -o homepilot -g users -m 0755 -d ${D}${HOMEPILOT_USER_HOME}/bin
	install -o homepilot -g users -m 0755 ${WORKDIR}/init_appdir.sh ${D}${HOMEPILOT_USER_HOME}/bin/init_appdir.sh

	# create INST_DEST_PREFIX folder
	install -d ${D}${INST_DEST_PREFIX}

	# Extract tarball into INST_DEST_PREFIX dir of target
	tar xzf ${S}/${TARBALL_NAME} -C ${D}${INST_DEST_PREFIX}

	# Clean-up messed up so-files from Jetty distribution ... 
	find ${D}${INST_DEST_PREFIX} -name '*.so' | xargs rm -f

	# add link for zddx (z-way) for local control
	install -d ${D}${ZWAY_DEST_PREFIX}/config
	ln -sf ${HOMEPILOT_USER_HOME}/.homepilot/zway ${D}${ZWAY_DEST_PREFIX}/config/zddx

        # Install some gpg stuff
        install -o homepilot -g users -m 0700 -d ${D}${HOMEPILOT_USER_HOME}/.gpg
        install -o homepilot -g users -m 0600 ${WORKDIR}/gpg/pubring.gpg ${D}${HOMEPILOT_USER_HOME}/.gpg/pubring.gpg
        install -o homepilot -g users -m 0600 ${WORKDIR}/gpg/random_seed ${D}${HOMEPILOT_USER_HOME}/.gpg/random_seed
        install -o homepilot -g users -m 0600 ${WORKDIR}/gpg/secring.gpg ${D}${HOMEPILOT_USER_HOME}/.gpg/secring.gpg
        install -o homepilot -g users -m 0600 ${WORKDIR}/gpg/trustdb.gpg ${D}${HOMEPILOT_USER_HOME}/.gpg/trustdb.gpg


	# Install all the init-scripts
	# 1 Create all the folders
	install -d ${D}${SVC_SERVICES}/homepilot-network-manager
	install -d ${D}${SVC_SERVICES}/dfservice
	install -d ${D}${SVC_SERVICES}/homepilot
	install -d ${D}${SVC_SERVICES}/jetty
	install -d ${D}${SVC_SERVICES}/z-way

	# 2 Move all the run-files
	install -m 0755 ${WORKDIR}/homepilot-network-manager ${D}${SVC_SERVICES}/homepilot-network-manager/run
	install -m 0755 ${WORKDIR}/dfservice ${D}${SVC_SERVICES}/dfservice/run
	install -m 0755 ${WORKDIR}/homepilot ${D}${SVC_SERVICES}/homepilot/run
	install -m 0755 ${WORKDIR}/jetty ${D}${SVC_SERVICES}/jetty/run
	install -m 0755 ${WORKDIR}/z-way ${D}${SVC_SERVICES}/z-way/run

	# 3 Disable all but hp
	touch ${D}${SVC_SERVICES}/dfservice/down
	touch ${D}${SVC_SERVICES}/jetty/down
	touch ${D}${SVC_SERVICES}/z-way/down
}

USERADD_PACKAGES = "${PN}"

USERADD_PARAM_${PN} = "-u 800 -d ${HOMEPILOT_USER_HOME} -g users -G dialout -r -m -s /bin/sh homepilot"

FILES_${PN} += "${INST_DEST_PREFIX} \
                ${ZWAY_DEST_PREFIX} \
		${SVC_SERVICES} \
		${HOMEPILOT_USER_HOME} \
"
