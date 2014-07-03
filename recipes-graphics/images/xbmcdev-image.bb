SUMMARY = "An image for HP2 using FB only."
LICENSE = "MIT"
PR="r0"

LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit core-image distro_features_check

CONFLICT_DISTRO_FEATURES = "directfb x11 wayland"

# SOC_EXTRA_IMAGE_FEATURES ?= "tools-testapps"
SOC_EXTRA_IMAGE_FEATURES=""

# Add extra image features
EXTRA_IMAGE_FEATURES += " \
    ${SOC_EXTRA_IMAGE_FEATURES} \
    nfs-server \
    tools-debug \
    tools-profile \
"

IMAGE_INSTALL += " \
	${CORE_IMAGE_BASE_INSTALL} \
	packagegroup-core-basic \
	libsdl \
	libxml2 \
	libftdi \
	libcec \
	dropbear \
	packagegroup-core-sdk \
	xbmc \
	procps \
	util-linux-mount \
	libntfs-3g \
	ntfsprogs \
	ntfs-3g \
	nfs-utils-client \
	ntp \
	ntp-utils \
	util-linux \
	firmware-imx-vpu-imx6d \
	ethtool \
	xz \
	lsof \
	fbida \
	imagemagick \
"

export IMAGE_BASENAME = "xbmcdev-image"

