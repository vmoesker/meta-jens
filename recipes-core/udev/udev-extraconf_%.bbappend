do_install_prepend () {
    echo "/dev/${ROOT_DEV_NAME}${ROOT_DEV_SEP}*" >> ${WORKDIR}/mount.blacklist
    if test "${WANTED_ROOT_DEV}" != "${INTERNAL_ROOT_DEV}";
    then
        echo "/dev/${ROOT_DEV_NAME-${INTERNAL_ROOT_DEV}}${ROOT_DEV_SEP-${INTERNAL_ROOT_DEV}}*" >> ${WORKDIR}/mount.blacklist
    fi
}
