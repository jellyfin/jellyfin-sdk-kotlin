package mediabrowser.apiinteraction.connectionmanager;

import mediabrowser.apiinteraction.ICredentialProvider;
import mediabrowser.apiinteraction.Response;
import mediabrowser.apiinteraction.http.HttpRequest;
import mediabrowser.apiinteraction.http.IAsyncHttpClient;
import mediabrowser.model.apiclient.ServerCredentials;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.extensions.StringHelper;
import mediabrowser.model.logging.ILogger;
import mediabrowser.model.registration.RegistrationInfo;
import mediabrowser.model.serialization.IJsonSerializer;

import java.util.ArrayList;

/**
 * Created by Luke on 3/12/2015.
 */
public class GetRegistrationInfoFindServersResponse extends Response<ArrayList<ServerInfo>> {

    private String featureName;
    private String connectedServerId;
    private ILogger logger;
    private Response<RegistrationInfo> response;
    private ICredentialProvider credentialProvider;
    private IAsyncHttpClient httpClient;
    private IJsonSerializer jsonSerializer;

    public GetRegistrationInfoFindServersResponse(String featureName, String connectedServerId, ILogger logger, Response<RegistrationInfo> response, ICredentialProvider credentialProvider, IAsyncHttpClient httpClient, IJsonSerializer jsonSerializer) {
        this.featureName = featureName;
        this.connectedServerId = connectedServerId;
        this.logger = logger;
        this.response = response;
        this.credentialProvider = credentialProvider;
        this.httpClient = httpClient;
        this.jsonSerializer = jsonSerializer;
    }

    @Override
    public void onResponse(ArrayList<ServerInfo> servers){

        for (ServerInfo server : servers){

            if (StringHelper.EqualsIgnoreCase(server.getId(), connectedServerId)){
                TestServer(server, response);
                return;
            }
        }

        TestConnect(response);
    }

    public void onError(Exception ex){
        logger.ErrorException("Error finding servers", ex);
        onResponse(new ArrayList<ServerInfo>());
    }

    void TestServer(ServerInfo server, Response<RegistrationInfo> response){

        String url = server.getManualAddress();

        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(url)){
            url = server.getLocalAddress();
        }

        url += "Registrations/" + featureName;

        HttpRequest request = new HttpRequest();
        request.setUrl(url);
        request.setMethod("GET");

        httpClient.Send(request, new GetRegistrationInfoInnerResponse(response, jsonSerializer, RegistrationInfo.class, this, logger));
    }

    void TestConnect(Response<RegistrationInfo> response){

        ServerCredentials credentials = credentialProvider.GetCredentials();

        if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(credentials.getConnectAccessToken()) &&
                !tangible.DotNetToJavaStringHelper.isNullOrEmpty(credentials.getConnectUserId()))
        {
            RegistrationInfo reg = new RegistrationInfo();
            reg.setName(featureName);
            reg.setIsTrial(true);
            response.onResponse(reg);
        }
    }
}
