#!/usr/bin/env bash

##############################################################################
##
##  Gradle start up script for UN*X
##
##############################################################################

# Unset CDPATH to avoid potential issues
unset CDPATH

# Resolve the directory of this script
DIRNAME="$(cd "$(dirname "$0")" && pwd)"
APP_BASE_NAME=$(basename "$0")
APP_HOME="$DIRNAME"

# Set default JVM options as an array
DEFAULT_JVM_OPTS=(-Xmx64m -Xms64m)

# Locate java
if [[ -n "$JAVA_HOME" ]]; then
    JAVA="$JAVA_HOME/bin/java"
    if [[ ! -x "$JAVA" ]]; then
        echo "ERROR: JAVA_HOME is set to an invalid directory: $JAVA_HOME" >&2
        echo "Please set the JAVA_HOME variable in your environment to match the location of your Java installation." >&2
        exit 1
    fi
else
    JAVA="java"
    command -v $JAVA >/dev/null 2>&1 || {
        echo "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH." >&2
        echo "Please set the JAVA_HOME variable in your environment to match the location of your Java installation." >&2
        exit 1
    }
fi

CLASSPATH="$APP_HOME/gradle/wrapper/gradle-wrapper.jar"

# Execute Gradle
exec "$JAVA" "${DEFAULT_JVM_OPTS[@]}" ${JAVA_OPTS} ${GRADLE_OPTS} \
    -Dorg.gradle.appname="$APP_BASE_NAME" \
    -classpath "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"

