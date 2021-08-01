#!/bin/bash
#author chenhsun00

cd ../..
pwd
mvn clean compile package
cd target || exit
jar ftv demo-0.0.1-SNAPSHOT.jar|grep no-such
unzip demo-0.0.1-SNAPSHOT.jar -d ff
cd ff || exit
cp ../../lib/no-such-method-error-1.0-SNAPSHOT.jar BOOT-INF/lib
cp ../../lib/no-such-method-error-0.1-SNAPSHOT.jar BOOT-INF/lib
rm org/springframework/boot/loader/ExecutableArchiveLauncher.class
cp ../../lab/jar/ExecutableArchiveLauncher.class org/springframework/boot/loader/
jar -fm0c demo.jar  META-INF/MANIFEST.MF BOOT-INF org
jar ftv demo.jar|grep no-such
java -jar demo.jar