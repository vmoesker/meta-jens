DESCRIPTION = "RDM Zway BLOB" 
HOMEPAGE = "http://www.rademacher.de"
LICENSE = "commercial"
LIC_FILES_CHKSUM = "file://${THISDIR}/files/license.txt;md5=3ebe3464e841ddbf115af1f7019017c5"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PV = "1.7.3"
PR = "72"
SRC_URI = "http://internal.rdm.local/blobs/rdm-z-way-server-v${PV}-R1.tgz \
           file://z-way-server \
	   file://config.xml"

S = "${WORKDIR}/z-way-http-test"
S2 = "${WORKDIR}/z-get-tty"

INST_DEST_PREFIX="/opt/z-way"
CONF_DEST_PREFIX="/home/homepilot/z-way"

# Do not start Z-Way on boot. HP will do this for you.
# inherit update-rc.d 

do_install() {
        # create target install folders
        install -d ${D}${INST_DEST_PREFIX}
	install -d ${D}${sysconfdir}

        # Extract tarball into INST_DEST_PREFIX dir of target
	(cd ${S} && tar cf - .) | (cd ${D}${INST_DEST_PREFIX} && tar xf -)
	(cd ${S2} && tar cf - z-get-tty-config ZDDX.indxml z-get-tty) | (cd ${D}${INST_DEST_PREFIX} && tar xf -)
	rm -f ${D}${INST_DEST_PREFIX}/.gitignore \
	      ${D}${INST_DEST_PREFIX}/htdocs/z-way-ha/.gitignore \
	      ${D}${INST_DEST_PREFIX}/htdocs/expert/.git \
	      ${D}${INST_DEST_PREFIX}/htdocs/expert/.gitignore \
	      ${D}${INST_DEST_PREFIX}/htdocs/z-way-ha-tv/.git \
	      ${D}${INST_DEST_PREFIX}/htdocs/z-way-ha-tv/.gitignore
	
	# Move config directory into CONF_DEST_PREFIX dir of target
	install -o homepilot -g users -d ${D}${CONF_DEST_PREFIX}
	mv ${D}${INST_DEST_PREFIX}/config ${D}${CONF_DEST_PREFIX}
	chown -R homepilot:users ${D}${CONF_DEST_PREFIX}

	# Create link to zddx configuration
	(cd ${D}${CONF_DEST_PREFIX}/config && rm -rf zddx && ln -sf /home/homepilot/.homepilot/z-way zddx)

	# Move tty-config directory into sysconfig dir of target
	mv ${D}${INST_DEST_PREFIX}/z-get-tty-config ${D}${sysconfdir}
	(cd ${D}${INST_DEST_PREFIX} && ln -s ${sysconfdir}/z-get-tty-config)

	# Install custom config file
	install ${WORKDIR}/config.xml ${D}${sysconfdir}/z-way.conf
	rm -f ${D}${INST_DEST_PREFIX}/config.xml
	ln -sf ${sysconfdir}/z-way.conf ${D}${INST_DEST_PREFIX}/config.xml 

	# Clean-up ZDDX device files
	cd ${D}${INST_DEST_PREFIX}/ZDDX/
	rm *.xml
	python2 MakeIndex.py
}

INSANE_SKIP_${PN} += "already-stripped"

FILES_${PN} += "${INST_DEST_PREFIX} \
        ${CONF_DEST_PREFIX} \
	${sysconfdir}"
FILES_${PN}-dbg += "${INST_DEST_PREFIX}/.debug ${INST_DEST_PREFIX}/*/.debug"
FILES_${PN}-dev += "${INST_DEST_PREFIX}/*/*.h"
FILES_${PN}-staticdev += "${INST_DEST_PREFIX}/*/*.a"

RDEPENDS_${PN} = "libarchive \
        libxml2 \
        openssl \
        yajl \
        curl \
        v8 \
        zlib"

INITSCRIPT_NAME = "z-way-server"
INITSCRIPT_PARAMS = "start 99 S . stop 20 0 1 6 ."
