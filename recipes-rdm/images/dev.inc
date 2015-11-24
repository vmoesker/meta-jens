EXTRA_IMAGE_FEATURES_dev = "tools-debug \
    tools-profile \
    tools-sdk \
    dbg-pkgs \
    dev-pkgs \
"

DEV_INSTALL = "\
	ltrace \
	systemtap \
	systemtap-uprobes \
	git \
	git-perltools \
	subversion \
	"

DEV_INSTALL_append_mx6qcurie = "\
	vivante-gpu-sdk \
	valgrind \
	"

inherit rootdev-check

CONFLICT_ROOT_DEVS = "${INTERNAL_ROOT_DEV} nfs"
