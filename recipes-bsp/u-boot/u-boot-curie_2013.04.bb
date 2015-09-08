# Copyright (C) 2014-2015 Shanghai Zhixing Information Technology Co.Ltd

require recipes-bsp/u-boot/u-boot-curie.inc

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=1707d6db1d42237583f50183a5651ecb"

SRCBRANCH = "curie_v2013.04_3.10.17"
REV = "f0ef55031b9cff41ac2cea37a9955672ed6a051d"

SRC_URI += "file://bootsettings.patch \
	   "
