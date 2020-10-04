# Kotlin Console Example

This sample project uses the kotlinx-cli library to build a command line tool that uses the Jellyfin
library. It's used as a showcase of the libraries abilities and is not meant for general use.

Features include:
  - Server discovery
  - List public users
  - Authenticate
  - List libraries

## Basic usage
Assuming the binary is called `jellyfin` the following sample will list all libraries in the demo instance:
```sh
jellyfin libraries --server https://demo.jellyfin.org/stable --token $(jellyfin login --server https://demo.jellyfin.org/stable --username demo)
```

This command is also provided in the `test.sh` file. It requires a local install first using `./gradlew installDist`.
