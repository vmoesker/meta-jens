include zway.inc
HP2_INSTALL = "service-df \
	${ZWAY_DEPS} \
	nginx-favs \
	libxml2 \
	libftdi \
	openjre-8 \
	zway-blob \
	hp2sm-zway \
	hp-blob \
        samba-base \
        wrkeyupload \
	hp2sm-system-wrc${WRC_VERSION} \
	udev-hp \
"
RDEPENDS_append = " packagegroup-base-zeroconf"
