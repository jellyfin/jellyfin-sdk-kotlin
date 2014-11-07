package mediabrowser.apiinteraction;

import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.dto.IHasServerId;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Observable;

public interface IConnectionManager {

    ApiClient GetApiClient(IHasServerId item);

    ApiClient GetApiClient(String serverId);

    void Connect(Response<ConnectionResult> response);

    void Connect(ServerInfo server, Response<ConnectionResult> response);

    void Connect(String address, Response<ConnectionResult> response);

    void Logout(EmptyResponse response);

    Observable getConnectedObservable();

    void LoginToConnect(String username, String password, final EmptyResponse response)  throws UnsupportedEncodingException, NoSuchAlgorithmException;
}
