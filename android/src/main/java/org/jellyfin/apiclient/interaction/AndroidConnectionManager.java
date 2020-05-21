package org.jellyfin.apiclient.interaction;

import android.content.Context;

import org.jellyfin.apiclient.interaction.connectionmanager.ConnectionManager;
import org.jellyfin.apiclient.interaction.device.IDevice;
import org.jellyfin.apiclient.interaction.http.IAsyncHttpClient;
import org.jellyfin.apiclient.logging.ILogger;
import org.jellyfin.apiclient.model.session.ClientCapabilities;

public class AndroidConnectionManager extends ConnectionManager {
    public AndroidConnectionManager(Context context, ILogger logger, IAsyncHttpClient httpClient, String applicationName, String applicationVersion, IDevice device, ClientCapabilities clientCapabilities, ApiEventListener apiEventListener) {
        super(
                logger,
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
}
