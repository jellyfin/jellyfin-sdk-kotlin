package org.jellyfin.apiclient.interaction;

import org.jellyfin.apiclient.interaction.device.IDevice;
import org.jellyfin.apiclient.interaction.http.HttpRequest;
import org.jellyfin.apiclient.interaction.http.IAsyncHttpClient;
import org.jellyfin.apiclient.interaction.tasks.CancellationToken;
import org.jellyfin.apiclient.interaction.tasks.IProgress;
import org.jellyfin.apiclient.interaction.websocket.ApiWebSocket;
import org.jellyfin.apiclient.logging.ILogger;
import org.jellyfin.apiclient.model.apiclient.RemoteLogoutReason;
import org.jellyfin.apiclient.model.apiclient.ServerInfo;
import org.jellyfin.apiclient.model.channels.AllChannelMediaQuery;
import org.jellyfin.apiclient.model.channels.ChannelFeatures;
import org.jellyfin.apiclient.model.channels.ChannelItemQuery;
import org.jellyfin.apiclient.model.channels.ChannelQuery;
import org.jellyfin.apiclient.model.configuration.ServerConfiguration;
import org.jellyfin.apiclient.model.configuration.UserConfiguration;
import org.jellyfin.apiclient.model.devices.ContentUploadHistory;
import org.jellyfin.apiclient.model.devices.LocalFileInfo;
import org.jellyfin.apiclient.model.dto.BaseItemDto;
import org.jellyfin.apiclient.model.dto.ItemCounts;
import org.jellyfin.apiclient.model.dto.ItemIndex;
import org.jellyfin.apiclient.model.dto.UserDto;
import org.jellyfin.apiclient.model.dto.UserItemDataDto;
import org.jellyfin.apiclient.model.entities.DisplayPreferences;
import org.jellyfin.apiclient.model.entities.ParentalRating;
import org.jellyfin.apiclient.model.livetv.BaseTimerInfoDto;
import org.jellyfin.apiclient.model.livetv.ChannelInfoDto;
import org.jellyfin.apiclient.model.livetv.GuideInfo;
import org.jellyfin.apiclient.model.livetv.LiveTvChannelQuery;
import org.jellyfin.apiclient.model.livetv.LiveTvInfo;
import org.jellyfin.apiclient.model.livetv.ProgramQuery;
import org.jellyfin.apiclient.model.livetv.RecommendedProgramQuery;
import org.jellyfin.apiclient.model.livetv.RecordingGroupQuery;
import org.jellyfin.apiclient.model.livetv.RecordingQuery;
import org.jellyfin.apiclient.model.livetv.SeriesTimerInfoDto;
import org.jellyfin.apiclient.model.livetv.SeriesTimerQuery;
import org.jellyfin.apiclient.model.livetv.TimerInfoDto;
import org.jellyfin.apiclient.model.livetv.TimerQuery;
import org.jellyfin.apiclient.model.mediainfo.LiveStreamRequest;
import org.jellyfin.apiclient.model.mediainfo.LiveStreamResponse;
import org.jellyfin.apiclient.model.mediainfo.PlaybackInfoRequest;
import org.jellyfin.apiclient.model.mediainfo.PlaybackInfoResponse;
import org.jellyfin.apiclient.model.mediainfo.SubtitleTrackInfo;
import org.jellyfin.apiclient.model.net.EndPointInfo;
import org.jellyfin.apiclient.model.net.HttpException;
import org.jellyfin.apiclient.model.playlists.PlaylistCreationRequest;
import org.jellyfin.apiclient.model.playlists.PlaylistCreationResult;
import org.jellyfin.apiclient.model.playlists.PlaylistItemQuery;
import org.jellyfin.apiclient.model.querying.AllThemeMediaResult;
import org.jellyfin.apiclient.model.querying.ArtistsQuery;
import org.jellyfin.apiclient.model.querying.EpisodeQuery;
import org.jellyfin.apiclient.model.querying.ItemCountsQuery;
import org.jellyfin.apiclient.model.querying.ItemFields;
import org.jellyfin.apiclient.model.querying.ItemQuery;
import org.jellyfin.apiclient.model.querying.ItemsByNameQuery;
import org.jellyfin.apiclient.model.querying.ItemsResult;
import org.jellyfin.apiclient.model.querying.LatestItemsQuery;
import org.jellyfin.apiclient.model.querying.NextUpQuery;
import org.jellyfin.apiclient.model.querying.PersonsQuery;
import org.jellyfin.apiclient.model.querying.QueryFilters;
import org.jellyfin.apiclient.model.querying.SeasonQuery;
import org.jellyfin.apiclient.model.querying.SessionQuery;
import org.jellyfin.apiclient.model.querying.SimilarItemsQuery;
import org.jellyfin.apiclient.model.querying.ThemeMediaResult;
import org.jellyfin.apiclient.model.querying.UpcomingEpisodesQuery;
import org.jellyfin.apiclient.model.querying.UserQuery;
import org.jellyfin.apiclient.model.results.ChannelInfoDtoResult;
import org.jellyfin.apiclient.model.results.ItemReviewsResult;
import org.jellyfin.apiclient.model.results.ReadySyncItemsResult;
import org.jellyfin.apiclient.model.results.SeriesTimerInfoDtoResult;
import org.jellyfin.apiclient.model.results.TimerInfoDtoResult;
import org.jellyfin.apiclient.model.search.SearchHintResult;
import org.jellyfin.apiclient.model.search.SearchQuery;
import org.jellyfin.apiclient.model.session.ClientCapabilities;
import org.jellyfin.apiclient.model.session.GeneralCommand;
import org.jellyfin.apiclient.model.session.MessageCommand;
import org.jellyfin.apiclient.model.session.PlayRequest;
import org.jellyfin.apiclient.model.session.PlaybackProgressInfo;
import org.jellyfin.apiclient.model.session.PlaybackStartInfo;
import org.jellyfin.apiclient.model.session.PlaybackStopInfo;
import org.jellyfin.apiclient.model.session.PlaystateRequest;
import org.jellyfin.apiclient.model.session.SessionInfoDto;
import org.jellyfin.apiclient.model.sync.SyncDataRequest;
import org.jellyfin.apiclient.model.sync.SyncDataResponse;
import org.jellyfin.apiclient.model.sync.SyncJob;
import org.jellyfin.apiclient.model.system.PublicSystemInfo;
import org.jellyfin.apiclient.model.system.SystemInfo;
import org.jellyfin.apiclient.model.users.AuthenticationResult;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;

public class ApiClient extends BaseApiClient {
 
    protected IAsyncHttpClient httpClient;
    private ApiEventListener apiEventListener;
    private ApiWebSocket apiWebSocket;

    private ServerInfo serverInfo;
    public ServerInfo getServerInfo() {
        return serverInfo;
    }

    private Observable authenticatedObservable = new AutomaticObservable();
    public Observable getAuthenticatedObservable() {
        return authenticatedObservable;
    }

    public ApiClient(IAsyncHttpClient httpClient, ILogger logger, String serverAddress, String accessToken, ApiEventListener apiEventListener)
    {
        super(logger, serverAddress, accessToken);

        this.httpClient = httpClient;
        this.apiEventListener = apiEventListener;

        ResetHttpHeaders();
    }

    public ApiClient(IAsyncHttpClient httpClient, ILogger logger, String serverAddress, String appName, String applicationVersion, IDevice device, ApiEventListener apiEventListener)
    {
        super(logger, serverAddress, appName, device, applicationVersion);

        this.httpClient = httpClient;
        this.apiEventListener = apiEventListener;

        ResetHttpHeaders();
    }

    public ApiClient(IAsyncHttpClient httpClient, ILogger logger, String serverAddress, String accessToken, String appName, String applicationVersion, IDevice device, ApiEventListener apiEventListener)
    {
        super(logger, serverAddress, accessToken, appName, device, applicationVersion);

        this.httpClient = httpClient;
        this.apiEventListener = apiEventListener;

        ResetHttpHeaders();
    }

    public void EnableAutomaticNetworking(ServerInfo info)
    {
        this.serverInfo = info;

        String serverAddress = info.getAddress();
        setServerAddress(serverAddress);
    }

    public void ensureWebSocket() {
        if (apiWebSocket == null) {
            Logger.debug("Creating ApiWebSocket");
            apiWebSocket = new ApiWebSocket(getJsonSerializer(), Logger, apiEventListener, this);
        }

        apiWebSocket.EnsureWebSocket();
    }

    void OnRemoteLoggedOut(HttpException httpError) {
        RemoteLogoutReason reason = RemoteLogoutReason.GeneralAccesError;
        if (httpError.getHeaders() != null) {
            String errorCode = httpError.getHeaders().get("X-Application-Error-Code");
            if ("ParentalControl".equalsIgnoreCase(errorCode)) {
                reason = RemoteLogoutReason.ParentalControlRestriction;
            }
        }

        apiEventListener.onRemoteLoggedOut(this, reason);
    }

    private void SendRequest(HttpRequest request, final boolean fireGlobalEvents, final Response<String> response)
    {
        httpClient.Send(request, new ApiClientRequestListener(this, fireGlobalEvents, response));
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

    public void getResponseStream(String address, Response<ResponseStreamInfo> response) {

        getResponseStreamInternal(address, response);
    }

    protected void getResponseStreamInternal(String address, Response<ResponseStreamInfo> response) {

        Logger.debug("Getting response stream from %s", address);

        HttpURLConnection conn = null;

        try
        {
            URL url = new URL(address);

            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true); // Allow Inputs
            conn.setUseCaches(false); // Don't use a Cached Copy
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Connection", "Keep-Alive");

            for (String key: this.HttpHeaders.keySet()) {
                conn.setRequestProperty(key, this.HttpHeaders.get(key));
            }

            conn.connect();

            ResponseStreamInfo info = new ResponseStreamInfo();
            info.ContentLength = conn.getContentLength();

            InputStream inputStream = conn.getInputStream();
            info.Stream = inputStream;

            response.onResponse(info);

        } catch (Exception ex) {
            response.onError(ex);
        }
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

        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, ItemCounts.class));
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

        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, UserDto[].class));
    }

    public void GetPublicUsersAsync(final Response<UserDto[]> response)
    {
        String url = GetApiUrl("Users/Public");

        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, UserDto[].class));
    }

    public void GetClientSessionsAsync(SessionQuery query, final Response<SessionInfoDto[]> response)
    {
        QueryStringDictionary queryString = new QueryStringDictionary();

        queryString.AddIfNotNullOrEmpty("ControllableByUserId", query.getControllableByUserId());

        String url = GetApiUrl("Sessions", queryString);

        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, SessionInfoDto[].class));
    }

    private void GetItemsFromUrl(String url, final Response<ItemsResult> response) {

        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, ItemsResult.class));
    }

    private void GetItemFromUrl(String url, final Response<BaseItemDto> response) {

        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, BaseItemDto.class));
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

    public void GetUpcomingEpisodesAsync(UpcomingEpisodesQuery query, final Response<ItemsResult> response)
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

        dict.AddIfNotNull("EnableImages", query.getEnableImages());
        dict.AddIfNotNull("ImageTypeLimit", query.getImageTypeLimit());
        dict.AddIfNotNull("EnableImageTypes", query.getEnableImageTypes());

        String url = GetApiUrl("Shows/Upcoming", dict);

        GetItemsFromUrl(url, response);
    }

    /// <summary>
    /// Gets the similar movies async.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    /// <exception cref="System.IllegalArgumentException">query</exception>
    public void GetSimilarItems(SimilarItemsQuery query, final Response<ItemsResult> response)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        String url = GetSimilarItemListUrl(query, "Items");

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

        dict.AddIfNotNullOrEmpty("AdjacentTo", query.getAdjacentTo());

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

    public void closeLiveStream(String liveStreamId, EmptyResponse response) {

        // LiveStreams/Close
        // LiveStreamId
        QueryStringDictionary dict = new QueryStringDictionary ();
        dict.Add("LiveStreamId", liveStreamId);

        String url = GetApiUrl("LiveStreams/Close", dict);

        PostAsync(url, new QueryStringDictionary (), response);
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
    public void GetInstantMixFromItem(SimilarItemsQuery query, final Response<ItemsResult> response)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        String url = GetInstantMixUrl(query, "Items");

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

        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, SystemInfo.class));
    }

    public void GetEndPointInfo(final Response<EndPointInfo> response)
    {
        String url = GetApiUrl("System/Endpoint");

        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, EndPointInfo.class));
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

        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, PublicSystemInfo.class));
    }

    /// <summary>
    /// Gets the current server configuration
    /// </summary>
    /// <returns>Task{ServerConfiguration}.</returns>
    public void GetServerConfigurationAsync(final Response<ServerConfiguration> response)
    {
        String url = GetApiUrl("System/Configuration");

        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, ServerConfiguration.class));
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

        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, UserDto.class));
    }

    /// <summary>
    /// Gets the parental ratings async.
    /// </summary>
    /// <returns>Task{List{ParentalRating}}.</returns>
    public void GetParentalRatingsAsync(final Response<ParentalRating[]> response)
    {
        String url = GetApiUrl("Localization/ParentalRatings");

        url = AddDataFormat(url);
        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, ParentalRating[].class));
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
        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, BaseItemDto[].class));
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

        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, BaseItemDto[].class));
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

        Send(url, "POST", dict, true, new SerializedResponse<>(response, jsonSerializer, UserItemDataDto.class));
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

        Send(url, "DELETE", new SerializedResponse<>(response, jsonSerializer, UserItemDataDto.class));
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

        Send(url, isFavorite ? "POST" : "DELETE", new SerializedResponse<>(response, jsonSerializer, UserItemDataDto.class));
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

        if (apiWebSocket != null && apiWebSocket.IsWebSocketOpen()) {
            //apiWebSocket.SendWebSocketMessage("ReportPlaybackProgress", info, response);
            //return;
        }

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

        String item = info.getItem() != null ? info.getItem().getName() : info.getItemId();
        Logger.info("ReportPlaybackStopped: Item %s, Ticks: %d", item, info.getPositionTicks());

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
            cmd.getArguments().put("Timeout", Long.toString(command.getTimeoutMs()));
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

        PostAsync(url, command, response);
    }

    private void DeleteAsync(String url, final EmptyResponse response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(url))
        {
            throw new IllegalArgumentException("url");
        }

        Response<String> stringResponse = new Response<>(response);

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

        Response<String> jsonResponse = new Response<>(response);

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
	    url = AddDataFormat(url);
        Send(url, "DELETE", new SerializedResponse<>(response, jsonSerializer, UserItemDataDto.class));
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
	    url = AddDataFormat(url);
        Send(url, "POST", new SerializedResponse<>(response, jsonSerializer, UserItemDataDto.class));
    }

    /// <summary>
    /// Authenticates a user and returns the result
    /// </summary>
    /// <param name="username">The username.</param>
    /// <param name="sha1Hash">The sha1 hash.</param>
    /// <returns>Task.</returns>
    /// <exception cref="System.IllegalArgumentException">userId</exception>
    public void AuthenticateUserAsync(String username, String password, final Response<AuthenticationResult> response) {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(username))
        {
            throw new IllegalArgumentException("username");
        }

        String url = GetApiUrl("Users/AuthenticateByName");

        QueryStringDictionary dict = new QueryStringDictionary ();

        dict.Add("username", username);
        dict.Add("pw", password);

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(response) {

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

        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, DisplayPreferences.class));
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

    public void MarkNotificationsRead(String userId, String[] notificationIdList, Boolean isRead, final EmptyResponse response)
    {
        String url = "Notifications/" + userId;

        url += isRead ? "/Read" : "/Unread";

        QueryStringDictionary dict = new QueryStringDictionary();

        dict.Add("Ids", notificationIdList);

        url = GetApiUrl(url, dict);

        PostAsync(url, response);
    }

    public void GetAllThemeMediaAsync(String userId, String itemId, Boolean inheritFromParent, final Response<AllThemeMediaResult> response)
    {
        QueryStringDictionary queryString = new QueryStringDictionary();

        queryString.Add("InheritFromParent", inheritFromParent);
        queryString.AddIfNotNullOrEmpty("UserId", userId);

        String url = GetApiUrl("Items/" + itemId + "/ThemeMedia", queryString);

        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, AllThemeMediaResult.class));
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

        queryString.Add("ParentId", query.getParentId());
        queryString.Add("IncludeItemTypes", query.getIncludeItemTypes());

        String url = GetApiUrl("Search/Hints", queryString);

        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, SearchHintResult.class));
    }

    public void GetThemeSongsAsync(String userId, String itemId, Boolean inheritFromParent, final Response<ThemeMediaResult> response)
    {
        QueryStringDictionary queryString = new QueryStringDictionary();

        queryString.Add("InheritFromParent", inheritFromParent);
        queryString.AddIfNotNullOrEmpty("UserId", userId);

        String url = GetApiUrl("Items/" + itemId + "/ThemeSongs", queryString);

        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, ThemeMediaResult.class));
    }

    public void GetThemeVideosAsync(String userId, String itemId, Boolean inheritFromParent, final Response<ThemeMediaResult> response)
    {
        QueryStringDictionary queryString = new QueryStringDictionary();

        queryString.Add("InheritFromParent", inheritFromParent);
        queryString.AddIfNotNullOrEmpty("UserId", userId);

        String url = GetApiUrl("Items/" + itemId + "/ThemeVideos", queryString);

        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, ThemeMediaResult.class));
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

        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, ItemReviewsResult.class));
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
        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, ItemIndex[].class));
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
        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, ItemIndex[].class));
    }

    public void ReportCapabilities(ClientCapabilities capabilities, final EmptyResponse response)
    {
        if (capabilities == null)
        {
            throw new IllegalArgumentException("capabilities");
        }

        String url = GetApiUrl("Sessions/Capabilities/Full");

        PostAsync(url, capabilities, response);
    }

    public void GetLiveTvInfoAsync(final Response<LiveTvInfo> response)
    {
        String url = GetApiUrl("LiveTv/Info");

        url = AddDataFormat(url);
        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, LiveTvInfo.class));
    }

    public void GetLiveTvRecordingGroupsAsync(RecordingGroupQuery query, final Response<ItemsResult> response)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();

        dict.AddIfNotNullOrEmpty("UserId", query.getUserId());

        String url = GetApiUrl("LiveTv/Recordings/Groups", dict);

        url = AddDataFormat(url);
        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, ItemsResult.class));
    }

    public void GetLiveTvRecordingsAsync(RecordingQuery query, final Response<ItemsResult> response)
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
        dict.AddIfNotNull("IsSeries", query.getIsSeries());
        dict.AddIfNotNull("IsKids", query.getIsKids());
        dict.AddIfNotNull("IsMovie", query.getIsMovie());
        dict.AddIfNotNull("IsSports", query.getIsSports());

        dict.AddIfNotNull("EnableImages", query.getEnableImages());
        dict.AddIfNotNull("ImageTypeLimit", query.getImageTypeLimit());
        dict.AddIfNotNull("EnableImageTypes", query.getEnableImageTypes());
        dict.AddIfNotNull("Fields", query.getFields());

        if (!query.getEnableTotalRecordCount())
        {
            dict.Add("EnableTotalRecordCount", "false");
        }

        String url = GetApiUrl("LiveTv/Recordings", dict);

        url = AddDataFormat(url);
        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, ItemsResult.class));
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
        dict.AddIfNotNull("IsFavorite", query.getIsFavorite());
        dict.AddIfNotNull("IsLiked", query.getIsLiked());
        dict.AddIfNotNull("IsDisliked", query.getIsDisliked());
        dict.AddIfNotNull("EnableFavoriteSorting", query.getEnableFavoriteSorting());
        dict.AddIfNotNull("SortBy",query.getSortBy());
        dict.AddIfNotNull("SortOrder",query.getSortOrder());

        if (query.getChannelType() != null)
        {
            dict.Add("ChannelType", query.getChannelType());
        }

        String url = GetApiUrl("LiveTv/Channels", dict);

        url = AddDataFormat(url);
        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, ChannelInfoDtoResult.class));
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
        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, ChannelInfoDto.class));
    }

    public void GetLiveTvRecordingAsync(String id, String userId, final Response<BaseItemDto> response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(id))
        {
            throw new IllegalArgumentException("id");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();
        dict.AddIfNotNullOrEmpty("userId", userId);

        String url = GetApiUrl("LiveTv/Recordings/" + id, dict);

        url = AddDataFormat(url);
        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, BaseItemDto.class));
    }

    public void GetLiveTvRecordingGroupAsync(String id, String userId, final Response<BaseItemDto> response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(id))
        {
            throw new IllegalArgumentException("id");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();
        dict.AddIfNotNullOrEmpty("userId", userId);

        String url = GetApiUrl("LiveTv/Recordings/Groups/" + id, dict);

        url = AddDataFormat(url);
        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, BaseItemDto.class));
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
        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, SeriesTimerInfoDto.class));
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
        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, SeriesTimerInfoDtoResult.class));
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
        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, TimerInfoDto.class));
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
        dict.AddIfNotNull("IsScheduled", query.getIsScheduled());

        String url = GetApiUrl("LiveTv/Timers", dict);

        url = AddDataFormat(url);
        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, TimerInfoDtoResult.class));
    }

    public void GetLiveTvProgramsAsync(ProgramQuery query, final Response<ItemsResult> response)
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
        if (!query.getEnableTotalRecordCount())
        {
            dict.Add("EnableTotalRecordCount", "false");
        }

        dict.AddIfNotNull("EnableImages", query.getEnableImages());
        dict.AddIfNotNull("ImageTypeLimit", query.getImageTypeLimit());
        dict.AddIfNotNull("EnableImageTypes", query.getEnableImageTypes());
        dict.AddIfNotNull("Fields", query.getFields());
        dict.AddIfNotNull("SortBy", query.getSortBy());

        dict.AddIfNotNullOrEmpty("UserId", query.getUserId());

        if (query.getChannelIds() != null)
        {
            dict.Add("ChannelIds", tangible.DotNetToJavaStringHelper.join(",", query.getChannelIds()));
        }

        // TODO: This endpoint supports POST if the query String is too long
        String url = GetApiUrl("LiveTv/Programs", dict);

        url = AddDataFormat(url);
        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, ItemsResult.class));
    }

    public void GetRecommendedLiveTvProgramsAsync(RecommendedProgramQuery query, final Response<ItemsResult> response)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();

        if (!query.getEnableTotalRecordCount())
        {
            dict.Add("EnableTotalRecordCount", "false");
        }

        dict.AddIfNotNull("EnableImages", query.getEnableImages());
        dict.AddIfNotNull("ImageTypeLimit", query.getImageTypeLimit());
        dict.AddIfNotNull("EnableImageTypes", query.getEnableImageTypes());
        dict.AddIfNotNull("Fields", query.getFields());

        dict.AddIfNotNullOrEmpty("UserId", query.getUserId());
        dict.AddIfNotNull("Limit", query.getLimit());
        dict.AddIfNotNull("HasAired", query.getHasAired());
        dict.AddIfNotNull("IsAiring", query.getIsAiring());
        dict.AddIfNotNull("IsMovie", query.getIsMovie());
        dict.AddIfNotNull("IsSeries", query.getIsSeries());
        dict.AddIfNotNull("IsSports", query.getIsSports());
        dict.AddIfNotNull("IsKids", query.getIsKids());
        dict.AddIfNotNull("IsNews", query.getIsNews());

        if (!query.getEnableTotalRecordCount())
        {
            dict.Add("EnableTotalRecordCount", "false");
        }

        String url = GetApiUrl("LiveTv/Programs/Recommended", dict);

        url = AddDataFormat(url);
        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, ItemsResult.class));
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
        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, SeriesTimerInfoDto.class));
    }

    public void GetDefaultLiveTvTimerInfo(final Response<SeriesTimerInfoDto> response)
    {
        String url = GetApiUrl("LiveTv/Timers/Defaults");

        url = AddDataFormat(url);
        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, SeriesTimerInfoDto.class));
    }

    public void GetLiveTvGuideInfo(final Response<GuideInfo> response)
    {
        String url = GetApiUrl("LiveTv/GuideInfo");

        url = AddDataFormat(url);
        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, GuideInfo.class));
    }

    public void GetLiveTvProgramAsync(String id, String userId, final Response<BaseItemDto> response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(id))
        {
            throw new IllegalArgumentException("id");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();
        dict.AddIfNotNullOrEmpty("userId", userId);

        String url = GetApiUrl("LiveTv/Programs/" + id, dict);

        url = AddDataFormat(url);
        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, BaseItemDto.class));
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

        cmd.getArguments().put("Index", Integer.toString(index));

        SendCommandAsync(sessionId, cmd, response);
    }

    public void SetSubtitleStreamIndex(String sessionId, Integer index, final EmptyResponse response)
    {
        GeneralCommand cmd = new GeneralCommand();

        cmd.setName("SetSubtitleStreamIndex");

        int indexValue = index == null ? -1 : index.intValue();

        cmd.getArguments().put("Index", Integer.toString(indexValue));

        SendCommandAsync(sessionId, cmd, response);
    }

    public void SetVolume(String sessionId, int volume, final EmptyResponse response)
    {
        GeneralCommand cmd = new GeneralCommand();

        cmd.setName("SetVolume");

        cmd.getArguments().put("Volume", Integer.toString(volume));

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
        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, ChannelFeatures.class));
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

    public void GetCurrentSessionAsync(final Response<SessionInfoDto[]> response)
    {
        QueryStringDictionary queryString = new QueryStringDictionary();

        queryString.Add("DeviceId", getDeviceId());
        String url = GetApiUrl("Sessions", queryString);

        url = AddDataFormat(url);
        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, SessionInfoDto[].class));
    }

    public void StopTranscodingProcesses(String deviceId, String playSessionId, final EmptyResponse response)
    {
        QueryStringDictionary queryString = new QueryStringDictionary();

        queryString.Add("DeviceId", getDeviceId());
        queryString.AddIfNotNullOrEmpty("PlaySessionId", playSessionId);
        String url = GetApiUrl("Videos/ActiveEncodings", queryString);

        DeleteAsync(url, response);
    }

    public void GetLatestChannelItems(AllChannelMediaQuery query, final Response<ItemsResult> response)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(query.getUserId()))
        {
            throw new IllegalArgumentException("UserId");
        }

        QueryStringDictionary queryString = new QueryStringDictionary();
        queryString.Add("UserId", query.getUserId());
        queryString.AddIfNotNull("StartIndex", query.getStartIndex());
        queryString.AddIfNotNull("Limit", query.getLimit());
        if (query.getFilters() != null) {
            queryString.AddIfNotNull("Filters", query.getFilters());
        }
        if (query.getFields() != null) {
            queryString.AddIfNotNull("Fields", query.getFields() != null && query.getFields().size() > 0
                    ? query.getFields().toArray(new ItemFields[query.getFields().size()]) : null);
        }
        queryString.AddIfNotNull("ChannelIds", query.getChannelIds());

        String url = GetApiUrl("Channels/Items/Latest", queryString);

        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, ItemsResult.class));
    }

    public void UpdateItem(String id, BaseItemDto itemDto, final EmptyResponse response) {
        String url = GetApiUrl("Items/" + id);

        PostAsync(url, itemDto, response);
    }

    public void DeleteItem(String id, final EmptyResponse response)
    {
        String url = GetApiUrl("Items/" + id);

        DeleteAsync(url, response);
    }

    public void Logout(final EmptyResponse response)
    {
        String url = GetApiUrl("Sessions/Logout");

        PostAsync(url, new EmptyResponse() {

            @Override
            public void onResponse() {

                ClearAuthenticationInfo();
                response.onResponse();
            }

            @Override
            public void onError(Exception ex) {

                Logger.error("Error logging out", ex);
                ClearAuthenticationInfo();
                response.onResponse();
            }
        });

        if (apiWebSocket != null && apiWebSocket.IsWebSocketOpenOrConnecting()) {
            apiWebSocket.Close();
            apiWebSocket = null;
        }
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
        queryString.AddIfNotNull("Fields", query.getFields());

        String url = GetApiUrl("Users/" + query.getUserId() + "/Items/Latest", queryString);

        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, BaseItemDto[].class));
    }

    public void AddToPlaylist(String playlistId, String[] itemIds, String userId, final EmptyResponse response)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(playlistId))
        {
            throw new IllegalArgumentException("playlistId");
        }

        QueryStringDictionary dict = new QueryStringDictionary();

        dict.AddIfNotNull("Ids", itemIds);
        String url = GetApiUrl("Playlists/" + playlistId + "/Items", dict);

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
        url = AddDataFormat(url);

        Send(url, "POST", new SerializedResponse<>(response, jsonSerializer, PlaylistCreationResult.class));
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

    public void MoveItem(String playlist, String item, int position, final EmptyResponse response) {
        String url = GetApiUrl("Playlists/" + playlist + "/Items/" + item + "/Move/" + position);

        PostAsync(url, response);
    }

    public void RemoveFromPlaylist(String playlistId, String[] entryIds, final EmptyResponse response)
    {
        QueryStringDictionary dict = new QueryStringDictionary();

        dict.AddIfNotNull("EntryIds", entryIds);
        String url = GetApiUrl("Playlists/" + playlistId + "/Items", dict);

        DeleteAsync(url, response);
    }

    public void GetFilters(String userId,
                           String parentId,
                           String[] mediaTypes,
                           String[] itemTypes,
                           Response<QueryFilters> response) {

        QueryStringDictionary queryString = new QueryStringDictionary();
        queryString.AddIfNotNullOrEmpty("UserId", userId);
        queryString.AddIfNotNullOrEmpty("ParentId", parentId);
        queryString.AddIfNotNull("IncludeItemTypes", itemTypes);
        queryString.AddIfNotNull("MediaTypes", mediaTypes);

        String url = GetApiUrl("Items/Filters", queryString);

        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, QueryFilters.class));
    }

    public void GetPlaybackInfo(PlaybackInfoRequest request, final Response<PlaybackInfoResponse> response)
    {
        QueryStringDictionary dict = new QueryStringDictionary();

        dict.Add("UserId", request.getUserId());

        String url = GetApiUrl("Items/" + request.getId() + "/PlaybackInfo", dict);

        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, PlaybackInfoResponse.class));
    }

    public void GetPlaybackInfoWithPost(PlaybackInfoRequest request, final Response<PlaybackInfoResponse> response)
    {
        String url = GetApiUrl("Items/" + request.getId() + "/PlaybackInfo");

        url = AddDataFormat(url);

        Send(url, "POST", jsonSerializer.SerializeToString(request), "application/json", new SerializedResponse<>(response, jsonSerializer, PlaybackInfoResponse.class));
    }

    public void OpenLiveStream(LiveStreamRequest request, final Response<LiveStreamResponse> response)
    {
        String url = GetApiUrl("LiveStreams/Open");

        url = AddDataFormat(url);

        String json = getJsonSerializer().SerializeToString(request);
        Send(url, "POST", json, "application/json", new SerializedResponse<>(response, jsonSerializer, LiveStreamResponse.class));
    }

    public void GetContentUploadHistory(final Response<ContentUploadHistory> response)
    {
        QueryStringDictionary dict = new QueryStringDictionary();

        dict.Add("DeviceId", getDeviceId());

        String url = GetApiUrl("Devices/CameraUploads", dict);

        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, ContentUploadHistory.class));
    }

    public void UploadFile(FileInputStream fileInputStream,
                           LocalFileInfo file,
                           IProgress<Double> progress,
                           CancellationToken cancellationToken) throws IOException, IllegalArgumentException {

        UploadFileInternal(fileInputStream, file, progress, cancellationToken);
    }

    protected void UploadFileInternal(FileInputStream fileInputStream,
                           LocalFileInfo file,
                           IProgress<Double> progress,
                           CancellationToken cancellationToken) throws IOException ,IllegalArgumentException {

        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(getDeviceId()))
        {
            throw new IllegalArgumentException("ApiClient.deviceId cannot be null or empty");
        }

        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(file.getId()))
        {
            throw new IllegalArgumentException("file.getId() cannot be null or empty");
        }

        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(file.getName()))
        {
            throw new IllegalArgumentException("file.getName() cannot be null or empty");
        }

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

            for (String key: this.HttpHeaders.keySet()) {
                conn.setRequestProperty(key, this.HttpHeaders.get(key));
            }

            String parameter = this.HttpHeaders.getAuthorizationParameter();
            if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(parameter))
            {
                String value = this.HttpHeaders.getAuthorizationScheme() + " " + parameter;
                conn.setRequestProperty("Authorization", value);
            }

            dos = new DataOutputStream(conn.getOutputStream());

            // createUserAction a buffer of  maximum size
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

            int responseCode = conn.getResponseCode();
            if (responseCode == 200 || responseCode == 204) {
                progress.reportComplete();
            } else {
                HttpException ex = new HttpException(serverResponseMessage);
                ex.setStatusCode(responseCode);
                progress.reportError(ex);
            }
        } catch (Exception ex) {
            Logger.error("Error uploading file", ex);
            progress.reportError(new HttpException(ex.getMessage()));
        } finally {
            // close the streams
            fileInputStream.close();
            if (dos != null) {
                dos.flush();
                dos.close();
            }
        }
    }

    public void UpdateUserConfiguration(String userId, UserConfiguration configuration, EmptyResponse response) {
        response.onError(new UnsupportedOperationException());
    }

    public void CancelSyncJob(SyncJob job, EmptyResponse response) {

        if (job == null)
        {
            throw new IllegalArgumentException("job");
        }

        String url = GetApiUrl("Sync/Jobs/" + job.getId());

        DeleteAsync(url, response);
    }

    public void UpdateSyncJob(SyncJob job, EmptyResponse response) {

        if (job == null)
        {
            throw new IllegalArgumentException("job");
        }

        String url = GetApiUrl("Sync/Jobs/" + job.getId());

        PostAsync(url, job, response);
    }

    public void GetSyncJobItemFile(String id, Response<ResponseStreamInfo> response) {

        getResponseStream(getSyncJobItemFileUrl(id), response);
    }

    public String getSyncJobItemFileUrl(String id)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(id))
        {
            throw new IllegalArgumentException("id");
        }

        return GetApiUrl("Sync/JobItems/" + id + "/File");
    }

    public void reportSyncJobItemTransferred(String id, EmptyResponse response) {

        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(id))
        {
            throw new IllegalArgumentException("id");
        }

        String url = GetApiUrl("Sync/JobItems/" + id + "/Transferred");

        PostAsync(url, response);
    }

    public void SyncData(SyncDataRequest request, final Response<SyncDataResponse> response) {

        if (request == null)
        {
            throw new IllegalArgumentException("request");
        }

        String url = GetApiUrl("Sync/Data");
        url = AddDataFormat(url);

        String json = getJsonSerializer().SerializeToString(request);
        Send(url, "POST", json, "application/json", new SerializedResponse<>(response, jsonSerializer, SyncDataResponse.class));
    }

    public void getSyncJobItemAdditionalFile(String syncJobItemId, String filename, final Response<ResponseStreamInfo> response) {

        QueryStringDictionary dict = new QueryStringDictionary();

        dict.AddIfNotNullOrEmpty("Name", filename);

        String url = GetApiUrl("Sync/JobItems/" + syncJobItemId + "/AdditionalFiles", dict);

        getResponseStream(url, response);
    }

    public void getReadySyncItems(String targetId, final Response<ReadySyncItemsResult> response) {

        QueryStringDictionary dict = new QueryStringDictionary();

        dict.AddIfNotNullOrEmpty("TargetId", targetId);

        String url = GetApiUrl("Sync/Items/Ready", dict);
        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, url, Logger, ReadySyncItemsResult.class));
    }

    public void detectBitrate(final Response<Long> response) {

        // First try a small amount so that we don't hang up their mobile connection
        detectBitrate(1000000, new Response<Long>(response) {

            @Override
            public void onResponse(Long bitrate) {

                if (bitrate < 3000000) {
                    response.onResponse(Math.round(bitrate * .8));
                    return;
                }

                // If that produced a fairly high speed, try again with a larger size to get a more accurate result
                detectBitrate(3000000, new Response<Long>(response) {

                    @Override
                    public void onResponse(Long bitrate) {

                        response.onResponse(Math.round(bitrate * .8));
                    }

                });
            }
        });
    }

    protected void detectBitrate(final long downloadBytes, final Response<Long> response) {

        detectBitrateInternal(downloadBytes, response);
    }

    protected void detectBitrateInternal(final long downloadBytes, final Response<Long> response) {

        QueryStringDictionary dict = new QueryStringDictionary();

        dict.Add("Size", downloadBytes);

        String address = GetApiUrl("Playback/BitrateTest", dict);

        HttpURLConnection conn = null;

        try
        {
            URL url = new URL(address);

            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true); // Allow Inputs
            conn.setUseCaches(false); // Don't use a Cached Copy
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Connection", "Keep-Alive");

            for (String key: this.HttpHeaders.keySet()) {
                conn.setRequestProperty(key, this.HttpHeaders.get(key));
            }

            final long startTime = System.currentTimeMillis();

            try (InputStream inputStream = conn.getInputStream()) {

                byte[] byteChunk = new byte[4096]; // Or whatever size you want to read in at a time.
                int n;

                while ( (n = inputStream.read(byteChunk)) > 0 ) {

                }

                double time = System.currentTimeMillis() - startTime;
                double bitrate = downloadBytes * 8;
                bitrate /= time;
                bitrate *= 1000;

                response.onResponse(Math.round(bitrate));
            } catch (IOException ioException) {
                response.onError(ioException);
                return;
            }
        } catch (Exception ex) {
            response.onError(ex);
        }
    }

    public void getSubtitles(String url, Response<SubtitleTrackInfo> response) {

        url = AddDataFormat(url);

        Send(url, "GET", new SerializedResponse<>(response, jsonSerializer, url, Logger, SubtitleTrackInfo.class));
    }
}
