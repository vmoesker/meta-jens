
include linux-curie.inc

SRCREV = "0c58d0f15879856dd750223abeeb0410a0891ca2"
LOCALVERSION = "-4.1.0+curie"

# Patches need for Yocto and not applied by Freescale when doing 4.1.0 branch
SRC_URI += "file://drm-vivante-Add-00-sufix-in-returned-bus-Id.patch \
            file://epdc-Rename-mxcfb_epdc_kernel.h-to-mxc_epdc.h.patch \
            file://0001-perf-tools-Fix-getrusage-related-build-failure-on-gl.patch \
           "
SRC_URI += "file://0001-Fix-linking-errors-when-only-mx6q_sabresd-is-selecte.patch \
            file://0002-Create-a-new-board-MX6Q_CURIE-for-Curie.patch \
            file://0003-addition-of-USB-Host-OTG-support-for-Curie.patch \
            file://0004-add-fec-init-for-Curie.patch \
            file://0005-remove-unused-UART3.patch \
            file://0006-select-correct-usb_otg_id-for-Curie.patch \
            file://0007-remove-unused-variable-ret-to-avoid-warning-in-Curie.patch \
            file://0008-addition-of-RTL8211E-phy-driver.patch \
            file://0009-reset-phy-before-fec_init.patch \
            file://0010-rtc-init-for-Curie.patch \
            file://0011-i2c-init-for-Curie.patch \
            file://0012-PMIC-DVFS-init-for-Curie.patch \
            file://0013-bus-freq-driver-init.patch \
            file://0014-addition-of-low-level-PM-driver.patch \
            file://0015-thermal-driver-init-for-Curie.patch \
            file://0016-addition-of-vmmc-fixed-regulator.patch \
            file://0017-remove-unused-header-sabresd_battery.h-for-Curie.patch \
            file://0018-addition-of-SDHC-driver-for-Curie.patch \
            file://0019-SATA-driver-init-for-Curie.patch \
            file://0020-OCOTP-driver-init-for-Curie.patch \
            file://0021-enabling-Wi-Fi-module-for-Curie.patch \
            file://0022-gpio-led-driver-for-Curie.patch \
            file://0023-button-driver-init-for-Curie.patch \
            file://0024-watchdog-driver-init-for-Curie.patch \
            file://0025-sdma-driver-init-for-Curie.patch \
            file://0026-hdmi-ipu-vpu-gpu-driver-for-Curie.patch \
            file://0027-spdif-driver-init-for-Curie.patch \
            file://0028-Set-bit-5-in-S-PDIF-register-SCR.patch \
            file://0029-PMU-driver-init.patch \
            file://0030-Merge-patches-for-CEC-issues-from-wolfgar.patch \
          "

COMPATIBLE_MACHINE = "(mx6curie)"
