package org.jellyfin.apiclient.interaction.connectionmanager;

import org.jellyfin.apiclient.interaction.ApiClient;
import org.jellyfin.apiclient.interaction.ApiEventListener;
import org.jellyfin.apiclient.interaction.ConnectionResult;
import org.jellyfin.apiclient.interaction.EmptyResponse;
import org.jellyfin.apiclient.interaction.IConnectionManager;
import org.jellyfin.apiclient.interaction.ICredentialProvider;
import org.jellyfin.apiclient.interaction.Response;
import org.jellyfin.apiclient.interaction.SerializedResponse;
import org.jellyfin.apiclient.interaction.device.IDevice;
import org.jellyfin.apiclient.interaction.discovery.IServerLocator;
import org.jellyfin.apiclient.interaction.http.HttpHeaders;
import org.jellyfin.apiclient.interaction.http.HttpRequest;
import org.jellyfin.apiclient.interaction.http.IAsyncHttpClient;
import org.jellyfin.apiclient.model.apiclient.ConnectionOptions;
import org.jellyfin.apiclient.model.apiclient.ConnectionState;
import org.jellyfin.apiclient.model.apiclient.ServerCredentials;
import org.jellyfin.apiclient.model.apiclient.ServerInfo;
import org.jellyfin.apiclient.model.dto.IHasServerId;
import org.jellyfin.apiclient.model.dto.UserDto;
import org.jellyfin.apiclient.model.extensions.StringHelper;
import org.jellyfin.apiclient.model.logging.ILogger;
import org.jellyfin.apiclient.serialization.IJsonSerializer;
import org.jellyfin.apiclient.model.session.ClientCapabilities;
import org.jellyfin.apiclient.model.system.PublicSystemInfo;
import org.jellyfin.apiclient.model.users.AuthenticationResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Pattern;

public class ConnectionManager implements IConnectionManager {

    private ICredentialProvider credentialProvider;
    protected ILogger logger;
    private IServerLocator serverDiscovery;
    protected IAsyncHttpClient httpClient;

    private HashMap<String, ApiClient> apiClients = new HashMap<>();
    protected IJsonSerializer jsonSerializer;

    protected String applicationName;
    protected String applicationVersion;
    protected IDevice device;
    protected ClientCapabilities clientCapabilities;
    protected ApiEventListener apiEventListener;

    public ConnectionManager(ICredentialProvider credentialProvider,
                             IJsonSerializer jsonSerializer,
                             ILogger logger,
                             IServerLocator serverDiscovery,
                             IAsyncHttpClient httpClient,
                             String applicationName,
                             String applicationVersion,
                             IDevice device,
                             ClientCapabilities clientCapabilities,
                             ApiEventListener apiEventListener) {

        this.credentialProvider = credentialProvider;
        this.logger = logger;
        this.serverDiscovery = serverDiscovery;
        this.httpClient = httpClient;
        this.applicationName = applicationName;
        this.applicationVersion = applicationVersion;
        this.device = device;
        this.clientCapabilities = clientCapabilities;
        this.apiEventListener = apiEventListener;
        this.jsonSerializer = jsonSerializer;
    }

    public ClientCapabilities getClientCapabilities() {
        return clientCapabilities;
    }

    @Override
    public ApiClient GetApiClient(IHasServerId item) {
        return GetApiClient(item.getServerId());
    }

    @Override
    public ApiClient GetApiClient(String serverId) {
        return apiClients.get(serverId);
    }

    @Override
    public ServerInfo getServerInfo(String serverId) {
        final ServerCredentials credentials = credentialProvider.GetCredentials();
        for (ServerInfo server : credentials.getServers()) {
            if (StringHelper.equalsIgnoreCase(server.getId(), serverId)) {
                return server;
            }
        }

        return null;
    }

    @Override
    public IDevice getDevice() {
        return this.device;
    }

    void OnFailedConnection(Response<ConnectionResult> response) {
        logger.Debug("No server available");
        ConnectionResult result = new ConnectionResult();
        result.setState(ConnectionState.Unavailable);
        response.onResponse(result);
    }

    void OnFailedConnection(Response<ConnectionResult> response, ArrayList<ServerInfo> servers) {
        logger.Debug("No saved authentication");
        ConnectionResult result = new ConnectionResult();
        result.setState(ConnectionState.ServerSelection);
        result.setServers(new ArrayList<>());
        response.onResponse(result);
    }

    @Override
    public void Connect(final Response<ConnectionResult> response) {
        logger.Debug("Entering initial connection workflow");
        GetAvailableServers(new GetAvailableServersResponse(logger, this, response));
    }

    @Override
    public void GetSavedServers(final Response<ArrayList<ServerInfo>> response) {
        final ServerCredentials credentials = credentialProvider.GetCredentials();
        response.onResponse(credentials.getServers());
    }

    void Connect(final ArrayList<ServerInfo> servers, final Response<ConnectionResult> response) {
        // Sort by last date accessed, descending
        Collections.sort(servers, new ServerInfoDateComparator());
        Collections.reverse(servers);

        if (servers.size() == 1) {
            Connect(servers.get(0), new ConnectionOptions(), new ConnectToSingleServerListResponse(response));
            return;
        }

        // Check the first server for a saved access token
        if (servers.size() == 0 || tangible.DotNetToJavaStringHelper.isNullOrEmpty(servers.get(0).getAccessToken())) {
            OnFailedConnection(response, servers);
            return;
        }

        ServerInfo firstServer = servers.get(0);
        Connect(firstServer, new ConnectionOptions(), new FirstServerConnectResponse(this, servers, response));
    }

    @Override
    public void Connect(final ServerInfo server, final Response<ConnectionResult> response) {
        Connect(server, new ConnectionOptions(), response);
    }

    @Override
    public void Connect(final ServerInfo server,
                        ConnectionOptions options,
                        final Response<ConnectionResult> response) {
        final String address = server.getAddress();
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(address)) {
            OnFailedConnection(response);
            return;
        }

        TryConnect(address, new TryConnectResponse(this, server, options, logger, response));
    }

    void OnSuccessfulConnection(final ServerInfo server,
                                final PublicSystemInfo systemInfo,
                                final ConnectionOptions connectionOptions,
                                final Response<ConnectionResult> response) {

        if (systemInfo == null) {
            throw new IllegalArgumentException();
        }

        ServerCredentials credentials = credentialProvider.GetCredentials();
        AfterConnectValidated(server, credentials, systemInfo, true, connectionOptions, response);
    }

    void AfterConnectValidated(final ServerInfo server,
                               final ServerCredentials credentials,
                               final PublicSystemInfo systemInfo,
                               boolean verifyLocalAuthentication,
                               final ConnectionOptions options,
                               final Response<ConnectionResult> response) {
        if (verifyLocalAuthentication && !tangible.DotNetToJavaStringHelper.isNullOrEmpty(server.getAccessToken())) {
            ValidateAuthentication(server, new AfterConnectValidatedResponse(this, server, credentials, systemInfo, options, response));
            return;
        }

        server.ImportInfo(systemInfo);

        if (options.getUpdateDateLastAccessed()) {
            server.setDateLastAccessed(new Date());
        }

        credentials.AddOrUpdateServer(server);
        credentialProvider.SaveCredentials(credentials);

        ConnectionResult result = new ConnectionResult();
        result.setApiClient(GetOrAddApiClient(server));
        result.setState(tangible.DotNetToJavaStringHelper.isNullOrEmpty(server.getAccessToken()) ?
                ConnectionState.ServerSignIn :
                ConnectionState.SignedIn);

        result.getServers().add(server);
        result.getApiClient().EnableAutomaticNetworking(server);

        if (result.getState() == ConnectionState.SignedIn) {
            AfterConnected(result.getApiClient(), options);
        }

        response.onResponse(result);
    }

    private void ConnectMultiple(final String[] addresses, final int current, final Response<ConnectionResult> innerResponse) {
        Response<ConnectionResult> response = new Response<ConnectionResult>() {
            @Override
            public void onResponse(ConnectionResult result) {
                if (result.getState() == ConnectionState.Unavailable) {
                    int next = current + 1;
                    if (addresses.length > next) {
                        ConnectMultiple(addresses, next, innerResponse);
                        return;
                    }
                }

                innerResponse.onResponse(result);
            }

            @Override
            public void onError(Exception exception) {
                innerResponse.onError(exception);
            }
        };

        String address = addresses[current];

        logger.Debug("Attempting to connect to server at %s", address);
        ServerInfo server = new ServerInfo();
        server.setAddress(address);
        Connect(server, new ConnectionOptions(), response);
    }

    @Override
    public void Connect(final String address, final Response<ConnectionResult> response) {
        final String[] normalizedAddresses = NormalizeAddress(address);
        ConnectMultiple(normalizedAddresses, 0, response);
    }

    @Override
    public void Logout(final EmptyResponse response) {
        logger.Debug("Logging out of all servers");
        LogoutAll(new LogoutAllResponse(credentialProvider, logger, response, this));
    }

    private void ValidateAuthentication(final ServerInfo server, final EmptyResponse response) {
        final String url = server.getAddress();

        HttpHeaders headers = new HttpHeaders();
        headers.SetAccessToken(server.getAccessToken());

        final HttpRequest request = new HttpRequest();
        request.setUrl(url + "/system/info?format=json");
        request.setMethod("GET");
        request.setRequestHeaders(headers);

        Response<String> stringResponse = new ValidateAuthenticationResponse(this, jsonSerializer, server, response, request, httpClient, url);

        httpClient.Send(request, stringResponse);
    }

    void TryConnect(String url, final Response<PublicSystemInfo> response) {
        url += "/system/info/public?format=json";

        HttpRequest request = new HttpRequest();
        request.setUrl(url);
        request.setMethod("GET");
        request.setTimeout(8000); // 8 seconds

        httpClient.Send(request, new SerializedResponse<>(response, jsonSerializer, PublicSystemInfo.class));
    }

    protected ApiClient InstantiateApiClient(String serverAddress) {
        return new ApiClient(httpClient,
                jsonSerializer,
                logger,
                serverAddress,
                applicationName,
                applicationVersion,
                device,
                apiEventListener);
    }

    private ApiClient GetOrAddApiClient(ServerInfo server) {
        ApiClient apiClient = apiClients.get(server.getId());
        if (apiClient == null) {
            String address = server.getAddress();
            apiClient = InstantiateApiClient(address);
            apiClients.put(server.getId(), apiClient);
            apiClient.getAuthenticatedObservable().addObserver(new AuthenticatedObserver(this, apiClient));
        }

        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(server.getAccessToken())) {
            apiClient.ClearAuthenticationInfo();
        } else {
            apiClient.SetAuthenticationInfo(server.getAccessToken(), server.getUserId());
        }

        return apiClient;
    }

    void AfterConnected(ApiClient apiClient, ConnectionOptions options) {
        if (options.getReportCapabilities()) {
            apiClient.ReportCapabilities(clientCapabilities, new EmptyResponse());
        }

        if (options.getEnableWebSocket()) {
            apiClient.ensureWebSocket();
        }
    }

    void OnAuthenticated(final ApiClient apiClient,
                         final AuthenticationResult result,
                         ConnectionOptions options,
                         final boolean saveCredentials) {
        logger.Debug("Updating credentials after local authentication");

        ServerInfo server = apiClient.getServerInfo();

        ServerCredentials credentials = credentialProvider.GetCredentials();

        if (options.getUpdateDateLastAccessed()) {
            server.setDateLastAccessed(new Date());
        }

        if (saveCredentials) {
            server.setUserId(result.getUser().getId());
            server.setAccessToken(result.getAccessToken());
        } else {
            server.setUserId(null);
            server.setAccessToken(null);
        }

        credentials.AddOrUpdateServer(server);
        credentialProvider.SaveCredentials(credentials);

        AfterConnected(apiClient, options);
        OnLocalUserSignIn(result.getUser());
    }

    void OnLocalUserSignIn(UserDto user) {
        // TODO: Fire event
    }

    void OnLocalUserSignout(ApiClient apiClient) {
        // TODO: Fire event
    }

    public void GetAvailableServers(final Response<ArrayList<ServerInfo>> response) {
        logger.Debug("Getting saved servers via credential provider");
        ServerCredentials credentials;
        try {
            credentials = credentialProvider.GetCredentials();
        } catch (Exception ex) {
            logger.ErrorException("Error getting available servers", ex);
            response.onResponse(new ArrayList<>());
            return;
        }

        logger.Debug("Scanning network for local servers");
        Response<ArrayList<ServerInfo>> findServersResponse = new FindServersResponse(this, credentials, new ArrayList<>(), response);
        FindServers(findServersResponse);
    }

    void OnGetServerResponse(ServerCredentials credentials,
                             ArrayList<ServerInfo> foundServers,
                             Response<ArrayList<ServerInfo>> response) {
        for (ServerInfo newServer : foundServers) {
            credentials.AddOrUpdateServer(newServer);
        }

        ArrayList<ServerInfo> servers = credentials.getServers();

        // Sort by last date accessed, descending
        Collections.sort(servers, new ServerInfoDateComparator());
        Collections.reverse(servers);

        credentials.setServers(servers);

        credentialProvider.SaveCredentials(credentials);

        ArrayList<ServerInfo> clone = new ArrayList<>();
        clone.addAll(credentials.getServers());

        response.onResponse(clone);
    }

    protected void FindServers(final Response<ArrayList<ServerInfo>> response) {
        FindServersInternal(response);
    }

    protected void FindServersInternal(final Response<ArrayList<ServerInfo>> response) {
        serverDiscovery.FindServers(1000, new FindServersInnerResponse(this, response));
    }

    public String[] NormalizeAddress(String address) throws IllegalArgumentException {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(address)) {
            throw new IllegalArgumentException("address");
        }

        // Better be safe
        address = address.trim();

        boolean protocolFound = Pattern.compile("^https?://.*$", Pattern.CASE_INSENSITIVE).matcher(address).matches();
        if (protocolFound) return new String[]{address};

        // Extra things like the default port could be added as options to this list

        return new String[]{
                "https://" + address,
                "http://" + address
        };
    }

    private void LogoutAll(final EmptyResponse response) {
        Object[] clientList = apiClients.values().toArray();

        final int count = clientList.length;
        if (count == 0) {
            response.onResponse();
            return;
        }

        final ArrayList<Integer> doneList = new ArrayList<>();
        for (Object clientObj : clientList) {
            ApiClient client = (ApiClient) clientObj;
            ApiClientLogoutResponse logoutResponse = new ApiClientLogoutResponse(doneList, count, response, this, client);
            if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(client.getAccessToken())) {
                client.Logout(logoutResponse);
            } else {
                logoutResponse.onResponse(false);
            }
        }
    }

    public void DeleteServer(final String id, final EmptyResponse response) {
        ServerCredentials credentials = credentialProvider.GetCredentials();
        ArrayList<ServerInfo> existing = credentials.getServers();

        ServerInfo server = null;
        for (ServerInfo current : existing) {
            if (StringHelper.equalsIgnoreCase(current.getId(), id)) {
                server = current;
                break;
            }
        }

        if (server == null) {
            response.onResponse();
            return;
        }

        OnServerDeleteResponse(id, response);
    }

    private void OnServerDeleteResponse(String id, EmptyResponse response) {
        ServerCredentials credentials = credentialProvider.GetCredentials();
        ArrayList<ServerInfo> existing = credentials.getServers();
        ArrayList<ServerInfo> newList = new ArrayList<>();

        for (ServerInfo current : existing) {
            if (!StringHelper.equalsIgnoreCase(current.getId(), id)) {
                newList.add(current);
            }
        }

        credentials.setServers(newList);
        credentialProvider.SaveCredentials(credentials);
        response.onResponse();
    }
}
