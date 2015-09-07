#@TYPE: Machine
#@DESCRIPTION: Machine configuration for various different Marvell Kirkwood based devices

TARGET_ARCH = "arm"

OVERLAY = "overlay"
OVERLAYFS = "#overlayfs"
UNIONFS = "#unionfs"
UNION_SHADOWS=".shadow/.etc .shadow/.home .work/.etc .work/.home"

SERIAL_CONSOLE = "115200 ttyS0"
KERNEL_IMAGETYPE = "zImage"
KERNEL_DEVICETREE = "kirkwood-guruplug-server-plus.dtb"

UBOOT_MACHINE = "guruplug_config"
UBOOT_SUFFIX = "kwb"
UBOOT_MAKE_TARGET = "u-boot.kwb"
UBOOT_ENTRYPOINT = "0x800000"
UBOOT_LOADADDRESS = "0x800000"
UBOOT_FDTADDRESS = "0x700000"

require conf/machine/include/tune-arm926ejs.inc
