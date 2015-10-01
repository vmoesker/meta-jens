PREFERRED_VERSION_u-boot-curie = "2015.04"
PREFERRED_VERSION_u-boot-fw-utils-curie = "2015.04"

UBOOT_MACHINE = "mx6qcurie_config"

UBOOT_MMC_DEV-emmc = "1"
UBOOT_MMC_DEV-sd = "0"
UBOOT_MMC_DEV = "${UBOOT_MMC_DEV-${WANTED_ROOT_DEV}}"
