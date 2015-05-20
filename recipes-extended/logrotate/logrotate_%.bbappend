do_install_append () {
	sed -i -e "s/^dateext$/#dateext/g" ${D}${sysconfdir}/logrotate.conf
}
