DESCRIPTION = "This package contains the Config::Any module with friends. \
Config::Any provides a facility for Perl applications and libraries to load \
configuration data from multiple different file formats. It supports XML, \
YAML, JSON, Apache-style configuration, Windows INI files, and even Perl \
code."

SECTION = "libs"
LICENSE = "Artistic-1.0 | GPL-1.0+"
PR = "r0"

LIC_FILES_CHKSUM = "file://README;beginline=142;endline=147;md5=c0389a2f70bdf583ae550ecc3ca7cbeb"

DEPENDS += "perl"

SRC_URI = "http://www.cpan.org/authors/id/B/BR/BRICAS/Config-Any-${PV}.tar.gz"

SRC_URI[md5sum] = "bf58a5cbd8b809886bd0459986e55ad7"
SRC_URI[sha256sum] = "710f8fc8f9414205cb58399bfbb4d9aaf7883f8ce046cee22913f6818795c61a"

S = "${WORKDIR}/Config-Any-${PV}"

EXTRA_CPANFLAGS = "EXPATLIBPATH=${STAGING_LIBDIR} EXPATINCPATH=${STAGING_INCDIR}"

inherit cpan

do_compile() {
	export LIBC="$(find ${STAGING_DIR_TARGET}/${base_libdir}/ -name 'libc-*.so')"
	cpan_do_compile
}

BBCLASSEXTEND = "native"
