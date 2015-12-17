include v8.inc
FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}-${PV}:"
SRC_URI = "svn://v8.googlecode.com/svn;protocol=http;module=tags/3.24.37;rev=23139;path_spec=${BPN}-${PV} \
    svn://gyp.googlecode.com/svn;module=trunk;protocol=http;rev=1831;path_spec=${BPN}-${PV}/build/gyp \
    svn://src.chromium.org/chrome/trunk;module=deps/third_party/icu46;protocol=http;rev=239289;path_spec=${BPN}-${PV}/third_party/icu \
    file://v8-3.22-RPi.patch;striplevel=0 \
    file://v8-arraybuffer.patch;striplevel=0 \
    file://v8-bignum-strict-overflow.patch;striplevel=0 \
"
