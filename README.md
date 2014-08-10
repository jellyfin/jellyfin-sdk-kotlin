MediaBrowser.ApiClient.Java
===========================

ApiClient allows Java clients to easily access the Media Browser Rest API.


Usage is very simple:

``` java

			// Developers should create their own logger implementation
			ILogger logger = new NullLogger();

			// The underlying http stack. Developers can inject their own if desired
            VolleyHttpClient volleyHttpClient = new VolleyHttpClient(logger, context);

			ApiClient client = new ApiClient(volleyHttpClient, logger, "http://localhost:8096", "My client name", "My device", "My device id");

			var authResult = await AuthenticateUserAsync("username", passwordHash);

            // Get the ten most recently added items for the current user
            var items = await client.GetItemsAsync(new ItemQuery
            {
                UserId = client.UserId,

                SortBy = new[] { ItemSortBy.DateCreated },
                SortOrder = SortOrder.Descending,

                // Get media only, don't return folder items
                Filters = new[] { ItemFilter.IsNotFolder },

                Limit = 10,

                // Search recursively through the user's library
                Recursive = true
            });

			await client.Logout();
```

# Service Apps #

If your app is some kind of service or utility (e.g. Sickbeard), you should construct ApiClient with your api key.

``` java

            // Developers should create their own logger implementation
			ILogger logger = new NullLogger();

            // The underlying http stack. Developers can inject their own if desired
            VolleyHttpClient volleyHttpClient = new VolleyHttpClient(logger, context);

			ApiClient client = new ApiClient(volleyHttpClient, logger, "http://localhost:8096", "0123456789");

			// Get the ten most recently added items for the current user
            client.GetItemsAsync(new ItemQuery
            {
                SortBy = new[] { ItemSortBy.DateCreated },
                SortOrder = SortOrder.Descending,

                // Get media only, don't return folder items
                Filters = new[] { ItemFilter.IsNotFolder },

                Limit = 10,

                // Search recursively through the user's library
                Recursive = true
            });
```




# Logging and Interfaces #

ApiClient and ApiWebSocket both have additional constructors available allowing you to pass in your own implementation of ILogger. The default implementation is NullLogger, which provides no logging. In addition you can also pass in your own implementation of IJsonSerializer, or use our ours which is currently based on Gson, although a switch to Jackson or Boon is planned.
















Special thanks to [Tangible Software Solutions](http://www.tangiblesoftwaresolutions.com/ "Tangible Software Solutions") for donating a license to our project.
