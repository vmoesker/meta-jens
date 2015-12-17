include v8.inc
SRC_URI = "svn://v8.googlecode.com/svn;protocol=http;module=tags/3.17.16.2;rev=25343;path_spec=${BPN}-${PV} \
    svn://gyp.googlecode.com/svn;module=trunk;protocol=http;rev=1501;path_spec=${BPN}-${PV}/build/gyp \
    file://v8-bignum-strict-overflow.patch;striplevel=0 \
    file://v8-sane-arm5e-handling.patch;striplevel=0 \
"

do_patch_ancient_v8 () {
    # grep -Rl Werror SConstruct build | xargs sed -i -e 's/-Werror/-Wno-error/g'
    sed -i -E -e 's/(v8_can_use_vfp._instructions=false)$/\1 -Darm_fpu=none/g' Makefile
}

do_patch[postfuncs] += " do_patch_ancient_v8 "

V8_FLAGS_append = " werror=no "
