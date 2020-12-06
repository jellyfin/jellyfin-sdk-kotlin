<h1 align="center">Jellyfin Java API Client</h1>
<h3 align="center">Part of the <a href="https://jellyfin.media">Jellyfin Project</a></h3>

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
<br/>
<a href="https://opencollective.com/jellyfin">
<img alt="Donate" src="https://img.shields.io/opencollective/all/jellyfin.svg?label=backers"/>
</a>
<a href="https://features.jellyfin.org">
<img alt="Submit Feature Requests" src="https://img.shields.io/badge/fider-vote%20on%20features-success.svg"/>
</a>
<a href="https://forum.jellyfin.org">
<img alt="Discuss on our Forum" src="https://img.shields.io/discourse/https/forum.jellyfin.org/users.svg"/>
</a>
<a href="https://matrix.to/#/+jellyfin:matrix.org">
<img alt="Chat on Matrix" src="https://img.shields.io/matrix/jellyfin:matrix.org.svg?logo=matrix"/>
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

This library allows Java and Android applications to easily access Jellyfin servers.
The dependencies are modular and can easily be swapped out with alternate implementations when desired.

## Setup

The apiclient is available through [JCenter](https://bintray.com/jellyfin/jellyfin-apiclient-java/jellyfin-apiclient-java), and thus can be installed via Gradle like any other dependency:

```kotlin
// build.gradle.kts
repositories {
	jcenter()
}

dependencies {
	val apiclientVersion = "…"

	// For non-Android projects
	implementation("org.jellyfin.apiclient:library:$apiclientVersion")

	// For Android apps (automatically includes the library and models)
	implementation("org.jellyfin.apiclient:android:$apiclientVersion")
}
```

```groovy
// build.gradle
repositories {
	jcenter()
}

dependencies {
	def apiclientVersion = "…"

	// For non-Android projects
	implementation "org.jellyfin.apiclient:library:$apiclientVersion"

	// For Android apps (automatically includes the library and models)
	implementation "org.jellyfin.apiclient:android:$apiclientVersion"
}
```

## Android Example

This Kotlin example creates a new instance of the Jellyfin class with Android support enabled.
It will then try to authenticate to a server with a username and password combination.

```kotlin
// Create a Jellyfin instance
val jellyfin = Jellyfin {
	// It is recommended to create an own logger implementation
	logger = NullLogger()
	android(context)
}

// Create a new api client
val apiClient = jellyfin.createApi(
	serverAddress = "http://localhost:8096",
	device = AndroidDevice.fromContext(context)
)

// Call authenticate function
apiClient.AuthenticateUserAsync("username", "password", object : Response<AuthenticationResult>() {
	override fun onResponse(result: AuthenticationResult) {
		// Authentication succeeded
	}

	override fun onError(error: Exception) {
		// Authentication failed
	}
})
```

## Websockets

Once you have an ApiClient instance you can easily connect to the server's websocket using the following command.

```kotlin
apiClient.OpenWebSocket()
```

This will open a connection in a background thread, and periodically check to ensure it's still connected. The web socket provides various events that can be used to receive notifications from the server. Simply override the methods in the ApiEventListener class which can be passed to the "createApi" function.

```kotlin
override fun onSetVolumeCommand(value: Int) {
}
```

## Using Java

The Jellyfin library supports both Java and Kotlin out of the box. The basic Android example in Java looks like this:

```java
// Create the options using the options builder
JellyfinOptions.Builder options = new JellyfinOptions.Builder();
options.setLogger(new NullLogger());
JellyfinAndroidKt.android(options, context);

// Create a Jellyfin instance
Jellyfin jellyfin = new Jellyfin(options.build());

// Create a new api client
ApiClient apiClient = jellyfin.createApi(
		"http://localhost:8096",
		null,
		AndroidDevice.fromContext(context),
		new ApiEventListener()
);

// Call authenticate function
apiClient.AuthenticateUserAsync("username", "password", new Response<AuthenticationResult>() {
	@Override
	public void onResponse(AuthenticationResult response) {
		// Authentication succeeded
	}

	@Override
	public void onError(Exception exception) {
		// Authentication failed
	}
});
```
