# Copyright (C) 2015 Jens Rehsack <sno@netbsd.org>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "An updater image for HP1"
LICENSE = "MIT"
PR="r0"

LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

COMPATIBLE_MACHINE = "(bohr-update)"

include recipes-core/images/core-image-base.bb
include rdm.inc

inherit core-image distro_features_check rootdev-check

COMPAT_ROOT_DEVS = "usb"

# Add extra image features
EXTRA_IMAGE_FEATURES += " \
    ssh-server-dropbear \
"

IMAGE_INSTALL += " \
	${CORE_IMAGE_BASE_INSTALL} \
	${RDM_ESSENTIALS} \
	${RECOVER_INSTALL} \
	bohr-update-image \
	init-update-stick \
"

export IMAGE_BASENAME = "rdm-update-image"
