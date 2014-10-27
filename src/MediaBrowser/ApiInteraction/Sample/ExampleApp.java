package MediaBrowser.ApiInteraction.Sample;

import MediaBrowser.ApiInteraction.*;
import MediaBrowser.ApiInteraction.Device.AndroidDevice;
import MediaBrowser.ApiInteraction.Device.IDevice;
import MediaBrowser.ApiInteraction.Http.VolleyHttpClient;
import MediaBrowser.Model.Dto.BaseItemDto;
import MediaBrowser.Model.Entities.SortOrder;
import MediaBrowser.Model.Logging.ILogger;
import MediaBrowser.Model.Querying.*;
import MediaBrowser.Model.Session.ClientCapabilities;
import MediaBrowser.Model.Users.AuthenticationResult;
import android.app.Application;
import com.android.volley.toolbox.ImageLoader;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class ExampleApp extends Application {

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

        IDevice device = new AndroidDevice(getApplicationContext());

        apiClient = new ApiClient(volleyHttpClient, logger, "http://localhost:8096", "My app name", device, "app version 123", apiEventListener, capabilities);
    }

    public ImageLoader getImageLoader() {
        return apiClient.getImageLoader();
    }

    public void Authenticate() throws UnsupportedEncodingException, NoSuchAlgorithmException {

        apiClient.AuthenticateUserAsync("username", "password", new Response<AuthenticationResult>(){

            @Override
            public void onResponse(AuthenticationResult result) {


            }

            @Override
            public void onError() {


            }

        });
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
        apiClient.GetItemsAsync(query, new Response<ItemsResult>());

    }
}
