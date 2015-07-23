FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot-bohr-${PV}:"

SRC_URI += "file://bootsettings.patch \
	    file://fw_env.config.patch \
	   "
