include v8.inc
FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}-${PV}:"
SRC_URI = "\
    git://chromium.googlesource.com/v8/v8.git;protocol=https;branch=candidates;tag=${PV};destsuffix=${BPN}-${PV} \
    git://chromium.googlesource.com/external/gyp;protocol=https;rev=a3e2a5caf24a1e0a45401e09ad131210bf16b852;destsuffix=${BPN}-${PV}/build/gyp \
    git://chromium.googlesource.com/chromium/deps/icu46;protocol=https;rev=83ca4e6be97c8117f2ec163a96d0bbab5ffd0069;destsuffix=${BPN}-${PV}/third_party/icu \
    file://v8-3.22-RPi.patch;striplevel=0 \
    file://v8-arraybuffer.patch;striplevel=0 \
    file://v8-bignum-strict-overflow.patch;striplevel=0 \
"
