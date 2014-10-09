package MediaBrowser.ApiInteraction;

import MediaBrowser.ApiInteraction.Discovery.IServerLocator;
import MediaBrowser.ApiInteraction.Network.INetworkConnection;
import MediaBrowser.Model.ApiClient.ServerDiscoveryInfo;
import MediaBrowser.Model.ApiClient.ServerInfo;
import MediaBrowser.Model.ApiClient.WakeOnLanInfo;
import MediaBrowser.Model.Dto.BaseItemDto;
import MediaBrowser.Model.Extensions.StringHelper;
import MediaBrowser.Model.Logging.ILogger;
import MediaBrowser.Model.Serialization.IJsonSerializer;
import MediaBrowser.Model.Session.ClientCapabilities;
import MediaBrowser.Model.Session.SessionInfoDto;
import MediaBrowser.Model.System.PublicSystemInfo;
import MediaBrowser.Model.System.SystemInfo;
import MediaBrowser.Model.Users.AuthenticationResult;
import org.apache.maven.settings.Server;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

public class ConnectionManager implements IConnectionManager {

    private ICredentialProvider _credentialProvider;
    private INetworkConnection _networkConnectivity;
    private ILogger _logger;
    private IServerLocator _serverDiscovery;
    private IAsyncHttpClient _httpClient;

    private HashMap<String, ApiClient> apiClients = new HashMap<String, ApiClient>();
    private IJsonSerializer jsonSerializer;

    private String applicationName;
    private String applicationVersion;
    private IDevice device;
    private ClientCapabilities clientCapabilities;
    private ApiEventListener apiEventListener;

    public ConnectionManager(ICredentialProvider credentialProvider,
                             INetworkConnection networkConnectivity,
                             ILogger logger,
                             IServerLocator serverDiscovery,
                             IAsyncHttpClient httpClient,
                             String applicationName,
                             String applicationVersion,
                             IDevice device,
                             ClientCapabilities clientCapabilities,
                             ApiEventListener apiEventListener) {

        _credentialProvider = credentialProvider;
        _networkConnectivity = networkConnectivity;
        _logger = logger;
        _serverDiscovery = serverDiscovery;
        _httpClient = httpClient;
        this.applicationName = applicationName;
        this.applicationVersion = applicationVersion;
        this.device = device;
        this.clientCapabilities = clientCapabilities;
        this.apiEventListener = apiEventListener;

        device.getResumeFromSleepObservable().addObserver(new GenericObserver() {

            @Override
            public void update(Observable observable, Object o)
            {
                WakeAllServers();
            }

        });
    }

    @Override
    public ApiClient GetApiClient(BaseItemDto item) {

        Object client = apiClients.values().toArray()[0];

        return (ApiClient)client;
    }

    private void OnFailedConnection(Response<ConnectionResult> response){

        _logger.Debug("No server available");

        ConnectionResult result = new ConnectionResult();
        result.setState(ConnectionState.Unavailable);
        response.onResponse(result);
    }

    @Override
    public void Connect(final Response<ConnectionResult> response) {

         _logger.Debug("Entering initial connection workflow");

         GetAvailableServers(new Response<ArrayList<ServerInfo>>(){

                @Override
                public void onResponse(ArrayList<ServerInfo> servers) {

                    ServerCredentialConfiguration credentials = _credentialProvider.GetCredentials();

                    final String lastServerId = credentials.getLastServerId();

                    _logger.Debug("Looping through server list");

                    Connect(servers, lastServerId, new Response<ConnectionResult>(){

                        @Override
                        public void onResponse(ConnectionResult result) {

                            if (result.getState() != ConnectionState.Unavailable)
                            {
                                response.onResponse(result);
                                return;
                            }

                            _logger.Debug("No saved servers available. Attempting discovery process");
                            FindServers(new Response<ArrayList<ServerInfo>>(){

                                @Override
                                public void onResponse(ArrayList<ServerInfo> foundServers) {

                                    Connect(foundServers, lastServerId, response);
                                }

                                @Override
                                public void onError() {

                                    OnFailedConnection(response);
                                }
                            });
                        }

                        @Override
                        public void onError() {

                            OnFailedConnection(response);
                        }
                    });
                }

                @Override
                public void onError() {

                    OnFailedConnection(response);
                }

        });
    }

    private void Connect(ArrayList<ServerInfo> servers, String lastServerId, Response<ConnectionResult> response){

        ArrayList<ServerInfo> sorted = new ArrayList<ServerInfo>();

        for(ServerInfo server : servers)  {
            if (StringHelper.EqualsIgnoreCase(server.getId(), lastServerId)){
                sorted.add(server);
            }
        }

        for(ServerInfo server : servers)  {
            if (!StringHelper.EqualsIgnoreCase(server.getId(), lastServerId)){
                sorted.add(server);
            }
        }

        if (servers.size() == 0){
            OnFailedConnection(response);
            return;
        }

        ConnectToServerAtListIndex(servers, 0, response);
    }

    private void ConnectToServerAtListIndex(final ArrayList<ServerInfo> servers,
                                            final int index,
                                            final Response<ConnectionResult> response){

        ServerInfo server = servers.get(index);

        Connect(server, true, new Response<ConnectionResult>() {

            private void TryNextServer() {

                int nextIndex = index + 1;
                if (nextIndex < servers.size()) {

                    _logger.Debug("Trying next server");
                    ConnectToServerAtListIndex(servers, nextIndex, response);

                } else {

                    // No connection is available
                    OnFailedConnection(response);
                }
            }

            @Override
            public void onResponse(ConnectionResult result) {

                if (result.getState() == ConnectionState.Unavailable) {
                    TryNextServer();
                } else {

                    _logger.Debug("Connected to server");
                    response.onResponse(result);
                }
            }

            @Override
            public void onError() {

                TryNextServer();
            }

        });
    }

    @Override
    public void Connect(final ServerInfo server,
                        final Response<ConnectionResult> response) {

        Connect(server, true, response);
    }

    private void Connect(final ServerInfo server,
                        final boolean enableWakeOnLan,
                        final Response<ConnectionResult> response) {

        boolean isConnectedToLocalNetwork = _networkConnectivity.getNetworkStatus().GetIsLocalNetworkAvailable();

        if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(server.getLocalAddress()) && isConnectedToLocalNetwork)
        {
            TryConnect(server.getLocalAddress(), new Response<PublicSystemInfo>(){

                @Override
                public void onResponse(PublicSystemInfo result) {

                    ConnectToFoundServer(server, result, ConnectionMode.Local, true, response);
                }

                @Override
                public void onError() {

                    // Wake on lan and try again
                    if (enableWakeOnLan && server.getWakeOnLanInfos().size() > 0)
                    {
                        WakeServer(server, new EmptyResponse() {

                            @Override
                            public void onResponse() {

                                // Try local connection again
                                Connect(server, false, response);
                            }

                            @Override
                            public void onError() {

                                // No local connection available
                                TryConnectToRemoteAddress(server, response);
                            }
                        });

                        return;
                    }

                    // No local connection available
                    TryConnectToRemoteAddress(server, response);
                }

            });

            return;
        }

        TryConnectToRemoteAddress(server, response);
    }

    private void TryConnectToRemoteAddress(final ServerInfo server,
                                           final Response<ConnectionResult> response){

        // If local connection is unavailable, try to connect to the remote address
        if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(server.getRemoteAddress()))
        {
            TryConnect(server.getRemoteAddress(), new Response<PublicSystemInfo>(){

                @Override
                public void onResponse(PublicSystemInfo result) {

                    ConnectToFoundServer(server, result, ConnectionMode.Remote, true, response);
                }

                @Override
                public void onError() {

                    // Unable to connect
                    OnFailedConnection(response);
                }

            });

            return;
        }

        // Unable to connect
        OnFailedConnection(response);
    }

    public void ConnectToFoundServer(final ServerInfo server,
                                     final PublicSystemInfo systemInfo,
                                     final ConnectionMode connectionMode,
                                     boolean verifyAuthentication,
                                     final Response<ConnectionResult> response) {

        UpdateServerInfo(server, systemInfo);

        if (verifyAuthentication && !tangible.DotNetToJavaStringHelper.isNullOrEmpty(server.getAccessToken()))
        {
            ValidateAuthentication(server, connectionMode, new EmptyResponse(){

                @Override
                public void onResponse() {

                    ConnectToFoundServer(server, systemInfo, connectionMode, false, response);
                }

                @Override
                public void onError() {

                    response.onError();
                }
            });
        }

        ServerCredentialConfiguration credentials = _credentialProvider.GetCredentials();

        credentials.AddOrUpdateServer(server);
        credentials.setLastServerId(server.getId());
        _credentialProvider.SaveCredentials(credentials);

        ConnectionResult result = new ConnectionResult();

        result.setApiClient(GetOrAddApiClient(server));
        result.setState(tangible.DotNetToJavaStringHelper.isNullOrEmpty(server.getAccessToken()) ?
                ConnectionState.ServerSignIn :
                ConnectionState.SignedIn);

        if (result.getState() == ConnectionState.SignedIn)
        {
            EnsureWebSocketIfConfigured(result.getApiClient());
        }

        result.setServerInfo(server);
        result.getApiClient().EnableAutomaticNetworking(server, connectionMode, _networkConnectivity);

        response.onResponse(result);
    }

    @Override
    public void Connect(String address, Response<ConnectionResult> response) {

        address = NormalizeAddress(address);

        _logger.Debug("Attempting to connect to server at %s", address);

        ServerInfo server = new ServerInfo();
        server.setLocalAddress(address);

        Connect(server, response);
    }

    @Override
    public void Logout(final Response<ConnectionResult> response) {

        _logger.Debug("Logging out of all servers");

        LogoutAll(new EmptyResponse() {

            private void OnSuccessOrFail() {

                _logger.Debug("Updating saved credentials for all servers");
                ServerCredentialConfiguration credentials = _credentialProvider.GetCredentials();

                for (ServerInfo server : credentials.getServers()) {

                    server.setAccessToken(null);
                    server.setUserId(null);
                }

                _credentialProvider.SaveCredentials(credentials);

                Connect(response);
            }

            @Override
            public void onResponse() {
                OnSuccessOrFail();
            }

            @Override
            public void onError() {
                OnSuccessOrFail();
            }
        });
    }

    private Observable connectedObservable = new Observable();
    @Override
    public Observable getConnectedObservable() {
        return connectedObservable;
    }

    private Observable remoteLoggedOutObservable = new Observable();
    @Override
    public Observable getRemoteLoggedOutObservable() {
        return remoteLoggedOutObservable;
    }

    private void ValidateAuthentication(final ServerInfo server, ConnectionMode connectionMode, final EmptyResponse response)
    {
        String url = connectionMode == ConnectionMode.Local ? server.getLocalAddress() : server.getRemoteAddress();

        url += "/mediabrowser/system/info?format=json";

        HttpHeaders headers = new HttpHeaders();
        headers.SetAccessToken(server.getAccessToken());

        final HttpRequest request = new HttpRequest();
        request.setUrl(url);
        request.setMethod("GET");
        request.setRequestHeaders(headers);

        Response<String> stringResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                SystemInfo obj = jsonSerializer.DeserializeFromString(jsonResponse, SystemInfo.class);

                UpdateServerInfo(server, obj);
                response.onResponse();
            }

            @Override
            public void onError() {

                server.setUserId(null);
                server.setAccessToken(null);
            }
        };

        _httpClient.Send(request, stringResponse);
    }

    private void TryConnect(String url, final Response<PublicSystemInfo> response)
    {
        url += "/mediabrowser/system/info/public?format=json";

        HttpRequest request = new HttpRequest();
        request.setUrl(url);
        request.setMethod("GET");

        Response<String> stringResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                PublicSystemInfo obj = jsonSerializer.DeserializeFromString(jsonResponse, PublicSystemInfo.class);

                response.onResponse(obj);
            }

            @Override
            public void onError() {

                response.onError();
            }
        };

        _httpClient.Send(request, stringResponse);
    }

    private void UpdateServerInfo(ServerInfo server, PublicSystemInfo systemInfo)
    {
        server.setName(systemInfo.getServerName());
        server.setId(systemInfo.getId());

        if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(systemInfo.getLocalAddress()))
        {
            server.setLocalAddress(systemInfo.getLocalAddress());
        }
        if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(systemInfo.getWanAddress()))
        {
            server.setRemoteAddress(systemInfo.getWanAddress());
        }

        if (systemInfo instanceof SystemInfo)
        {
            SystemInfo fullSystemInfo = (SystemInfo)systemInfo;

            server.setWakeOnLanInfos(new ArrayList<WakeOnLanInfo>());

            if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(fullSystemInfo.getMacAddress()))
            {
                WakeOnLanInfo info = new WakeOnLanInfo();
                info.setMacAddress(fullSystemInfo.getMacAddress());

                server.getWakeOnLanInfos().add(info);
            }
        }
    }

    private ApiClient GetOrAddApiClient(ServerInfo server)
    {
        ApiClient apiClient = apiClients.get(server.getId());

        if (apiClient == null){

            apiClient = new ApiClient(_httpClient,
                    _logger,
                    server.getLocalAddress(),
                    applicationName,
                    device.getDeviceName(),
                    device.getDeviceId(),
                    applicationVersion,
                    apiEventListener,
                    clientCapabilities);

            apiClients.put(server.getId(), apiClient);

            final ApiClient finalApiClient = apiClient;

            apiClient.getAuthenticatedObservable().addObserver(new GenericObserver() {

                @Override
                public void update(Observable observable, Object o)
                {
                    OnAuthenticated(finalApiClient, (AuthenticationResult) o);
                }

            });
        }

        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(server.getAccessToken()))
        {
            apiClient.ClearAuthenticationInfo();
        }
        else
        {
            apiClient.SetAuthenticationInfo(server.getAccessToken(), server.getUserId());

            EnsureWebSocketIfConfigured(apiClient);
        }

        return apiClient;
    }

    private void EnsureWebSocketIfConfigured(ApiClient apiClient)
    {
        apiClient.OpenWebSocket();
    }

    private void OnAuthenticated(final ApiClient apiClient, final AuthenticationResult result)
    {
        _logger.Debug("Updating credentials after authentication");

        apiClient.GetSystemInfoAsync(new Response<SystemInfo>(){

            @Override
            public void onResponse(SystemInfo info) {

                ServerInfo server = apiClient.getServerInfo();
                UpdateServerInfo(server, info);

                ServerCredentialConfiguration credentials = _credentialProvider.GetCredentials();
                credentials.setLastServerId(server.getId());

                server.setUserId(result.getUser().getId());
                server.setAccessToken(result.getAccessToken());

                credentials.AddOrUpdateServer(server);
                _credentialProvider.SaveCredentials(credentials);

                EnsureWebSocketIfConfigured(apiClient);
            }
        });
    }

    private void GetAvailableServers(final Response<ArrayList<ServerInfo>> response)
    {
        _logger.Debug("Locating available servers");

        _logger.Debug("Getting saved servers via credential provider");
        ArrayList<ServerInfo> servers = _credentialProvider.GetCredentials().getServers();

        response.onResponse(servers);
    }

    private void FindServers(final Response<ArrayList<ServerInfo>> response)
    {
        final ArrayList<ServerInfo> servers = new ArrayList<ServerInfo>();

        Response<ServerDiscoveryInfo[]> serverDiscoveryResponse = new Response<ServerDiscoveryInfo[]>(){

            @Override
            public void onResponse(ServerDiscoveryInfo[] foundServers) {

                for (int i=0; i< foundServers.length; i++) {

                    ServerInfo server = new ServerInfo();

                    server.setId(foundServers[i].getId());
                    server.setLocalAddress(foundServers[i].getAddress());
                    server.setName(foundServers[i].getName());

                    servers.add(server);
                }

                response.onResponse(servers);
            }

            @Override
            public void onError() {

                response.onResponse(servers);
            }
        };

        _serverDiscovery.FindServers(serverDiscoveryResponse);
    }

    private void WakeAllServers()
    {
        _logger.Debug("Waking all servers");

        for(ServerInfo server : _credentialProvider.GetCredentials().getServers()){

            WakeServer(server, new EmptyResponse());
        }
    }

    private void WakeServer(ServerInfo info, final EmptyResponse response)
    {
        _logger.Debug("Waking server: %s, Id: %s", info.getName(), info.getId());

        ArrayList<WakeOnLanInfo> wakeList = info.getWakeOnLanInfos();

        final int count = wakeList.size();

        if (count == 0){
            _logger.Debug("Server %s has no saved wake on lan profiles", info.getName());
            response.onResponse();
            return;
        }

        final ArrayList<EmptyResponse> doneList = new ArrayList<EmptyResponse>();

        for(WakeOnLanInfo wakeOnLanInfo : wakeList){

            WakeServer(info, new EmptyResponse(){

                private void OnServerDone(){
                    synchronized(doneList) {

                        doneList.add(new EmptyResponse());

                        if (doneList.size() >= count){
                            response.onResponse();
                        }
                    }
                }

                @Override
                public void onResponse() {

                    OnServerDone();
                }

                @Override
                public void onError() {

                    OnServerDone();
                }
            });
        }
    }

    private void WakeServer(WakeOnLanInfo info, EmptyResponse response) throws IOException {

        _networkConnectivity.SendWakeOnLan(info.getMacAddress(), info.getPort(), response);
    }

    private String NormalizeAddress(String address) throws IllegalArgumentException {

        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(address))
        {
            throw new IllegalArgumentException("address");
        }

        if (StringHelper.IndexOfIgnoreCase(address, "http") == -1)
        {
            address = "http://" + address;
        }

        return address;
    }

    private void LogoutAll(final EmptyResponse response){

        Object[] clientList = apiClients.values().toArray();

        final int count = clientList.length;

        if (count == 0){
            response.onResponse();
            return;
        }

        final ArrayList<EmptyResponse> doneList = new ArrayList<EmptyResponse>();

        for(Object clientObj : clientList){

            ApiClient client = (ApiClient)clientObj;
            client.Logout(new EmptyResponse() {

                @Override
                public void onResponse() {

                    synchronized (doneList) {

                        doneList.add(new EmptyResponse());

                        if (doneList.size() >= count) {
                            response.onResponse();
                        }

                    }

                }

                @Override
                public void onError() {

                    synchronized (doneList) {

                        doneList.add(new EmptyResponse());

                        if (doneList.size() >= count) {
                            response.onResponse();
                        }

                    }

                }

            });
        }
    }
}
