DESCRIPTION = "System::Image::Update helps managing updates of OS images \
in embedded systems"

SECTION = "libs"
LICENSE = "Artistic-1.0 | GPL-2.0"
PR = "r0"

MAINTAINER=	"Poky <poky@yoctoproject.org>"
HOMEPAGE=	"https://metacpan.org/release/Unix-Statgrab"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Artistic-1.0;md5=cda03bbdc3c1951996392b872397b798 \
file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "git://github.com/rehsack/System-Image-Update.git"
SRCREV = "09a3907ea9e2412f6ca3ae16aa503078fb1022e7"

RDEPENDS_${PN} += "moo-perl"
RDEPENDS_${PN} += "moox-cmd-perl"
RDEPENDS_${PN} += "moox-options-perl"
RDEPENDS_${PN} += "moox-configfromfile-perl"
RDEPENDS_${PN} += "net-async-http-perl"

S = "${WORKDIR}/git"

inherit cpan

BBCLASSEXTEND = "native"
