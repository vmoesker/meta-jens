FILESEXTRAPATHS_prepend_poky := "${THISDIR}/files:"

SRC_URI += "file://psplash-allow-config-set-via-cppflags.patch"

CPPFLAGS_append = " -DPSPLASH_IMG_FULLSCREEN=1 -DPSPLASH_BACKGROUND_COLOR=0xff,0xff,0xff -DPSPLASH_BAR_BACKGROUND_COLOR=0xff,0xff,0xff "
