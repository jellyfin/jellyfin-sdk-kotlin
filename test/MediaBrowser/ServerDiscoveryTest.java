package MediaBrowser;

import MediaBrowser.ApiInteraction.ConsoleLogger;
import MediaBrowser.ApiInteraction.ServerDiscovery;
import MediaBrowser.ApiInteraction.ServerDiscoveryResponse;
import MediaBrowser.Model.ApiClient.ServerDiscoveryInfo;
import MediaBrowser.Model.Logging.ILogger;
import MediaBrowser.Model.Logging.NullLogger;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.sameInstance;
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

        ServerDiscovery discovery = new ServerDiscovery(logger);

        discovery.FindServers(new ServerDiscoveryResponse(){

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
