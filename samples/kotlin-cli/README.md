# Kotlin CLI Example

This sample project uses the Clikt library to build a command line tool that uses the Jellyfin
SDK. It is used as a showcase the abilities of the SDK and is not meant for general use.

Features include:
  - Server discovery
  - List public users
  - Authenticate
  - List libraries

## Basic usage
Assuming the binary is called `jellyfin` the following sample will list all libraries in the demo
instance:
```sh
JELLYFIN_SERVER="https://demo.jellyfin.org/stable"
JELLYFIN_TOKEN=`jellyfin login --username demo`

jellyfin libraries
```

This script is also provided as part of the `test.sh` script.
