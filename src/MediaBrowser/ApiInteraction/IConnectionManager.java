package MediaBrowser.ApiInteraction;

import MediaBrowser.Model.ApiClient.ServerInfo;
import MediaBrowser.Model.Dto.BaseItemDto;
import MediaBrowser.Model.Dto.IHasServerId;

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

    Observable getRemoteLoggedOutObservable();

    void LoginToConnect(String username, String password, final EmptyResponse response)  throws UnsupportedEncodingException, NoSuchAlgorithmException;
}
