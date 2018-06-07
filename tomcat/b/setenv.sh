#!/bin/sh

set -e

if [ ! -z "$CLASSPATH" ];
    then CLASSPATH="$CLASSPATH":
fi
CLASSPATH="$CLASSPATH""$CATALINA_BASE"/lib

JARS="log4j-core-2.11.0.jar log4j-api-2.11.0.jar log4j-jul-2.11.0.jar"
for jar in $JARS; do
    export CLASSPATH="$CLASSPATH":"$CATALINA_HOME"/lib/"$jar"
done

export LOGGING_MANAGER="-Djava.util.logging.manager=org.apache.logging.log4j.jul.LogManager"
export LOGGING_CONFIG="-Dlog4j.configurationFile=${CATALINA_BASE}/conf/log4j2.xml"
