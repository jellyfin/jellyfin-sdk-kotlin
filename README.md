MediaBrowser.ApiClient.Java
===========================

This library is built with Volley and OkHttp, and allows Android clients to easily access the Media Browser Rest API. The http stack can be swapped out if a pure Java version is needed.


Usage is very simple:

``` java

        // Developers should create their own logger implementation
        logger = new ConsoleLogger();

        // The underlying http stack. Developers can inject their own if desired
        IAsyncHttpClient volleyHttpClient = new VolleyHttpClient(logger, getApplicationContext());

        apiClient = new ApiClient(volleyHttpClient, logger, "http://localhost:8096", "My app name", "My device", "My device id", "app version 123");

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
        apiClient = new ApiClient(volleyHttpClient, logger, "http://localhost:8096", "My api key");

```

A complete [service example can be found here.](https://github.com/MediaBrowser/MediaBrowser.ApiClient.Java/blob/master/src/MediaBrowser/ApiInteraction/Sample/ExampleService.java "service example can be found here.")



# Logging and Interfaces #

ApiClient and ApiWebSocket both have additional constructors available allowing you to pass in your own implementation of ILogger. The default implementation is ConsoleLogger, which provides logging using System.Out. In addition you can also pass in your own implementation of IJsonSerializer, or use ours which is currently based on Gson, although a switch to Jackson or Boon is planned.
















Special thanks to [Tangible Software Solutions](http://www.tangiblesoftwaresolutions.com/ "Tangible Software Solutions") for donating a license to our project.
