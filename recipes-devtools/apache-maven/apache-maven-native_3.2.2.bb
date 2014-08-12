DESCRIPTION = "Apache Maven is a software project management and comprehension tool. Based on the concept of a project object model (POM), Maven can manage a project's build, reporting and documentation from a central piece of information."
HOMEPAGE = "http://maven.apache.org/"

LICENSE = "Apache 2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

# http://ftp.fau.de/apache/maven/maven-3/3.2.2/source/apache-maven-3.2.2-src.tar.gz
# http://www.apache.org/dist/maven/maven-3/3.2.2/source/apache-maven-3.2.2-src.tar.gz.md5
# dc3c7042348859fd2358ff31648da923

DEPENDS = "ant-native "

inherit java-library java-native

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI = "http://ftp.fau.de/apache/maven/maven-3/3.2.2/source/apache-maven-${PV}-src.tar.gz \
	file://own-repo.patch"
SRC_URI[md5sum] = "dc3c7042348859fd2358ff31648da923"

S = "${WORKDIR}/apache-maven-${PV}"

do_removebinaries() {
    # do not remove bundled jar for maven-ant-tasks
    echo "No, Sir!"
}

do_compile() {
    	cd ${S}

    	export M2_HOME="${STAGING_LIBDIR_NATIVE}/${PN}-${PV}"
	export M2_REPO_LOCAL="${BASE_WORKDIR}/${PN}-${PV}"
	which ant
	which javac
	ant 
}
