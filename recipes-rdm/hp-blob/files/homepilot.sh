#!/bin/sh

@HOMEPILOT_USER_HOME@/bin/init_appdir.sh

cd @HOMEPILOT_BASE@/lib/java
time @JAVA_ELF@ -jar migrator.jar > /var/log/hp2/migrator-console.log
exec @JAVA_ELF@ -Dfile.encoding=UTF-8 -jar homepilot.jar

