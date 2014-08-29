DESCRIPTION = "RDM Zway BLOB" 
HOMEPAGE = "http://www.rademacher.de"
LICENSE = "commercial"
LIC_FILES_CHKSUM = "file://${THISDIR}/files/license.txt;md5=3ebe3464e841ddbf115af1f7019017c5"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PV = "0.002"
SRC_URI = "http://internal.rdm.local/blobs/rdm-zway-${PV}.tar.gz \
           file://z-way-server \
	   file://config.xml"

S = "${WORKDIR}/z-way-http-test"

INST_DEST_PREFIX="/opt/z-way"

# Do not start Z-Way on boot. HP will do this for you.
# inherit update-rc.d 

do_install() {
        #create init.d directory
        install -d ${D}${sysconfdir}/init.d/

        #install init.d script and make it executable
        install -m 0755  ${WORKDIR}/z-way-server ${D}${sysconfdir}/init.d/z-way-server

        # create INST_DEST_PREFIX folder
        install -d ${D}${INST_DEST_PREFIX}

        # Extract tarball into INST_DEST_PREFIX dir of target
	(cd ${S} && tar cf - .) | (cd ${D}${INST_DEST_PREFIX} && tar xf -)
	rm -f ${D}${INST_DEST_PREFIX}/.gitignore \
	      ${D}${INST_DEST_PREFIX}/htdocs/z-way-ha/.gitignore \
	      ${D}${INST_DEST_PREFIX}/htdocs/expert/.git \
	      ${D}${INST_DEST_PREFIX}/htdocs/expert/.gitignore \
	      ${D}${INST_DEST_PREFIX}/htdocs/z-way-ha-tv/.git \
	      ${D}${INST_DEST_PREFIX}/htdocs/z-way-ha-tv/.gitignore
	
	# Install custom config file
	install ${WORKDIR}/config.xml ${D}${sysconfdir}/z-way.conf
	rm -f ${D}${INST_DEST_PREFIX}/config.xml
	ln -sf ${D}${INST_DEST_PREFIX}/config.xml ${D}${sysconfdir}/z-way.conf

	# Clean-up ZDDX device files
	cd ${D}${INST_DEST_PREFIX}/ZDDX/
	rm *.xml
	python2 MakeIndex.py

	# Clean-up config-files 
	rm -rf ${D}${INST_DEST_PREFIX}/config/zddx
	ln -sf /home/root/.homepilot/zway ${D}${INST_DEST_PREFIX}/config/zddx


}

INSANE_SKIP_${PN} += "already-stripped"

FILES_${PN} += "${INST_DEST_PREFIX} \
	${sysconfdir}/z-way.conf"
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
