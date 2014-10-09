#!/bin/sh

listen_port=8079
log_out="/dev/null"

cd @JETTY_BASE@
exec @JAVA_ELF@ -jar start.jar -Djetty.port=$listen_port > $log_out 2>&1
