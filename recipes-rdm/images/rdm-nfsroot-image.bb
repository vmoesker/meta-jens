SUMMARY = "RDM Core Image"
LICENSE = "MIT"
PR="r0"

LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

include recipes-core/images/core-image-base.bb
include rdm.inc

inherit core-image distro_features_check rootdev-check

COMPAT_ROOT_DEVS = "nfs"

RDM_BASE_INSTALL_remove = "\
	cronie \
	dosfstools \
	sudo \
	nginx \
	nginx-legal \
	nginx-manual \
	libstatgrab \
	crda \
	lsof \
	logrotate \
	boot-fsck \
	initoverlay-${WANTED_ROOT_DEV} \
	system-image-update \
	hp2sm \
	wifi-fallback \
"
IMAGE_INSTALL += " \
	${CORE_IMAGE_BASE_INSTALL} \
	${RDM_BASE_INSTALL} \
	${RECOVER_INSTALL} \
	rdm-data-nfs \
"

export IMAGE_BASENAME = "rdm-nfsroot-image"
