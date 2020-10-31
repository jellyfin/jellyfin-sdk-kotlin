#!/bin/bash

function jellyfin() {
	"$(pwd)/build/install/kotlin-cli/bin/kotlin-cli" "$@"
}

server=https://demo.jellyfin.org/stable

jellyfin users --server $server

jellyfin libraries --server $server --token "$(jellyfin login --server $server --username demo)"
