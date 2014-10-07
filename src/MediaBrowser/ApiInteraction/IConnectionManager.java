package MediaBrowser.ApiInteraction;

import MediaBrowser.Model.ApiClient.ServerInfo;
import MediaBrowser.Model.Dto.BaseItemDto;

import java.util.Observable;

public interface IConnectionManager {

    ApiClient GetApiClient(BaseItemDto item);

    void Connect(Response<ConnectionResult> response);

    void Connect(ServerInfo server, Response<ConnectionResult> response);

    void Connect(String address, Response<ConnectionResult> response);

    void Logout(Response<ConnectionResult> response);

    Observable getConnectedObservable();

    Observable getRemoteLoggedOutObservable();
}
