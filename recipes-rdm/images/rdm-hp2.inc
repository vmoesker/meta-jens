include zway.inc
HP2_INSTALL = "service-df \
	${ZWAY_DEPS} \
	libxml2 \
	libftdi \
	openjre-8 \
	zway-blob \
	hp-blob \
        samba \
        wrkeyupload \
	wrc-startup \
	udev-hp \
"
RDEPENDS_append = " packagegroup-base-zeroconf"
