package MediaBrowser;

import MediaBrowser.ApiInteraction.*;
import MediaBrowser.ApiInteraction.Http.VolleyHttpClient;
import MediaBrowser.Model.Logging.ILogger;
import MediaBrowser.Model.System.PublicSystemInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(RobolectricTestRunner.class)
public class PublicMethodTests {

    private ILogger logger;
    private ApiClient apiClient;

    @Before
    public void setUp() throws Exception {

        logger = new ConsoleLogger();

        // The underlying http stack. Developers can inject their own if desired
        IAsyncHttpClient volleyHttpClient = new VolleyHttpClient(logger, Robolectric.application.getApplicationContext());

        apiClient = new ApiClient(volleyHttpClient, logger, "http://localhost:8096", "My app name", "My device", "My device id", "app version 123");
    }

    @Test
    public void getPublicSystemInfo_shouldReturnResults() throws Exception {

        apiClient.GetPublicSystemInfoAsync(new Response<PublicSystemInfo>() {

            @Override
            public void onResponse(PublicSystemInfo jsonResponse) {

                // Do something with response
                logger.Info(jsonResponse.getId());
                logger.Info(jsonResponse.getServerName());
                logger.Info(jsonResponse.getVersion());
            }

            @Override
            public void onError() {

                // Do something with error
                fail("Method failed");
            }
        });
    }

}
