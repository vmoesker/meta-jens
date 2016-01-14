#!/bin/sh

# Frank measured >45 seconds between LED turned into ready state and every application is runnning
# sleep 120 seconds just to be sure

. @LIBEXEC@/ledctrl

sleep 120s
led_bootup

exit 0
