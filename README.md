MediaBrowser.ApiClient.Java
===========================

This library allows Android clients to easily access the Media Browser Rest API. It is built with Volley, OkHttp, Boon and Robolectric. The dependencies are modular and can easily be swapped out with alternate implementations, if needed.

# Single Server Example #

This is an example of connecting to a single server using a fixed, predictable address, from an app that has user-specific features.


``` java

        // Developers should create their own logger implementation
        logger = new ConsoleLogger();

        // The underlying http stack. Developers can inject their own if desired
        IAsyncHttpClient volleyHttpClient = new VolleyHttpClient(logger, getApplicationContext());

		ApiClient apiClient = new ApiClient(volleyHttpClient, logger, "http://localhost:8096", "My app name", "My device", "My device id", "app version 123", new ApiEventListener(), new ClientCapabilities());

		apiClient.AuthenticateUserAsync("username", "password", new Response<AuthenticationResult>(){

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

A complete [app example can be found here.](https://github.com/MediaBrowser/MediaBrowser.ApiClient.Java/blob/master/src/MediaBrowser/ApiInteraction/Sample/ExampleApp.java "app example can be found here.")

# Service Apps #

If your app is some kind of service or utility (e.g. Sickbeard), you should construct ApiClient with your api key.

``` java

        // Developers should create their own logger implementation
        logger = new ConsoleLogger();

        // The underlying http stack. Developers can inject their own if desired
        IAsyncHttpClient volleyHttpClient = new VolleyHttpClient(logger, getApplicationContext());

		// Services should just authenticate using their api key
        ApiClient apiClient = new ApiClient(volleyHttpClient, logger, "http://localhost:8096", "My api key", new ApiEventListener(), new ClientCapabilities());

```

A complete [service example can be found here.](https://github.com/MediaBrowser/MediaBrowser.ApiClient.Java/blob/master/src/MediaBrowser/ApiInteraction/Sample/ExampleService.java "service example can be found here.")


# Web Socket #

Once you have an ApiClient instance, you can easily connect to the server's web socket using:

``` java

            ApiClient.OpenWebSocket();
```

This will open a connection in a background thread, and periodically check to ensure it's still connected. The web socket provides various events that can be used to receive notifications from the server. Simply override the methods in ApiEventListener:


``` java

			@Override
            public void onSetVolumeCommand(int value)
    		{
    		}
```

# Multi-Server Usage #


The above examples are designed for cases when your app always connects to a single server, and you always know the address. An example is an app that will always run within a local network and only connect to one server at a time. If your app is designed to support multiple networks and/or multiple servers, then **IConnectionManager** should be used in place of the above example.

IConnectionManager features:

- Supports connection to multiple servers
- Automatic local server discovery
- Wake on Lan
- Automatic LAN to WAN failover


``` c#

            // Developers are encouraged to create their own ILogger implementation
			ILogger logger = new NullLogger();

			// This describes the device capabilities
			ClientCapabilities capabilities = new ClientCapabilities();

			// Developers will have to implement this
			IDevice device = new AndroidDevice();
			
			// Developers will have to implement ICredentialProvider
			ICredentialProvider credentialProvider = new CredentialProvider();

			// If using the portable class library you'll need to supply your own INetworkConnection implementation.
			INetworkConnection networkConnection = new NetworkConnection(logger);

            // If using the portable class library you'll need to supply your own IServerLocator implementation.
			IServerLocator serverLocator = new ServerLocator(logger);

            // The underlying http stack. Developers can inject their own if desired
        	IAsyncHttpClient volleyHttpClient = new VolleyHttpClient(logger, getApplicationContext());

			ApiEventListener eventListener = new ApiEventListener();

			IConnectionManager connectionManager = new ConnectionManager(credentialProvider,
                networkConnection,
                logger,
                serverLocator,
                volleyHttpClient,
				"My app name"
                "1.0.0.0",
                device,
                capabilities,
                eventListener);
          
```

# Multi-Server Startup Workflow #

After you've created your instance of IConnectionManager, simply call the Connect method. It will return a result object with three properties:

- State
- ServerInfo
- ApiClient

ServerInfo and ApiClient will be null if State == Unavailable. Let's look at an example.


``` java

         connectionManager.Connect(new Response<ConnectionResult>(){

            @Override
            public void onResponse(ConnectionResult result) {

				switch (result.getState())
				{
					case ConnectionState.Unavailable:
						// No servers found. User must manually enter connection info.
	
					case ConnectionState.ServerSignIn:
						// A server was found and the user needs to login.
						// Display a login screen and authenticate with the server using result.ApiClient
	
					case ConnectionState.SignedIn:
						// A server was found and the user has been signed in using previously saved credentials.
						// Ready to browse using result.ApiClient
				}

            }

        });


```

When the user wishes to logout of the individual server, simply call apiClient.Logout as normal.

If the user wishes to connect to a new server, simply use the Connect overload that accepts an address. 


``` java

		 String address = "http://192.168.1.174:8096";

         connectionManager.Connect(address, new Response<ConnectionResult>(){

            @Override
            public void onResponse(ConnectionResult result) {

				// Proceed with same switch statement as above example

            }

        });

			

```

If at anytime the RemoteLoggedOut event is fired, simply start the workflow all over again by calling connectionManager.Connect().

ConnectionManager will handle opening and closing web socket connections at the appropiate times. All your app needs to do is use an ApiClient instance to subscribe to individual events.


``` java

			@Override
            public void onSetVolumeCommand(int value)
    		{
    		}

```

With multi-server connectivity it is not recommended to keep a global ApiClient instance, or pass an ApiClient around the application. Instead keep a factory that will resolve the appropiate ApiClient instance depending on context. In order to help with this, ConnectionManager has a GetApiClient method that accepts a BaseItemDto and returns an ApiClient from the server it belongs to.


Special thanks to [Tangible Software Solutions](http://www.tangiblesoftwaresolutions.com/ "Tangible Software Solutions") for donating a license to our project.
