RDM_BASE_INSTALL = "hp2sm \
	alsa-utils \
	init-iecset \
	e2fsprogs \
	btrfs-tools \
	dosfstools \
	system-image-update \
	prd-flash \
	nano \
	ledctrl \
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
	util-linux \
	ethtool \
	xz \
	lsof \
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
