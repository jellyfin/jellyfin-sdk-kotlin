package org.jellyfin.apiclient.interaction.connectionmanager;

import org.jellyfin.apiclient.interaction.EmptyResponse;
import org.jellyfin.apiclient.interaction.Response;
import org.jellyfin.apiclient.model.apiclient.ServerInfo;
import org.jellyfin.apiclient.model.connect.ConnectAuthenticationExchangeResult;
import org.jellyfin.apiclient.model.serialization.IJsonSerializer;

public class ExchangeTokenResponse extends Response<String> {

    private IJsonSerializer jsonSerializer;
    private ServerInfo server;
    private EmptyResponse response;

    public ExchangeTokenResponse(IJsonSerializer jsonSerializer, ServerInfo server, EmptyResponse response) {
        this.jsonSerializer = jsonSerializer;
        this.server = server;
        this.response = response;
    }

    @Override
    public void onResponse(String jsonResponse) {

        ConnectAuthenticationExchangeResult obj = jsonSerializer.DeserializeFromString(jsonResponse, ConnectAuthenticationExchangeResult.class);

        server.setUserId(obj.getLocalUserId());
        server.setAccessToken(obj.getAccessToken());
        response.onResponse();
    }

    @Override
    public void onError(Exception ex) {

        server.setUserId(null);
        server.setAccessToken(null);
        response.onResponse();
    }
}
