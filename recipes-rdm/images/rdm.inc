RDM_BASE_INSTALL = "u-boot-fw-utils \
	cronie \
	collectd-graphs \
	fontconfig \
	liberation-fonts \
	date-set \
	e2fsprogs \
	e2fsprogs-tune2fs \
	attr \
	acl \
	btrfs-tools \
	dosfstools \
	nano \
	sudo \
	nginx \
	tzdata \
	tcpdump \
	ifplugd \
	libstatgrab \
	procps \
	util-linux-mount \
	nfs-utils-client \
	ntp \
	ntpdate \
	ntp-utils \
	openvpn \
	util-linux \
	ethtool \
	crda \
	xz \
	lsof \
	logrotate \
	qbrqry \
	boot-fsck \
	initoverlay \
	system-image-update \
	hp2sm \
	prd-flash \
	wifi-fallback \
"
RDM_BASE_INSTALL_append_mx6qcurie = "\
	ledctrl \
	alsa-utils \
	init-iecset \
	fb-cursor-off \
"
RDM_INSTALL = "${RDM_BASE_INSTALL} \
	udev-extraconf \
	libntfs-3g \
	ntfsprogs \
	ntfs-3g \
	ksm-init \
"
RECOVER_INSTALL="parted \
"
SKIPPED_DEPS = "sysdetails \
	thermaldetails \
	mmc-slowdown \
	stressapptest \
	"
