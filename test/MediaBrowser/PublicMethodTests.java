package MediaBrowser;

import MediaBrowser.ApiInteraction.*;
import MediaBrowser.ApiInteraction.Http.VolleyHttpClient;
import MediaBrowser.Model.Logging.ILogger;
import MediaBrowser.Model.Session.ClientCapabilities;
import MediaBrowser.Model.System.PublicSystemInfo;
import MediaBrowser.Model.Users.AuthenticationResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

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

        ClientCapabilities capabilities = new ClientCapabilities();

        ApiEventListener apiEventListener = new ApiEventListener();

        apiClient = new ApiClient(volleyHttpClient, logger, "http://localhost:8096", "My app name", "My device", "My device id", "app version 123", apiEventListener, capabilities);
    }

    @Test
    public void getPublicSystemInfo_shouldReturnResults() throws Exception {

        logger.Debug("getPublicSystemInfo_shouldReturnResults");

        apiClient.GetPublicSystemInfoAsync(new Response<PublicSystemInfo>() {

            @Override
            public void onResponse(PublicSystemInfo jsonResponse) {

                logger.Debug("getPublicSystemInfo_shouldReturnResults: response");

                // Do something with response
                logger.Info(jsonResponse.getId());
                logger.Info(jsonResponse.getServerName());
                logger.Info(jsonResponse.getVersion());
            }

            @Override
            public void onError() {

                logger.Debug("getPublicSystemInfo_shouldReturnResults: error");

                // Do something with error
                fail("Method failed");
            }
        });
    }

    public void testLogin() throws Exception {

        apiClient.AuthenticateUserAsync("username", "password", new Response<AuthenticationResult>(){

            @Override
            public void onResponse(AuthenticationResult result) {


            }

            @Override
            public void onError() {


            }

        });
    }

}
