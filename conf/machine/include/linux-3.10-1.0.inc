#PREFERRED_VERSION_linux-curie = "3.10.17"
PREFERRED_VERSION_linux-curie = "3.10.80"

UBOOT_ENTRYPOINT = "0x17800000"
UBOOT_LOADADDRESS = "0x12000000"
UBOOT_FDTADDRESS = "0x18000000"

PREFERRED_VERSION_firmware-imx = "3.10.17-1.0.0"
PREFERRED_VERSION_imx-lib = "3.10.17-1.0.0"
PREFERRED_VERSION_imx-vpu = "3.10.17-1.0.0"
PREFERRED_VERSION_gpu-viv-bin-mx6q = "3.10.17-1.0.1-hfp"
PREFERRED_VERSION_gpu-viv-bin-mx6dl = "3.10.17-1.0.1-hfp"

OVERLAY = "#overlay"
OVERLAYFS = "overlayfs"
UNIONFS = "#unionfs"
UNION_SHADOWS=".shadow/.etc .shadow/.home"

# 2.6.37 and later kernels use OMAP_SERIAL, ttyO2
# earlier kernels use ttyS2
SERIAL_CONSOLE = "115200 ttymxc0"

# kernel device tree
KERNEL_DEVICETREE = "imx6q-curie.dtb"
KERNEL_MMC_BASE_DEV = "2"

KERNEL_MMC_DEV = "${@${KERNEL_MMC_BASE_DEV}-${SDCARD_IMAGE}}"
KERNEL_EMMC_DEV = "${KERNEL_MMC_BASE_DEV}"
KERNEL_SD_DEV = "${@${KERNEL_MMC_BASE_DEV}-1}"
