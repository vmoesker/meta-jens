DESCRIPTION = "V8 JavaScript engine"
HOMEPAGE = "https://code.google.com/p/v8/"

LICENSE = "BSD-3-Clause | BSD-4-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9 \
file://${COMMON_LICENSE_DIR}/BSD-4-Clause;md5=624d9e67e8ac41a78f6b6c2c55a83a2b"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

S = "${WORKDIR}/v8"
V8_TARGET = "${TUNE_ARCH}"
# XXX fix them for general use
V8_FLAGS = "arm_version=7 armfpu=neon armfloatabi=hard armthumb=off "

do_compile () {
    export LD="${CXX}"
    export LINK="${CXX}"
    make i18nsupport=off library=shared ${V8_FLAGS} ${V8_TARGET}.release
}

do_install () {
    install -d ${D}/opt/v8
    install -d ${D}/opt/v8/include
    install -d ${D}/opt/v8/lib
    install -c -m 644 ${S}/out/${V8_TARGET}.release/lib.target/* ${D}/opt/v8/lib
    install -c -m 644 ${S}/include/* ${D}/opt/v8/include
}

FILES_${PN} += "/opt/v8"

BBCLASSEXTEND = "native"
