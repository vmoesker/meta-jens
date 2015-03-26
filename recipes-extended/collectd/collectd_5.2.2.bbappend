FILESEXTRAPATHS_prepend := "${THISDIR}/collectd:"

SRC_URI += "file://collectd-perl.patch \
            file://collectd-libstatgrab.patch \
            file://collectd.conf \
"

PACKAGECONFIG[sensors] = "--enable-sensors --with-libsensors=yes, \
        --disable-sensors --with-libsensors=no,lmsensors,lmsensors-sensors"
PACKAGECONFIG[libstatgrab] = "--with-libstatgrab,--without-libstatgrab,libstatgrab,libstatgrab"

DEPENDS += "perl-native"
RDEPENDS_${PN} += "perl"

inherit cpan-base perlnative

get_perl_cfgvar[vardepvalue] = "${PERL_OWN_DIR}"
def get_perl_cfgvar(d,var):
    import re
    cfg = d.expand('${STAGING_LIBDIR}${PERL_OWN_DIR}/perl/config.sh')
    try:
        f = open(cfg, 'r')
    except IOError:
        return None
    l = f.readlines();
    f.close();
    r = re.compile("^" + var + "='([^']*)'")
    for s in l:
        m = r.match(s)
        if m:
            return m.group(1)
    return None

export PERL_CFLAGS = "${@get_perl_cfgvar(d, 'ccflags')} -I${STAGING_LIBDIR}${PERL_OWN_DIR}/perl/${@get_perl_version(d)}/CORE"
export PERL_LDFLAGS = "${@get_perl_cfgvar(d, 'ccdlflags')} -L${STAGING_LIBDIR}${PERL_OWN_DIR}/perl/${@get_perl_version(d)}/CORE -lperl ${@get_perl_cfgvar(d, 'perllibs')}"
#  -v -Wl,-debug -Wl,-t -Wl,--verbose

# --enable-debug --enable-dns
# 
# --enable-openvpn --enable-processes --enable-protocols
# --enable-perl --with-libperl
EXTRA_OECONF = " \
                ${FPLAYOUT} \
                --disable-perl --with-libperl=no --with-perl-bindings=no \
                --enable-cpu --enable-cpufreq --enable-exec --enable-df \
                --enable-disk --enable-ethstat --enable-interface \
                --enable-load --enable-logfile --enable-memory \
                --enable-nginx --enable-ntpd --enable-serial \
                --enable-thermal --enable-wireless \
                --with-libgcrypt=${STAGING_BINDIR_CROSS}/libgcrypt-config \
                --disable-notify_desktop \
"

do_install_append () {
	install -m 644 ${WORKDIR}/collectd.conf ${D}/${sysconfdir}/
}
