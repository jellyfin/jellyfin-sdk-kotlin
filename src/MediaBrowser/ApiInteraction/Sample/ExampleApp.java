package MediaBrowser.ApiInteraction.Sample;

import MediaBrowser.ApiInteraction.ApiClient;
import MediaBrowser.ApiInteraction.IAsyncHttpClient;
import MediaBrowser.ApiInteraction.Response;
import MediaBrowser.ApiInteraction.VolleyHttpClient;
import MediaBrowser.Model.Dto.BaseItemDto;
import MediaBrowser.Model.Dto.ItemCounts;
import MediaBrowser.Model.Entities.SortOrder;
import MediaBrowser.Model.Logging.ILogger;
import MediaBrowser.Model.Logging.NullLogger;
import MediaBrowser.Model.Querying.ItemFilter;
import MediaBrowser.Model.Querying.ItemQuery;
import MediaBrowser.Model.Querying.ItemSortBy;
import MediaBrowser.Model.Querying.QueryResult;
import android.app.Application;
import com.android.volley.toolbox.ImageLoader;

public class ExampleApp extends Application {

    ApiClient apiClient;
    ILogger logger;

    @Override
    public void onCreate() {

        super.onCreate();


        // Developers should create their own logger implementation
        logger = new NullLogger();

        // The underlying http stack. Developers can inject their own if desired
        IAsyncHttpClient volleyHttpClient = new VolleyHttpClient(logger, getApplicationContext());

        apiClient = new ApiClient(volleyHttpClient, logger, "http://localhost:8096", "My app name", "My device", "My device id", "app version 123");
    }

    public ImageLoader getImageLoader() {
        return apiClient.getImageLoader();
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
            public void onError() {

                // Do something with error
            }
        };

        // Get the ten most recently added items for the current user
        apiClient.GetItemsAsync(query, new Response<QueryResult<BaseItemDto>>());

    }
}
