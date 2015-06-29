# Copyright (C) 2015 Jens Rehsack <sno@netbsd.org>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Stressful Application Test (or stressapptest, its unix name) \
tries to maximize randomized traffic to memory from processor and I/O, with \
the intent of creating a realistic high load situation in order to test the \
existing hardware devices in a computer."

HOMEPAGE = "https://code.google.com/p/stressapptest/"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "libaio"
PR = "r0"

SRC_REV="49"
SRC_URI = "svn://stressapptest.googlecode.com/svn/;module=trunk;protocol=http;path_spec=stressapptest;rev=${SRC_REV}"

S = "${WORKDIR}/stressapptest"
inherit autotools
