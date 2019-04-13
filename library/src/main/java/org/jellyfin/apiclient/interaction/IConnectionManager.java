package org.jellyfin.apiclient.interaction;

import org.jellyfin.apiclient.interaction.device.IDevice;
import org.jellyfin.apiclient.model.apiclient.ConnectionOptions;
import org.jellyfin.apiclient.model.apiclient.ServerInfo;
import org.jellyfin.apiclient.model.connect.PinCreationResult;
import org.jellyfin.apiclient.model.connect.PinExchangeResult;
import org.jellyfin.apiclient.model.connect.PinStatusResult;
import org.jellyfin.apiclient.model.dto.IHasServerId;
import org.jellyfin.apiclient.model.registration.RegistrationInfo;
import org.jellyfin.apiclient.model.session.ClientCapabilities;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

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

    @Deprecated
    void GetRegistrationInfo(String featureName, String serverId, String localUsername, Response<RegistrationInfo> response);
}
