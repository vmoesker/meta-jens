#!/bin/sh
### BEGIN INIT INFO
# Provides:          cleanunion
# Required-Start:    $local_fs
# Required-Stop: 
# Default-Start:     S
# Default-Stop:
# Short-Description: Clean union filesystems upon request
# Description:
### END INIT INFO

set -x

test -e /etc/default/rcS && . /etc/default/rcS

PATH=/sbin:/bin:/usr/sbin:/usr/bin

test -e /etc/default/cleanunion.conf -o -e /etc/cleanunion.conf || exit 0

test -e /etc/default/cleanunion.conf && . /etc/default/cleanunion.conf
test -e /etc/cleanunion.conf && . /etc/cleanunion.conf

DEF_IFS="$IFS"
for cleanup_spec in ${CLEANUP_SPEC}
do
    test -f "${cleanup_spec}" || continue
    IFS=$'\n'
    path_specs=`<${cleanup_spec}`
    for path_spec in ${path_specs}
    do
	rm -rf "${path_spec}"
    done
    rm -f "${cleanup_spec}"
    IFS="$DEF_IFS"
done
