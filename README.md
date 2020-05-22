<h1 align="center">Jellyfin</h1>
<h3 align="center">The Free Software Media System</h3>

---

<p align="center">
<img alt="Logo Banner" src="https://raw.githubusercontent.com/jellyfin/jellyfin-ux/master/branding/SVG/banner-logo-solid.svg?sanitize=true"/>
<br/>
<br/>
<a href="https://github.com/jellyfin/jellyfin-apiclient-java">
<img alt="MIT license" src="https://img.shields.io/github/license/jellyfin/jellyfin-apiclient-java.svg"/>
</a>
<a href="https://github.com/jellyfin/jellyfin-apiclient-java/releases">
<img alt="Current Release" src="https://img.shields.io/github/release/jellyfin/jellyfin-apiclient-java.svg"/>
</a>
<a href="https://dev.azure.com/jellyfin-project/jellyfin/_build/latest?definitionId=6&branchName=master">
<img alt="Azure DevOps builds" src="https://dev.azure.com/jellyfin-project/jellyfin/_apis/build/status/Jellyfin%20API%20Client%20Java%20CI?branchName=master">
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

This library allows Java and Android applications to easily access the Jellyfin API. It is built with Volley, OkHttp, Boon, and Robolectric. The dependencies are modular and can easily be swapped out with alternate implementations when desired.

## Single Server Example

This is an example of connecting to a single server using a fixed address from an app that has requires a user login.

``` java
// Developers should create their own logger implementation
logger = new NullLogger();

// The underlying http stack. Developers can inject their own if desired
IAsyncHttpClient httpClient = new VolleyHttpClient(logger, getApplicationContext());

// Android developers should use AndroidDevice
IDevice device = new Device("deviceId", "deviceName");

ApiClient apiClient = new ApiClient(httpClient, logger, "http://localhost:8096", "My app name", "app version 123", device, new ApiEventListener());

apiClient.AuthenticateUserAsync("username", "password", new Response<AuthenticationResult>() {
    @Override
    public void onResponse(AuthenticationResult result) {
        // Authentication succeeded
    }

    @Override
    public void onError() {
        // Authentication failed
    }
});
```

## Service Apps

If your app is some kind of service or utility (e.g. Sickbeard), you should construct ApiClient with an API key supplied by your users.

``` java
// Developers should create their own logger implementation
logger = new NullLogger();

// The underlying http stack. Developers can inject their own if desired
IAsyncHttpClient httpClient = new VolleyHttpClient(logger, getApplicationContext());

// Services should just authenticate using their api key
ApiClient apiClient = new ApiClient(httpClient, logger, "http://localhost:8096", "apikey", new ApiEventListener());
```

## Web Socket

Once you have an ApiClient instance you can easily connect to the server's web socket using the following command.

``` java
ApiClient.OpenWebSocket();
```

This will open a connection in a background thread, and periodically check to ensure it's still connected. The web socket provides various events that can be used to receive notifications from the server. Simply override the methods in ApiEventListener:

``` java
@Override
public void onSetVolumeCommand(int value) {
}
```

## Multi-Server Usage

The above examples are designed for cases when your app always connects to a single server, and you always know the address. If your app is designed to support multiple networks and/or multiple servers, then **IConnectionManager** should be used in place of the above example.

IConnectionManager features:

- Supports connection to multiple servers
- Automatic local server discovery
- Wake on Lan
- Automatic LAN to WAN failover

``` java
// Android developer should use AndroidCredentialProvider
ICredentialProvider credentialProvider = new CredentialProvider();

INetworkConnection networkConnection = new NetworkConnection(logger);

// Developers are encouraged to create their own ILogger implementation
ILogger logger = new NullLogger();

// The underlying http stack. Developers can inject their own if desired
IAsyncHttpClient httpClient = new VolleyHttpClient(logger, getApplicationContext());

// Android developers should use AndroidDevice
IDevice device = new Device("deviceId", "deviceName");

// This describes the device capabilities
ClientCapabilities capabilities = new ClientCapabilities();

ApiEventListener eventListener = new ApiEventListener();

// Android developers should use AndroidConnectionManager
IConnectionManager connectionManager = new ConnectionManager(credentialProvider,
    networkConnection,
    logger,
    httpClient,
    "My app name"
    "1.0.0.0",
    device,
    capabilities,
    eventListener);
```

## Multi-Server Startup Workflow

After you've created your instance of IConnectionManager, simply call the Connect method. It will return a result object with three properties:

- State
- ServerInfo
- ApiClient

ServerInfo and ApiClient will be null if State == Unavailable. Let's look at an example.

``` java
connectionManager.Connect(new Response<ConnectionResult>() {
    @Override
    public void onResponse(ConnectionResult result) {
        switch (result.getState()) {
            case ConnectionState.ConnectSignIn:
                // Connect sign in screen should be presented
                // Authenticate using LoginToConnect, then call Connect again to start over
            case ConnectionState.ServerSignIn:
                // A server was found and the user needs to login.
                // Display a login screen and authenticate with the server using result.ApiClient
            case ConnectionState.ServerSelection:
                // Multiple servers available
                // Display a selection screen by calling GetAvailableServers
                // When a server is chosen, call the Connect overload that accept either a ServerInfo object or a String url.
            case ConnectionState.SignedIn:
                // A server was found and the user has been signed in using previously saved credentials.
                // Ready to browse using result.ApiClient
        }
    }
);
```

When the user wishes to logout of the individual server simply call apiClient.Logout() with no special parameters. If the user will to connect to a new server use the Connect overload which accepts an address for the new server.

``` java
String address = "http://192.168.1.174:8096";
connectionManager.Connect(address, new Response<ConnectionResult>() {
    @Override
    public void onResponse(ConnectionResult result) {
        switch (result.State) {
            case ConnectionState.Unavailable:
                // Server unreachable
            case ConnectionState.ServerSignIn:
                // A server was found and the user needs to login.
                // Display a login screen and authenticate with the server using result.ApiClient
            case ConnectionState.SignedIn:
                // A server was found and the user has been signed in using previously saved credentials.
                // Ready to browse using result.ApiClient
        }
    }
);
```

If at anytime the RemoteLoggedOut event is fired, simply start the workflow all over again by calling connectionManager.Connect().

ConnectionManager will handle opening and closing web socket connections at the appropiate times. All your app needs to do is use an ApiClient instance to subscribe to individual events.

``` java
@Override
public void onSetVolumeCommand(int value) {
}
```

With multi-server connectivity it is not recommended to keep a global ApiClient instance, or pass an ApiClient around the application. Instead keep a factory that will resolve the appropriate ApiClient instance depending on context. In order to help with this, ConnectionManager has a GetApiClient method that accepts a BaseItemDto and returns an ApiClient from the server it belongs to.

## Android Usage

Android is fully supported, and special subclasses are provided for it:

- AndroidConnectionManager
- AndroidApiClient
