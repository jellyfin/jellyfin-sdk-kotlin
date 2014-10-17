package MediaBrowser;

import MediaBrowser.ApiInteraction.*;
import MediaBrowser.ApiInteraction.Discovery.ServerLocator;
import MediaBrowser.ApiInteraction.Http.VolleyHttpClient;
import MediaBrowser.ApiInteraction.Network.NetworkConnection;
import MediaBrowser.Model.Logging.ILogger;
import MediaBrowser.Model.Serialization.IJsonSerializer;
import MediaBrowser.Model.Session.ClientCapabilities;
import android.content.Context;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.fail;

@RunWith(RobolectricTestRunner.class)
public class ConnectionManagerTests {

    private ILogger logger = new ConsoleLogger();
    private IConnectionManager connectionManager;
    private IJsonSerializer jsonSerializer = new JsonSerializer();

    @Before
    public void setUp() throws Exception {

        // The underlying http stack. Developers can inject their own if desired
        Context context = Robolectric.application.getApplicationContext();

        IAsyncHttpClient volleyHttpClient = new VolleyHttpClient(logger, context);

        ClientCapabilities capabilities = new ClientCapabilities();

        ApiEventListener apiEventListener = new ApiEventListener();

        connectionManager = new ConnectionManager(new FakeCredentialProvider(),
                new NetworkConnection(context, logger),
                jsonSerializer,
                logger,
                new ServerLocator(logger, jsonSerializer),
                volleyHttpClient,
                "App Name",
                "App Version",
                new FakeDevice(),
                capabilities,
                apiEventListener);
    }

    @Test
    public void testConnectionManager() throws Exception {

        logger.Debug("testConnectionManager");

        connectionManager.Connect(new Response<ConnectionResult>(){

            @Override
            public void onResponse(ConnectionResult result) {

                logger.Debug("testConnectionManager: response");

            }

            @Override
            public void onError() {

                logger.Debug("testConnectionManager: error");

                // Do something with error
                fail("Method failed");
            }

        });
    }

}
