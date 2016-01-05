include v8.inc
SRC_URI = "svn://v8.googlecode.com/svn;protocol=https;module=trunk;rev=23139;path_spec=v8 \
svn://gyp.googlecode.com/svn;module=trunk;protocol=https;rev=1831;path_spec=v8/build/gyp \
svn://src.chromium.org/chrome/trunk;module=deps/third_party/icu52;protocol=https;rev=277999;path_spec=v8/third_party/icu \
git://chromium.googlesource.com/chromium/buildtools.git;rev=fb782d4369d5ae04f17a2fceef7de5a63e50f07b;protocol=https \
svn://googletest.googlecode.com/svn;module=trunk;protocol=http;rev=692;path_spec=v8/testing/gtest \
svn://googlemock.googlecode.com/svn;module=trunk;protocol=http;rev=485;path_spec=v8/testing/gmock "
