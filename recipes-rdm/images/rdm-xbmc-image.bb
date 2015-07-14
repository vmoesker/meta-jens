SUMMARY = "An image for HP2 using FB only."
LICENSE = "MIT"
PR="r0"

LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

include recipes-core/images/core-image-base.bb
include rdm.inc
include rdm-xbmc.inc

inherit core-image distro_features_check

# Add extra image features
EXTRA_IMAGE_FEATURES += " \
    nfs-server \
    ssh-server-dropbear \
"

IMAGE_INSTALL += " \
	${CORE_IMAGE_BASE_INSTALL} \
	${XBMC_INSTALL} \
	${RDM_INSTALL} \
"

export IMAGE_BASENAME = "rdm-xbmc-image"
