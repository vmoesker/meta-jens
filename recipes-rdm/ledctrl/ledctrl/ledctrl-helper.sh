#!/bin/sh

function fail () {
    echo "$0 <error|failure|bootgodown>" >&2
    exit 1
}

test $# -eq 1 || fail

. @LIBEXEC@/ledctrl

case "$1" in
    error)
        led_error
        ;;
    failure)
        led_failure
        ;;
    bootgodown)
        led_bootgodown
        ;;
    *)
        fail
        ;;
esac
