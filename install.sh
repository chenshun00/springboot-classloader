#!/usr/bin/env bash
mvn install:install-file -DgroupId=com.example -DartifactId=no-such-method-error -Dversion=2.0-SNAPSHOT -Dpackaging=jar -Dfile=./lib/no-such-method-error-2.0-SNAPSHOT.jar
mvn install:install-file -DgroupId=com.example -DartifactId=no-such-method-error -Dversion=1.0-SNAPSHOT -Dpackaging=jar -Dfile=./lib/no-such-method-error-1.0-SNAPSHOT.jar
mvn install:install-file -DgroupId=com.example -DartifactId=no-such-method-error -Dversion=0.1-SNAPSHOT -Dpackaging=jar -Dfile=./lib/no-such-method-error-0.1-SNAPSHOT.jar
mvn install:install-file -DgroupId=com.example -DartifactId=no-such-method-common -Dversion=1.0-SNAPSHOT -Dpackaging=jar -Dfile=./lib/no-such-method-common-1.0-SNAPSHOT.jar