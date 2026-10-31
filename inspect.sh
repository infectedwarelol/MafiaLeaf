#!/usr/bin/env bash
set -uo pipefail

cd "$(dirname "$0")" || exit 1

owned_sources="mafialeaf-server/src/main/java/me/jules/mafialeaf"
errors=0
warnings=0

error() {
    echo "error: $*"
    errors=$((errors + 1))
}

warning() {
    echo "warning: $*"
    warnings=$((warnings + 1))
}

build_log="$(mktemp)"
trap 'rm -f "$build_log"' EXIT

echo "==> Building"
if ! ./gradlew createMojmapPaperclipJar --console=plain >"$build_log" 2>&1; then
    error "gradle build failed: $(grep -m1 -E 'error:|FAILURE|What went wrong' "$build_log" || echo 'see gradle output')"
fi
while IFS= read -r line; do
    warning "javac: $line"
done < <(grep -E 'me/jules/mafialeaf/.*\.java:[0-9]+: warning:' "$build_log")

echo "==> Checking jar"
version="$(sed -n 's/^version=//p' gradle.properties)"
jar="mafialeaf-server/build/libs/mafialeaf-paperclip-${version}-mojmap.jar"
server_jar="mafialeaf-server/build/libs/mafialeaf-server-${version}.jar"
if [[ -z "$version" ]]; then
    error "gradle.properties has no version"
elif [[ ! -f "$jar" ]]; then
    error "$jar was not built"
elif [[ ! -f "$server_jar" ]]; then
    error "$server_jar was not built"
else
    brand="$(unzip -p "$server_jar" META-INF/MANIFEST.MF 2>/dev/null | tr -d '\r' | sed -n 's/^Brand-Name: //p')"
    if [[ "$brand" != "MafiaLeaf" ]]; then
        error "manifest Brand-Name is '$brand', expected 'MafiaLeaf'"
    fi
fi

echo "==> Checking patches"
for repository in paper-server mafialeaf-server/src/minecraft/java; do
    if [[ -d "$repository/.git" ]] && [[ -n "$(git -C "$repository" status --porcelain)" ]]; then
        warning "$repository has uncommitted changes that are not in the patch files"
    fi
done

echo "==> Checking sources"
while IFS= read -r line; do
    warning "comment: $line"
done < <(grep -rnE '^\s*(//|/\*|\*)' "$owned_sources")
while IFS= read -r line; do
    warning "unfinished: $line"
done < <(grep -rnE 'TODO|FIXME|XXX' "$owned_sources")
while IFS= read -r line; do
    error "upstream branding: $line"
done < <(grep -rnE 'api\.leafmc\.one|leafmc\.one/download|Winds-Studio/Leaf/issues - ' mafialeaf-server/src/main/java paper-server/src/main/java --include=*.java)

echo "==> $errors error(s), $warnings warning(s)"
[[ $errors -eq 0 && $warnings -eq 0 ]]
