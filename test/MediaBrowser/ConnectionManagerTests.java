package MediaBrowser;

import MediaBrowser.apiinteraction.*;
import MediaBrowser.apiinteraction.discovery.ServerLocator;
import MediaBrowser.apiinteraction.android.VolleyHttpClient;
import MediaBrowser.apiinteraction.android.AndroidNetworkConnection;
import MediaBrowser.apiinteraction.http.IAsyncHttpClient;
import MediaBrowser.apiinteraction.serialization.BoonJsonSerializer;
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
    private IJsonSerializer jsonSerializer = new BoonJsonSerializer();

    @Before
    public void setUp() throws Exception {

        // The underlying http stack. Developers can inject their own if desired
        Context context = Robolectric.application.getApplicationContext();

        IAsyncHttpClient volleyHttpClient = new VolleyHttpClient(logger, context);

        ClientCapabilities capabilities = new ClientCapabilities();

        ApiEventListener apiEventListener = new ApiEventListener();

        connectionManager = new ConnectionManager(new FakeCredentialProvider(),
                new AndroidNetworkConnection(context, logger),
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
            public void onError(Exception ex) {

                logger.Debug("testConnectionManager: error");

                // Do something with error
                fail("Method failed");
            }

        });
    }

}
