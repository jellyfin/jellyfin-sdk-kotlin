package org.jellyfin.apiclient.interaction.connectionmanager;

import org.jellyfin.apiclient.interaction.ApiClient;
import org.jellyfin.apiclient.interaction.EmptyResponse;
import org.jellyfin.apiclient.interaction.Response;
import org.jellyfin.apiclient.interaction.http.HttpRequest;
import org.jellyfin.apiclient.interaction.http.IAsyncHttpClient;
import org.jellyfin.apiclient.model.apiclient.ServerInfo;
import org.jellyfin.apiclient.model.serialization.IJsonSerializer;
import org.jellyfin.apiclient.model.system.SystemInfo;

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

        if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(server.getUserId()) &&
                !tangible.DotNetToJavaStringHelper.isNullOrEmpty(server.getId())) {
            ApiClient client = connectionManager.GetApiClient(server.getId());
            if (client != null) {
                String apiUrl = client.GetApiUrl("Users/" + server.getUserId());
                apiUrl = client.AddDataFormat(apiUrl);
                request.setUrl(apiUrl);

                httpClient.Send(request, new ValidateAuthenticationInnerResponse(connectionManager, jsonSerializer, server, response));
                return;
            }
        }
        response.onResponse();
    }

    @Override
    public void onError(Exception ex) {
        server.setUserId(null);
        server.setAccessToken(null);
        response.onResponse();
    }
}
