package MediaBrowser;

import MediaBrowser.ApiInteraction.ConsoleLogger;
import MediaBrowser.ApiInteraction.JsonSerializer;
import MediaBrowser.ApiInteraction.Response;
import MediaBrowser.ApiInteraction.Discovery.ServerDiscovery;
import MediaBrowser.Model.ApiClient.ServerDiscoveryInfo;
import MediaBrowser.Model.Logging.ILogger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(RobolectricTestRunner.class)
public class ServerDiscoveryTest {

    private ILogger logger;

    @Before
    public void setUp() throws Exception {
        logger = new ConsoleLogger();
    }

    @Test
    public void findServers_shouldReturnResults() throws Exception {

        ServerDiscovery discovery = new ServerDiscovery(logger, new JsonSerializer());

        discovery.FindServers(new Response<ServerDiscoveryInfo[]>(){

            @Override
            public void onResponse(ServerDiscoveryInfo[] jsonResponse) {

                // Do something with response
                assertThat(jsonResponse.length, equalTo(1));
            }

            @Override
            public void onError() {

                // Do something with error
                fail("Server discovery failed");
            }
        });
    }
}
