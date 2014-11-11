package mediabrowser.apiinteraction;

import mediabrowser.apiinteraction.cryptography.Md5;
import mediabrowser.apiinteraction.cryptography.Sha1;
import mediabrowser.apiinteraction.device.IDevice;
import mediabrowser.apiinteraction.http.HttpRequest;
import mediabrowser.apiinteraction.http.IAsyncHttpClient;
import mediabrowser.apiinteraction.network.INetworkConnection;
import mediabrowser.apiinteraction.tasks.CancellationToken;
import mediabrowser.apiinteraction.tasks.IProgress;
import mediabrowser.apiinteraction.websocket.ApiWebSocket;
import mediabrowser.model.apiclient.ConnectionMode;
import mediabrowser.model.apiclient.RemoteLogoutReason;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.channels.AllChannelMediaQuery;
import mediabrowser.model.channels.ChannelFeatures;
import mediabrowser.model.channels.ChannelItemQuery;
import mediabrowser.model.channels.ChannelQuery;
import mediabrowser.model.configuration.ServerConfiguration;
import mediabrowser.model.connect.ConnectPassword;
import mediabrowser.model.devices.ContentUploadHistory;
import mediabrowser.model.devices.DevicesOptions;
import mediabrowser.model.devices.LocalFileInfo;
import mediabrowser.model.dto.*;
import mediabrowser.model.entities.DisplayPreferences;
import mediabrowser.model.entities.ParentalRating;
import mediabrowser.model.extensions.StringHelper;
import mediabrowser.model.globalization.CountryInfo;
import mediabrowser.model.globalization.CultureDto;
import mediabrowser.model.livetv.*;
import mediabrowser.model.logging.ILogger;
import mediabrowser.model.net.HttpException;
import mediabrowser.model.notifications.NotificationQuery;
import mediabrowser.model.notifications.NotificationResult;
import mediabrowser.model.notifications.NotificationsSummary;
import mediabrowser.model.playlists.PlaylistCreationRequest;
import mediabrowser.model.playlists.PlaylistCreationResult;
import mediabrowser.model.playlists.PlaylistItemQuery;
import mediabrowser.model.plugins.PluginInfo;
import mediabrowser.model.querying.*;
import mediabrowser.model.results.*;
import mediabrowser.model.search.SearchHintResult;
import mediabrowser.model.search.SearchQuery;
import mediabrowser.model.serialization.IJsonSerializer;
import mediabrowser.model.session.*;
import mediabrowser.model.system.PublicSystemInfo;
import mediabrowser.model.system.SystemInfo;
import mediabrowser.model.tasks.TaskInfo;
import mediabrowser.model.tasks.TaskTriggerInfo;
import mediabrowser.model.users.AuthenticationResult;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;

public class ApiClient extends BaseApiClient {
 
    protected IAsyncHttpClient httpClient;
    private ApiEventListener apiEventListener;

    private ConnectionMode connectionMode = ConnectionMode.Local;
    private INetworkConnection networkConnection;
    private ApiWebSocket apiWebSocket;

    private ServerInfo serverInfo;
    public ServerInfo getServerInfo(){
        return serverInfo;
    }

    private ClientCapabilities capabilities;
    public ClientCapabilities getCapabilities(){
        return capabilities;
    }

    private Observable authenticatedObservable = new Observable();
    public Observable getAuthenticatedObservable() {
        return authenticatedObservable;
    }

    public ApiClient(IAsyncHttpClient httpClient, IJsonSerializer jsonSerializer, ILogger logger, String serverAddress, String accessToken, ApiEventListener apiEventListener, ClientCapabilities capabilities)
    {
        super(logger, jsonSerializer, serverAddress, accessToken);

        this.httpClient = httpClient;
        this.apiEventListener = apiEventListener;
        this.capabilities = capabilities;

        ResetHttpHeaders();
    }

    public ApiClient(IAsyncHttpClient httpClient, IJsonSerializer jsonSerializer, ILogger logger, String serverAddress, String clientName, IDevice device, String applicationVersion, ApiEventListener apiEventListener, ClientCapabilities capabilities)
    {
        super(logger, jsonSerializer, serverAddress, clientName, device, applicationVersion);

        this.httpClient = httpClient;
        this.apiEventListener = apiEventListener;
        this.capabilities = capabilities;

        ResetHttpHeaders();
    }

    public void EnableAutomaticNetworking(ServerInfo info, ConnectionMode initialMode, INetworkConnection networkConnection)
    {
        this.networkConnection = networkConnection;
        this.connectionMode = initialMode;
        this.serverInfo = info;

        String serverAddress = initialMode == ConnectionMode.Local ?
                info.getLocalAddress() :
                info.getRemoteAddress();

        setServerAddress(serverAddress);
    }

    public void OpenWebSocket(){

        if (apiWebSocket == null){

            Logger.Debug("Creating ApiWebSocket");
            apiWebSocket = new ApiWebSocket(getJsonSerializer(), Logger, apiEventListener, this);
        }

        apiWebSocket.EnsureWebSocket();
    }

    private void OnRemoteLoggedOut(HttpException httpError) {

        RemoteLogoutReason reason = RemoteLogoutReason.GeneralAccesError;

        if (httpError.getHeaders() != null  ){

            String errorCode = httpError.getHeaders().get("X-Application-Error-Code");

            if (StringHelper.EqualsIgnoreCase(errorCode, "ParentalControl")) {
                reason = RemoteLogoutReason.ParentalControlRestriction;
            }
        }

        apiEventListener.onRemoteLoggedOut(this, reason);
    }

    private void SendRequest(HttpRequest request, final boolean fireGlobalEvents, final Response<String> response)
    {
        httpClient.Send(request, new Response<String>(){

            @Override
            public void onResponse(String stringResponse) {

                response.onResponse(stringResponse);
            }

            @Override
            public void onError(Exception ex) {

                if (ex instanceof HttpException) {

                    HttpException httpError = (HttpException)ex;

                    if (fireGlobalEvents && httpError.getStatusCode() != null && httpError.getStatusCode() == 401) {

                        OnRemoteLoggedOut(httpError);
                    }
                }

                response.onError(ex);
            }
        });
    }

    private void Send(String url, String method, final Response<String> response)
    {
        HttpRequest request = new HttpRequest();
        request.setUrl(url);
        request.setMethod(method);
        request.setRequestHeaders(this.HttpHeaders);
        SendRequest(request, true, response);
    }

    private void Send(String url, String method, String requestContent, String requestContentType, final Response<String> response)
    {
        HttpRequest request = new HttpRequest();
        request.setUrl(url);
        request.setMethod(method);
        request.setRequestHeaders(this.HttpHeaders);
        request.setRequestContent(requestContent);
        request.setRequestContentType(requestContentType);
        SendRequest(request, true, response);
    }

    private void Send(String url,
                      String method,
                      QueryStringDictionary postData,
                      boolean fireGlobalEvents,
                      final Response<String> response)
    {
        HttpRequest request = new HttpRequest();
        request.setUrl(url);
        request.setMethod(method);
        request.setRequestHeaders(this.HttpHeaders);
        request.setPostData(postData);
        SendRequest(request, fireGlobalEvents, response);
    }

    public void GetItemAsync(String id, String userId, final Response<BaseItemDto> response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(id))
        {
            throw new IllegalArgumentException("id");
        }

        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(userId))
        {
            throw new IllegalArgumentException("userId");
        }

        String url = GetApiUrl("Users/" + userId + "/Items/" + id);

        GetItemFromUrl(url, response);
    }

    public void GetIntrosAsync(String itemId, String userId, final Response<ItemsResult> response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(itemId))
        {
            throw new IllegalArgumentException("itemId");
        }

        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(userId))
        {
            throw new IllegalArgumentException("userId");
        }

        String url = GetApiUrl("Users/" + userId + "/Items/" + itemId + "/Intros");

        GetItemsFromUrl(url, response);
    }

    public void GetItemCountsAsync(ItemCountsQuery query, final Response<ItemCounts> response)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();

        dict.AddIfNotNullOrEmpty("UserId", query.getUserId());
        dict.AddIfNotNull("IsFavorite", query.getIsFavorite());

        String url = GetApiUrl("Items/Counts", dict);

        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<ItemCounts>(response, jsonSerializer, ItemCounts.class));
    }

    public void GetRootFolderAsync(String userId, final Response<BaseItemDto> response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(userId))
        {
            throw new IllegalArgumentException("userId");
        }

        String url = GetApiUrl("Users/" + userId + "/Items/Root");

        GetItemFromUrl(url, response);
    }

    public void GetUsersAsync(UserQuery query, final Response<UserDto[]> response)
    {
        QueryStringDictionary queryString = new QueryStringDictionary();

        queryString.AddIfNotNull("IsDisabled", query.getIsDisabled());
        queryString.AddIfNotNull("IsHidden", query.getIsHidden());

        String url = GetApiUrl("Users", queryString);

        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<UserDto[]>(response, jsonSerializer, new UserDto[]{}.getClass()));
    }

    public void GetPublicUsersAsync(final Response<UserDto[]> response)
    {
        String url = GetApiUrl("Users/Public");

        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<UserDto[]>(response, jsonSerializer, new UserDto[]{}.getClass()));
    }

    public void GetClientSessionsAsync(SessionQuery query, final Response<SessionInfoDto[]> response)
    {
        QueryStringDictionary queryString = new QueryStringDictionary();

        queryString.AddIfNotNullOrEmpty("ControllableByUserId", query.getControllableByUserId());

        String url = GetApiUrl("Sessions", queryString);

        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<SessionInfoDto[]>(response, jsonSerializer, new SessionInfoDto[]{}.getClass()));
    }

    private void GetItemsFromUrl(String url, final Response<ItemsResult> response) {

        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<ItemsResult>(response, jsonSerializer, ItemsResult.class));
    }

    private void GetItemFromUrl(String url, final Response<BaseItemDto> response) {

        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<BaseItemDto>(response, jsonSerializer, BaseItemDto.class));
    }

    public void GetItemsAsync(ItemQuery query, final Response<ItemsResult> response)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        String url = GetItemListUrl(query);

        GetItemsFromUrl(url, response);
    }

    /// <summary>
    /// Gets the next up async.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    /// <exception cref="System.IllegalArgumentException">query</exception>
    public void GetNextUpEpisodesAsync(NextUpQuery query, final Response<ItemsResult> response)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        String url = GetNextUpUrl(query);

        GetItemsFromUrl(url, response);
    }

    public void GetUpcomingEpisodesAsync(NextUpQuery query, final Response<ItemsResult> response)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();

        dict.AddIfNotNull("Fields", query.getFields());

        dict.AddIfNotNull("Limit", query.getLimit());

        dict.AddIfNotNull("StartIndex", query.getStartIndex());

        dict.Add("UserId", query.getUserId());

        String url = GetApiUrl("Shows/Upcoming", dict);

        GetItemsFromUrl(url, response);
    }

    /// <summary>
    /// Gets the similar movies async.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    /// <exception cref="System.IllegalArgumentException">query</exception>
    public void GetSimilarMoviesAsync(SimilarItemsQuery query, final Response<ItemsResult> response)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        String url = GetSimilarItemListUrl(query, "Movies");

        GetItemsFromUrl(url, response);
    }

    /// <summary>
    /// Gets the similar trailers async.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    /// <exception cref="System.IllegalArgumentException">query</exception>
    public void GetSimilarTrailersAsync(SimilarItemsQuery query, final Response<ItemsResult> response)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        String url = GetSimilarItemListUrl(query, "Trailers");

        GetItemsFromUrl(url, response);
    }

    /// <summary>
    /// Gets the similar series async.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    /// <exception cref="System.IllegalArgumentException">query</exception>
    public void GetSimilarSeriesAsync(SimilarItemsQuery query, final Response<ItemsResult> response)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        String url = GetSimilarItemListUrl(query, "Shows");

        GetItemsFromUrl(url, response);
    }

    public void GetEpisodesAsync(EpisodeQuery query, final Response<ItemsResult> response)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        QueryStringDictionary dict = new QueryStringDictionary();

        dict.AddIfNotNull("Season", query.getSeasonNumber());
        dict.AddIfNotNullOrEmpty("UserId", query.getUserId());

        dict.AddIfNotNullOrEmpty("SeasonId", query.getSeasonId());

        dict.AddIfNotNull("Fields", query.getFields());

        dict.AddIfNotNull("IsMissing", query.getIsMissing());
        dict.AddIfNotNull("IsVirtualUnaired", query.getIsVirtualUnaired());

        String url = GetApiUrl("Shows/" + query.getSeriesId() + "/Episodes", dict);

        GetItemsFromUrl(url, response);
    }

    public void GetSeasonsAsync(SeasonQuery query, final Response<ItemsResult> response)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();

        dict.AddIfNotNullOrEmpty("UserId", query.getUserId());
        dict.AddIfNotNull("Fields", query.getFields());

        dict.AddIfNotNull("IsMissing", query.getIsMissing());
        dict.AddIfNotNull("IsVirtualUnaired", query.getIsVirtualUnaired());
        dict.AddIfNotNull("IsSpecialSeason", query.getIsSpecialSeason());

        String url = GetApiUrl("Shows/" + query.getSeriesId() + "/Seasons", dict);

        GetItemsFromUrl(url, response);
    }

    /// <summary>
    /// Gets the similar games async.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    /// <exception cref="System.IllegalArgumentException">query</exception>
    public void GetSimilarGamesAsync(SimilarItemsQuery query, final Response<ItemsResult> response)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        String url = GetSimilarItemListUrl(query, "Games");

        GetItemsFromUrl(url, response);
    }

    /// <summary>
    /// Gets the similar albums async.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    /// <exception cref="System.IllegalArgumentException">query</exception>
    public void GetSimilarAlbumsAsync(SimilarItemsQuery query, final Response<ItemsResult> response)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        String url = GetSimilarItemListUrl(query, "Albums");

        GetItemsFromUrl(url, response);
    }

    /// <summary>
    /// Gets the people async.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    /// <exception cref="System.IllegalArgumentException">userId</exception>
    public void GetPeopleAsync(PersonsQuery query, final Response<ItemsResult> response)
    {
        String url = GetItemByNameListUrl("Persons", query);

        if (query.getPersonTypes() != null && query.getPersonTypes().length > 0)
        {
            url += "&PersonTypes=" + tangible.DotNetToJavaStringHelper.join(",", query.getPersonTypes());
        }

        GetItemsFromUrl(url, response);
    }

    /// <summary>
    /// Gets the instant mix from album async.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    /// <exception cref="System.IllegalArgumentException">query</exception>
    public void GetInstantMixFromAlbumAsync(SimilarItemsQuery query, final Response<ItemsResult> response)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        String url = GetInstantMixUrl(query, "Albums");

        GetItemsFromUrl(url, response);
    }

    /// <summary>
    /// Gets the instant mix from artist async.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    /// <exception cref="System.IllegalArgumentException">query</exception>
    public void GetInstantMixFromArtistAsync(SimilarItemsByNameQuery query, final Response<ItemsResult> response)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        String url = GetInstantMixByNameUrl(query, "Artists");

        GetItemsFromUrl(url, response);
    }

    /// <summary>
    /// Gets the instant mix from music genre async.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    /// <exception cref="System.IllegalArgumentException">query</exception>
    public void GetInstantMixFromMusicGenreAsync(SimilarItemsByNameQuery query, final Response<ItemsResult> response)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        String url = GetInstantMixByNameUrl(query, "MusicGenres");

        GetItemsFromUrl(url, response);
    }

    /// <summary>
    /// Gets the instant mix from song async.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    /// <exception cref="System.IllegalArgumentException">query</exception>
    public void GetInstantMixFromSongAsync(SimilarItemsQuery query, final Response<ItemsResult> response)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        String url = GetInstantMixUrl(query, "Songs");

        GetItemsFromUrl(url, response);
    }

    /// <summary>
    /// Gets the game genres async.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    public void GetGameGenresAsync(ItemsByNameQuery query, final Response<ItemsResult> response)
    {
        String url = GetItemByNameListUrl("GameGenres", query);

        GetItemsFromUrl(url, response);
    }

    /// <summary>
    /// Gets the genres async.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    public void GetGenresAsync(ItemsByNameQuery query, final Response<ItemsResult> response)
    {
        String url = GetItemByNameListUrl("Genres", query);

        GetItemsFromUrl(url, response);
    }

    /// <summary>
    /// Gets the music genres async.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    public void GetMusicGenresAsync(ItemsByNameQuery query, final Response<ItemsResult> response)
    {
        String url = GetItemByNameListUrl("MusicGenres", query);

        GetItemsFromUrl(url, response);
    }

    /// <summary>
    /// Gets the studios async.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    public void GetStudiosAsync(ItemsByNameQuery query, final Response<ItemsResult> response)
    {
        String url = GetItemByNameListUrl("Studios", query);

        GetItemsFromUrl(url, response);
    }

    /// <summary>
    /// Gets the artists.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    /// <exception cref="System.IllegalArgumentException">userId</exception>
    public void GetArtistsAsync(ArtistsQuery query, final Response<ItemsResult> response)
    {
        String url = GetItemByNameListUrl("Artists", query);

        GetItemsFromUrl(url, response);
    }

    /// <summary>
    /// Gets the artists.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    /// <exception cref="System.IllegalArgumentException">userId</exception>
    public void GetAlbumArtistsAsync(ArtistsQuery query, final Response<ItemsResult> response)
    {
        String url = GetItemByNameListUrl("Artists/AlbumArtists", query);

        GetItemsFromUrl(url, response);
    }

    /// <summary>
    /// Gets a studio
    /// </summary>
    /// <param name="name">The name.</param>
    /// <param name="userId">The user id.</param>
    /// <returns>Task{BaseItemDto}.</returns>
    /// <exception cref="System.IllegalArgumentException">userId</exception>
    public void GetStudioAsync(String name, String userId, final Response<BaseItemDto> response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(name))
        {
            throw new IllegalArgumentException("name");
        }
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(userId))
        {
            throw new IllegalArgumentException("userId");
        }

        QueryStringDictionary dict = new QueryStringDictionary();

        dict.Add("userId", userId);

        String url = GetApiUrl("Studios/" + GetSlugName(name), dict);

        GetItemFromUrl(url, response);
    }

    /// <summary>
    /// Gets a genre
    /// </summary>
    /// <param name="name">The name.</param>
    /// <param name="userId">The user id.</param>
    /// <returns>Task{BaseItemDto}.</returns>
    /// <exception cref="System.IllegalArgumentException">userId</exception>
    public void GetGenreAsync(String name, String userId, final Response<BaseItemDto> response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(name))
        {
            throw new IllegalArgumentException("name");
        }
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(userId))
        {
            throw new IllegalArgumentException("userId");
        }

        QueryStringDictionary dict = new QueryStringDictionary();

        dict.Add("userId", userId);

        String url = GetApiUrl("Genres/" + GetSlugName(name), dict);

        GetItemFromUrl(url, response);
    }

    public void GetMusicGenreAsync(String name, String userId, final Response<BaseItemDto> response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(name))
        {
            throw new IllegalArgumentException("name");
        }
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(userId))
        {
            throw new IllegalArgumentException("userId");
        }

        QueryStringDictionary dict = new QueryStringDictionary();

        dict.Add("userId", userId);

        String url = GetApiUrl("MusicGenres/" + GetSlugName(name), dict);

        GetItemFromUrl(url, response);
    }

    public void GetGameGenreAsync(String name, String userId, final Response<BaseItemDto> response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(name))
        {
            throw new IllegalArgumentException("name");
        }
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(userId))
        {
            throw new IllegalArgumentException("userId");
        }

        QueryStringDictionary dict = new QueryStringDictionary();

        dict.Add("userId", userId);

        String url = GetApiUrl("GameGenres/" + GetSlugName(name), dict);

        GetItemFromUrl(url, response);
    }

    /// <summary>
    /// Gets the music genre async.
    /// </summary>
    /// <param name="name">The name.</param>
    /// <returns>Task{BaseItemDto}.</returns>
    /// <exception cref="System.IllegalArgumentException">name</exception>
    public void GetMusicGenreAsync(String name, final Response<BaseItemDto> response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(name))
        {
            throw new IllegalArgumentException("name");
        }

        String url = GetApiUrl("MusicGenres/" + GetSlugName(name));

        GetItemFromUrl(url, response);
    }

    /// <summary>
    /// Gets the artist async.
    /// </summary>
    /// <param name="name">The name.</param>
    /// <param name="userId">The user id.</param>
    /// <returns>Task{BaseItemDto}.</returns>
    /// <exception cref="System.IllegalArgumentException">name</exception>
    public void GetArtistAsync(String name, String userId, final Response<BaseItemDto> response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(name))
        {
            throw new IllegalArgumentException("name");
        }
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(userId))
        {
            throw new IllegalArgumentException("userId");
        }

        QueryStringDictionary dict = new QueryStringDictionary();

        dict.Add("userId", userId);

        String url = GetApiUrl("Artists/" + GetSlugName(name), dict);

        GetItemFromUrl(url, response);
    }

    /// <summary>
    /// Restarts the server async.
    /// </summary>
    /// <returns>Task.</returns>
    public void RestartServerAsync(final EmptyResponse response)
    {
        String url = GetApiUrl("System/Restart");

        PostAsync(url, response);
    }

    /// <summary>
    /// Gets the system status async.
    /// </summary>
    /// <returns>Task{SystemInfo}.</returns>
    public void GetSystemInfoAsync(final Response<SystemInfo> response)
    {
        String url = GetApiUrl("System/Info");

        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<SystemInfo>(response, jsonSerializer, SystemInfo.class));
    }

    /// <summary>
    /// get public system information as an asynchronous operation.
    /// </summary>
    /// <param name="cancellationToken">The cancellation token.</param>
    /// <returns>Task&lt;PublicSystemInfo&gt;.</returns>
    public void GetPublicSystemInfoAsync(final Response<PublicSystemInfo> response)
    {
        String url = GetApiUrl("System/Info/Public");

        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<PublicSystemInfo>(response, jsonSerializer, PublicSystemInfo.class));
    }

    /// <summary>
    /// Gets a person
    /// </summary>
    /// <param name="name">The name.</param>
    /// <returns>Task{BaseItemDto}.</returns>
    /// <exception cref="System.IllegalArgumentException">userId</exception>
    public void GetPersonAsync(String name, String userId, final Response<BaseItemDto> response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(name))
        {
            throw new IllegalArgumentException("name");
        }
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(userId))
        {
            throw new IllegalArgumentException("userId");
        }

        QueryStringDictionary dict = new QueryStringDictionary();

        dict.Add("userId", userId);

        String url = GetApiUrl("Persons/" + GetSlugName(name), dict);

        GetItemFromUrl(url, response);
    }

    /// <summary>
    /// Gets a list of plugins installed on the server
    /// </summary>
    /// <returns>Task{PluginInfo[]}.</returns>
    public void GetInstalledPluginsAsync(final Response<PluginInfo[]> response)
    {
        String url = GetApiUrl("Plugins");

        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                PluginInfo[] obj = DeserializeFromString(jsonResponse, PluginInfo[].class);
                response.onResponse(obj);
            }
        };

        Send(url, "GET", jsonResponse);
    }

    /// <summary>
    /// Gets the current server configuration
    /// </summary>
    /// <returns>Task{ServerConfiguration}.</returns>
    public void GetServerConfigurationAsync(final Response<ServerConfiguration> response)
    {
        String url = GetApiUrl("System/Configuration");

        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<ServerConfiguration>(response, jsonSerializer, ServerConfiguration.class));
    }

    /// <summary>
    /// Gets the scheduled tasks.
    /// </summary>
    /// <returns>Task{TaskInfo[]}.</returns>
    public void GetScheduledTasksAsync(final Response<TaskInfo[]> response)
    {
        String url = GetApiUrl("ScheduledTasks");

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                TaskInfo[] obj = DeserializeFromString(jsonResponse, TaskInfo[].class);
                response.onResponse(obj);
            }
        };

        Send(url, "GET", jsonResponse);
    }

    /// <summary>
    /// Gets the scheduled task async.
    /// </summary>
    /// <param name="id">The id.</param>
    /// <returns>Task{TaskInfo}.</returns>
    /// <exception cref="System.IllegalArgumentException">id</exception>
    public void GetScheduledTaskAsync(String id, final Response<TaskInfo> response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(id))
        {
            throw new IllegalArgumentException("id");
        }

        String url = GetApiUrl("ScheduledTasks/" + id);

        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<TaskInfo>(response, jsonSerializer, TaskInfo.class));
    }

    /// <summary>
    /// Gets a user by id
    /// </summary>
    /// <param name="id">The id.</param>
    /// <returns>Task{UserDto}.</returns>
    /// <exception cref="System.IllegalArgumentException">id</exception>
    public void GetUserAsync(String id, final Response<UserDto> response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(id))
        {
            throw new IllegalArgumentException("id");
        }

        String url = GetApiUrl("Users/" + id);

        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<UserDto>(response, jsonSerializer, UserDto.class));
    }

    /// <summary>
    /// Gets the parental ratings async.
    /// </summary>
    /// <returns>Task{List{ParentalRating}}.</returns>
    public void GetParentalRatingsAsync(final Response<ParentalRating[]> response)
    {
        String url = GetApiUrl("Localization/ParentalRatings");

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                ParentalRating[] obj = DeserializeFromString(jsonResponse, ParentalRating[].class);
                response.onResponse(obj);
            }
        };

        Send(url, "GET", jsonResponse);
    }

    /// <summary>
    /// Gets local trailers for an item
    /// </summary>
    /// <param name="userId">The user id.</param>
    /// <param name="itemId">The item id.</param>
    /// <returns>Task{ItemsResult}.</returns>
    /// <exception cref="System.IllegalArgumentException">query</exception>
    public void GetLocalTrailersAsync(String userId, String itemId, final Response<BaseItemDto[]> response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(userId))
        {
            throw new IllegalArgumentException("userId");
        }
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(itemId))
        {
            throw new IllegalArgumentException("itemId");
        }

        String url = GetApiUrl("Users/" + userId + "/Items/" + itemId + "/LocalTrailers");

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                BaseItemDto[] obj = DeserializeFromString(jsonResponse, BaseItemDto[].class);
                response.onResponse(obj);
            }
        };

        Send(url, "GET", jsonResponse);
    }

    /// <summary>
    /// Gets special features for an item
    /// </summary>
    /// <param name="userId">The user id.</param>
    /// <param name="itemId">The item id.</param>
    /// <returns>Task{BaseItemDto[]}.</returns>
    /// <exception cref="System.IllegalArgumentException">userId</exception>
    public void GetSpecialFeaturesAsync(String userId, String itemId, final Response<BaseItemDto[]> response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(userId))
        {
            throw new IllegalArgumentException("userId");
        }
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(itemId))
        {
            throw new IllegalArgumentException("itemId");
        }

        String url = GetApiUrl("Users/" + userId + "/Items/" + itemId + "/SpecialFeatures");

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                BaseItemDto[] obj = DeserializeFromString(jsonResponse, BaseItemDto[].class);
                response.onResponse(obj);
            }
        };

        Send(url, "GET", jsonResponse);
    }

    /// <summary>
    /// Gets the cultures async.
    /// </summary>
    /// <returns>Task{CultureDto[]}.</returns>
    public void GetCulturesAsync(final Response<CultureDto[]> response)
    {
        String url = GetApiUrl("Localization/Cultures");

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                CultureDto[] obj = DeserializeFromString(jsonResponse, CultureDto[].class);
                response.onResponse(obj);
            }
        };

        Send(url, "GET", jsonResponse);
    }

    /// <summary>
    /// Gets the countries async.
    /// </summary>
    /// <returns>Task{CountryInfo[]}.</returns>
    public void GetCountriesAsync(final Response<CountryInfo[]> response)
    {
        String url = GetApiUrl("Localization/Countries");

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                CountryInfo[] obj = DeserializeFromString(jsonResponse, CountryInfo[].class);
                response.onResponse(obj);
            }
        };

        Send(url, "GET", jsonResponse);
    }

    /// <summary>
    /// Gets the game system summaries async.
    /// </summary>
    /// <returns>Task{List{GameSystemSummary}}.</returns>
    public void GetGameSystemSummariesAsync(final Response<GameSystemSummary[]> response)
    {
        String url = GetApiUrl("Games/SystemSummaries");

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                GameSystemSummary[] obj = DeserializeFromString(jsonResponse, GameSystemSummary[].class);
                response.onResponse(obj);
            }
        };

        Send(url, "GET", jsonResponse);
    }
 
    public void MarkPlayedAsync(String itemId, String userId, Date datePlayed, final Response<UserItemDataDto> response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(itemId)) {
            throw new IllegalArgumentException("itemId");
        }
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(userId)) {
            throw new IllegalArgumentException("userId");
        }

        QueryStringDictionary dict = new QueryStringDictionary();

        if (datePlayed != null)
        {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            dict.Add("DatePlayed", formatter.format(datePlayed));
        }

        String url = GetApiUrl("Users/" + userId + "/PlayedItems/" + itemId, dict);
        url = AddDataFormat(url);

        Response<String> jsonResponse = new Response<String>(response){
            @Override
            public void onResponse(String jsonResponse) {
                UserItemDataDto obj = DeserializeFromString(jsonResponse, UserItemDataDto.class);
                response.onResponse(obj);
            }
        };

        Send(url, "POST", dict, true, jsonResponse);
    }

    /// <summary>
    /// Marks the unplayed async.
    /// </summary>
    /// <param name="itemId">The item id.</param>
    /// <param name="userId">The user id.</param>
    /// <returns>Task{UserItemDataDto}.</returns>
    /// <exception cref="System.IllegalArgumentException">
    /// itemId
    /// or
    /// userId
    /// </exception>
    public void MarkUnplayedAsync(String itemId, String userId, final Response<UserItemDataDto> response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(itemId)) {
            throw new IllegalArgumentException("itemId");
        }
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(userId)) {
            throw new IllegalArgumentException("userId");
        }

        String url = GetApiUrl("Users/" + userId + "/PlayedItems/" + itemId);
        url = AddDataFormat(url);

        Response<String> jsonResponse = new Response<String>(response){
            @Override
            public void onResponse(String jsonResponse) {
                UserItemDataDto obj = DeserializeFromString(jsonResponse, UserItemDataDto.class);
                response.onResponse(obj);
            }
        };

        Send(url, "DELETE", jsonResponse);
    }

    /// <summary>
    /// Updates the favorite status async.
    /// </summary>
    /// <param name="itemId">The item id.</param>
    /// <param name="userId">The user id.</param>
    /// <param name="isFavorite">if set to <c>true</c> [is favorite].</param>
    /// <returns>Task.</returns>
    /// <exception cref="System.IllegalArgumentException">itemId</exception>
    public void UpdateFavoriteStatusAsync(String itemId, String userId, Boolean isFavorite, final Response<UserItemDataDto> response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(itemId)) {
            throw new IllegalArgumentException("itemId");
        }
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(userId)) {
            throw new IllegalArgumentException("userId");
        }

        String url = GetApiUrl("Users/" + userId + "/FavoriteItems/" + itemId);
        url = AddDataFormat(url);

        Response<String> jsonResponse = new Response<String>(response){
            @Override
            public void onResponse(String jsonResponse) {
                UserItemDataDto obj = DeserializeFromString(jsonResponse, UserItemDataDto.class);
                response.onResponse(obj);
            }
        };

        Send(url, isFavorite ? "POST" : "DELETE", jsonResponse);
    }

    /// <summary>
    /// Reports to the server that the user has begun playing an item
    /// </summary>
    /// <param name="info">The information.</param>
    /// <returns>Task{UserItemDataDto}.</returns>
    /// <exception cref="System.IllegalArgumentException">itemId</exception>
    public void ReportPlaybackStartAsync(PlaybackStartInfo info, final EmptyResponse response)
    {
        if (info == null)
        {
            throw new IllegalArgumentException("info");
        }

        Logger.Debug("ReportPlaybackStart: Item {0}", info.getItem());
 
        /*if (WebSocketConnection != null && WebSocketConnection.IsConnected)
        {
            return WebSocketConnection.SendAsync("ReportPlaybackStart", JsonSerializer.SerializeToString(info));
        }*/

        String url = GetApiUrl("Sessions/Playing");

        PostAsync(url, info, response);
    }

    /// <summary>
    /// Reports playback progress to the server
    /// </summary>
    /// <param name="info">The information.</param>
    /// <returns>Task{UserItemDataDto}.</returns>
    /// <exception cref="System.IllegalArgumentException">itemId</exception>
    public void ReportPlaybackProgressAsync(PlaybackProgressInfo info, final EmptyResponse response)
    {
        if (info == null)
        {
            throw new IllegalArgumentException("info");
        }
 
        /*if (WebSocketConnection != null && WebSocketConnection.IsConnected)
        {
            return WebSocketConnection.SendAsync("ReportPlaybackProgress", JsonSerializer.SerializeToString(info));
        }*/

        String url = GetApiUrl("Sessions/Playing/Progress");

        PostAsync(url, info, response);
    }

    /// <summary>
    /// Reports to the server that the user has stopped playing an item
    /// </summary>
    /// <param name="info">The information.</param>
    /// <returns>Task{UserItemDataDto}.</returns>
    /// <exception cref="System.IllegalArgumentException">itemId</exception>
    public void ReportPlaybackStoppedAsync(PlaybackStopInfo info, final EmptyResponse response)
    {
        if (info == null)
        {
            throw new IllegalArgumentException("info");
        }
 
        /*if (WebSocketConnection != null && WebSocketConnection.IsConnected)
        {
            return WebSocketConnection.SendAsync("ReportPlaybackStopped", JsonSerializer.SerializeToString(info));
        }*/

        String url = GetApiUrl("Sessions/Playing/Stopped");

        PostAsync(url, info, response);
    }

    /// <summary>
    /// Instructs another client to browse to a library item.
    /// </summary>
    /// <param name="sessionId">The session id.</param>
    /// <param name="itemId">The id of the item to browse to.</param>
    /// <param name="itemName">The name of the item to browse to.</param>
    /// <param name="itemType">The type of the item to browse to.</param>
    /// <returns>Task.</returns>
    /// <exception cref="System.IllegalArgumentException">sessionId
    /// or
    /// itemId
    /// or
    /// itemName
    /// or
    /// itemType</exception>
    public void SendBrowseCommandAsync(String sessionId, String itemId, String itemName, String itemType, final EmptyResponse response)
    {
        GeneralCommand cmd = new GeneralCommand();

        cmd.setName("DisplayContent");

        cmd.getArguments().put("ItemType", itemType);
        cmd.getArguments().put("ItemId", itemId);
        cmd.getArguments().put("ItemName", itemName);

        SendCommandAsync(sessionId, cmd, response);
    }

    /// <summary>
    /// Sends the play command async.
    /// </summary>
    /// <param name="sessionId">The session id.</param>
    /// <param name="request">The request.</param>
    /// <returns>Task.</returns>
    /// <exception cref="System.IllegalArgumentException">sessionId
    /// or
    /// request</exception>
    public void SendPlayCommandAsync(String sessionId, PlayRequest request, final EmptyResponse response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(sessionId))
        {
            throw new IllegalArgumentException("sessionId");
        }
        if (request == null)
        {
            throw new IllegalArgumentException("request");
        }

        QueryStringDictionary dict = new QueryStringDictionary();
        dict.Add("ItemIds", request.getItemIds());
        dict.AddIfNotNull("StartPositionTicks", request.getStartPositionTicks());
        dict.Add("PlayCommand", request.getPlayCommand());

        String url = GetApiUrl("Sessions/" + sessionId + "/Playing", dict);

        PostAsync(url, response);
    }

    public void SendMessageCommandAsync(String sessionId, MessageCommand command, final EmptyResponse response)
    {
        GeneralCommand cmd = new GeneralCommand();

        cmd.setName("DisplayMessage");

        cmd.getArguments().put("Header", command.getHeader());
        cmd.getArguments().put("Text", command.getText());

        if (command.getTimeoutMs() != null)
        {
            cmd.getArguments().put("Timeout", StringHelper.ToStringCultureInvariant(command.getTimeoutMs()));
        }

        SendCommandAsync(sessionId, cmd, response);
    }

    /// <summary>
    /// Sends the system command async.
    /// </summary>
    /// <param name="sessionId">The session id.</param>
    /// <param name="command">The command.</param>
    /// <returns>Task.</returns>
    /// <exception cref="System.IllegalArgumentException">sessionId</exception>
    public void SendCommandAsync(String sessionId, GeneralCommand command, final EmptyResponse response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(sessionId))
        {
            throw new IllegalArgumentException("sessionId");
        }

        String url = GetApiUrl("Sessions/" + sessionId + "/Command");

        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                response.onResponse();
            }
        };

        Send(url, "GET", jsonResponse);
    }

    private void DeleteAsync(String url, final EmptyResponse response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(url))
        {
            throw new IllegalArgumentException("url");
        }

        Response<String> stringResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                response.onResponse();
            }
        };

        Send(url, "DELETE", stringResponse);
    }

    private void PostAsync(String url, final EmptyResponse response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(url))
        {
            throw new IllegalArgumentException("url");
        }

        PostAsync(url, new QueryStringDictionary(), response);
    }

    private void PostAsync(String url,
                          QueryStringDictionary postBody,
                          boolean fireGlobalEvents,
                          final Response<String> response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(url))
        {
            throw new IllegalArgumentException("url");
        }

        Send(url, "POST", postBody, fireGlobalEvents, response);
    }

    private void PostAsync(String url, Object obj, final EmptyResponse response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(url))
        {
            throw new IllegalArgumentException("url");
        }

        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                response.onResponse();
            }
        };

        String json = getJsonSerializer().SerializeToString(obj);

        Send(url, "POST", json, "application/json", jsonResponse);
    }

    /// <summary>
    /// Sends the playstate command async.
    /// </summary>
    /// <param name="sessionId">The session id.</param>
    /// <param name="request">The request.</param>
    /// <returns>Task.</returns>
    public void SendPlaystateCommandAsync(String sessionId, PlaystateRequest request, final EmptyResponse response)
    {
        QueryStringDictionary dict = new QueryStringDictionary();
        dict.AddIfNotNull("SeekPositionTicks", request.getSeekPositionTicks());

        String url = GetApiUrl("Sessions/" + sessionId + "/Playing/" + request.getCommand(), dict);

        PostAsync(url, response);
    }
 
    /// <summary>
    /// Clears a user's rating for an item
    /// </summary>
    /// <param name="itemId">The item id.</param>
    /// <param name="userId">The user id.</param>
    /// <returns>Task{UserItemDataDto}.</returns>
    /// <exception cref="System.IllegalArgumentException">itemId</exception>
    public void ClearUserItemRatingAsync(String itemId, String userId, final Response<UserItemDataDto> response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(itemId))
        {
            throw new IllegalArgumentException("itemId");
        }
 
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(userId))
        {
            throw new IllegalArgumentException("userId");
        }
 
        String url = GetApiUrl("Users/" + userId + "/Items/" + itemId + "/Rating");
        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                UserItemDataDto obj = DeserializeFromString(jsonResponse, UserItemDataDto.class);
                response.onResponse(obj);
            }
        };

        Send(url, "DELETE", jsonResponse);
    }
 
    /// <summary>
    /// Updates a user's rating for an item, based on likes or dislikes
    /// </summary>
    /// <param name="itemId">The item id.</param>
    /// <param name="userId">The user id.</param>
    /// <param name="likes">if set to <c>true</c> [likes].</param>
    /// <returns>Task.</returns>
    /// <exception cref="System.IllegalArgumentException">itemId</exception>
    public void UpdateUserItemRatingAsync(String itemId, String userId, Boolean likes, final Response<UserItemDataDto> response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(itemId))
        {
            throw new IllegalArgumentException("itemId");
        }
 
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(userId))
        {
            throw new IllegalArgumentException("userId");
        }
 
        QueryStringDictionary dict = new QueryStringDictionary ();
 
        dict.Add("likes", likes);
 
        String url = GetApiUrl("Users/" + userId + "/Items/" + itemId + "/Rating", dict);
        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                UserItemDataDto obj = DeserializeFromString(jsonResponse, UserItemDataDto.class);
                response.onResponse(obj);
            }
        };

        Send(url, "POST", jsonResponse);
    }

    /// <summary>
    /// Authenticates a user and returns the result
    /// </summary>
    /// <param name="username">The username.</param>
    /// <param name="sha1Hash">The sha1 hash.</param>
    /// <returns>Task.</returns>
    /// <exception cref="System.IllegalArgumentException">userId</exception>
    public void AuthenticateUserAsync(String username, String password, final Response<AuthenticationResult> response)
            throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(username))
        {
            throw new IllegalArgumentException("username");
        }

        String url = GetApiUrl("Users/AuthenticateByName");

        QueryStringDictionary dict = new QueryStringDictionary ();

        dict.Add("username", username);
        dict.Add("password", Sha1.getHash(password));
        dict.Add("passwordMd5", Md5.getHash(ConnectPassword.PerformPreHashFilter(password)));

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                AuthenticationResult obj = DeserializeFromString(jsonResponse, AuthenticationResult.class);

                SetAuthenticationInfo(obj.getAccessToken(), obj.getUser().getId());

                getAuthenticatedObservable().notifyObservers(obj);

                response.onResponse(obj);
            }
        };

        PostAsync(url, dict, false, jsonResponse);
    }

    /// <summary>
    /// Updates the server configuration async.
    /// </summary>
    /// <param name="configuration">The configuration.</param>
    /// <returns>Task.</returns>
    /// <exception cref="System.IllegalArgumentException">configuration</exception>
    public void UpdateServerConfigurationAsync(ServerConfiguration configuration, final EmptyResponse response)
    {
        if (configuration == null)
        {
            throw new IllegalArgumentException("configuration");
        }

        String url = GetApiUrl("System/Configuration");

        PostAsync(url, configuration, response);
    }

    /// <summary>
    /// Updates the scheduled task triggers.
    /// </summary>
    /// <param name="id">The id.</param>
    /// <param name="triggers">The triggers.</param>
    /// <returns>Task{RequestResult}.</returns>
    /// <exception cref="System.IllegalArgumentException">id</exception>
    public void UpdateScheduledTaskTriggersAsync(String id, TaskTriggerInfo[] triggers, final EmptyResponse response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(id))
        {
            throw new IllegalArgumentException("id");
        }

        if (triggers == null)
        {
            throw new IllegalArgumentException("triggers");
        }

        String url = GetApiUrl("ScheduledTasks/" + id + "/Triggers");

        PostAsync(url, triggers, response);
    }

    /// <summary>
    /// Gets the display preferences.
    /// </summary>
    /// <param name="id">The id.</param>
    /// <param name="userId">The user id.</param>
    /// <param name="client">The client.</param>
    /// <returns>Task{BaseItemDto}.</returns>
    public void GetDisplayPreferencesAsync(String id, String userId, String client, final Response<DisplayPreferences> response)
    {
        QueryStringDictionary dict = new QueryStringDictionary();

        dict.Add("userId", userId);
        dict.Add("client", client);

        String url = GetApiUrl("DisplayPreferences/" + id, dict);

        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<DisplayPreferences>(response, jsonSerializer, DisplayPreferences.class));
    }

    /// <summary>
    /// Updates display preferences for a user
    /// </summary>
    /// <param name="displayPreferences">The display preferences.</param>
    /// <returns>Task{DisplayPreferences}.</returns>
    /// <exception cref="System.IllegalArgumentException">userId</exception>
    public void UpdateDisplayPreferencesAsync(DisplayPreferences displayPreferences, String userId, String client, final EmptyResponse response)
    {
        if (displayPreferences == null)
        {
            throw new IllegalArgumentException("displayPreferences");
        }

        QueryStringDictionary dict = new QueryStringDictionary();

        dict.Add("userId", userId);
        dict.Add("client", client);

        String url = GetApiUrl("DisplayPreferences/" + displayPreferences.getId(), dict);

        PostAsync(url, displayPreferences, response);
    }

    public void GetNotificationsSummary(String userId, final Response<NotificationsSummary> response)
    {
        String url = GetApiUrl("Notifications/" + userId + "/Summary");

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                NotificationsSummary obj = DeserializeFromString(jsonResponse, NotificationsSummary.class);
                response.onResponse(obj);
            }
        };

        Send(url, "GET", jsonResponse);
    }

    public void MarkNotificationsRead(String userId, String[] notificationIdList, Boolean isRead, final EmptyResponse response)
    {
        String url = "Notifications/" + userId;

        url += isRead ? "/Read" : "/Unread";

        QueryStringDictionary dict = new QueryStringDictionary();

        dict.Add("Ids", notificationIdList);

        url = GetApiUrl(url, dict);

        PostAsync(url, response);
    }

    public void GetNotificationsAsync(NotificationQuery query, final Response<NotificationResult> response)
    {
        String url = "Notifications/" + query.getUserId();

        QueryStringDictionary dict = new QueryStringDictionary();
        dict.AddIfNotNull("ItemIds", query.getIsRead());
        dict.AddIfNotNull("StartIndex", query.getStartIndex());
        dict.AddIfNotNull("Limit", query.getLimit());

        url = GetApiUrl(url, dict);

        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<NotificationResult>(response, jsonSerializer, NotificationResult.class));
    }

    public void GetAllThemeMediaAsync(String userId, String itemId, Boolean inheritFromParent, final Response<AllThemeMediaResult> response)
    {
        QueryStringDictionary queryString = new QueryStringDictionary();

        queryString.Add("InheritFromParent", inheritFromParent);
        queryString.AddIfNotNullOrEmpty("UserId", userId);

        String url = GetApiUrl("Items/" + itemId + "/ThemeMedia", queryString);

        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<AllThemeMediaResult>(response, jsonSerializer, AllThemeMediaResult.class));
    }

    public void GetSearchHintsAsync(SearchQuery query, final Response<SearchHintResult> response)
    {
        if (query == null || tangible.DotNetToJavaStringHelper.isNullOrEmpty(query.getSearchTerm()))
        {
            throw new IllegalArgumentException("query");
        }

        QueryStringDictionary queryString = new QueryStringDictionary();

        queryString.AddIfNotNullOrEmpty("SearchTerm", query.getSearchTerm());
        queryString.AddIfNotNullOrEmpty("UserId", query.getUserId());
        queryString.AddIfNotNull("StartIndex", query.getStartIndex());
        queryString.AddIfNotNull("Limit", query.getLimit());

        queryString.Add("IncludeArtists", query.getIncludeArtists());
        queryString.Add("IncludeGenres", query.getIncludeGenres());
        queryString.Add("IncludeMedia", query.getIncludeMedia());
        queryString.Add("IncludePeople", query.getIncludePeople());
        queryString.Add("IncludeStudios", query.getIncludeStudios());

        String url = GetApiUrl("Search/Hints", queryString);

        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<SearchHintResult>(response, jsonSerializer, SearchHintResult.class));
    }

    public void GetThemeSongsAsync(String userId, String itemId, Boolean inheritFromParent, final Response<ThemeMediaResult> response)
    {
        QueryStringDictionary queryString = new QueryStringDictionary();

        queryString.Add("InheritFromParent", inheritFromParent);
        queryString.AddIfNotNullOrEmpty("UserId", userId);

        String url = GetApiUrl("Items/" + itemId + "/ThemeSongs", queryString);

        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<ThemeMediaResult>(response, jsonSerializer, ThemeMediaResult.class));
    }

    public void GetThemeVideosAsync(String userId, String itemId, Boolean inheritFromParent, final Response<ThemeMediaResult> response)
    {
        QueryStringDictionary queryString = new QueryStringDictionary();

        queryString.Add("InheritFromParent", inheritFromParent);
        queryString.AddIfNotNullOrEmpty("UserId", userId);

        String url = GetApiUrl("Items/" + itemId + "/ThemeVideos", queryString);

        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<ThemeMediaResult>(response, jsonSerializer, ThemeMediaResult.class));
    }

    /// <summary>
    /// Gets the critic reviews.
    /// </summary>
    /// <param name="itemId">The item id.</param>
    /// <param name="startIndex">The start index.</param>
    /// <param name="limit">The limit.</param>
    /// <returns>Task{ItemReviewsResult}.</returns>
    /// <exception cref="System.IllegalArgumentException">
    /// id
    /// or
    /// userId
    /// </exception>
    public void GetCriticReviews(String itemId, Integer startIndex, Integer limit, final Response<ItemReviewsResult> response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(itemId))
        {
            throw new IllegalArgumentException("itemId");
        }

        QueryStringDictionary queryString = new QueryStringDictionary();

        queryString.AddIfNotNull("startIndex", startIndex);
        queryString.AddIfNotNull("limit", limit);

        String url = GetApiUrl("Items/" + itemId + "/CriticReviews", queryString);

        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<ItemReviewsResult>(response, jsonSerializer, ItemReviewsResult.class));
    }

    /// <summary>
    /// Gets the index of the game player.
    /// </summary>
    /// <param name="userId">The user id.</param>
    /// <param name="cancellationToken">The cancellation token.</param>
    /// <returns>Task{List{ItemIndex}}.</returns>
    public void GetGamePlayerIndex(String userId, final Response<ItemIndex[]> response)
    {
        QueryStringDictionary queryString = new QueryStringDictionary();

        queryString.AddIfNotNullOrEmpty("UserId", userId);

        String url = GetApiUrl("Games/PlayerIndex", queryString);

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                ItemIndex[] obj = DeserializeFromString(jsonResponse, ItemIndex[].class);
                response.onResponse(obj);
            }
        };

        Send(url, "GET", jsonResponse);
    }

    /// <summary>
    /// Gets the index of the year.
    /// </summary>
    /// <param name="userId">The user id.</param>
    /// <param name="includeItemTypes">The include item types.</param>
    /// <param name="cancellationToken">The cancellation token.</param>
    /// <returns>Task{List{ItemIndex}}.</returns>
    public void GetYearIndex(String userId, String[] includeItemTypes, final Response<ItemIndex[]> response)
    {
        QueryStringDictionary queryString = new QueryStringDictionary();

        queryString.AddIfNotNullOrEmpty("UserId", userId);
        queryString.AddIfNotNull("IncludeItemTypes", includeItemTypes);

        String url = GetApiUrl("Items/YearIndex", queryString);

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                ItemIndex[] obj = DeserializeFromString(jsonResponse, ItemIndex[].class);
                response.onResponse(obj);
            }
        };

        Send(url, "GET", jsonResponse);
    }

    public void ReportCapabilities(ClientCapabilities capabilities, final EmptyResponse response)
    {
        if (capabilities == null)
        {
            throw new IllegalArgumentException("capabilities");
        }

        QueryStringDictionary dict = new QueryStringDictionary();

        dict.AddIfNotNull("PlayableMediaTypes", capabilities.getPlayableMediaTypes());
        dict.AddIfNotNull("SupportedCommands", capabilities.getSupportedCommands());

        String url = GetApiUrl("Sessions/Capabilities", dict);

        PostAsync(url, response);
    }

    public void GetLiveTvInfoAsync(final Response<LiveTvInfo> response)
    {
        String url = GetApiUrl("LiveTv/Info");

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                LiveTvInfo obj = DeserializeFromString(jsonResponse, LiveTvInfo.class);
                response.onResponse(obj);
            }
        };

        Send(url, "GET", jsonResponse);
    }

    public void GetLiveTvRecordingGroupsAsync(RecordingGroupQuery query, final Response<RecordingGroupDtoResult> response)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();

        dict.AddIfNotNullOrEmpty("UserId", query.getUserId());

        String url = GetApiUrl("LiveTv/Recordings/Groups", dict);

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                RecordingGroupDtoResult obj = DeserializeFromString(jsonResponse, new RecordingGroupDtoResult().getClass());
                response.onResponse(obj);
            }
        };

        Send(url, "GET", jsonResponse);
    }

    public void GetLiveTvRecordingsAsync(RecordingQuery query, final Response<RecordingInfoDtoResult> response)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();

        dict.AddIfNotNullOrEmpty("UserId", query.getUserId());
        dict.AddIfNotNullOrEmpty("ChannelId", query.getChannelId());
        dict.AddIfNotNullOrEmpty("GroupId", query.getGroupId());
        dict.AddIfNotNullOrEmpty("Id", query.getUserId());
        dict.AddIfNotNullOrEmpty("SeriesTimerId", query.getSeriesTimerId());
        dict.AddIfNotNull("IsInProgress", query.getIsInProgress());
        dict.AddIfNotNull("StartIndex", query.getStartIndex());
        dict.AddIfNotNull("Limit", query.getLimit());

        String url = GetApiUrl("LiveTv/Recordings", dict);

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                RecordingInfoDtoResult obj = DeserializeFromString(jsonResponse, new RecordingInfoDtoResult().getClass());
                response.onResponse(obj);
            }
        };

        Send(url, "GET", jsonResponse);
    }

    public void GetLiveTvChannelsAsync(LiveTvChannelQuery query, final Response<ChannelInfoDtoResult> response)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();

        dict.AddIfNotNullOrEmpty("UserId", query.getUserId());
        dict.AddIfNotNull("StartIndex", query.getStartIndex());
        dict.AddIfNotNull("Limit", query.getLimit());

        if (query.getChannelType() != null)
        {
            dict.Add("ChannelType", query.getChannelType());
        }

        String url = GetApiUrl("LiveTv/Channels", dict);

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                ChannelInfoDtoResult obj = DeserializeFromString(jsonResponse, new ChannelInfoDtoResult().getClass());
                response.onResponse(obj);
            }
        };

        Send(url, "GET", jsonResponse);
    }

    public void CancelLiveTvSeriesTimerAsync(String id, final EmptyResponse response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(id))
        {
            throw new IllegalArgumentException("id");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();

        String url = GetApiUrl("LiveTv/SeriesTimers/" + id, dict);

        DeleteAsync(url, response);
    }

    public void CancelLiveTvTimerAsync(String id, final EmptyResponse response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(id))
        {
            throw new IllegalArgumentException("id");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();

        String url = GetApiUrl("LiveTv/Timers/" + id, dict);

        DeleteAsync(url, response);
    }

    public void DeleteLiveTvRecordingAsync(String id, final EmptyResponse response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(id))
        {
            throw new IllegalArgumentException("id");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();

        String url = GetApiUrl("LiveTv/Recordings/" + id, dict);

        DeleteAsync(url, response);
    }

    public void GetLiveTvChannelAsync(String id, String userId, final Response<ChannelInfoDto> response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(id))
        {
            throw new IllegalArgumentException("id");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();
        dict.AddIfNotNullOrEmpty("userId", userId);

        String url = GetApiUrl("LiveTv/Channels/" + id, dict);

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                ChannelInfoDto obj = DeserializeFromString(jsonResponse, ChannelInfoDto.class);
                response.onResponse(obj);
            }
        };

        Send(url, "GET", jsonResponse);
    }

    public void GetLiveTvRecordingAsync(String id, String userId, final Response<RecordingInfoDto> response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(id))
        {
            throw new IllegalArgumentException("id");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();
        dict.AddIfNotNullOrEmpty("userId", userId);

        String url = GetApiUrl("LiveTv/Recordings/" + id, dict);

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                RecordingInfoDto obj = DeserializeFromString(jsonResponse, RecordingInfoDto.class);
                response.onResponse(obj);
            }
        };

        Send(url, "GET", jsonResponse);
    }

    public void GetLiveTvRecordingGroupAsync(String id, String userId, final Response<RecordingGroupDto> response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(id))
        {
            throw new IllegalArgumentException("id");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();
        dict.AddIfNotNullOrEmpty("userId", userId);

        String url = GetApiUrl("LiveTv/Recordings/Groups/" + id, dict);

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                RecordingGroupDto obj = DeserializeFromString(jsonResponse, RecordingGroupDto.class);
                response.onResponse(obj);
            }
        };

        Send(url, "GET", jsonResponse);
    }

    public void GetLiveTvSeriesTimerAsync(String id, final Response<SeriesTimerInfoDto> response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(id))
        {
            throw new IllegalArgumentException("id");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();

        String url = GetApiUrl("LiveTv/SeriesTimers/" + id, dict);

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                SeriesTimerInfoDto obj = DeserializeFromString(jsonResponse, SeriesTimerInfoDto.class);
                response.onResponse(obj);
            }
        };

        Send(url, "GET", jsonResponse);
    }

    public void GetLiveTvSeriesTimersAsync(SeriesTimerQuery query, final Response<SeriesTimerInfoDtoResult> response)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();

        dict.AddIfNotNullOrEmpty("SortBy", query.getSortBy());
        dict.Add("SortOrder", query.getSortOrder());

        String url = GetApiUrl("LiveTv/SeriesTimers", dict);

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                SeriesTimerInfoDtoResult obj = DeserializeFromString(jsonResponse, new SeriesTimerInfoDtoResult().getClass());
                response.onResponse(obj);
            }
        };

        Send(url, "GET", jsonResponse);
    }

    public void GetLiveTvTimerAsync(String id, final Response<TimerInfoDto> response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(id))
        {
            throw new IllegalArgumentException("id");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();

        String url = GetApiUrl("LiveTv/Timers/" + id, dict);

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                TimerInfoDto obj = DeserializeFromString(jsonResponse, TimerInfoDto.class);
                response.onResponse(obj);
            }
        };

        Send(url, "GET", jsonResponse);
    }

    public void GetLiveTvTimersAsync(TimerQuery query, final Response<TimerInfoDtoResult> response)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();

        dict.AddIfNotNullOrEmpty("ChannelId", query.getChannelId());
        dict.AddIfNotNullOrEmpty("SeriesTimerId", query.getSeriesTimerId());

        String url = GetApiUrl("LiveTv/Timers", dict);

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                TimerInfoDtoResult obj = DeserializeFromString(jsonResponse, new TimerInfoDtoResult().getClass());
                response.onResponse(obj);
            }
        };

        Send(url, "GET", jsonResponse);
    }

    public void GetLiveTvProgramsAsync(ProgramQuery query, final Response<ProgramInfoDtoResult> response)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();

        String isoDateFormat = "o";

        if (query.getMaxEndDate() != null)
        {
            dict.Add("MaxEndDate", getIsoString(query.getMaxEndDate()));
        }
        if (query.getMaxStartDate() != null)
        {
            dict.Add("MaxStartDate", getIsoString(query.getMaxStartDate()));
        }
        if (query.getMinEndDate() != null)
        {
            dict.Add("MinEndDate", getIsoString(query.getMinEndDate()));
        }
        if (query.getMinStartDate() != null)
        {
            dict.Add("MinStartDate", getIsoString(query.getMinStartDate()));
        }

        dict.AddIfNotNullOrEmpty("UserId", query.getUserId());

        if (query.getChannelIdList() != null)
        {
            dict.Add("ChannelIds", tangible.DotNetToJavaStringHelper.join(",", query.getChannelIdList()));
        }

        // TODO: This endpoint supports POST if the query String is too long
        String url = GetApiUrl("LiveTv/Programs", dict);

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                ProgramInfoDtoResult obj = DeserializeFromString(jsonResponse, new ProgramInfoDtoResult().getClass());
                response.onResponse(obj);
            }
        };

        Send(url, "GET", jsonResponse);
    }

    public void GetRecommendedLiveTvProgramsAsync(RecommendedProgramQuery query, final Response<ProgramInfoDtoResult> response)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();

        dict.AddIfNotNullOrEmpty("UserId", query.getUserId());
        dict.AddIfNotNull("Limit", query.getLimit());
        dict.AddIfNotNull("HasAired", query.getHasAired());
        dict.AddIfNotNull("IsAiring", query.getIsAiring());

        String url = GetApiUrl("LiveTv/Programs/Recommended", dict);

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                ProgramInfoDtoResult obj = DeserializeFromString(jsonResponse, new ProgramInfoDtoResult().getClass());
                response.onResponse(obj);
            }
        };

        Send(url, "GET", jsonResponse);
    }

    public void CreateLiveTvSeriesTimerAsync(SeriesTimerInfoDto timer, final EmptyResponse response)
    {
        if (timer == null)
        {
            throw new IllegalArgumentException("timer");
        }

        String url = GetApiUrl("LiveTv/SeriesTimers");

        PostAsync(url, timer, response);
    }

    public void CreateLiveTvTimerAsync(BaseTimerInfoDto timer, final EmptyResponse response)
    {
        if (timer == null)
        {
            throw new IllegalArgumentException("timer");
        }

        String url = GetApiUrl("LiveTv/Timers");

        PostAsync(url, timer, response);
    }

    public void GetDefaultLiveTvTimerInfo(String programId, final Response<SeriesTimerInfoDto> response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(programId))
        {
            throw new IllegalArgumentException("programId");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();

        dict.AddIfNotNullOrEmpty("programId", programId);

        String url = GetApiUrl("LiveTv/Timers/Defaults", dict);

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                SeriesTimerInfoDto obj = DeserializeFromString(jsonResponse, SeriesTimerInfoDto.class);
                response.onResponse(obj);
            }
        };

        Send(url, "GET", jsonResponse);
    }

    public void GetDefaultLiveTvTimerInfo(final Response<SeriesTimerInfoDto> response)
    {
        String url = GetApiUrl("LiveTv/Timers/Defaults");

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                SeriesTimerInfoDto obj = DeserializeFromString(jsonResponse, SeriesTimerInfoDto.class);
                response.onResponse(obj);
            }
        };

        Send(url, "GET", jsonResponse);
    }

    public void GetLiveTvGuideInfo(final Response<GuideInfo> response)
    {
        String url = GetApiUrl("LiveTv/GuideInfo");

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                GuideInfo obj = DeserializeFromString(jsonResponse, GuideInfo.class);
                response.onResponse(obj);
            }
        };

        Send(url, "GET", jsonResponse);
    }

    public void GetLiveTvProgramAsync(String id, String userId, final Response<ProgramInfoDto> response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(id))
        {
            throw new IllegalArgumentException("id");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();
        dict.AddIfNotNullOrEmpty("userId", userId);

        String url = GetApiUrl("LiveTv/Programs/" + id, dict);

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                ProgramInfoDto obj = DeserializeFromString(jsonResponse, ProgramInfoDto.class);
                response.onResponse(obj);
            }
        };

        Send(url, "GET", jsonResponse);
    }

    public void UpdateLiveTvSeriesTimerAsync(SeriesTimerInfoDto timer, final EmptyResponse response)
    {
        if (timer == null)
        {
            throw new IllegalArgumentException("timer");
        }

        String url = GetApiUrl("LiveTv/SeriesTimers/" + timer.getId());

        PostAsync(url, timer, response);
    }

    public void UpdateLiveTvTimerAsync(TimerInfoDto timer, final EmptyResponse response)
    {
        if (timer == null)
        {
            throw new IllegalArgumentException("timer");
        }

        String url = GetApiUrl("LiveTv/Timers/" + timer.getId());

        PostAsync(url, timer, response);
    }

    public void SendString(String sessionId, String text, final EmptyResponse response)
    {
        GeneralCommand cmd = new GeneralCommand();

        cmd.setName("SendString");

        cmd.getArguments().put("String", text);

        SendCommandAsync(sessionId, cmd, response);
    }

    public void SetAudioStreamIndex(String sessionId, int index, final EmptyResponse response)
    {
        GeneralCommand cmd = new GeneralCommand();

        cmd.setName("SetAudioStreamIndex");

        cmd.getArguments().put("Index", StringHelper.ToStringCultureInvariant(index));

        SendCommandAsync(sessionId, cmd, response);
    }

    public void SetSubtitleStreamIndex(String sessionId, Integer index, final EmptyResponse response)
    {
        GeneralCommand cmd = new GeneralCommand();

        cmd.setName("SetSubtitleStreamIndex");

        int indexValue = index == null ? -1 : index.intValue();

        cmd.getArguments().put("Index", StringHelper.ToStringCultureInvariant(indexValue));

        SendCommandAsync(sessionId, cmd, response);
    }

    public void SetVolume(String sessionId, int volume, final EmptyResponse response)
    {
        GeneralCommand cmd = new GeneralCommand();

        cmd.setName("SetVolume");

        cmd.getArguments().put("Volume", StringHelper.ToStringCultureInvariant(volume));

        SendCommandAsync(sessionId, cmd, response);
    }

    public void GetAdditionalParts(String itemId, String userId, final Response<ItemsResult> response)
    {
        QueryStringDictionary queryString = new QueryStringDictionary();

        queryString.AddIfNotNullOrEmpty("UserId", userId);

        String url = GetApiUrl("Videos/" + itemId + "/AdditionalParts", queryString);

        GetItemsFromUrl(url, response);
    }

    public void GetChannelFeatures(String channelId, final Response<ChannelFeatures> response)
    {
        String url = GetApiUrl("Channels/" + channelId + "/Features");

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                ChannelFeatures obj = DeserializeFromString(jsonResponse, ChannelFeatures.class);
                response.onResponse(obj);
            }
        };

        Send(url, "GET", jsonResponse);
    }

    public void GetChannelItems(ChannelItemQuery query, final Response<ItemsResult> response)
    {
        QueryStringDictionary queryString = new QueryStringDictionary();

        queryString.AddIfNotNullOrEmpty("UserId", query.getUserId());
        queryString.AddIfNotNull("StartIndex", query.getStartIndex());
        queryString.AddIfNotNull("Limit", query.getLimit());
        queryString.AddIfNotNullOrEmpty("FolderId", query.getFolderId());

        queryString.AddIfNotNull("Fields", query.getFields());
        queryString.AddIfNotNull("Limit", query.getLimit());

        queryString.AddIfNotNull("Filters", query.getFilters());
        queryString.AddIfNotNull("SortBy", query.getSortBy());
        queryString.Add("SortOrder", query.getSortOrder());

        String url = GetApiUrl("Channels/" + query.getChannelId() + "/Items", queryString);

        GetItemsFromUrl(url, response);
    }

    public void GetChannels(ChannelQuery query, final Response<ItemsResult> response)
    {
        QueryStringDictionary queryString = new QueryStringDictionary();

        queryString.AddIfNotNullOrEmpty("UserId", query.getUserId());
        queryString.AddIfNotNull("SupportsLatestItems", query.getSupportsLatestItems());
        queryString.AddIfNotNull("StartIndex", query.getStartIndex());
        queryString.AddIfNotNull("Limit", query.getLimit());
        queryString.AddIfNotNull("IsFavorite", query.getIsFavorite());

        String url = GetApiUrl("Channels", queryString);

        GetItemsFromUrl(url, response);
    }

    public void GetCurrentSessionAsync(final Response<SessionInfoDto> response)
    {
        QueryStringDictionary queryString = new QueryStringDictionary();

        queryString.Add("DeviceId", getDeviceId());
        String url = GetApiUrl("Sessions", queryString);

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                SessionInfoDto obj = DeserializeFromString(jsonResponse, SessionInfoDto.class);
                response.onResponse(obj);
            }
        };

        Send(url, "GET", jsonResponse);
    }

    public void StopTranscodingProcesses(String deviceId, final EmptyResponse response)
    {
        QueryStringDictionary queryString = new QueryStringDictionary();

        queryString.Add("DeviceId", getDeviceId());
        String url = GetApiUrl("Videos/ActiveEncodings", queryString);

        DeleteAsync(url, response);
    }

    public void GetLatestChannelItems(AllChannelMediaQuery query, final Response<ItemsResult> response)
    {
        throw new UnsupportedOperationException();
    }

    public void Logout(final EmptyResponse response)
    {
        String url = GetApiUrl("Sessions/Logout");

        PostAsync(url, response);

        ClearAuthenticationInfo();
    }

    public void GetUserViews(String userId, final Response<ItemsResult> response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(userId))
        {
            throw new IllegalArgumentException("userId");
        }

        String url = GetApiUrl("Users/" + userId + "/Views");

        GetItemsFromUrl(url, response);
    }

    public void GetLatestItems(LatestItemsQuery query, final Response<BaseItemDto[]> response)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        QueryStringDictionary queryString = new QueryStringDictionary();
        queryString.AddIfNotNull("GroupItems", query.getGroupItems());
        queryString.AddIfNotNull("IncludeItemTypes", query.getIncludeItemTypes());
        queryString.AddIfNotNullOrEmpty("ParentId", query.getParentId());
        queryString.AddIfNotNull("IsPlayed", query.getIsPlayed());
        queryString.AddIfNotNull("StartIndex", query.getStartIndex());
        queryString.AddIfNotNull("Limit", query.getLimit());

        String url = GetApiUrl("Users/" + query.getUserId() + "/Items/Latest", queryString);

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                BaseItemDto[] obj = DeserializeFromString(jsonResponse, BaseItemDto[].class);
                response.onResponse(obj);
            }
        };

        Send(url, "GET", jsonResponse);
    }

    public void AddToPlaylist(String playlistId, String[] itemIds, String userId, final EmptyResponse response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(playlistId))
        {
            throw new IllegalArgumentException("playlistId");
        }

        QueryStringDictionary dict = new QueryStringDictionary();

        dict.AddIfNotNull("Ids", itemIds);
        String url = GetApiUrl("Playlists/"+playlistId+"/Items", dict);

        PostAsync(url, response);
    }

    public void CreatePlaylist(PlaylistCreationRequest request, final Response<PlaylistCreationResult> response)
    {
        QueryStringDictionary queryString = new QueryStringDictionary();

        queryString.Add("UserId", request.getUserId());
        queryString.Add("Name", request.getName());

        queryString.AddIfNotNullOrEmpty("MediaType", request.getMediaType());

        queryString.AddIfNotNull("Ids", request.getItemIdList());

        String url = GetApiUrl("Playlists/", queryString);
        Response<String> jsonResponse = new Response<String>(response){

            @Override
            public void onResponse(String jsonResponse) {

                PlaylistCreationResult obj = DeserializeFromString(jsonResponse, PlaylistCreationResult.class);
                response.onResponse(obj);
            }
        };

        Send(url, "POST", jsonResponse);
    }

    public void GetPlaylistItems(PlaylistItemQuery query, final Response<ItemsResult> response)
    {
        QueryStringDictionary queryString = new QueryStringDictionary();

        queryString.AddIfNotNull("StartIndex", query.getStartIndex());

        queryString.AddIfNotNull("Limit", query.getLimit());
        queryString.Add("UserId", query.getUserId());

        queryString.AddIfNotNull("fields", query.getFields());

        String url = GetApiUrl("Playlists/" + query.getId() + "/Items", queryString);

        GetItemsFromUrl(url, response);
    }

    public void RemoveFromPlaylist(String playlistId, String[] entryIds, final EmptyResponse response)
    {
        QueryStringDictionary dict = new QueryStringDictionary();

        dict.AddIfNotNull("EntryIds", entryIds);
        String url = GetApiUrl("Playlists/"+playlistId+"/Items", dict);

        DeleteAsync(url, response);
    }

    public void GetDevicesOptions(final Response<DevicesOptions> response)
    {
        String url = GetApiUrl("System/Configuration/devices");

        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<DevicesOptions>(response, jsonSerializer, DevicesOptions.class));
    }

    public void GetContentUploadHistory(final Response<ContentUploadHistory> response)
    {
        QueryStringDictionary dict = new QueryStringDictionary();

        dict.Add("DeviceId", getDeviceId());

        String url = GetApiUrl("Devices/CameraUploads", dict);

        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<ContentUploadHistory>(response, jsonSerializer, ContentUploadHistory.class));
    }

    public void UploadFile(FileInputStream fileInputStream,
                           LocalFileInfo file,
                           IProgress<Double> progress,
                           CancellationToken cancellationToken) throws IOException {

        QueryStringDictionary dict = new QueryStringDictionary();

        dict.Add("DeviceId", getDeviceId());
        dict.Add("Name", file.getName());
        dict.Add("Id", file.getId());
        dict.AddIfNotNullOrEmpty("Album", file.getAlbum());

        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        URL url = new URL(GetApiUrl("Devices/CameraUploads", dict));

        int maxBufferSize = 1 * 1024 * 1024;

        try {

            // Open a HTTP  connection to  the URL
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true); // Allow Inputs
            conn.setDoOutput(true); // Allow Outputs
            conn.setUseCaches(false); // Don't use a Cached Copy
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", file.getMimeType());

            for (String key: this.HttpHeaders.keySet()){
                conn.setRequestProperty(key, this.HttpHeaders.get(key));
            }

            String parameter = this.HttpHeaders.getAuthorizationParameter();
            if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(parameter))
            {
                String value = this.HttpHeaders.getAuthorizationScheme() + " " + parameter;
                conn.setRequestProperty("Authorization", value);
            }

            dos = new DataOutputStream(conn.getOutputStream());

            // create a buffer of  maximum size
            int bytesAvailable = fileInputStream.available();

            int bufferSize = Math.min(bytesAvailable, maxBufferSize);
            byte[] buffer = new byte[bufferSize];

            // read file and write it into form...
            long bytesRead = fileInputStream.read(buffer, 0, bufferSize);

            while (bytesRead > 0) {

                dos.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }

            String serverResponseMessage = conn.getResponseMessage();

            if(conn.getResponseCode() == 200){

                progress.reportComplete();
            }
            else{
                progress.reportError(null);
            }

        }
        catch (Exception ex) {

            progress.reportError(ex);
        }
        finally {

            //close the streams //
            fileInputStream.close();

            if (dos != null){
                dos.flush();
                dos.close();
            }
        }
    }
}