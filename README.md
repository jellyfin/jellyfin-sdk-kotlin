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

This will open the connection in a background thread, and periodically check to ensure it's still connected. The web socket provides various events that can be used to receive notifications from the server. Simply override the methods in ApiEventListener:


``` java

			@Override
            public void onSetVolumeCommand(int value)
    		{
    		}
```

# Multi-Server Usage #


The above examples are designed for cases when your app always connects to a single server, and you always know the address. An example is an app that will always run within a local network and only connect to one server at a time. If your app is designed to support multiple networks and/or multiple servers, then **IConnectionManager** should be used in place of the above example.










Special thanks to [Tangible Software Solutions](http://www.tangiblesoftwaresolutions.com/ "Tangible Software Solutions") for donating a license to our project.
