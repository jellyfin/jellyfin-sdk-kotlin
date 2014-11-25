package mediabrowser.apiinteraction.android;

import mediabrowser.apiinteraction.*;
import mediabrowser.apiinteraction.connectionmanager.ConnectionManager;
import mediabrowser.apiinteraction.discovery.IServerLocator;
import mediabrowser.apiinteraction.discovery.ServerLocator;
import mediabrowser.apiinteraction.http.IAsyncHttpClient;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.logging.ILogger;
import mediabrowser.model.serialization.IJsonSerializer;
import mediabrowser.model.session.ClientCapabilities;
import android.content.Context;

import java.util.ArrayList;

public class AndroidConnectionManager extends ConnectionManager {

    public AndroidConnectionManager(Context context, IJsonSerializer jsonSerializer, ILogger logger, IAsyncHttpClient httpClient, String applicationName, String applicationVersion, ClientCapabilities clientCapabilities, ApiEventListener apiEventListener) {

        super(new AndroidCredentialProvider(jsonSerializer, context),
                new AndroidNetworkConnection(context, logger),
                jsonSerializer,
                logger,
                new ServerLocator(logger, jsonSerializer),
                httpClient,
                applicationName,
                applicationVersion,
                new AndroidDevice(context),
                clientCapabilities, apiEventListener);
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

    @Override
    protected void FindServers(final Response<ArrayList<ServerInfo>> response)
    {
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                FindServersInternal(response);
            }
        });

        thread.start();
    }
}
