# 文件说明

```text
arthas
└── arthas-bin.zip  ---> 魔改后的Arthas,新增命令行输出
clazz
└── ExecutableArchiveLauncher.class ---> 修改以后的class文件,用于验证NoSuchMethodError 
lab
├── explode 解压后的jar包
│   ├── must_error.bash         必然出现NoSuchMethodError
│   ├── must_not_error.bash     必然不出现NoSuchMethodError
│   └── normal.bash             没有jar包冲突的情况
└── jar
    ├── ExecutableArchiveLauncher.class ---> 输出加载的jar包顺序
    ├── normal.bash             没有jar包冲突的情况
    ├── test1jar.bash           加入一个jar包冲突的情况
    ├── test2jar.bash           加入二个jar包冲突的情况
    └── testOk.bash             测试jar重排序后的冲突的情况
lib
├── no-such-method-common-1.0-SNAPSHOT.jar  ---> jar包冲突验证(验证加载顺序与字符的关系)
├── no-such-method-error-0.1-SNAPSHOT.jar   ---> jar包冲突验证(验证加载顺序与字符的关系)
├── no-such-method-error-1.0-SNAPSHOT.jar   ---> jar包冲突验证(验证加载顺序与字符的关系)
└── no-such-method-error-2.0-SNAPSHOT.jar   ---> jar包冲突验证(验证加载顺序与字符的关系)
```

* no-such-method-error-0.1-SNAPSHOT.jar
```java
package com.example.noSuchMethodErrorDemo;

public class FirstDemo {
  public void first() {
    System.out.println("first");
  }

  public void second() {
    System.out.println("3.0");
  }
}
```

* no-such-method-error-1.0-SNAPSHOT.jar
```java
package com.example.noSuchMethodErrorDemo;

public class FirstDemo {
  public void first() {
    System.out.println("first");
  }
}
```

* no-such-method-error-2.0-SNAPSHOT.jar
```java
package com.example.noSuchMethodErrorDemo;

public class FirstDemo {
  public void second() {
    System.out.println("second");
  }
}
```

# SpringBoot启动的n种方式以及他们的classloader

使用arthas验证各种启动方式下加载的URL

获取IDEA启动的PID

```shell
ps -ef |grep demo-0.0.1-SNAPSHOT.jar|grep -v grep | awk '{print $2}'
```

获取jar包的PID

```shell
ps -ef |grep DemoApplication|grep -v grep | awk '{print $2}'
```

## IDEA main方法启动

2种启动方式

* IDE启动
* 命令行启动(具备IDE效果)
    * cd
    * 执行shell

```shell
cd /Users/chenshun/open/demo/src/main/java
/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/bin/java -javaagent:/Users/chenshun/open/classloader-dmeo/src/test/resources/lib/boot-strap-1.0-SNAPSHOT.jar -XX:TieredStopAtLevel=1 -noverify -Dspring.output.ansi.enabled=always -Dcom.sun.management.jmxremote -Dspring.jmx.enabled=true -Dspring.liveBeansView.mbeanDomain -Dspring.application.admin.enabled=true -Dfile.encoding=UTF-8 -classpath /Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/charsets.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/deploy.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/ext/cldrdata.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/ext/dnsns.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/ext/jaccess.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/ext/jfxrt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/ext/localedata.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/ext/nashorn.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/ext/sunec.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/ext/sunjce_provider.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/ext/sunpkcs11.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/ext/zipfs.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/javaws.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/jce.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/jfr.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/jfxswt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/jsse.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/management-agent.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/plugin.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/resources.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/rt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/lib/ant-javafx.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/lib/dt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/lib/javafx-mx.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/lib/jconsole.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/lib/packager.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/lib/sa-jdi.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/lib/tools.jar:/Users/chenshun/open/demo/target/classes:/Users/chenshun/.m2/repository/org/example/no-such-method-common/1.0-SNAPSHOT/no-such-method-common-1.0-SNAPSHOT.jar:/Users/chenshun/.m2/repository/com/example/no-such-method-error/2.0-SNAPSHOT/no-such-method-error-2.0-SNAPSHOT.jar:/Users/chenshun/.m2/repository/org/springframework/boot/spring-boot-starter-web/2.5.3/spring-boot-starter-web-2.5.3.jar:/Users/chenshun/.m2/repository/org/springframework/boot/spring-boot-starter/2.5.3/spring-boot-starter-2.5.3.jar:/Users/chenshun/.m2/repository/org/springframework/boot/spring-boot/2.5.3/spring-boot-2.5.3.jar:/Users/chenshun/.m2/repository/org/springframework/boot/spring-boot-autoconfigure/2.5.3/spring-boot-autoconfigure-2.5.3.jar:/Users/chenshun/.m2/repository/org/springframework/boot/spring-boot-starter-logging/2.5.3/spring-boot-starter-logging-2.5.3.jar:/Users/chenshun/.m2/repository/ch/qos/logback/logback-classic/1.2.4/logback-classic-1.2.4.jar:/Users/chenshun/.m2/repository/ch/qos/logback/logback-core/1.2.4/logback-core-1.2.4.jar:/Users/chenshun/.m2/repository/org/slf4j/slf4j-api/1.7.32/slf4j-api-1.7.32.jar:/Users/chenshun/.m2/repository/org/apache/logging/log4j/log4j-to-slf4j/2.14.1/log4j-to-slf4j-2.14.1.jar:/Users/chenshun/.m2/repository/org/apache/logging/log4j/log4j-api/2.14.1/log4j-api-2.14.1.jar:/Users/chenshun/.m2/repository/org/slf4j/jul-to-slf4j/1.7.32/jul-to-slf4j-1.7.32.jar:/Users/chenshun/.m2/repository/jakarta/annotation/jakarta.annotation-api/1.3.5/jakarta.annotation-api-1.3.5.jar:/Users/chenshun/.m2/repository/org/springframework/spring-core/5.3.9/spring-core-5.3.9.jar:/Users/chenshun/.m2/repository/org/springframework/spring-jcl/5.3.9/spring-jcl-5.3.9.jar:/Users/chenshun/.m2/repository/org/yaml/snakeyaml/1.28/snakeyaml-1.28.jar:/Users/chenshun/.m2/repository/org/springframework/boot/spring-boot-starter-json/2.5.3/spring-boot-starter-json-2.5.3.jar:/Users/chenshun/.m2/repository/com/fasterxml/jackson/core/jackson-databind/2.12.4/jackson-databind-2.12.4.jar:/Users/chenshun/.m2/repository/com/fasterxml/jackson/core/jackson-annotations/2.12.4/jackson-annotations-2.12.4.jar:/Users/chenshun/.m2/repository/com/fasterxml/jackson/core/jackson-core/2.12.4/jackson-core-2.12.4.jar:/Users/chenshun/.m2/repository/com/fasterxml/jackson/datatype/jackson-datatype-jdk8/2.12.4/jackson-datatype-jdk8-2.12.4.jar:/Users/chenshun/.m2/repository/com/fasterxml/jackson/datatype/jackson-datatype-jsr310/2.12.4/jackson-datatype-jsr310-2.12.4.jar:/Users/chenshun/.m2/repository/com/fasterxml/jackson/module/jackson-module-parameter-names/2.12.4/jackson-module-parameter-names-2.12.4.jar:/Users/chenshun/.m2/repository/org/springframework/boot/spring-boot-starter-tomcat/2.5.3/spring-boot-starter-tomcat-2.5.3.jar:/Users/chenshun/.m2/repository/org/apache/tomcat/embed/tomcat-embed-core/9.0.50/tomcat-embed-core-9.0.50.jar:/Users/chenshun/.m2/repository/org/apache/tomcat/embed/tomcat-embed-el/9.0.50/tomcat-embed-el-9.0.50.jar:/Users/chenshun/.m2/repository/org/apache/tomcat/embed/tomcat-embed-websocket/9.0.50/tomcat-embed-websocket-9.0.50.jar:/Users/chenshun/.m2/repository/org/springframework/spring-web/5.3.9/spring-web-5.3.9.jar:/Users/chenshun/.m2/repository/org/springframework/spring-beans/5.3.9/spring-beans-5.3.9.jar:/Users/chenshun/.m2/repository/org/springframework/spring-webmvc/5.3.9/spring-webmvc-5.3.9.jar:/Users/chenshun/.m2/repository/org/springframework/spring-aop/5.3.9/spring-aop-5.3.9.jar:/Users/chenshun/.m2/repository/org/springframework/spring-context/5.3.9/spring-context-5.3.9.jar:/Users/chenshun/.m2/repository/org/springframework/spring-expression/5.3.9/spring-expression-5.3.9.jar:/Users/chenshun/.m2/repository/org/springframework/boot/spring-boot-loader/2.5.3/spring-boot-loader-2.5.3.jar com.example.demo.DemoApplication
```

## java -jar 启动

```shell
mvn clean compile package
cd target 
java -jar demo-0.0.1-SNAPSHOT.jar
```

## 解压启动

```shell
mvn clean compile package
cd target
unzip demo-0.0.1-SNAPSHOT.jar -d ff
cd ff
java org.springframework.boot.loader.PropertiesLauncher
```

# 定制NoSuchMethodError

理解NoSuchMethodError出现的本质原因
> jvm加载的class没有这个方法.为啥没有这个方法，原因有两
> 一、classpath没有这个class文件(jar中不包含对应的class文件)
> 二、加载class的时候，不符合预期的jar包中的class被优先加载了

明白 `优先` 的实现。

> 优先的原因在于加载lib中jar包的时候是否对其进行排序
> 解压启动会排序
> jar启动随机加载

由于maven传递性，过于简单的使用IDEA难以复现，只能采取拷贝jar包的方式进行处理

## 解压启动

验证实验

* `classpath` 只有 `no-such-method-error-2.0-SNAPSHOT.jar` 的时候，正常运行
* `classpath` 同时有 `1.0` 和 `2.0` 时必然出现NoSuchMethodError
* `classpath` 同时有 `1.0` 、`2.0` 以及 `0.1` 时必然不出现NoSuchMethodError

## java -jar 启动

由于演示过于简单,maven编译将jar包冲突编译到一起，只能先解压随后进行拷贝处理

### jar包压缩

```shell
 jar -fm0c demo.jar  META-INF/MANIFEST.MF BOOT-INF org
```

验证实验

* `classpath` 只有 `no-such-method-error-2.0-SNAPSHOT.jar` 的时候，正常运行
* `classpath` 同时有 `1.0` 和 `2.0` 时表现出相对偶然性
* `classpath` 同时有 `1.0` 、`2.0` 以及 `0.1` 时表现出相对偶然性
* 修改代码将随机的jar包进行排序
  * `classpath` 同时有 `1.0` 和 `2.0` 必然出现
  * `classpath` 同时有 `1.0` 、`2.0` 以及 `0.1` 时必然不出现
