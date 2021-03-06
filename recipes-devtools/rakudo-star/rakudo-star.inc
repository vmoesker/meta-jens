# Copyright (C) 2015 Jens Rehsack <sno@netbsd.org>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "The Rakudo Star distribution (aka Rakudo *) combines a Rakudo \
Perl 6 compiler with additional resources including a selection of modules."
HOMEPAGE = "http://rakudo.org/"
LICENSE = "Artistic-2.0"
SECTION = "devel"
DEPENDS = "virtual/java-native virtual/javac-native nqp-native"

DEPENDS  += "${@["nqp", "nqp-native"][(bb.data.inherits_class('native', d))]}"
RDEPENDS_${PN} = "nqp"

inherit perlnative siteinfo

SRC_URI = "http://rakudo.org/downloads/star/rakudo-star-${PV}.tar.gz"
LIC_FILES_CHKSUM = "file://LICENSE;md5=18740546821e33d23e8809da70d4a79a"

do_configure () {
    cd "${S}"
    perl Configure.pl --prefix="${prefix}" --backends=jvm --sysroot="${STAGING_DIR_TARGET}" ${@['--sdkroot "${STAGING_DIR_NATIVE}"', ""][(bb.data.inherits_class('native', d))]}
}

do_install () {
    oe_runmake 'DESTDIR=${D}' install
}
