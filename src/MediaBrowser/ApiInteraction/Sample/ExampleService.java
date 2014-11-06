package MediaBrowser.ApiInteraction.Sample;

import MediaBrowser.ApiInteraction.*;
import MediaBrowser.ApiInteraction.Android.VolleyHttpClient;
import MediaBrowser.ApiInteraction.Http.IAsyncHttpClient;
import MediaBrowser.ApiInteraction.Serialization.BoonJsonSerializer;
import MediaBrowser.Model.Dto.BaseItemDto;
import MediaBrowser.Model.Entities.SortOrder;
import MediaBrowser.Model.Logging.ILogger;
import MediaBrowser.Model.Querying.*;
import MediaBrowser.Model.Serialization.IJsonSerializer;
import MediaBrowser.Model.Session.ClientCapabilities;
import android.app.Application;

public class ExampleService extends Application {

    ApiClient apiClient;
    ILogger logger;

    @Override
    public void onCreate() {

        super.onCreate();


        // Developers should create their own logger implementation
        logger = new ConsoleLogger();

        // The underlying http stack. Developers can inject their own if desired
        IAsyncHttpClient volleyHttpClient = new VolleyHttpClient(logger, getApplicationContext());

        ClientCapabilities capabilities = new ClientCapabilities();

        ApiEventListener apiEventListener = new ApiEventListener();

        IJsonSerializer jsonSerializer = new BoonJsonSerializer();

        apiClient = new ApiClient(volleyHttpClient, jsonSerializer, logger, "http://localhost:8096", "My api key", apiEventListener, capabilities);
    }

    public void GetItems(){

        ItemQuery query = new ItemQuery();

        query.setSortBy(new String[]{ItemSortBy.DateCreated});
        query.setSortOrder(SortOrder.Descending);
        query.setFilters(new ItemFilter[]{ItemFilter.IsNotFolder});
        query.setLimit(10);

        Response<QueryResult<BaseItemDto>> response = new Response<QueryResult<BaseItemDto>>(){

            @Override
            public void onResponse(QueryResult<BaseItemDto> jsonResponse) {

                // Do something with response
            }

            @Override
            public void onError(Exception ex) {

                // Do something with error
            }
        };

        // Get the ten most recently added items for the current user
        apiClient.GetItemsAsync(query, new Response<ItemsResult>());

    }
}
