#!/usr/bin/env bash
set -euo pipefail

cd "$(dirname "$0")"

SERVER_JAR="${SERVER_JAR:-mafialeaf-paperclip-1.21.11-R0.1-SNAPSHOT-mojmap.jar}"
HEAP="${HEAP:-20G}"
DIRECT_MEMORY="${DIRECT_MEMORY:-2G}"

exec java \
    -Xms"$HEAP" -Xmx"$HEAP" \
    -XX:MaxDirectMemorySize="$DIRECT_MEMORY" \
    -XX:+UseZGC -XX:+ZGenerational \
    -XX:+AlwaysPreTouch \
    -XX:+DisableExplicitGC \
    -XX:+PerfDisableSharedMem \
    -XX:+UseStringDeduplication \
    -XX:ReservedCodeCacheSize=400M \
    -XX:+ExitOnOutOfMemoryError \
    -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./crash-heap.hprof \
    -Xlog:gc*:file=logs/gc.log:time,uptime:filecount=5,filesize=20M \
    -Dfile.encoding=UTF-8 \
    -jar "$SERVER_JAR" --nogui "$@"
