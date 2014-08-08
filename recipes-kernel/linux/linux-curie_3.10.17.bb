# Copyright (C) 2014 Shanghai Zhixing Information Technology Co.Ltd

SUMMARY = "Linux Kernel for Curie Board"
DESCRIPTION = "Linux Kernel for Curie Board"

require recipes-kernel/linux/linux-imx.inc
require recipes-kernel/linux/linux-dtb.inc

DEPENDS += "lzop-native bc-native"

SRCBRANCH = "imx_3.10.17_1.0.0_ga"
SRCREV = "232293e0abb46639e188ab9d8643f1dbf94534f6"
LOCALVERSION = "-1.0.0_ga+curie"

# patches for curie
SRC_URI += "file://0001-add-dts-files-for-imx6q-curie-board.patch \
	    file://0002-add-usb-host-otg-support-for-curie-board.patch \
	    file://0003-add-ethernet-support-for-curie-board.patch \
	    file://0004-add-i2c-bus-devices-for-curie-board.patch \
	    file://0005-add-power-control.patch \
	    file://0006-add-sdhci-support-for-curie-board.patch \
	    file://0007-add-sata-support-for-curie-board-imx6q-only.patch \
	    file://0008-enable-wifi-module-for-curie-board.patch \
	    file://0009-add-led-supports-for-curie-board.patch \
	    file://0010-add-user-button-for-curie-board.patch \
	    file://0011-add-support-of-hdmi-gpu-and-vpu-for-curie-board.patch \
	    file://0012-add-support-of-spdif-for-curie-board.patch \
	    file://0013-add-reset-control-for-ethernet-phy-of-curie-board.patch \
	    file://0014-add-support-for-imx6-duallite-curie-board.patch \
	    file://0015-change-default-fb-bpp-to-32-otherwise-the-gpu-test-o.patch \
           "

SRC_URI += "file://0001-Add-directory-inclusion-for-coming-rtl8189es-drv.patch \
            file://0002-import-rtl8189es-driver.patch \
            file://0003-Add-platform-specific-modifications-for-Curie.patch \
           "

COMPATIBLE_MACHINE = "(curie)"


