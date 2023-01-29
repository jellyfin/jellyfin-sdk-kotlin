#!/bin/bash

../../gradlew :samples:kotlin-cli:installDist

function jellyfin() {
	"$(pwd)/build/install/kotlin-cli/bin/kotlin-cli" "$@"
}

export JELLYFIN_SERVER=${1:-"https://demo.jellyfin.org/unstable"}
echo "JELLYFIN_SERVER=$JELLYFIN_SERVER"

export JELLYFIN_TOKEN=`jellyfin login --username demo`
echo "JELLYFIN_TOKEN=$JELLYFIN_TOKEN"

echo "jellyfin users"
jellyfin users

echo "jellyfin libraries"
jellyfin libraries

echo "finished"
