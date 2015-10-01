FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = "\
    file://remove-depreciated-IRQF_DISABLED.patch \
    file://remove-f_dentry-macro.patch \
    file://remove-pm-feature.patch \
"
