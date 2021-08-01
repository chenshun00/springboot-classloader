#!/bin/bash
#author chenhsun00

cd ../..
pwd
mvn clean compile package
cd target || exit
jar ftv demo-0.0.1-SNAPSHOT.jar|grep no-such
echo '开始启动'
java -jar demo-0.0.1-SNAPSHOT.jar