#!/bin/bash
#author chenhsun00

cd ../..
pwd
mvn clean compile package
cd target || exit
jar ftv demo-0.0.1-SNAPSHOT.jar|grep no-such-method-error
echo '查看maven编译后的jar包冲突情况'
unzip demo-0.0.1-SNAPSHOT.jar -d ff > /dev/null 2>&1
cd ff || exit
cp ../../lib/no-such-method-error-1.0-SNAPSHOT.jar BOOT-INF/lib
cp ../../lib/no-such-method-error-0.1-SNAPSHOT.jar BOOT-INF/lib
#拷贝jar包完成后，删除一份数据
rm org/springframework/boot/loader/ExecutableArchiveLauncher.class
cp ../../clazz/ExecutableArchiveLauncher.class org/springframework/boot/loader/
echo 'copy完成'
jar -fm0c demo.jar  META-INF/MANIFEST.MF BOOT-INF org
jar ftv demo.jar|grep no-such-method-error
echo 'java -jar demo.jar'
java -jar demo.jar