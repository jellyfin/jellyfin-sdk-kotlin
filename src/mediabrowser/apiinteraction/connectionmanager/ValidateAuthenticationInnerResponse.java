package mediabrowser.apiinteraction.connectionmanager;

import mediabrowser.apiinteraction.EmptyResponse;
import mediabrowser.apiinteraction.Response;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.dto.UserDto;
import mediabrowser.model.serialization.IJsonSerializer;

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
