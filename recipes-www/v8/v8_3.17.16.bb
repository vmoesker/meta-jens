include v8.inc
SRC_URI = "\
    git://git@bitbucket.org/rdm-dev/v8-armv5-serguei.git;protocol=ssh;branch=trunk;tag=${PV};destsuffix=${BPN}-${PV} \
    git://chromium.googlesource.com/external/gyp;protocol=https;rev=f7bc250ccc4d619a1cf238db87e5979f89ff36d7;destsuffix=${BPN}-${PV}/build/gyp \
    file://v8-bignum-strict-overflow.patch;striplevel=0 \
    file://v8-sane-arm5e-handling.patch;striplevel=0 \
    file://v8_3.17.16-weird-dirty-serguei.patch \
"

do_patch_ancient_v8 () {
    # grep -Rl Werror SConstruct build | xargs sed -i -e 's/-Werror/-Wno-error/g'
    sed -i -E -e 's/(v8_can_use_vfp._instructions=false)$/\1 -Darm_fpu=none/g' Makefile
}

do_patch[postfuncs] += " do_patch_ancient_v8 "

V8_FLAGS_append = " werror=no "
