FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://nisdomainname"

do_install_append() {
	install -d ${D}${sysconfdir}
	install -m 0644 ${WORKDIR}/nisdomainname ${D}${sysconfdir}/nisdomainname
}
