package mediabrowser.apiinteraction.android;

import mediabrowser.apiinteraction.*;
import mediabrowser.apiinteraction.discovery.IServerLocator;
import mediabrowser.apiinteraction.http.IAsyncHttpClient;
import mediabrowser.model.logging.ILogger;
import mediabrowser.model.serialization.IJsonSerializer;
import mediabrowser.model.session.ClientCapabilities;
import android.content.Context;

public class AndroidConnectionManager extends ConnectionManager {

    public AndroidConnectionManager(Context context, IJsonSerializer jsonSerializer, ILogger logger, IServerLocator serverDiscovery, IAsyncHttpClient httpClient, String applicationName, String applicationVersion, ClientCapabilities clientCapabilities, ApiEventListener apiEventListener) {

        super(new AndroidCredentialProvider(jsonSerializer, context), new AndroidNetworkConnection(context, logger), jsonSerializer, logger, serverDiscovery, httpClient, applicationName, applicationVersion, new AndroidDevice(context), clientCapabilities, apiEventListener);
    }

    @Override
    protected ApiClient InstantiateApiClient(String serverAddress) {

        return new AndroidApiClient(httpClient,
                jsonSerializer,
                logger,
                serverAddress,
                applicationName,
                device,
                applicationVersion,
                apiEventListener,
                clientCapabilities);
    }
}
