package org.jellyfin.apiclient.interaction;

import android.content.Context;

import org.jellyfin.apiclient.interaction.connectionmanager.ConnectionManager;
import org.jellyfin.apiclient.interaction.device.IDevice;
import org.jellyfin.apiclient.interaction.discovery.ServerLocator;
import org.jellyfin.apiclient.interaction.http.IAsyncHttpClient;
import org.jellyfin.apiclient.logging.ILogger;
import org.jellyfin.apiclient.model.apiclient.ServerInfo;
import org.jellyfin.apiclient.model.session.ClientCapabilities;
import org.jellyfin.apiclient.serialization.IJsonSerializer;

import java.util.ArrayList;

public class AndroidConnectionManager extends ConnectionManager {
    public AndroidConnectionManager(Context context, IJsonSerializer jsonSerializer, ILogger logger, IAsyncHttpClient httpClient, String applicationName, String applicationVersion, IDevice device, ClientCapabilities clientCapabilities, ApiEventListener apiEventListener) {
        super(
                jsonSerializer,
                logger,
                new ServerLocator(logger, jsonSerializer),
                httpClient,
                applicationName,
                applicationVersion,
                device,
                clientCapabilities,
                apiEventListener
        );
    }

    @Override
    protected ApiClient InstantiateApiClient(String serverAddress) {
        return new AndroidApiClient(httpClient,
                logger,
                serverAddress,
                applicationName,
                device,
                applicationVersion,
                apiEventListener);
    }

    @Override
    protected void FindServers(final Response<ArrayList<ServerInfo>> response) {
        Thread thread = new Thread(new FindServersRunnable(this, response));
        thread.start();
    }
}
