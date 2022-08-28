# Getting started

The SDK is currently available for the JVM and Android 4.4 and up. Java 8 or higher is required. Android versions below
Android 8 should use [library desugaring](https://developer.android.com/studio/write/java8-support#library-desugaring).

## Setup

Releases are published to `mavenCentral()`. Make sure to use the correct library depending on your
platform.

![Latest version on Maven Central](https://img.shields.io/maven-central/v/org.jellyfin.sdk/jellyfin-core.svg)

**Gradle with Kotlin DSL**

```kotlin
implementation("org.jellyfin.sdk:jellyfin-core:$sdkVersion")
```

<details>
  <summary>Gradle with Groovy</summary>

  ```groovy
  implementation "org.jellyfin.sdk:jellyfin-core:$sdkVersion"
   ```
</details>

<details>
  <summary>Maven</summary>

  ```xml
  <dependency>
      <groupId>org.jellyfin.sdk</groupId>
      <artifactId>jellyfin-core</artifactId>
      <version>$sdkVersion</version>
  </dependency>
   ```
</details>

<details>
  <summary>Using SNAPSHOT versions</summary>

When working on new features in your application you might need a build of the SDK targeting the next server version.
For this use case we publish two SNAPSHOT releases: `master-SNAPSHOT` and `openapi-unstable-SNAPSHOT`. To use the
snapshot versions, add the snapshot repository to your build script:
`https://s01.oss.sonatype.org/content/repositories/snapshots/`

An example using Gradle with Kotlin DSL that only allows the `master-SNAPSHOT` version:

  ```kotlin
  repositories {
      maven("https://s01.oss.sonatype.org/content/repositories/snapshots/") {
          content {
              // Only allow SDK snapshots
              includeVersionByRegex("org\\.jellyfin\\.sdk", ".*", "master-SNAPSHOT")
          }
      }
  }
   ```
</details>

## Usage

### Creating a Jellyfin instance

Most functionality of the SDK requires an instance of the Jellyfin class. This class holds
the configuration required to make API calls and platform specific options. The Jellyfin class can
be instantiated using a custom Kotlin DSL:

```kotlin
val jellyfin = createJellyfin {
    clientInfo = ClientInfo(name = "My awesome client!", version = "1.33.7",)

    // Uncomment when using android:
    // context = /* Android Context */
}
```

Make sure to supply the client information if you want to make API calls. The context is required for Android
applications.

### Creating an API instance

API calls require an API instance. This can be done with the createApi function. It requires a
server address. The client and device information are set automatically but can be changed. All
properties can be changed later in the API instance.

```kotlin
val api = jellyfin.createApi(
    baseUrl = "https://demo.jellyfin.org/stable/",
    // Optional options:
    // accessToken = "access token or api key"
    // clientInfo = ClientInfo(), // defaults to parent info
    // deviceInfo = DeviceInfo(), // defaults to parent info
    // httpClientOptions = HttpClientOptions() // allows setting additional options
)
```

### Authenticating a user

All API operations are grouped. To make use of an operation you need to first get the group from your ApiClient
instance. All groups are defined as extension functions, and you can alternatively construct the API instances
manually.

```kotlin
val userApi = api.userApi

try {
    val authenticationResult by userApi.authenticateUserByName(
        username = "demo", 
        password = "",
    )
    
    // Use access token in api instance
    api.accessToken = authenticationResult.accessToken
    
    // Print session information
    println(authenticationResult.sessionInfo)
} catch(err: ApiClientException) {
    // Catch exceptions
    println("Something went wrong! ${err.message}")
}
```

### WebSockets

Jellyfin uses WebSockets to communicate events like library changes and activities. This API can be
used with the SocketApi. Documentation is available in the [docs](/docs) folder.

```kotlin
val instance = api.ws()

instance.addGlobalListener { message ->
	println(message)
}
```

### Server discovery

The server discovery feature can be used to find servers on the local network, normalize inputted
server addresses and to determine the best server to use from a list of addresses.

```kotlin
// Discover servers on the local network
jellyfin.discovery.discoverLocalServers().collect {
    println("Server ${it.name} was found at address ${it.address}")
}

// Get all candidates for a given input
val candidates = jellyfin.discovery.getAddressCandidates("demo.jellyfin.org/stable")

// Get a flow of potential servers to connect to
val recommended = jellyfin.discovery.getRecommendedServers(candidates, RecommendedServerInfoScore.GOOD)
```

## More examples

We provide a few small projects in the [samples](/samples) folder. The samples are used for testing
new features and can be used as a basis for your own application.

## Projects using the SDK

### Official Jellyfin clients

- [Jellyfin for Android](https://github.com/jellyfin/jellyfin-android) is the official Android client for phones and tablets.
- [Jellyfin for Android TV](https://github.com/jellyfin/jellyfin-androidtv) is the official Android TV client for Android TV, Nvidia Shield, Amazon Fire TV and Google TV.

### Third party clients

- [Findroid](https://github.com/jarnedemeulemeester/findroid) provides a native user interface to browse and play movies and series.

_Want to add your project? We'd love to know about it, open an issue or create pull request!_
