#!/bin/sh

@HOMEPILOT_USER_HOME@/bin/init_appdir.sh

cd @HOMEPILOT_BASE@/lib/java
exec @JAVA_ELF@ -jar @HOMEPILOT_BASE@/lib/java/homepilot.jar
