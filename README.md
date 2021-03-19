<h1 align="center">Jellyfin Kotlin API Client</h1>
<h3 align="center">Part of the <a href="https://jellyfin.org/">Jellyfin Project</a></h3>

---

<p align="center">
<img alt="Logo Banner" src="https://raw.githubusercontent.com/jellyfin/jellyfin-ux/master/branding/SVG/banner-logo-solid.svg?sanitize=true"/>
<br/>
<br/>
<a href="https://github.com/jellyfin/jellyfin-apiclient-java">
<img alt="LGPL 3.0 license" src="https://img.shields.io/github/license/jellyfin/jellyfin-apiclient-java.svg"/>
</a>
<a href="https://github.com/jellyfin/jellyfin-apiclient-java/releases">
<img alt="Current Release" src="https://img.shields.io/github/release/jellyfin/jellyfin-apiclient-java.svg"/>
</a>
<a href="https://bintray.com/jellyfin/jellyfin-apiclient-java/jellyfin-apiclient-java">
<img alt="Bintray Release" src="https://img.shields.io/bintray/v/jellyfin/jellyfin-apiclient-java/jellyfin-apiclient-java.svg"/>
</a>
<br/>
<a href="https://opencollective.com/jellyfin">
<img alt="Donate" src="https://img.shields.io/opencollective/all/jellyfin.svg?label=backers"/>
</a>
<a href="https://matrix.to/#/+jellyfin-android-dev:matrix.org">
<img alt="Chat on Matrix" src="https://img.shields.io/matrix/jellyfin-android-dev:matrix.org.svg?logo=matrix"/>
</a>
<a href="https://www.reddit.com/r/jellyfin">
<img alt="Join our Subreddit" src="https://img.shields.io/badge/reddit-r%2Fjellyfin-%23FF5700.svg"/>
</a>
<a href="https://github.com/jellyfin/jellyfin-apiclient-java/releases.atom">
<img alt="Release RSS Feed" src="https://img.shields.io/badge/rss-releases-ffa500?logo=rss" />
</a>
<a href="https://github.com/jellyfin/jellyfin-apiclient-java/commits/master.atom">
<img alt="Master Commits RSS Feed" src="https://img.shields.io/badge/rss-commits-ffa500?logo=rss" />
</a>
</p>

---

The Jellyfin Kotlin API Client is a library implementing the Jellyfin API to easily access servers.
It is currently available for the JVM and Android platforms.

## Setup

Releases are published to `mavenCentral()`. Make sure to use the correct library depending on your
platform.

![Latest version on Maven Central](https://img.shields.io/maven-central/v/org.jelylfin.apiclient/jellyfin-core)

**Gradle with Kotlin DSL**

```kotlin
implementation("org.jellyfin.apiclient:jellyfin-core:$apiclientVersion")

// Or when using Android
implementation("org.jellyfin.apiclient:jellyfin-platform-android:$apiclientVersion")
```

<details>
  <summary>Gradle with Groovy</summary>
  
  ```groovy
  implementation "org.jellyfin.apiclient:jellyfin-core:$apiclientVersion"

  // Or when using Android
  implementation "org.jellyfin.apiclient:jellyfin-platform-android:$apiclientVersion"
   ```
</details>

<details>
  <summary>Maven</summary>
  
  ```xml
  <dependency>
      <groupId>org.jellyfin.apiclient</groupId>
      <artifactId>jellyfin-core</artifactId>
      <version>$apiclientVersion</version>
  </dependency>

  <!-- Or when using Android -->
  <dependency>
      <groupId>org.jellyfin.apiclient</groupId>
      <artifactId>jellyfin-platform-android</artifactId>
      <version>$apiclientVersion</version>
  </dependency>
   ```
</details>

## Usage

### Creating a Jellyfin instance

Most functionality of the API Client requires an instance of the Jellyfin class. This class holds
the configuration required to make API calls and platform specific options. The Jellyfin class can
be instantiated using a custom Kotlin DSL:

```kotlin
val jellyfin = Jellyfin {
    // Uncomment when using jellyfin-platform-android:
    // android()

    clientInfo = ClientInfo(name = "My awesome client!", version = "1.33.7",)
    
    // Uncomment if not using jellyfin-platform-android:
    // deviceInfo = DeviceInfo(id = UUID.randomUUID().toString(), name = "Awesome device",)   
}
```

Make sure to supply the client and device information if you want to make API calls. Use the
`android()` helper function when targeting Android to enable server discovery and set the device
information automatically. 

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
)
```

### Authenticating a user

All API operations are grouped. To make use of an operation you need to construct an instance of the
group and give it your API instance.

```kotlin
val userApi = UserApi(api)

val authenticationResult by userApi.authenticateUserByName(
    username = "demo", 
    password = "",
)

// Use access token in api instance
api.accessToken = authenticationResult.accessToken

println(authenticationResult.accessToken)
```

### WebSockets

Jellyfin uses WebSockets to communicate events like library changes and activities. Using this API
can be done with the special WebSocketApi class.

```kotlin
val webSocketApi = WebSocketApi(api)

// Publish messages
webSocketApi.publish(ActivityLogEntryStartMessage())
webSocketApi.publish(SessionsStartMessage())
webSocketApi.publish(ScheduledTasksInfoStartMessage())

// Listen for messages
webSocketApi.subscribe().onEach { message ->
    println(message)
}.collect()
```

### Server discovery

The server discovery feature can be used to find servers on the local network, normalize inputted
server addresses and to determine the best server to use from a list of adresses.
 
```kotlin
// Discover servers on the local network
jellyfin.discovery.discoverLocalServers().onEach {
    println("Server ${it.name} was found at address ${it.address}")
}.collect()

// Get all candidates for a given input
val candidates = jellyfin.discovery.getAddressCandidates("demo.jellyfin.org/stable")

// Get best option from the candidates
val recommended = jellyfin.discovery.getRecommendedServer(candidates)
```

## More examples

We provide a few small projects in the [samples](/samples) folder. The samples are used for testing
new features and can be used as a basis for your own application.

## Projects using the API client

### Official Jellyfin clients

  - [Jellyfin for Android](https://github.com/jellyfin/jellyfin-android) is the official Android client for phones and tablets.
  - [Jellyfin for Android TV](https://github.com/jellyfin/jellyfin-androidtv) is the official Android TV client for Android TV, Nvidia Shield, Amazon Fire TV and Google TV.

### Third party clients

  - [Gelli](https://github.com/dkanada/gelli) is a music-focused Android client.

_Want to add your project? Please create a pull request!_
