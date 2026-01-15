#!/bin/sh
# ----------------------------------------------------------------------------
# Maven Wrapper startup script, version 3.2.0
# ----------------------------------------------------------------------------

MAVEN_WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain

# resolve links
PRG="$0"
while [ -h "$PRG" ]; do
  ls=`ls -ld "$PRG"`
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "$link" : '/.*' > /dev/null; then
    PRG="$link"
  else
    PRG=`dirname "$PRG"`"/$link"
  fi
done

SAVED_PWD=`pwd`
BASEDIR=`dirname "$PRG"`/..;
BASEDIR=`cd "$BASEDIR" && pwd`

if [ -z "$MAVEN_PROJECTBASEDIR" ]; then
  MAVEN_PROJECTBASEDIR="$BASEDIR"
fi

# OS specific support
cygwin=false
mingw=false
case "`uname`" in
  CYGWIN*) cygwin=true ;;
  MINGW*) mingw=true ;;
  *) ;;
esac

MAVEN_WRAPPER_DIR="$MAVEN_PROJECTBASEDIR/.mvn/wrapper"
MAVEN_WRAPPER_JAR="$MAVEN_WRAPPER_DIR/maven-wrapper.jar"
MAVEN_WRAPPER_PROPERTIES="$MAVEN_WRAPPER_DIR/maven-wrapper.properties"

if [ ! -r "$MAVEN_WRAPPER_PROPERTIES" ]; then
  echo "Cannot find $MAVEN_WRAPPER_PROPERTIES" >&2
  exit 1
fi

# download maven-wrapper.jar if necessary
if [ ! -r "$MAVEN_WRAPPER_JAR" ]; then
  echo "Downloading Maven wrapper..." >&2
  wrapperUrl=`sed -n 's/^wrapperUrl=//p' "$MAVEN_WRAPPER_PROPERTIES"`
  if [ -z "$wrapperUrl" ]; then
    wrapperUrl="https://repo.maven.apache.org/maven2/io/takari/maven-wrapper/0.5.6/maven-wrapper-0.5.6.jar"
  fi
  if command -v curl >/dev/null 2>&1; then
    curl -fsSL -o "$MAVEN_WRAPPER_JAR" "$wrapperUrl" || exit 1
  elif command -v wget >/dev/null 2>&1; then
    wget -O "$MAVEN_WRAPPER_JAR" "$wrapperUrl" || exit 1
  else
    echo "Neither curl nor wget is available to download Maven Wrapper." >&2
    exit 1
  fi
fi

if $cygwin || $mingw; then
  MAVEN_PROJECTBASEDIR=`cygpath --path --windows "$MAVEN_PROJECTBASEDIR"`
  MAVEN_WRAPPER_JAR=`cygpath --path --windows "$MAVEN_WRAPPER_JAR"`
fi

JAVA_CMD="${JAVA_HOME:+$JAVA_HOME/bin/}java"

exec "$JAVA_CMD" \
  -classpath "$MAVEN_WRAPPER_JAR" \
  -Dmaven.multiModuleProjectDirectory="$MAVEN_PROJECTBASEDIR" \
  ${MAVEN_OPTS} \
  $MAVEN_WRAPPER_LAUNCHER "$@"
