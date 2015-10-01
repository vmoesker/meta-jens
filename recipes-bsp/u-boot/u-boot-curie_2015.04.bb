# Copyright (C) 2015 Shanghai Zhixing Information Technology Co.Ltd

require recipes-bsp/u-boot/u-boot-curie.inc

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRCBRANCH = "curie_v2015.04_3.14.38_6ul_ga"
REV = "6717e63130518d7a67f3781d6e9dee6cf643ba50"

SRCREPO = "rehsack"

SRC_URI += "file://bootsettings.patch \
	   "
