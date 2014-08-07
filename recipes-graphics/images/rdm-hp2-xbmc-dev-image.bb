SUMMARY = "An image for HP2 using FB only."
LICENSE = "MIT"
PR="r0"

LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

include recipes-core/images/core-image-base.bb
include zway.inc

inherit core-image distro_features_check

CONFLICT_DISTRO_FEATURES = "directfb wayland"

# SOC_EXTRA_IMAGE_FEATURES ?= "tools-testapps"
SOC_EXTRA_IMAGE_FEATURES?=""

# Add extra image features
EXTRA_IMAGE_FEATURES += " \
    ${SOC_EXTRA_IMAGE_FEATURES} \
    nfs-server \
    tools-debug \
    tools-profile \
    dbg-pkgs \
    dev-pkgs \
"

IMAGE_INSTALL += " \
	${CORE_IMAGE_BASE_INSTALL} \
	${MACHINE_FIRMWARE} \
	${ZWAY_DEPS} \
	tcpdump \
	packagegroup-core-basic \
	ifplugd \
	udev-extraconf \
	valgrind \
	strace \
	libsdl \
	libxml2 \
	libftdi \
	libcec \
	dropbear \
	libstatgrab \
	fsl-rc-local \
	packagegroup-core-sdk \
	xbmc \
	xbmc-rdm-hp-addon \
	xbmc-image-custom-rfs \
	openjdk-7-jdk \
	procps \
	util-linux-mount \
	libntfs-3g \
	ntfsprogs \
	ntfs-3g \
	nfs-utils-client \
	ntp \
	ntp-utils \
	util-linux \
	ethtool \
	xz \
	nano \
	lsof \
	imagemagick \
"

export IMAGE_BASENAME = "rdm-hp2-xbmc-dev-image"
