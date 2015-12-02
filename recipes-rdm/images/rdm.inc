RDM_ESSENTIALS = "\
	e2fsprogs \
	e2fsprogs-tune2fs \
	attr \
	acl \
	tzdata \
	tcpdump \
	ifplugd \
	procps \
	util-linux-mount \
	nfs-utils-client \
	ntp \
	ntpdate \
	ntp-utils \
	util-linux \
	xz \
	qbrqry \
	udev-extraconf-mount-blacklist-${WANTED_ROOT_DEV} \
	prd-flash-${WANTED_ROOT_DEV} \
"
RDM_BASE_INSTALL = "\
	${RDM_ESSENTIALS} \
	date-set \
	cronie \
	dosfstools \
	sudo \
	nginx \
	nginx-legal \
	nginx-manual \
	libstatgrab \
	crda \
	lsof \
	logrotate \
	boot-fsck \
	initoverlay-${WANTED_ROOT_DEV} \
	system-image-update \
	hp2sm \
	hp2sm-ethtool \
	wifi-fallback \
"
RDM_ESSENTIALS_append_bohr = "\
	u-boot-fw-utils \
	mtd-utils \
	mtd-utils-jffs2 \
	mtd-utils-ubifs \
	mtd-utils-misc \
"
RDM_ESSENTIALS_append_bohr-update = "\
	date-set \
	u-boot-fw-utils \
	mtd-utils \
	mtd-utils-jffs2 \
	mtd-utils-ubifs \
	mtd-utils-misc \
"
RDM_ESSENTIALS_append_mx6 = "\
	udev-rules-imx \
"
RDM_ESSENTIALS_append_mx6qcurie = "\
	u-boot-fw-utils-curie \
	btrfs-tools \
	ledctrl \
	fb-cursor-off \
"
RDM_INSTALL = "${RDM_BASE_INSTALL} \
	ksm-init \
	openvpn \
"
RDM_INSTALL_append_mx6qcurie = "\
	libntfs-3g \
	ntfsprogs \
	ntfs-3g \
"
RECOVER_INSTALL="parted \
"
SKIPPED_DEPS = "sysdetails \
	thermaldetails \
	mmc-slowdown \
	stressapptest \
	collectd-graphs \
	"
