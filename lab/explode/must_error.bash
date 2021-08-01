#!/bin/bash
#author chenhsun00

cd ../..
pwd
mvn clean compile package
cd target || exit
unzip demo-0.0.1-SNAPSHOT.jar -d ff
cd ff || exit
cp ../../lib/no-such-method-error-1.0-SNAPSHOT.jar BOOT-INF/lib
ls -al no-such*
echo '开始启动'
java org.springframework.boot.loader.PropertiesLauncher