#!/bin/bash

function jellyfin() {
	"$(pwd)/build/install/kotlin-console/bin/kotlin-console" "$@"
}

server=https://demo.jellyfin.org/stable

jellyfin libraries --server $server --token "$(jellyfin login --server $server --username demo)"
