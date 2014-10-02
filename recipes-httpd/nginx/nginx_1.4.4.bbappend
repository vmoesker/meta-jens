FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "http://internal.rdm.local/blobs/nginx-legal-0.1.tar.gz \
	http://internal.rdm.local/blobs/nginx-manual-0.1.tar.gz \
	http://internal.rdm.local/blobs/nginx-html-0.1.tar.gz \
"

do_install_append () {
	install -d ${D}${localstatedir}/lib/nginx/

	install -o www -g www-data -m 0755 -t ${D}${localstatedir}/www/localhost/html/ ${WORKDIR}/html/*
	
	install -o www -g www-data -m 0755 -d ${D}${localstatedir}/www/localhost/legal/
	install -o www -g www-data -m 0755 -d ${D}${localstatedir}/www/localhost/manual/
	install -o www -g www-data -m 0755 -t ${D}${localstatedir}/www/localhost/legal/ ${WORKDIR}/legal/*
	install -o www -g www-data -m 0755 -t ${D}${localstatedir}/www/localhost/manual/ ${WORKDIR}/manual/*
}
