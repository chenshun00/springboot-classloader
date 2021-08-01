#!/bin/bash
#author chenhsun00

cd ../..
pwd
mvn clean compile package
cd target || exit
unzip demo-0.0.1-SNAPSHOT.jar -d ff
cd ff || exit
ls -al no-such*
echo '开始启动'
java org.springframework.boot.loader.PropertiesLauncher