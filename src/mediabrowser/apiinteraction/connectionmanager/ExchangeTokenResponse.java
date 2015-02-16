package mediabrowser.apiinteraction.connectionmanager;

import mediabrowser.apiinteraction.EmptyResponse;
import mediabrowser.apiinteraction.Response;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.connect.ConnectAuthenticationExchangeResult;
import mediabrowser.model.serialization.IJsonSerializer;

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
