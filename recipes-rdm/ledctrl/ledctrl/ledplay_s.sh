#!/bin/sh
### BEGIN INIT INFO
# Provides:             ledplay_s
# Required-Start:       $local_fs
# Required-Stop:      $local_fs
# Default-Start:
# Default-Stop:
# Short-Description:  Enables/Disables each LED once at boot
### END INIT INFO

. @LIBEXEC@/ledctrl
led_test_s

exit 0
