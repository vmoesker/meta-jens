FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://nginx-logrotate.conf"
SRC_URI += "file://nginx-varlib.volatiles"
SRC_URI += "http://internal.rdm.local/blobs/nginx-html-0.2.tar.gz;name=html"

SRC_URI[html.md5sum] = "ab456627a7b5871badb77995c95a61a4"
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
	install -m 644 ${WORKDIR}/nginx-varlib.volatiles ${D}${sysconfdir}/default/volatiles/98_nginx_varlib

	install -m 755 -d ${D}${sysconfdir}/logrotate.d
	install -m 644 ${WORKDIR}/nginx-logrotate.conf ${D}${sysconfdir}/logrotate.d/nginx

	install -o www -g www-data -m 0755 -t ${D}${localstatedir}/www/localhost/html/ ${WORKDIR}/html/*
}
