DESCRIPTION = "RDM Zway BLOB" 
HOMEPAGE = "http://www.rademacher.de"
LICENSE = "commercial"
LIC_FILES_CHKSUM = "file://${THISDIR}/files/license.txt;md5=3ebe3464e841ddbf115af1f7019017c5"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PV = "0.001"
SRC_URI = "http://internal.rdm.local/blobs/rdm-zway-0.001.tar.gz \
           file://z-way-server"

S = "${WORKDIR}/rdm"

INST_DEST_PREFIX="/opt/z-way"

inherit update-rc.d

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

        # Clean-up messed up so-files from Jetty distribution ... 
}

INSANE_SKIP_${PN} += "already-stripped"

FILES_${PN} += "${INST_DEST_PREFIX}"
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
