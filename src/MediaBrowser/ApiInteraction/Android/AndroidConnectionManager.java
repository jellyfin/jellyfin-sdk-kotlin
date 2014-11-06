package MediaBrowser.ApiInteraction.Android;

import MediaBrowser.ApiInteraction.*;
import MediaBrowser.ApiInteraction.Discovery.IServerLocator;
import MediaBrowser.ApiInteraction.Http.IAsyncHttpClient;
import MediaBrowser.Model.Logging.ILogger;
import MediaBrowser.Model.Serialization.IJsonSerializer;
import MediaBrowser.Model.Session.ClientCapabilities;
import android.content.Context;

public class AndroidConnectionManager extends ConnectionManager {

    public AndroidConnectionManager(Context context, ICredentialProvider credentialProvider, IJsonSerializer jsonSerializer, ILogger logger, IServerLocator serverDiscovery, IAsyncHttpClient httpClient, String applicationName, String applicationVersion, ClientCapabilities clientCapabilities, ApiEventListener apiEventListener) {
        super(credentialProvider, new AndroidNetworkConnection(context, logger), jsonSerializer, logger, serverDiscovery, httpClient, applicationName, applicationVersion, new AndroidDevice(context), clientCapabilities, apiEventListener);
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
