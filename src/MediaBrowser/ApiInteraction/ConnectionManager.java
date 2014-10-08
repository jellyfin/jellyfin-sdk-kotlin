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

    @Override
    public void Connect(final Response<ConnectionResult> response) {

        GetAvailableServers(new Response<ArrayList<ServerInfo>>(){

            @Override
            public void onResponse(ArrayList<ServerInfo> servers) {

                ServerCredentialConfiguration credentials = _credentialProvider.GetCredentials();

                final String lastServerId = credentials.getLastServerId();

                Connect(credentials.getServers(), lastServerId, new Response<ConnectionResult>(){

                    @Override
                    public void onResponse(ConnectionResult result) {

                        if (result.getState() != ConnectionState.Unavailable)
                        {
                            response.onResponse(result);
                            return;
                        }

                        FindServers(new Response<ArrayList<ServerInfo>>(){

                            @Override
                            public void onResponse(ArrayList<ServerInfo> foundServers) {

                                Connect(foundServers, lastServerId, response);
                            }
                        });
                    }

                    @Override
                    public void onError() {

                        response.onError();
                    }
                });
            }

            @Override
            public void onError() {

                response.onError();
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
            ConnectionResult result = new ConnectionResult();
            result.setState(ConnectionState.Unavailable);
            response.onResponse(result);
            return;
        }
    }

    @Override
    public void Connect(ServerInfo server, Response<ConnectionResult> response) {

        ConnectionResult result = new ConnectionResult();
        result.setState(ConnectionState.Unavailable);
        response.onResponse(result);
    }

    @Override
    public void Connect(String address, Response<ConnectionResult> response) {

        ServerInfo server = new ServerInfo();
        server.setLocalAddress(NormalizeAddress(address));

        Connect(server, response);
    }

    @Override
    public void Logout(final Response<ConnectionResult> response) {

        LogoutAll(new EmptyResponse(){

            @Override
            public void onResponse() {

                ServerCredentialConfiguration credentials = _credentialProvider.GetCredentials();

                for(ServerInfo server : credentials.getServers()){

                    server.setAccessToken(null);
                    server.setUserId(null);
                }

                _credentialProvider.SaveCredentials(credentials);

                Connect(response);
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

                PublicSystemInfo obj = null;

                response.onResponse(obj);
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
        Response<SystemInfo> systemInfoResponse = new Response<SystemInfo>(){

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
        };

        apiClient.GetSystemInfoAsync(systemInfoResponse);
    }

    private void GetAvailableServers(final Response<ArrayList<ServerInfo>> response)
    {
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
        for(ServerInfo server : _credentialProvider.GetCredentials().getServers()){

            WakeServer(server, new EmptyResponse());
        }
    }

    private void WakeServer(ServerInfo info, final EmptyResponse response)
    {
        ArrayList<WakeOnLanInfo> wakeList = info.getWakeOnLanInfos();

        final int count = wakeList.size();

        if (count == 0){
            response.onResponse();
            return;
        }

        final ArrayList<EmptyResponse> doneList = new ArrayList<EmptyResponse>();

        for(WakeOnLanInfo wakeOnLanInfo : wakeList){

            WakeServer(info, new EmptyResponse(){

                @Override
                public void onResponse() {

                    synchronized(doneList) {

                        doneList.add(new EmptyResponse());

                        if (doneList.size() >= count){
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
