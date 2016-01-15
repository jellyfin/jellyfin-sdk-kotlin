package mediabrowser.apiinteraction.connectionmanager;

import mediabrowser.apiinteraction.ApiClient;
import mediabrowser.apiinteraction.ICredentialProvider;
import mediabrowser.apiinteraction.Response;
import mediabrowser.apiinteraction.connect.ConnectService;
import mediabrowser.apiinteraction.http.HttpRequest;
import mediabrowser.apiinteraction.http.IAsyncHttpClient;
import mediabrowser.model.apiclient.ServerCredentials;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.extensions.StringHelper;
import mediabrowser.model.logging.ILogger;
import mediabrowser.model.net.EndPointInfo;
import mediabrowser.model.registration.RegistrationInfo;
import mediabrowser.model.serialization.IJsonSerializer;

import java.util.ArrayList;

/**
 * Created by Luke on 3/12/2015.
 */
public class GetRegistrationInfoFindServersResponse extends Response<ArrayList<ServerInfo>> {

    private ConnectionManager connectionManager;
    private String featureName;
    private String connectedServerId;
    private ILogger logger;
    private Response<RegistrationInfo> response;
    private ICredentialProvider credentialProvider;
    private ConnectService connectService;

    public GetRegistrationInfoFindServersResponse(ConnectionManager connectionManager, String featureName, String connectedServerId, ILogger logger, Response<RegistrationInfo> response, ICredentialProvider credentialProvider, ConnectService connectService) {
        this.connectionManager = connectionManager;
        this.featureName = featureName;
        this.connectedServerId = connectedServerId;
        this.logger = logger;
        this.response = response;
        this.credentialProvider = credentialProvider;
        this.connectService = connectService;
    }

    @Override
    public void onResponse(ArrayList<ServerInfo> servers){

        for (ServerInfo server : servers){

            if (StringHelper.EqualsIgnoreCase(server.getId(), connectedServerId)){
                TestServer(server, response);
                return;
            }
        }

        RegistrationInfo reg = new RegistrationInfo();
        reg.setName(featureName);
        response.onResponse(reg);
    }

    public void onError(Exception ex){
        logger.ErrorException("Error finding servers", ex);
        onResponse(new ArrayList<ServerInfo>());
    }

    void TestServer(final ServerInfo server, final Response<RegistrationInfo> response){

        final ApiClient apiClient = connectionManager.GetApiClient(server.getId());

        final GetRegistrationInfoFindServersResponse self = this;

        if (server.getDateLastLocalConnection() != 0){
            apiClient.GetRegistrationInfo(featureName, new GetRegistrationInfoInnerResponse(response, self, logger));
            return;
        }

        // TODO: Get Endpoint Info
        apiClient.GetEndPointInfo(new Response<EndPointInfo>(response){

            @Override
            public void onResponse(EndPointInfo info){

                if (info.getIsInNetwork()){

                    connectionManager.updateDateLastLocalConnection(server.getId());
                    apiClient.GetRegistrationInfo(featureName, new GetRegistrationInfoInnerResponse(response, self, logger));

                } else{
                    RegistrationInfo reg = new RegistrationInfo();
                    reg.setName(featureName);
                    response.onResponse(reg);
                }
            }

        });
    }

    void TestConnect(Response<RegistrationInfo> response){

        ServerCredentials credentials = credentialProvider.GetCredentials();

        if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(credentials.getConnectAccessToken()) &&
                !tangible.DotNetToJavaStringHelper.isNullOrEmpty(credentials.getConnectUserId()))
        {

            connectService.GetRegistrationInfo(credentials.getConnectUserId(), featureName, credentials.getConnectAccessToken(), response);
        }
        else{
            RegistrationInfo reg = new RegistrationInfo();
            reg.setName(featureName);
            response.onResponse(reg);
        }
    }
}
