SUMMARY = "An image for HP2 using FB only."
LICENSE = "MIT"
PR="r0"

LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

include recipes-core/images/core-image-base.bb
include dev.inc
include rdm.inc
include rdm-hp2.inc

inherit core-image distro_features_check

CONFLICT_DISTRO_FEATURES = "directfb wayland"

# SOC_EXTRA_IMAGE_FEATURES ?= "tools-testapps"
SOC_EXTRA_IMAGE_FEATURES?=""

# Add extra image features
EXTRA_IMAGE_FEATURES += " \
    ${SOC_EXTRA_IMAGE_FEATURES} \
    nfs-server \
    ssh-server-dropbear \
    ${EXTRA_IMAGE_FEATURES_dev} \
"

IMAGE_INSTALL += " \
	${CORE_IMAGE_BASE_INSTALL} \
	${MACHINE_FIRMWARE} \
	${HP2_INSTALL} \
	${RDM_INSTALL} \
	${DEV_INSTALL} \
	fb-init \
	tzdata \
	tcpdump \
	ifplugd \
	libstatgrab \
	fsl-rc-local \
	procps \
	util-linux-mount \
	libntfs-3g \
	ntp \
	ntp-utils \
	util-linux \
	ethtool \
	xz \
	lsof \
"

export IMAGE_BASENAME = "rdm-hp2-dev-image"
