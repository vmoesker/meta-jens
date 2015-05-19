FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "http://internal.rdm.local/blobs/nginx-legal-0.2.tar.gz;name=legal \
	http://internal.rdm.local/blobs/nginx-manual-0.4.tar.gz;name=manual \
	http://internal.rdm.local/blobs/nginx-html-0.2.tar.gz;name=html \
	file://nginx-varlib.volatiles \
	file://nginx-logrotate.conf \
"

SRC_URI[legal.md5sum] = "ccb221827f2bcd827cca8db6a3d14d70"
SRC_URI[manual.md5sum] = "d42c56ce54078c53bb8a4a151bb89eeb"
SRC_URI[html.md5sum] = "ab456627a7b5871badb77995c95a61a4"
SRC_URI[legal.sha256sum] = "f8643b50b34b5cf5ce483bf01012f97dc35b2d0a5b79adbe2d73042ca47edf1e"
SRC_URI[manual.sha256sum] = "17e16a35206b2a0379d758a5b0f355e095db5064ee51e91d26cea3c527edc50c"
SRC_URI[html.sha256sum] = "c370d6fce8a81fb2bf5ba0454d230263ea4ec57aa50626ca8fccb3a5269be17c"

do_configure () {
	if [ "${SITEINFO_BITS}" = "64" ]; then
		PTRSIZE=8
	else
		PTRSIZE=4
	fi

	echo $CFLAGS
	echo $LDFLAGS

	./configure \
	--crossbuild=Linux:${TUNE_ARCH} \
	--with-endian=${@base_conditional('SITEINFO_ENDIANNESS', 'le', 'little', 'big', d)} \
	--with-int=4 \
	--with-long=${PTRSIZE} \
	--with-long-long=8 \
	--with-ptr-size=${PTRSIZE} \
	--with-sig-atomic-t=${PTRSIZE} \
	--with-size-t=${PTRSIZE} \
	--with-off-t=${PTRSIZE} \
	--with-time-t=${PTRSIZE} \
	--with-sys-nerr=132 \
	--conf-path=${sysconfdir}/nginx/nginx.conf \
	--http-log-path=${localstatedir}/log/nginx/access.log \
	--error-log-path=${localstatedir}/log/nginx/error.log \
	--pid-path=/run/nginx/nginx.pid \
	--prefix=${prefix} \
	--with-http_ssl_module \
	--with-http_gzip_static_module \
	--with-http_stub_status_module
}

do_install_append () {
	install -d ${D}${localstatedir}/lib/nginx/
	install -d ${D}${sysconfdir}/default/volatiles

	install -o www -g www-data -m 0755 -t ${D}${localstatedir}/www/localhost/html/ ${WORKDIR}/html/*
	
	install -o www -g www-data -m 0755 -d ${D}${localstatedir}/www/localhost/legal/
	install -o www -g www-data -m 0755 -d ${D}${localstatedir}/www/localhost/manual/
	install -o www -g www-data -m 0755 -t ${D}${localstatedir}/www/localhost/legal/ ${WORKDIR}/legal/*
	install -o www -g www-data -m 0755 -t ${D}${localstatedir}/www/localhost/manual/ ${WORKDIR}/manual/*

	install -m 644 ${WORKDIR}/nginx-varlib.volatiles ${D}${sysconfdir}/default/volatiles/98_nginx_varlib

	install -m 755 -d ${D}${sysconfdir}/logrotate.d
	install -m 644 ${WORKDIR}/nginx-logrotate.conf ${D}${sysconfdir}/logrotate.d/nginx
}
