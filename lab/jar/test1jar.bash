#!/bin/bash
#author chenhsun00

cd ../..
pwd
mvn clean compile package
cd target || exit
#
echo '查看maven编译后的jar包冲突情况'
jar ftv demo-0.0.1-SNAPSHOT.jar|grep no-such-method-error
unzip demo-0.0.1-SNAPSHOT.jar -d ff > /dev/null 2>&1
cd ff || exit
cp ../../lib/no-such-method-error-1.0-SNAPSHOT.jar BOOT-INF/lib
rm org/springframework/boot/loader/ExecutableArchiveLauncher.class
cp ../../lab/jar/ExecutableArchiveLauncher.class org/springframework/boot/loader/
jar -fm0c demo.jar  META-INF/MANIFEST.MF BOOT-INF org
jar ftv demo.jar|grep no-such-method-error
java -jar demo.jar