DESCRIPTION = "V8 JavaScript engine"
HOMEPAGE = "https://code.google.com/p/v8/"

LICENSE = "BSD-3-Clause | BSD-4-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9 \
file://${COMMON_LICENSE_DIR}/BSD-4-Clause;md5=624d9e67e8ac41a78f6b6c2c55a83a2b"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

S = "${WORKDIR}/v8"

inherit autotools pkgconfig
