package mediabrowser.apiinteraction.connectionmanager;

import mediabrowser.apiinteraction.EmptyResponse;
import mediabrowser.apiinteraction.Response;
import mediabrowser.apiinteraction.http.HttpRequest;
import mediabrowser.apiinteraction.http.IAsyncHttpClient;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.serialization.IJsonSerializer;
import mediabrowser.model.system.SystemInfo;

/**
 * Created by Luke on 2/16/2015.
 */
public class ValidateAuthenticationResponse extends Response<String> {

    private ConnectionManager connectionManager;
    private IJsonSerializer jsonSerializer;
    private ServerInfo server;
    private EmptyResponse response;
    private HttpRequest request;
    private IAsyncHttpClient httpClient;
    private String url;

    public ValidateAuthenticationResponse(ConnectionManager connectionManager, IJsonSerializer jsonSerializer, ServerInfo server, EmptyResponse response, HttpRequest request, IAsyncHttpClient httpClient, String url) {
        this.connectionManager = connectionManager;
        this.jsonSerializer = jsonSerializer;
        this.server = server;
        this.response = response;
        this.request = request;
        this.httpClient = httpClient;
        this.url = url;
    }

    @Override
    public void onResponse(String jsonResponse) {

        SystemInfo obj = jsonSerializer.DeserializeFromString(jsonResponse, SystemInfo.class);
        server.ImportInfo(obj);

        if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(server.getUserId()))
        {
            request.setUrl(url + "/mediabrowser/users/" + server.getUserId() + "?format=json");

            httpClient.Send(request, new ValidateAuthenticationInnerResponse(connectionManager, jsonSerializer, server, response));
        }
        else {
            response.onResponse();
        }
    }

    @Override
    public void onError(Exception ex) {

        server.setUserId(null);
        server.setAccessToken(null);
        response.onResponse();
    }
}
