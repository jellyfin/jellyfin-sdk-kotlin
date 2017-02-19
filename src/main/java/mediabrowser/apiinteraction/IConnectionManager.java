package mediabrowser.apiinteraction;

import mediabrowser.apiinteraction.device.IDevice;
import mediabrowser.model.apiclient.ConnectionOptions;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.connect.PinCreationResult;
import mediabrowser.model.connect.PinExchangeResult;
import mediabrowser.model.connect.PinStatusResult;
import mediabrowser.model.dto.IHasServerId;
import mediabrowser.model.registration.RegistrationInfo;
import mediabrowser.model.session.ClientCapabilities;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Observable;

public interface IConnectionManager {

    ClientCapabilities getClientCapabilities();

    ApiClient GetApiClient(IHasServerId item);

    ApiClient GetApiClient(String serverId);

    ServerInfo getServerInfo(String serverId);

    IDevice getDevice();

    void Connect(Response<ConnectionResult> response);

    void Connect(ServerInfo server, Response<ConnectionResult> response);

    void Connect(ServerInfo server, ConnectionOptions options, Response<ConnectionResult> response);

    void Connect(String address, Response<ConnectionResult> response);

    void Logout(EmptyResponse response);

    void GetAvailableServers(final Response<ArrayList<ServerInfo>> response);

    void GetSavedServers(final Response<ArrayList<ServerInfo>> response);

    void LoginToConnect(String username, String password, final EmptyResponse response)  throws UnsupportedEncodingException, NoSuchAlgorithmException;

    void CreatePin(String deviceId, Response<PinCreationResult> response);

    void GetPinStatus(PinCreationResult pin, Response<PinStatusResult> response);

    void ExchangePin(PinCreationResult pin, final Response<PinExchangeResult> response);

    void DeleteServer(final String id, final EmptyResponse response);

    void GetRegistrationInfo(String featureName, String serverId, String localUsername, Response<RegistrationInfo> response);
}
