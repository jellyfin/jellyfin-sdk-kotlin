package mediabrowser;

import mediabrowser.logging.ConsoleLogger;
import mediabrowser.apiinteraction.discovery.ServerLocator;
import mediabrowser.apiinteraction.serialization.BoonJsonSerializer;
import mediabrowser.apiinteraction.Response;
import mediabrowser.model.apiclient.ServerDiscoveryInfo;
import mediabrowser.model.logging.ILogger;
import mediabrowser.model.serialization.IJsonSerializer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(RobolectricTestRunner.class)
public class ServerLocatorTest {

    private ILogger logger;
    private IJsonSerializer jsonSerializer = new BoonJsonSerializer();

    @Before
    public void setUp() throws Exception {
        logger = new ConsoleLogger();
    }

    @Test
    public void findServers_shouldReturnResults() throws Exception {

        ServerLocator discovery = new ServerLocator(logger, jsonSerializer);

        discovery.FindServers(new Response<ServerDiscoveryInfo[]>(){

            @Override
            public void onResponse(ServerDiscoveryInfo[] servers) {

                // Do something with response
                assertThat(servers.length, equalTo(1));

                logger.Info(servers[0].getAddress());
                logger.Info(servers[0].getId());
                logger.Info(servers[0].getName());
            }

            @Override
            public void onError(Exception ex) {

                // Do something with error
                fail("Server discovery failed");
            }
        });
    }
}
