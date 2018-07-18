#!/bin/sh
set -x
APP_HOME=$(readlink -f $(dirname $0))
APP_MAIN=com.fit.test.Test  
JAVA_OPTS="-Xms256m -Xmx512m"  
 
CLASSPATH=$APP_HOME
for jar in "$APP_HOME"/lib/*.jar;  
do  
  CLASSPATH="$CLASSPATH":"$jar"  
done  
javac -classpath $CLASSPATH $APP_HOME/Test.java

