FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = "\
    git://git@bitbucket.org/rdm-dev/nginx-html.git;protocol=ssh;branch=jethro-ijsselmeer;name=html;destsuffix=html \
    file://nginx-logrotate.conf \
    file://nginx-varlib.volatiles \
"

SRCREV_html = "f5cd9dc38fe3b2d58a48242fcf224a738b311610"

PACKAGES_prepend = "${PN}-favs ${PN}-legal ${PN}-manual "

do_install_append () {
	install -d ${D}${localstatedir}/lib/nginx/
	install -d ${D}${sysconfdir}/default/volatiles
	install -m 644 ${WORKDIR}/nginx-varlib.volatiles ${D}${sysconfdir}/default/volatiles/98_nginx_varlib

	install -m 755 -d ${D}${sysconfdir}/logrotate.d
	install -m 644 ${WORKDIR}/nginx-logrotate.conf ${D}${sysconfdir}/logrotate.d/nginx

	install -o www -g www-data -m 0755 -d ${D}${localstatedir}/www/localhost/html/
	install -o www -g www-data -m 0644 ${WORKDIR}/html/50x.html ${WORKDIR}/html/logo-50x.png ${D}${localstatedir}/www/localhost/html/

	install -o www -g www-data -m 0755 -d ${D}${localstatedir}/www/localhost/favs
	install -o www -g www-data -m 0644 ${WORKDIR}/html/favs/index.html ${WORKDIR}/html/favs/jquery-1.7.1.min.js ${D}${localstatedir}/www/localhost/favs

	install -o www -g www-data -m 0755 -d ${D}${localstatedir}/www/localhost/legal
	install -o www -g www-data -m 0644 ${WORKDIR}/html/legal/* ${D}${localstatedir}/www/localhost/legal

	install -o www -g www-data -m 0755 -d ${D}${localstatedir}/www/localhost/manual
	install -o www -g www-data -m 0644 ${WORKDIR}/html/manual/* ${D}${localstatedir}/www/localhost/manual
}

FILES_${PN}-favs = "${localstatedir}/www/localhost/favs"
FILES_${PN}-legal = "${localstatedir}/www/localhost/legal"
FILES_${PN}-manual = "${localstatedir}/www/localhost/manual"
