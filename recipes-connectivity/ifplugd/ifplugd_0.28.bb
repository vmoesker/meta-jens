DESCRIPTION = "Daemon for monitoring ethernet device link state"
# ORIG_URI=http://0pointer.de/lennart/projects/ifplugd/ifplugd-0.28.tar.gz
# MY_URI=file:///home/sno/ifplugd-0.28.tar.gz
SRC_URI     = "http://0pointer.de/lennart/projects/ifplugd/ifplugd-0.28.tar.gz \
               file://ifplugd.conf"
DEPENDS     = "libdaemon"
LICENSE     = "GPL-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=94d55d512a9ba36caa9b7df079bae19f"

SRC_URI[md5sum] = "df6f4bab52f46ffd6eb1f5912d4ccee3"
SRC_URI[sha256sum] = "474754ac4ab32d738cbf2a4a3e87ee0a2c71b9048a38bdcd7df1e4f9fd6541f0"

EXTRA_OECONF_append = "--disable-lynx"

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME_${PN} = "${PN}"
INITSCRIPT_PARAMS_${PN} = "defaults 05"

do_install_append() {
	install -m 644 ${WORKDIR}/ifplugd.conf ${D}${sysconfdir}/ifplugd/
	sed -i -e "s,/bin/bash,/bin/sh," ${D}${sysconfdir}/init.d/ifplugd
}

inherit autotools update-rc.d pkgconfig
