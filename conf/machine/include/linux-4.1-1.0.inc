PREFERRED_VERSION_linux-curie = "4.1"

UBOOT_ENTRYPOINT = "0x17800000"
UBOOT_LOADADDRESS = "0x12000000"
UBOOT_FDTADDRESS = "0x18000000"

PREFERRED_VERSION_firmware-imx = "5.0"
PREFERRED_VERSION_imx-lib = "5.1"
PREFERRED_VERSION_imx-vpu = "5.4.31"
PREFERRED_VERSION_gpu-viv-bin-mx6q = "5.0.11.p6.3-hfp"
PREFERRED_VERSION_gpu-viv-bin-mx6dl = "5.0.11.p6.3-hfp"

OVERLAY = "overlay"
OVERLAYFS = "#overlayfs"
UNIONFS = "#unionfs"
UNION_SHADOWS=".shadow/.etc .shadow/.home"

# 2.6.37 and later kernels use OMAP_SERIAL, ttyO2
# earlier kernels use ttyS2
SERIAL_CONSOLE = "115200 ttymxc0"

# kernel device tree
KERNEL_DEVICETREE = "imx6q-curie.dtb"
KERNEL_MMC_BASE_DEV = "3"

KERNEL_MMC_DEV-emmc = "3"
KERNEL_MMC_DEV-sd = "2"
KERNEL_MMC_DEV = "${KERNEL_MMC_DEV-${WANTED_ROOT_DEV}}"
