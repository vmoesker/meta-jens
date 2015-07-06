DESCRIPTION = "pvr addons for XBMC"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

inherit autotools 

SRC_URI = "git://github.com/opdenkamp/xbmc-pvr-addons.git;rev=c2f8ea7223e2879c934c4c06b025313e418587c7;branch=helix"
CACHED_CONFIGUREVARS = "ac_cv_lib_GL_main=no"
S = "${WORKDIR}/git"

PR = "r2"

EXTRA_OECONF="--prefix=/imx6/kodi"

FILES_${PN} += "/imx6/kodi"
FILES_${PN}-dbg += "/imx6/kodi/lib/kodi/addons/*/.debug/"
