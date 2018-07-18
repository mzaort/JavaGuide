#!/bin/sh 
set -x
APP_HOME=$(readlink -f $(dirname $0))
APP_MAIN=com.fit.test.Test
JAVA_OPTS="-Xms256m -Xmx512m" 
 
CLASSPATH=$APP_HOME/Java-Guide-1.0.jar
for jar in "$APP_HOME"/lib/*.jar; 
do  
  CLASSPATH="$CLASSPATH":"$jar"  
done  
java $JAVA_OPTS -classpath $CLASSPATH $APP_MAIN

