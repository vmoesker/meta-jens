#!/bin/sh

@HOMEPILOT_USER_HOME@/bin/init_appdir.sh

cd @HOMEPILOT_BASE@/lib/java
time @JAVA_ELF@ -jar migrator.jar
exec @JAVA_ELF@ -Dfile.encoding=UTF-8 -Djava.security.egd=file:/dev/./urandom -jar homepilot.jar

