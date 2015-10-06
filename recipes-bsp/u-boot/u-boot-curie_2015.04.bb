# Copyright (C) 2015 Shanghai Zhixing Information Technology Co.Ltd

require recipes-bsp/u-boot/u-boot-curie.inc

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRCBRANCH = "curie_v2015.04_3.14.38_6ul_ga"
REV = "695a9e2d8d921eabe7816054a124e34a8b963827"

SRCREPO = "rehsack"

SRC_URI += "file://bootsettings.patch \
	   "
