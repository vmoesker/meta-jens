DESCRIPTION = "LIBNFS is a client library for accessing NFS shares over a network."

LICENSE = "LGPL-2.1+ & GPL-3.0+ & BSD-2-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=0019ace2726c6f181791a9ac04c7ac6a"

PR = "r0"

SRC_URI = "git://github.com/sahlberg/libnfs.git;protocol=https;tag=e775160243f9081b6bf96ce5b6ed307df2f2e157"
S = "${WORKDIR}/git"


inherit autotools lib_package pkgconfig


