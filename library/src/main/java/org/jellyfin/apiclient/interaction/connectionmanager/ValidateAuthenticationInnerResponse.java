package org.jellyfin.apiclient.interaction.connectionmanager;

import org.jellyfin.apiclient.interaction.EmptyResponse;
import org.jellyfin.apiclient.interaction.Response;
import org.jellyfin.apiclient.model.apiclient.ServerInfo;
import org.jellyfin.apiclient.model.dto.UserDto;
import org.jellyfin.apiclient.serialization.IJsonSerializer;

/**
 * Created by Luke on 2/16/2015.
 */
public class ValidateAuthenticationInnerResponse extends Response<String> {

    private ConnectionManager connectionManager;
    private IJsonSerializer jsonSerializer;
    private ServerInfo server;
    private EmptyResponse response;

    public ValidateAuthenticationInnerResponse(ConnectionManager connectionManager, IJsonSerializer jsonSerializer, ServerInfo server, EmptyResponse response) {
        this.connectionManager = connectionManager;
        this.jsonSerializer = jsonSerializer;
        this.server = server;
        this.response = response;
    }

    @Override
    public void onResponse(String stringResponse) {
        UserDto user = jsonSerializer.DeserializeFromString(stringResponse, UserDto.class);
        connectionManager.OnLocalUserSignIn(user);
        response.onResponse();
    }

    @Override
    public void onError(Exception ex) {
        server.setUserId(null);
        server.setAccessToken(null);
        response.onResponse();
    }
}
