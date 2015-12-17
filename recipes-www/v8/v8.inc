DESCRIPTION = "V8 JavaScript engine"
HOMEPAGE = "https://code.google.com/p/v8/"

LICENSE = "BSD-3-Clause | BSD-4-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9 \
file://${COMMON_LICENSE_DIR}/BSD-4-Clause;md5=624d9e67e8ac41a78f6b6c2c55a83a2b"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PACKAGES_prepend = " ${PN}-shell-dbg ${PN}-shell "

exec_prefix="/opt/v8"

S = "${WORKDIR}/${BPN}-${PV}"
V8_TARGET = "${TUNE_ARCH}"
# XXX fix them for general use
V8_FLAGS = ""

V8_FLAGS .= "${@bb.utils.contains("TUNE_FEATURES", "armv4", " arm_version=4 armv7=false ", \
               bb.utils.contains("TUNE_FEATURES", "armv5", " arm_version=5 armv7=false ", \
                 bb.utils.contains("TUNE_FEATURES", "armv6", " arm_version=6 armv7=false ", \
                   bb.utils.contains("TUNE_FEATURES", "armv7", " arm_version=7 ", \
                     bb.utils.contains("TUNE_FEATURES", "armv7a", " arm_version=7 ", \
                       bb.utils.contains("TUNE_FEATURES", "armv8", " arm_version=8 ", \
                         "", d), \
                       d), \
                     d), \
                   d), \
                 d), \
               d)}"

V8_FLAGS .= "${@bb.utils.contains("TUNE_FEATURES", "vfpv4", " armfpu=vfpv4 armfloatabi=hard hardfp=on ", \
               bb.utils.contains("TUNE_FEATURES", "neon", " armfpu=neon armneon=on vfp2=off vfp3=off armfloatabi=hard hardfp=on ", \
                 bb.utils.contains("TUNE_FEATURES", "vfp", " armfpu=vfp vfp3=off armfloatabi=hard hardfp=on ", \
                   bb.utils.contains("TUNE_FEATURES", "arm", " armfpu=none vfp2=off vfp3=off armfloatabi=softfp hardfp=off ", \
                     "", d), \
                   d), \
                 d), \
               d)}"

V8_FLAGS .= "${@bb.utils.contains("TUNE_FEATURES", "arm", " armthumb=off ", \
               bb.utils.contains("TUNE_FEATURES", "thumb", " armthumb=on ", \
                 "", d), \
               d)}"

do_patch_for_rpath () {
    egrep -Rl "rpath=.*ORIGIN/" build | xargs sed -i -E -e 's,(rpath=.*ORIGIN/)lib.%s/'"'"'[^)]+,\1../lib'"'"',g' -e 's,rpath-link=\\,rpath-link=,g'
}

do_patch[postfuncs] += " do_patch_for_rpath "

do_compile () {
    export LD="${CXX}"
    export LINK="${CXX}"
    make i18nsupport=off library=shared V=1 ${V8_FLAGS} ${V8_TARGET}.release
}

do_install () {
    install -d ${D}${exec_prefix}
    install -d ${D}${includedir}
    install -d ${D}${libdir}
    install -d ${D}${bindir}

    install -c -m 644 ${S}/out/${V8_TARGET}.release/lib.target/* ${D}${libdir}
    install -c -m 644 ${S}/include/* ${D}${includedir}
    install -c -m 755 ${S}/out/${V8_TARGET}.release/d8 ${S}/out/${V8_TARGET}.release/lineprocessor \
                      ${S}/out/${V8_TARGET}.release/process ${S}/out/${V8_TARGET}.release/shell \
                      ${D}${bindir}
}

FILES_${PN}-shell = "\
    ${bindir} \
"

FILES_${PN}-shell-dbg = "\
    ${bindir}/.debug \
"

FILES_${PN}_append = "\
    ${exec_prefix}/lib/*.so \
"

FILES_${PN}-dev_remove = "\
    ${exec_prefix}/lib/lib*.so \
"

BBCLASSEXTEND = "native"
