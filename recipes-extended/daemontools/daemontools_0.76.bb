DESCRIPTION = "daemontools is a collection of tools for managing UNIX services"
HOMEPAGE = "http://cr.yp.to/daemontools.html"

LICENSE = "PD"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/PD;md5=b3597d12946881e13cb3b548d1173851"

inherit djbware

FILESEXTRAPATHS_prepend := "${THISDIR}/${PV}-${PV}:"

S = "${WORKDIR}/admin/${PN}-${PV}"

SRC_URI = "http://cr.yp.to/daemontools/daemontools-0.76.tar.gz \
           file://error.h-use-errno.h.patch \
           file://Makefile-no-run-crosscompiled.patch \
           file://svscanboot-target-fs-adoptions.patch \
          "

SRC_URI[md5sum] = "1871af2453d6e464034968a0fbcb2bfc"
SRC_URI[sha256sum] = "a55535012b2be7a52dcd9eccabb9a198b13be50d0384143bd3b32b8710df4c1f"

do_install () {
    djbware_do_install

    # prepare for installing base-dir for services
    install -d 0755 ${D}/etc/daemontools/service
}
