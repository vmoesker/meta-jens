SUMMARY = "RDM Core Image"
LICENSE = "MIT"
PR="r0"

LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

include recipes-core/images/core-image-base.bb
include rdm.inc

inherit core-image distro_features_check rootdev-check

COMPAT_ROOT_DEVS = "nfs"

IMAGE_INSTALL += " \
	${CORE_IMAGE_BASE_INSTALL} \
	${RDM_ESSENTIALS} \
	${RECOVER_INSTALL} \
	rdm-data-nfs \
"

export IMAGE_BASENAME = "rdm-nfsroot-image"
