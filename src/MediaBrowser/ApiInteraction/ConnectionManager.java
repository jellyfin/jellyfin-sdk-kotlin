package MediaBrowser.ApiInteraction;

import MediaBrowser.Model.ApiClient.ServerInfo;
import MediaBrowser.Model.Dto.BaseItemDto;

import java.util.Observable;

public class ConnectionManager implements IConnectionManager {

    @Override
    public ApiClient GetApiClient(BaseItemDto item) {
        return null;
    }

    @Override
    public void Connect(Response<ConnectionResult> response) {

    }

    @Override
    public void Connect(ServerInfo server, Response<ConnectionResult> response) {

    }

    @Override
    public void Connect(String address, Response<ConnectionResult> response) {

    }

    @Override
    public void Logout(Response<ConnectionResult> response) {

    }

    @Override
    public Observable getConnectedObservable() {
        return null;
    }

    @Override
    public Observable getRemoteLoggedOutObservable() {
        return null;
    }
}
