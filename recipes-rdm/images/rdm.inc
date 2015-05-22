RDM_BASE_INSTALL = "hp2sm \
	alsa-utils \
	cronie \
	collectd \
	init-iecset \
	e2fsprogs \
	e2fsprogs-tune2fs \
	attr \
	acl \
	btrfs-tools \
	dosfstools \
	initoverlay \
	system-image-update \
	prd-flash \
	nano \
	ledctrl \
	fb-cursor-off \
	sudo \
	qbrqry \
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
	wifi-fallback \
	xz \
	lsof \
	logrotate \
	thermaldetails \
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
SKIPPED_DEPS = "sysdetails"
