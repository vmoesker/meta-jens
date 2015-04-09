SUMMARY = "A full-featured SSL VPN solution via tun device"
HOMEPAGE = "http://openvpn.sourceforge.net"
SECTION = "console/network"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=5aac200199fde47501876cba7263cb0c"
DEPENDS = "lzo openssl"

inherit autotools

SRC_URI = "https://swupdate.openvpn.org/community/releases/openvpn-${PV}.tar.gz"

SRC_URI[md5sum] = "6ca03fe0fd093e0d01601abee808835c"
SRC_URI[sha256sum] = "7baed2ff39c12e1a1a289ec0b46fcc49ff094ca58b8d8d5f29b36ac649ee5b26"

CFLAGS += "-fno-inline"

EXTRA_OECONF += "--disable-server"

FILES_${PN}-dbg += "/usr/lib/openvpn/plugins/.debug"

RRECOMMENDS_${PN} = "kernel-module-tun"
