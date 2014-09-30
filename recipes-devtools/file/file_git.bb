SUMMARY = "File classification tool"
DESCRIPTION = "File attempts to classify files depending \
on their contents and prints a description if a match is found."
HOMEPAGE = "http://www.darwinsys.com/file/"
SECTION = "console/utils"

# two clause BSD
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;beginline=2;md5=6a7382872edb68d33e1a9398b6e03188"

S = "${WORKDIR}/git"

PV = "5.19"

DEPENDS = "zlib file-native"
DEPENDS_class-native = "zlib-native"

SRC_URI = "git://github.com/file/file.git;rev=768e1399dc4b303b5f7e561a122fa8fbab000895"

inherit autotools

FILES_${PN} += "${datadir}/misc/*.mgc"

do_install_append_class-native() {
	create_cmdline_wrapper ${D}/${bindir}/file \
		--magic-file ${datadir}/misc/magic.mgc
}


BBCLASSEXTEND = "native nativesdk"
