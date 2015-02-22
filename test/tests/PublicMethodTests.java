package tests;

import mediabrowser.apiinteraction.*;
import mediabrowser.apiinteraction.connectionmanager.ServerInfoDateComparator;
import mediabrowser.apiinteraction.device.IDevice;
import mediabrowser.apiinteraction.android.VolleyHttpClient;
import mediabrowser.apiinteraction.http.IAsyncHttpClient;
import mediabrowser.apiinteraction.serialization.BoonJsonSerializer;
import mediabrowser.logging.ConsoleLogger;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.logging.ILogger;
import mediabrowser.model.serialization.IJsonSerializer;
import mediabrowser.model.session.ClientCapabilities;
import mediabrowser.model.system.PublicSystemInfo;
import mediabrowser.model.users.AuthenticationResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
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

        IDevice device = new mediabrowser.FakeDevice();

        IJsonSerializer jsonSerializer = new BoonJsonSerializer();

        apiClient = new ApiClient(volleyHttpClient, jsonSerializer, logger, "http://localhost:8096", "My app name", device, "app version 123", apiEventListener);
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
            public void onError(Exception ex) {

                logger.Debug("getPublicSystemInfo_shouldReturnResults: error");

                // Do something with error
                fail("Method failed");
            }
        });
    }

    public void testLogin() throws Exception {

        apiClient.AuthenticateUserAsync("username", "password", new Response<AuthenticationResult>() {

            @Override
            public void onResponse(AuthenticationResult result) {


            }

            @Override
            public void onError(Exception ex) {


            }

        });
    }

    @Test
    public void TestServerInfoSort() throws Exception {

        ServerInfo server1 = new ServerInfo();
        server1.setDateLastAccessed(new Date(2010, 1, 1));

        ServerInfo server2 = new ServerInfo();
        server2.setDateLastAccessed(new Date(2010, 1, 2));

        ServerInfo server3 = new ServerInfo();
        server3.setDateLastAccessed(new Date(2010, 1, 3));

        ArrayList<ServerInfo> servers = new ArrayList<>();
        servers.add(server2);
        servers.add(server3);
        servers.add(server1);

        Collections.sort(servers, new ServerInfoDateComparator());
        Collections.reverse(servers);

        assertThat(servers.get(0).getDateLastAccessed().getDay(), equalTo(1));
    }

}
