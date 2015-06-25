#!/bin/sh

listen_port=8079

cd @JETTY_BASE@
exec @JAVA_ELF@ -jar start.jar -Djetty.port=$listen_port 2>&1
