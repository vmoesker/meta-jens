DESCRIPTION = "RDM Zway BLOB"
HOMEPAGE = "http://www.rademacher.de"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://${THISDIR}/files/license.txt;md5=3ebe3464e841ddbf115af1f7019017c5"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

exec_prefix="/opt/z-way"

SRC_URI = "\
    file://config.xml \
    file://configjson-06b2d3b23dce96e1619d2b53d6c947ec.json \
    file://z-way.run \
    file://z-way-log.run \
"

SRC_URI_append_mx6 = "\
    git://git@bitbucket.org/rdm-dev/rdm-z-way-server;protocol=ssh;branch=z-way-server-HomePilot2-v2.2.1-rc4 \
"
SRCREV_mx6 = "eea4d03b7fe430bd3e69764d403f8dcd8c6de77a"

SRC_URI_append_kirkwood = "\
    git://git@bitbucket.org/rdm-dev/rdm-z-way-server.git;protocol=ssh;branch=z-way-server-HomePilot1-v2.2.1-rc4 \
"
SRCREV_kirkwood = "1ae4441f5ec6e0673afef9f10ab290ac04c8c1c8"

DEPENDS = "v8 hp2-base"
RDEPENDS_${PN} += "daemontools"
RDEPENDS_${PN} += "hp2-base"
RDEPENDS_${PN} += "libarchive"
RDEPENDS_${PN} += "libcurl"
RDEPENDS_${PN} += "libxml2"
RDEPENDS_${PN} += "openssl"
RDEPENDS_${PN} += "v8"
RDEPENDS_${PN} += "yajl"
RDEPENDS_${PN} += "zlib"

S = "${WORKDIR}/git/z-way-server"

INST_DEST_PREFIX="${exec_prefix}"
CONF_DEST_PREFIX="/home/homepilot/z-way"

HOMEPILOT_USER="homepilot"
SVC_SERVICES="${sysconfdir}/daemontools/service"

ZW_TTY_DEVICE = "/dev/ttyZWave"

do_install() {
        # create target install folders
        install -d ${D}${INST_DEST_PREFIX}
	install -d ${D}${sysconfdir}
	install -d -o homepilot -g users ${D}${CONF_DEST_PREFIX}
	install -d -o homepilot -g users ${D}${CONF_DEST_PREFIX}/storage

        # Extract tarball into INST_DEST_PREFIX dir of target
    (cd ${S} && tar cf - *) | (cd ${D}${INST_DEST_PREFIX} && tar xf -)
    rm -f ${D}${INST_DEST_PREFIX}/htdocs/expert/.gitignore

	# Move config directory into CONF_DEST_PREFIX dir of target
	mv ${D}${INST_DEST_PREFIX}/config ${D}${CONF_DEST_PREFIX}

	# Create link to config
	(cd ${D}${INST_DEST_PREFIX} && ln -s ${CONF_DEST_PREFIX}/config)
	(cd ${D}${INST_DEST_PREFIX}/automation && ln -s ${CONF_DEST_PREFIX}/storage)

	# Create link to zddx configuration
	(cd ${D}${CONF_DEST_PREFIX}/config && rm -rf zddx && ln -sf /home/homepilot/.homepilot/z-way zddx)

	# Move tty-config directory into sysconfig dir of target
	mv ${D}${INST_DEST_PREFIX}/z-get-tty-config ${D}${sysconfdir}
	(cd ${D}${INST_DEST_PREFIX} && ln -s ${sysconfdir}/z-get-tty-config)

	# Install custom config file
	install ${WORKDIR}/config.xml ${D}${sysconfdir}/z-way.conf
	rm -f ${D}${INST_DEST_PREFIX}/config.xml
	ln -sf ${sysconfdir}/z-way.conf ${D}${INST_DEST_PREFIX}/config.xml

	# Edit config file
	cp ${WORKDIR}/configjson-06b2d3b23dce96e1619d2b53d6c947ec.json ${D}${CONF_DEST_PREFIX}/storage
	sed -i -e 's#@ZW_TTY_DEVICE[@]#${ZW_TTY_DEVICE}#' ${D}${CONF_DEST_PREFIX}/storage/configjson-06b2d3b23dce96e1619d2b53d6c947ec.json

	# Clean-up ZDDX device files
	cd ${D}${INST_DEST_PREFIX}/ZDDX/
	rm *.xml
	python2 MakeIndex.py

    # Configure startup
    install -d ${D}${SVC_SERVICES}/z-way
    install -d ${D}${SVC_SERVICES}/z-way/log
    install -m 0755 ${WORKDIR}/z-way.run ${D}${SVC_SERVICES}/z-way/run
    install -m 0755 ${WORKDIR}/z-way-log.run ${D}${SVC_SERVICES}/z-way/log/run
    sed -i -e "s,@ZWAY_BASE@,${INST_DEST_PREFIX},g" -e "s,@HOMEPILOT_USER@,${HOMEPILOT_USER},g" ${D}${SVC_SERVICES}/z-way/run
    touch ${D}${SVC_SERVICES}/z-way/down

	chown -R root:root ${D}${INST_DEST_PREFIX} ${D}${sysconfdir}
	chown -R homepilot:users ${D}${CONF_DEST_PREFIX}
}

INSANE_SKIP_${PN} += "already-stripped"

FILES_${PN}_append = "\
	${INST_DEST_PREFIX}/config \
	${INST_DEST_PREFIX}/z-way-server \
	${INST_DEST_PREFIX}/z-get-tty-config \
	${INST_DEST_PREFIX}/z-get-tty \
	${INST_DEST_PREFIX}/config.xml \
	${INST_DEST_PREFIX}/ChangeLog \
	${INST_DEST_PREFIX}/z-cfg-update \
	${INST_DEST_PREFIX}/automation \
	${INST_DEST_PREFIX}/htdocs \
	${INST_DEST_PREFIX}/translations \
	${INST_DEST_PREFIX}/libs/lib*.so \
	${INST_DEST_PREFIX}/modules/mod*.so \
	${INST_DEST_PREFIX}/ZDDX \
	${CONF_DEST_PREFIX} \
	${sysconfdir}"
FILES_${PN}-dbg += "${INST_DEST_PREFIX}/.debug ${INST_DEST_PREFIX}/*/.debug"
#FILES_${PN}-dev += "${INST_DEST_PREFIX}/*/*.h"
FILES_${PN}-staticdev += "${INST_DEST_PREFIX}/*/*.a"
