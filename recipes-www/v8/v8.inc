DESCRIPTION = "V8 JavaScript engine"
HOMEPAGE = "https://code.google.com/p/v8/"

LICENSE = "BSD-3-Clause | BSD-4-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9 \
file://${COMMON_LICENSE_DIR}/BSD-4-Clause;md5=624d9e67e8ac41a78f6b6c2c55a83a2b"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

S = "${WORKDIR}/v8"
V8_TARGET = "${TUNE_ARCH}"
# XXX fix them for general use
V8_FLAGS = ""

V8_FLAGS .= "${@bb.utils.contains("TUNE_FEATURES", "armv4", " arm_version=4 ", \
               bb.utils.contains("TUNE_FEATURES", "armv5", " arm_version=5 ", \
                 bb.utils.contains("TUNE_FEATURES", "armv6", " arm_version=6 ", \
                   bb.utils.contains("TUNE_FEATURES", "armv7", " arm_version=7 ", \
                     bb.utils.contains("TUNE_FEATURES", "armv7a", " arm_version=7 ", \
                       bb.utils.contains("TUNE_FEATURES", "armv8", " arm_version=8 ", \
                         "", d), \
                       d), \
                     d), \
                   d), \
                 d), \
               d)}"

V8_FLAGS .= "${@bb.utils.contains("TUNE_FEATURES", "vfpv4", " armfpu=vfpv4 armfloatabi=hard ", \
               bb.utils.contains("TUNE_FEATURES", "neon", " armfpu=neon armfloatabi=hard ", \
                 bb.utils.contains("TUNE_FEATURES", "vfp", " armfpu=vfp armfloatabi=hard ", \
                   bb.utils.contains("TUNE_FEATURES", "arm", " armfpu=vfp armfloatabi=soft ", \
                     "", d), \
                   d), \
                 d), \
               d)}"

V8_FLAGS .= "${@bb.utils.contains("TUNE_FEATURES", "thumb", " armthumb=on ", \
               bb.utils.contains("TUNE_FEATURES", "arm", " armthumb=off ", \
                 "", d), \
               d)}"

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
