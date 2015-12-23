DESCRIPTION = "pvr addons for XBMC"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

inherit autotools 

SRC_URI = "git://github.com/opdenkamp/xbmc-pvr-addons.git;rev=c2f8ea7223e2879c934c4c06b025313e418587c7;branch=helix \
	  file://patch-vpath_json.diff \
	  "
CACHED_CONFIGUREVARS = "ac_cv_lib_GL_main=no"
#CACHED_CONFIGUREVARS = "ac_cv_lib_GL_main=no ac_cv_lib_GLESv2_main=no"
#DEPENDS = "virtual/libgl virtual/libgles2"
DEPENDS = "virtual/libgles2"
S = "${WORKDIR}/git"

PR = "r2"

#EXTRA_OECONF="--prefix=/imx6/kodi"
prefix="/imx6/kodi"
exec_prefix="/imx6/kodi"

FILES_${PN} += "/imx6/kodi"
FILES_${PN}-dbg += "/imx6/kodi/lib/kodi/addons/*/.debug/ \
                    /imx6/kodi/lib/pvr.*/.debug/"
                               
