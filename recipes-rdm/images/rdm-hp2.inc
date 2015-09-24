include zway.inc
HP2_INSTALL = "service-df \
	${ZWAY_DEPS} \
	nginx-favs \
	libxml2 \
	libftdi \
	openjre-8 \
	zway-blob \
	hp-blob \
        samba-base \
        wrkeyupload \
	wrc-startup \
	udev-hp \
"
RDEPENDS_append = " packagegroup-base-zeroconf"
