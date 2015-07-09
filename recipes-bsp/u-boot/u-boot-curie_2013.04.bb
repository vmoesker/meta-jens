# Copyright (C) 2014-2015 Shanghai Zhixing Information Technology Co.Ltd

require recipes-bsp/u-boot/u-boot-curie.inc

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=1707d6db1d42237583f50183a5651ecb"

SRCBRANCH = "curie_v2013.04_3.10.17"
SRCREV = "20d0e998b0978f87d440f1c46b76116d11ba3128"

SRC_URI += "file://bootsettings.patch \
	   "
