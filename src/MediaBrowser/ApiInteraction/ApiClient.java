package MediaBrowser.ApiInteraction;

import MediaBrowser.Model.Configuration.ServerConfiguration;
import MediaBrowser.Model.Dto.*;
import MediaBrowser.Model.Entities.DisplayPreferences;
import MediaBrowser.Model.Entities.ParentalRating;
import MediaBrowser.Model.Globalization.CountryInfo;
import MediaBrowser.Model.Globalization.CultureDto;
import MediaBrowser.Model.LiveTv.*;
import MediaBrowser.Model.Logging.ILogger;
import MediaBrowser.Model.Notifications.NotificationQuery;
import MediaBrowser.Model.Notifications.NotificationResult;
import MediaBrowser.Model.Plugins.PluginInfo;
import MediaBrowser.Model.Querying.*;
import MediaBrowser.Model.Search.SearchHintResult;
import MediaBrowser.Model.Search.SearchQuery;
import MediaBrowser.Model.Serialization.IJsonSerializer;
import MediaBrowser.Model.Session.SessionInfoDto;
import MediaBrowser.Model.System.PublicSystemInfo;
import MediaBrowser.Model.System.SystemInfo;
import MediaBrowser.Model.Tasks.TaskInfo;
import com.android.volley.toolbox.ImageLoader;

import java.util.List;

public class ApiClient extends BaseApiClient {

/*    public event EventHandler<HttpResponseEventArgs> HttpResponseReceived
    {
        add { HttpClient.HttpResponseReceived += value; }
        remove
        {
            HttpClient.HttpResponseReceived -= value;
        }
    }

    /// <summary>
    /// Gets or sets the web socket connection.
    /// </summary>
    /// <value>The web socket connection.</value>
    public ApiWebSocket WebSocketConnection { get; set; }*/

    private IAsyncHttpClient _httpClient;

   public ApiClient(IAsyncHttpClient httpClient, ILogger logger, String serverAddress, String accessToken)
   {
        super(logger, new JsonSerializer(), serverAddress, accessToken);

       _httpClient = httpClient;

        ResetHttpHeaders();
    }

    public ApiClient(IAsyncHttpClient httpClient, ILogger logger, String serverAddress, String clientName, String deviceName, String deviceId, String applicationVersion)
    {
        super(logger, new JsonSerializer(), serverAddress, clientName, deviceName, deviceId, applicationVersion);

        _httpClient = httpClient;

        ResetHttpHeaders();
    }

    @Override
    protected void SetAuthorizationHttpRequestHeader(String scheme, String parameter) {
        _httpClient.SetAuthorizationHeader(scheme, parameter);
    }

    @Override
    protected void SetHttpRequestHeader(String name, String value) {
        _httpClient.SetHttpRequestHeader(name, value);
    }

    @Override
    protected void ClearHttpRequestHeader(String name) {
        _httpClient.ClearHttpRequestHeader(name);
    }

    public ImageLoader getImageLoader() {
        return _httpClient.getImageLoader();
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

    public void GetIntrosAsync(String itemId, String userId, final Response<QueryResult<BaseItemDto>> response)
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
        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                ItemCounts obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
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
        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                UserDto[] obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
    }

    public void GetPublicUsersAsync(final Response<UserDto[]> response)
    {
        String url = GetApiUrl("Users/Public");

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                UserDto[] obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
    }

    public void GetClientSessionsAsync(SessionQuery query, final Response<SessionInfoDto[]> response)
    {
        QueryStringDictionary queryString = new QueryStringDictionary();

        queryString.AddIfNotNullOrEmpty("ControllableByUserId", query.getControllableByUserId());

        String url = GetApiUrl("Sessions", queryString);

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                SessionInfoDto[] obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
    }

    private void GetItemsFromUrl(String url, final Response<QueryResult<BaseItemDto>> response) {

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                QueryResult<BaseItemDto> obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
    }

    private void GetItemFromUrl(String url, final Response<BaseItemDto> response) {

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                BaseItemDto obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
    }

    public void GetItemsAsync(ItemQuery query, final Response<QueryResult<BaseItemDto>> response)
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
    public void GetNextUpEpisodesAsync(NextUpQuery query, final Response<QueryResult<BaseItemDto>> response)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        String url = GetNextUpUrl(query);

        GetItemsFromUrl(url, response);
    }

    public void GetUpcomingEpisodesAsync(NextUpQuery query, final Response<QueryResult<BaseItemDto>> response)
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
    public void GetSimilarMoviesAsync(SimilarItemsQuery query, final Response<QueryResult<BaseItemDto>> response)
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
    public void GetSimilarTrailersAsync(SimilarItemsQuery query, final Response<QueryResult<BaseItemDto>> response)
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
    public void GetSimilarSeriesAsync(SimilarItemsQuery query, final Response<QueryResult<BaseItemDto>> response)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        String url = GetSimilarItemListUrl(query, "Shows");

        GetItemsFromUrl(url, response);
    }

    public void GetEpisodesAsync(EpisodeQuery query, final Response<QueryResult<BaseItemDto>> response)
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

    public void GetSeasonsAsync(SeasonQuery query, final Response<QueryResult<BaseItemDto>> response)
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
    public void GetSimilarGamesAsync(SimilarItemsQuery query, final Response<QueryResult<BaseItemDto>> response)
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
    public void GetSimilarAlbumsAsync(SimilarItemsQuery query, final Response<QueryResult<BaseItemDto>> response)
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
    public void GetPeopleAsync(PersonsQuery query, final Response<QueryResult<BaseItemDto>> response)
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
    public void GetInstantMixFromAlbumAsync(SimilarItemsQuery query, final Response<QueryResult<BaseItemDto>> response)
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
    public void GetInstantMixFromArtistAsync(SimilarItemsByNameQuery query, final Response<QueryResult<BaseItemDto>> response)
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
    public void GetInstantMixFromMusicGenreAsync(SimilarItemsByNameQuery query, final Response<QueryResult<BaseItemDto>> response)
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
    public void GetInstantMixFromSongAsync(SimilarItemsQuery query, final Response<QueryResult<BaseItemDto>> response)
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
    public void GetGameGenresAsync(ItemsByNameQuery query, final Response<QueryResult<BaseItemDto>> response)
    {
        String url = GetItemByNameListUrl("GameGenres", query);

        GetItemsFromUrl(url, response);
    }

    /// <summary>
    /// Gets the genres async.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    public void GetGenresAsync(ItemsByNameQuery query, final Response<QueryResult<BaseItemDto>> response)
    {
        String url = GetItemByNameListUrl("Genres", query);

        GetItemsFromUrl(url, response);
    }

    /// <summary>
    /// Gets the music genres async.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    public void GetMusicGenresAsync(ItemsByNameQuery query, final Response<QueryResult<BaseItemDto>> response)
    {
        String url = GetItemByNameListUrl("MusicGenres", query);

        GetItemsFromUrl(url, response);
    }

    /// <summary>
    /// Gets the studios async.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    public void GetStudiosAsync(ItemsByNameQuery query, final Response<QueryResult<BaseItemDto>> response)
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
    public void GetArtistsAsync(ArtistsQuery query, final Response<QueryResult<BaseItemDto>> response)
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
    public void GetAlbumArtistsAsync(ArtistsQuery query, final Response<QueryResult<BaseItemDto>> response)
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

    /*/// <summary>
    /// Restarts the server async.
    /// </summary>
    /// <returns>Task.</returns>
    public Task RestartServerAsync()
    {
        String url = GetApiUrl("System/Restart");

        return PostAsync<EmptyRequestResult>(url, new QueryStringDictionary(), CancellationToken.None);
    }*/

    /// <summary>
    /// Gets the system status async.
    /// </summary>
    /// <returns>Task{SystemInfo}.</returns>
    public void GetSystemInfoAsync(final Response<SystemInfo> response)
    {
        String url = GetApiUrl("System/Info");

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                SystemInfo obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
    }

    /// <summary>
    /// get public system information as an asynchronous operation.
    /// </summary>
    /// <param name="cancellationToken">The cancellation token.</param>
    /// <returns>Task&lt;PublicSystemInfo&gt;.</returns>
    public void GetPublicSystemInfoAsync(final Response<PublicSystemInfo> response)
    {
        String url = GetApiUrl("System/Info");

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                PublicSystemInfo obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
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

        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                PluginInfo[] obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
    }

    /// <summary>
    /// Gets the current server configuration
    /// </summary>
    /// <returns>Task{ServerConfiguration}.</returns>
    public void GetServerConfigurationAsync(final Response<ServerConfiguration> response)
    {
        String url = GetApiUrl("System/Configuration");

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                ServerConfiguration obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
    }

    /// <summary>
    /// Gets the scheduled tasks.
    /// </summary>
    /// <returns>Task{TaskInfo[]}.</returns>
    public void GetScheduledTasksAsync(final Response<TaskInfo[]> response)
    {
        String url = GetApiUrl("ScheduledTasks");

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                TaskInfo[] obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
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
        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                TaskInfo obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
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
        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                UserDto obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
    }

    /// <summary>
    /// Gets the parental ratings async.
    /// </summary>
    /// <returns>Task{List{ParentalRating}}.</returns>
    public void GetParentalRatingsAsync(final Response<ParentalRating[]> response)
    {
        String url = GetApiUrl("Localization/ParentalRatings");

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                ParentalRating[] obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
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
        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                BaseItemDto[] obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
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
        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                BaseItemDto[] obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
    }

    /// <summary>
    /// Gets the cultures async.
    /// </summary>
    /// <returns>Task{CultureDto[]}.</returns>
    public void GetCulturesAsync(final Response<CultureDto[]> response)
    {
        String url = GetApiUrl("Localization/Cultures");

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                CultureDto[] obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
    }

    /// <summary>
    /// Gets the countries async.
    /// </summary>
    /// <returns>Task{CountryInfo[]}.</returns>
    public void GetCountriesAsync(final Response<CountryInfo[]> response)
    {
        String url = GetApiUrl("Localization/Countries");

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                CountryInfo[] obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
    }

    /// <summary>
    /// Gets the game system summaries async.
    /// </summary>
    /// <returns>Task{List{GameSystemSummary}}.</returns>
    public void GetGameSystemSummariesAsync(final Response<GameSystemSummary[]> response)
    {
        String url = GetApiUrl("Games/SystemSummaries");

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                GameSystemSummary[] obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
    }

    /*public Task<UserItemDataDto> MarkPlayedAsync(String itemId, String userId, DateTime? datePlayed)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(itemId))
        {
            throw new IllegalArgumentException("itemId");
        }
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(userId))
        {
            throw new IllegalArgumentException("userId");
        }

        QueryStringDictionary dict = new QueryStringDictionary();

        if (datePlayed.HasValue)
        {
            dict.Add("DatePlayed", datePlayed.Value.ToString("yyyyMMddHHmmss"));
        }

        String url = GetApiUrl("Users/" + userId + "/PlayedItems/" + itemId, dict);

        return PostAsync<UserItemDataDto>(url, new Dictionary<String, String>(), CancellationToken.None);
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
    public Task<UserItemDataDto> MarkUnplayedAsync(String itemId, String userId)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(itemId))
        {
            throw new IllegalArgumentException("itemId");
        }
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(userId))
        {
            throw new IllegalArgumentException("userId");
        }

        String url = GetApiUrl("Users/" + userId + "/PlayedItems/" + itemId);

        return DeleteAsync<UserItemDataDto>(url, CancellationToken.None);
    }

    /// <summary>
    /// Updates the favorite status async.
    /// </summary>
    /// <param name="itemId">The item id.</param>
    /// <param name="userId">The user id.</param>
    /// <param name="isFavorite">if set to <c>true</c> [is favorite].</param>
    /// <returns>Task.</returns>
    /// <exception cref="System.IllegalArgumentException">itemId</exception>
    public Task<UserItemDataDto> UpdateFavoriteStatusAsync(String itemId, String userId, Boolean isFavorite)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(itemId))
        {
            throw new IllegalArgumentException("itemId");
        }
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(userId))
        {
            throw new IllegalArgumentException("userId");
        }

        String url = GetApiUrl("Users/" + userId + "/FavoriteItems/" + itemId);

        if (isFavorite)
        {
            return PostAsync<UserItemDataDto>(url, new Dictionary<String, String>(), CancellationToken.None);
        }

        return DeleteAsync<UserItemDataDto>(url, CancellationToken.None);
    }

    /// <summary>
    /// Reports to the server that the user has begun playing an item
    /// </summary>
    /// <param name="info">The information.</param>
    /// <returns>Task{UserItemDataDto}.</returns>
    /// <exception cref="System.IllegalArgumentException">itemId</exception>
    public Task ReportPlaybackStartAsync(PlaybackStartInfo info)
    {
        if (info == null)
        {
            throw new IllegalArgumentException("info");
        }

        Logger.Debug("ReportPlaybackStart: Item {0}", info.ItemId);

        if (WebSocketConnection != null && WebSocketConnection.IsConnected)
        {
            return WebSocketConnection.SendAsync("ReportPlaybackStart", JsonSerializer.SerializeToString(info));
        }

        String url = GetApiUrl("Sessions/Playing");

        return PostAsync<PlaybackStartInfo, EmptyRequestResult>(url, info, CancellationToken.None);
    }

    /// <summary>
    /// Reports playback progress to the server
    /// </summary>
    /// <param name="info">The information.</param>
    /// <returns>Task{UserItemDataDto}.</returns>
    /// <exception cref="System.IllegalArgumentException">itemId</exception>
    public Task ReportPlaybackProgressAsync(PlaybackProgressInfo info)
    {
        if (info == null)
        {
            throw new IllegalArgumentException("info");
        }

        if (WebSocketConnection != null && WebSocketConnection.IsConnected)
        {
            return WebSocketConnection.SendAsync("ReportPlaybackProgress", JsonSerializer.SerializeToString(info));
        }

        String url = GetApiUrl("Sessions/Playing/Progress");

        return PostAsync<PlaybackProgressInfo, EmptyRequestResult>(url, info, CancellationToken.None);
    }

    /// <summary>
    /// Reports to the server that the user has stopped playing an item
    /// </summary>
    /// <param name="info">The information.</param>
    /// <returns>Task{UserItemDataDto}.</returns>
    /// <exception cref="System.IllegalArgumentException">itemId</exception>
    public Task ReportPlaybackStoppedAsync(PlaybackStopInfo info)
    {
        if (info == null)
        {
            throw new IllegalArgumentException("info");
        }

        if (WebSocketConnection != null && WebSocketConnection.IsConnected)
        {
            return WebSocketConnection.SendAsync("ReportPlaybackStopped", JsonSerializer.SerializeToString(info));
        }

        String url = GetApiUrl("Sessions/Playing/Stopped");

        return PostAsync<PlaybackStopInfo, EmptyRequestResult>(url, info, CancellationToken.None);
    }

    /// <summary>
    /// Instructs antoher client to browse to a library item.
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
    public Task SendBrowseCommandAsync(String sessionId, String itemId, String itemName, String itemType)
    {
        var cmd = new GeneralCommand
        {
            Name = "DisplayContent"
        };

        cmd.Arguments["ItemType"] = itemType;
        cmd.Arguments["ItemId"] = itemId;
        cmd.Arguments["ItemName"] = itemName;

        return SendCommandAsync(sessionId, cmd);
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
    public Task SendPlayCommandAsync(String sessionId, PlayRequest request)
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
        dict.Add("ItemIds", request.ItemIds);
        dict.AddIfNotNull("StartPositionTicks", request.StartPositionTicks);
        dict.Add("PlayCommand", request.PlayCommand.ToString());

        String url = GetApiUrl("Sessions/" + sessionId + "/Playing", dict);

        return PostAsync<EmptyRequestResult>(url, new Dictionary<String, String>(), CancellationToken.None);
    }

    public Task SendMessageCommandAsync(String sessionId, MessageCommand command)
    {
        var cmd = new GeneralCommand
        {
            Name = "DisplayMessage"
        };

        cmd.Arguments["Header"] = command.Header;
        cmd.Arguments["Text"] = command.Text;

        if (command.TimeoutMs.HasValue)
        {
            cmd.Arguments["ItemName"] = command.TimeoutMs.Value.ToString(CultureInfo.InvariantCulture);
        }

        return SendCommandAsync(sessionId, cmd);
    }

    /// <summary>
    /// Sends the system command async.
    /// </summary>
    /// <param name="sessionId">The session id.</param>
    /// <param name="command">The command.</param>
    /// <returns>Task.</returns>
    /// <exception cref="System.IllegalArgumentException">sessionId</exception>
    public Task SendCommandAsync(String sessionId, GeneralCommand command)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(sessionId))
        {
            throw new IllegalArgumentException("sessionId");
        }

        String url = GetApiUrl("Sessions/" + sessionId + "/Command");

        return PostAsync<GeneralCommand, EmptyRequestResult>(url, command, CancellationToken.None);
    }

    /// <summary>
    /// Sends the playstate command async.
    /// </summary>
    /// <param name="sessionId">The session id.</param>
    /// <param name="request">The request.</param>
    /// <returns>Task.</returns>
    public Task SendPlaystateCommandAsync(String sessionId, PlaystateRequest request)
    {
        QueryStringDictionary dict = new QueryStringDictionary();
        dict.AddIfNotNull("SeekPositionTicks", request.SeekPositionTicks);

        String url = GetApiUrl("Sessions/" + sessionId + "/Playing/" + request.Command.ToString(), dict);

        return PostAsync<EmptyRequestResult>(url, new Dictionary<String, String>(), CancellationToken.None);
    }

    /// <summary>
    /// Clears a user's rating for an item
    /// </summary>
    /// <param name="itemId">The item id.</param>
    /// <param name="userId">The user id.</param>
    /// <returns>Task{UserItemDataDto}.</returns>
    /// <exception cref="System.IllegalArgumentException">itemId</exception>
    public Task<UserItemDataDto> ClearUserItemRatingAsync(String itemId, String userId)
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

        return DeleteAsync<UserItemDataDto>(url, CancellationToken.None);
    }

    /// <summary>
    /// Updates a user's rating for an item, based on likes or dislikes
    /// </summary>
    /// <param name="itemId">The item id.</param>
    /// <param name="userId">The user id.</param>
    /// <param name="likes">if set to <c>true</c> [likes].</param>
    /// <returns>Task.</returns>
    /// <exception cref="System.IllegalArgumentException">itemId</exception>
    public Task<UserItemDataDto> UpdateUserItemRatingAsync(String itemId, String userId, Boolean likes)
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

        return PostAsync<UserItemDataDto>(url, new Dictionary<String, String>(), CancellationToken.None);
    }

    /// <summary>
    /// Authenticates a user and returns the result
    /// </summary>
    /// <param name="username">The username.</param>
    /// <param name="sha1Hash">The sha1 hash.</param>
    /// <returns>Task.</returns>
    /// <exception cref="System.IllegalArgumentException">userId</exception>
    public async Task<AuthenticationResult> AuthenticateUserAsync(String username, byte[] sha1Hash)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(username))
        {
            throw new IllegalArgumentException("username");
        }

        var password = BitConverter.ToString(sha1Hash).Replace("-", String.Empty);
        String url = GetApiUrl("Users/AuthenticateByName");

        var args = new Dictionary<String, String>();

        args["username"] = Uri.EscapeDataString(username);
        args["password"] = password;

        var result = await PostAsync<AuthenticationResult>(url, args, CancellationToken.None);

        SetAuthenticationInfo(result.AccessToken, result.User.Id);

        return result;
    }

    /// <summary>
    /// Updates the server configuration async.
    /// </summary>
    /// <param name="configuration">The configuration.</param>
    /// <returns>Task.</returns>
    /// <exception cref="System.IllegalArgumentException">configuration</exception>
    public Task UpdateServerConfigurationAsync(ServerConfiguration configuration)
    {
        if (configuration == null)
        {
            throw new IllegalArgumentException("configuration");
        }

        String url = GetApiUrl("System/Configuration");

        return PostAsync<ServerConfiguration, EmptyRequestResult>(url, configuration, CancellationToken.None);
    }*/

/*    /// <summary>
    /// Updates the scheduled task triggers.
    /// </summary>
    /// <param name="id">The id.</param>
    /// <param name="triggers">The triggers.</param>
    /// <returns>Task{RequestResult}.</returns>
    /// <exception cref="System.IllegalArgumentException">id</exception>
    public Task UpdateScheduledTaskTriggersAsync(String id, TaskTriggerInfo[] triggers)
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

        return PostAsync<TaskTriggerInfo[], EmptyRequestResult>(url, triggers, CancellationToken.None);
    }*/

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
        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                DisplayPreferences obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
    }

    /*/// <summary>
    /// Updates display preferences for a user
    /// </summary>
    /// <param name="displayPreferences">The display preferences.</param>
    /// <returns>Task{DisplayPreferences}.</returns>
    /// <exception cref="System.IllegalArgumentException">userId</exception>
    public Task UpdateDisplayPreferencesAsync(DisplayPreferences displayPreferences, String userId, String client)
    {
        if (displayPreferences == null)
        {
            throw new IllegalArgumentException("displayPreferences");
        }

        QueryStringDictionary dict = new QueryStringDictionary();

        dict.Add("userId", userId);
        dict.Add("client", client);

        String url = GetApiUrl("DisplayPreferences/" + displayPreferences.Id, dict);

        return PostAsync<DisplayPreferences, EmptyRequestResult>(url, displayPreferences, cancellationToken);
    }

    /// <summary>
    /// Posts a set of data to a url, and deserializes the return stream into T
    /// </summary>
    /// <typeparam name="T"></typeparam>
    /// <param name="url">The URL.</param>
    /// <param name="args">The args.</param>
    /// <param name="cancellationToken">The cancellation token.</param>
    /// <returns>Task{``0}.</returns>
    public async Task<T> PostAsync<T>(String url, Dictionary<String, String> args)
    where T : class
    {
        url = AddDataFormat(url);

        // Create the post body
        var Strings = args.Keys.Select(key => String.Format("{0}={1}", key, args[key]));
        var postContent = String.Join("&", Strings.ToArray());

        const String contentType = "application/x-www-form-urlencoded";

        using (var stream = await HttpClient.PostAsync(url, contentType, postContent, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<T>(stream);
        }
    }

    /// <summary>
    /// Deletes the async.
    /// </summary>
    /// <typeparam name="T"></typeparam>
    /// <param name="url">The URL.</param>
    /// <param name="cancellationToken">The cancellation token.</param>
    /// <returns>Task{``0}.</returns>
    private async Task<T> DeleteAsync<T>(String url)
    where T : class
    {
        url = AddDataFormat(url);

        using (var stream = await HttpClient.DeleteAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<T>(stream);
        }
    }

    /// <summary>
    /// Posts an object of type TInputType to a given url, and deserializes the response into an object of type TOutputType
    /// </summary>
    /// <typeparam name="TInputType">The type of the T input type.</typeparam>
    /// <typeparam name="TOutputType">The type of the T output type.</typeparam>
    /// <param name="url">The URL.</param>
    /// <param name="obj">The obj.</param>
    /// <param name="cancellationToken">The cancellation token.</param>
    /// <returns>Task{``1}.</returns>
    private async Task<TOutputType> PostAsync<TInputType, TOutputType>(String url, TInputType obj)
    where TOutputType : class
    {
        url = AddDataFormat(url);

        const String contentType = "application/json";

        var postContent = SerializeToJson(obj);

        using (var stream = await HttpClient.PostAsync(url, contentType, postContent, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<TOutputType>(stream);
        }
    }

    /// <summary>
    /// This is a helper around getting a stream from the server that contains serialized data
    /// </summary>
    /// <param name="url">The URL.</param>
    /// <param name="cancellationToken">The cancellation token.</param>
    /// <returns>Task{Stream}.</returns>
    public Task<Stream> GetSerializedStreamAsync(String url)
    {
        url = AddDataFormat(url);

        return HttpClient.GetAsync(url, cancellationToken);
    }

    public Task<Stream> GetSerializedStreamAsync(String url)
    {
        return GetSerializedStreamAsync(url, CancellationToken.None);
    }

    public async Task<NotificationsSummary> GetNotificationsSummary(String userId)
    {
        String url = GetApiUrl("Notifications/" + userId + "/Summary");

        using (var stream = await GetSerializedStreamAsync(url).ConfigureAwait(false))
        {
            return DeserializeFromStream<NotificationsSummary>(stream);
        }
    }

    public Task MarkNotificationsRead(String userId, IEnumerable<String> notificationIdList, Boolean isRead)
    {
        String url = "Notifications/" + userId;

        url += isRead ? "/Read" : "/Unread";

        QueryStringDictionary dict = new QueryStringDictionary();

        var ids = notificationIdList.ToArray();

        dict.Add("Ids", String.Join(",", ids));

        url = GetApiUrl(url, dict);

        return PostAsync<EmptyRequestResult>(url, new Dictionary<String, String>(), CancellationToken.None);
    }*/

    public void GetNotificationsAsync(NotificationQuery query, final Response<NotificationResult> response)
    {
        String url = "Notifications/" + query.getUserId();

        QueryStringDictionary dict = new QueryStringDictionary();
        dict.AddIfNotNull("ItemIds", query.getIsRead());
        dict.AddIfNotNull("StartIndex", query.getStartIndex());
        dict.AddIfNotNull("Limit", query.getLimit());

        url = GetApiUrl(url, dict);

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                NotificationResult obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
    }

    public void GetAllThemeMediaAsync(String userId, String itemId, Boolean inheritFromParent, final Response<AllThemeMediaResult> response)
    {
        QueryStringDictionary queryString = new QueryStringDictionary();

        queryString.Add("InheritFromParent", inheritFromParent);
        queryString.AddIfNotNullOrEmpty("UserId", userId);

        String url = GetApiUrl("Items/" + itemId + "/ThemeMedia", queryString);

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                AllThemeMediaResult obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
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
        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                SearchHintResult obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
    }

    public void GetThemeSongsAsync(String userId, String itemId, Boolean inheritFromParent, final Response<ThemeMediaResult> response)
    {
        QueryStringDictionary queryString = new QueryStringDictionary();

        queryString.Add("InheritFromParent", inheritFromParent);
        queryString.AddIfNotNullOrEmpty("UserId", userId);

        String url = GetApiUrl("Items/" + itemId + "/ThemeSongs", queryString);

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                ThemeMediaResult obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
    }

    public void GetThemeVideosAsync(String userId, String itemId, Boolean inheritFromParent, final Response<ThemeMediaResult> response)
    {
        QueryStringDictionary queryString = new QueryStringDictionary();

        queryString.Add("InheritFromParent", inheritFromParent);
        queryString.AddIfNotNullOrEmpty("UserId", userId);

        String url = GetApiUrl("Items/" + itemId + "/ThemeVideos", queryString);

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                ThemeMediaResult obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
    }

    /*/// <summary>
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
    public async Task<QueryResult<ItemReview>> GetCriticReviews(String itemId, int? startIndex = null, int? limit = null)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(itemId))
        {
            throw new IllegalArgumentException("itemId");
        }

        QueryStringDictionary queryString = new QueryStringDictionary();

        queryString.AddIfNotNull("startIndex", startIndex);
        queryString.AddIfNotNull("limit", limit);

        String url = GetApiUrl("Items/" + itemId + "/CriticReviews", queryString);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<QueryResult<ItemReview>>(stream);
        }
    }*/

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
        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                ItemIndex[] obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
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
        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                ItemIndex[] obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
    }

    /*public Task ReportCapabilities(ClientCapabilities capabilities)
    {
        if (capabilities == null)
        {
            throw new IllegalArgumentException("capabilities");
        }

        QueryStringDictionary dict = new QueryStringDictionary();
        dict.AddIfNotNull("PlayableMediaTypes", capabilities.PlayableMediaTypes);
        dict.AddIfNotNull("SupportedCommands", capabilities.SupportedCommands);

        String url = GetApiUrl("Sessions/Capabilities", dict);

        return PostAsync<EmptyRequestResult>(url, dict, cancellationToken);
    }*/

    public void GetLiveTvInfoAsync(final Response<LiveTvInfo> response)
    {
        String url = GetApiUrl("LiveTv/Info");

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                LiveTvInfo obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
    }

    public void GetLiveTvRecordingGroupsAsync(RecordingGroupQuery query, final Response<QueryResult<RecordingGroupDto>> response)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();

        dict.AddIfNotNullOrEmpty("UserId", query.getUserId());

        String url = GetApiUrl("LiveTv/Recordings/Groups", dict);

        url = AddDataFormat(url);
        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                QueryResult<RecordingGroupDto> obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
    }

    public void GetLiveTvRecordingsAsync(RecordingQuery query, final Response<QueryResult<RecordingInfoDto>> response)
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
        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                QueryResult<RecordingInfoDto> obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
    }

    public void GetLiveTvChannelsAsync(LiveTvChannelQuery query, final Response<QueryResult<ChannelInfoDto>> response)
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
        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                QueryResult<ChannelInfoDto> obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
    }

    /*public Task CancelLiveTvSeriesTimerAsync(String id)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(id))
        {
            throw new IllegalArgumentException("id");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();

        String url = GetApiUrl("LiveTv/SeriesTimers/" + id, dict);

        return HttpClient.DeleteAsync(url, cancellationToken);
    }

    public Task CancelLiveTvTimerAsync(String id)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(id))
        {
            throw new IllegalArgumentException("id");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();

        String url = GetApiUrl("LiveTv/Timers/" + id, dict);

        return HttpClient.DeleteAsync(url, cancellationToken);
    }

    public Task DeleteLiveTvRecordingAsync(String id)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(id))
        {
            throw new IllegalArgumentException("id");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();

        String url = GetApiUrl("LiveTv/Recordings/" + id, dict);

        return HttpClient.DeleteAsync(url, cancellationToken);
    }*/

/*    public async Task<ChannelInfoDto> GetLiveTvChannelAsync(String id, String userId)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(id))
        {
            throw new IllegalArgumentException("id");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();
        dict.AddIfNotNullOrEmpty("userId", userId);

        String url = GetApiUrl("LiveTv/Channels/" + id, dict);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<ChannelInfoDto>(stream);
        }
    }

    public async Task<RecordingInfoDto> GetLiveTvRecordingAsync(String id, String userId)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(id))
        {
            throw new IllegalArgumentException("id");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();
        dict.AddIfNotNullOrEmpty("userId", userId);

        String url = GetApiUrl("LiveTv/Recordings/" + id, dict);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<RecordingInfoDto>(stream);
        }
    }

    public async Task<RecordingGroupDto> GetLiveTvRecordingGroupAsync(String id, String userId)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(id))
        {
            throw new IllegalArgumentException("id");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();
        dict.AddIfNotNullOrEmpty("userId", userId);

        String url = GetApiUrl("LiveTv/Recordings/Groups/" + id, dict);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<RecordingGroupDto>(stream);
        }
    }

    public async Task<SeriesTimerInfoDto> GetLiveTvSeriesTimerAsync(String id)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(id))
        {
            throw new IllegalArgumentException("id");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();

        String url = GetApiUrl("LiveTv/SeriesTimers/" + id, dict);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<SeriesTimerInfoDto>(stream);
        }
    }

    public async Task<QueryResult<SeriesTimerInfoDto>> GetLiveTvSeriesTimersAsync(SeriesTimerQuery query)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();

        dict.AddIfNotNullOrEmpty("SortBy", query.SortBy);
        dict.Add("SortOrder", query.SortOrder.ToString());

        String url = GetApiUrl("LiveTv/SeriesTimers", dict);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<QueryResult<SeriesTimerInfoDto>>(stream);
        }
    }

    public async Task<TimerInfoDto> GetLiveTvTimerAsync(String id)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(id))
        {
            throw new IllegalArgumentException("id");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();

        String url = GetApiUrl("LiveTv/Timers/" + id, dict);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<TimerInfoDto>(stream);
        }
    }

    public async Task<QueryResult<TimerInfoDto>> GetLiveTvTimersAsync(TimerQuery query)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();

        dict.AddIfNotNullOrEmpty("ChannelId", query.ChannelId);
        dict.AddIfNotNullOrEmpty("SeriesTimerId", query.SeriesTimerId);

        String url = GetApiUrl("LiveTv/Timers", dict);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<QueryResult<TimerInfoDto>>(stream);
        }
    }

    public async Task<QueryResult<ProgramInfoDto>> GetLiveTvProgramsAsync(ProgramQuery query)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();

        const String isoDateFormat = "o";

        if (query.MaxEndDate.HasValue)
        {
            dict.Add("MaxEndDate", query.MaxEndDate.Value.ToUniversalTime().ToString(isoDateFormat));
        }
        if (query.MaxStartDate.HasValue)
        {
            dict.Add("MaxStartDate", query.MaxStartDate.Value.ToUniversalTime().ToString(isoDateFormat));
        }
        if (query.MinEndDate.HasValue)
        {
            dict.Add("MinEndDate", query.MinEndDate.Value.ToUniversalTime().ToString(isoDateFormat));
        }
        if (query.MinStartDate.HasValue)
        {
            dict.Add("MinStartDate", query.MinStartDate.Value.ToUniversalTime().ToString(isoDateFormat));
        }

        dict.AddIfNotNullOrEmpty("UserId", query.UserId);

        if (query.ChannelIdList != null)
        {
            dict.Add("ChannelIds", String.Join(",", query.ChannelIdList));
        }

        // TODO: This endpoint supports POST if the query String is too long
        String url = GetApiUrl("LiveTv/Programs", dict);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<QueryResult<ProgramInfoDto>>(stream);
        }
    }

    public async Task<QueryResult<ProgramInfoDto>> GetRecommendedLiveTvProgramsAsync(RecommendedProgramQuery query)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();

        dict.AddIfNotNullOrEmpty("UserId", query.UserId);
        dict.AddIfNotNull("Limit", query.Limit);
        dict.AddIfNotNull("HasAired", query.HasAired);
        dict.AddIfNotNull("IsAiring", query.IsAiring);

        String url = GetApiUrl("LiveTv/Programs/Recommended", dict);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<QueryResult<ProgramInfoDto>>(stream);
        }
    }

    public Task CreateLiveTvSeriesTimerAsync(SeriesTimerInfoDto timer)
    {
        if (timer == null)
        {
            throw new IllegalArgumentException("timer");
        }

        String url = GetApiUrl("LiveTv/SeriesTimers");

        return PostAsync<SeriesTimerInfoDto, EmptyRequestResult>(url, timer, cancellationToken);
    }

    public Task CreateLiveTvTimerAsync(BaseTimerInfoDto timer)
    {
        if (timer == null)
        {
            throw new IllegalArgumentException("timer");
        }

        String url = GetApiUrl("LiveTv/Timers");

        return PostAsync<BaseTimerInfoDto, EmptyRequestResult>(url, timer, cancellationToken);
    }

    public async Task<SeriesTimerInfoDto> GetDefaultLiveTvTimerInfo(String programId)
    {
        if (String.IsNullOrWhiteSpace(programId))
        {
            throw new IllegalArgumentException("programId");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();

        dict.AddIfNotNullOrEmpty("programId", programId);

        String url = GetApiUrl("LiveTv/Timers/Defaults", dict);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<SeriesTimerInfoDto>(stream);
        }
    }

    public async Task<SeriesTimerInfoDto> GetDefaultLiveTvTimerInfo()
    {
        String url = GetApiUrl("LiveTv/Timers/Defaults");

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<SeriesTimerInfoDto>(stream);
        }
    }

    public async Task<GuideInfo> GetLiveTvGuideInfo()
    {
        String url = GetApiUrl("LiveTv/GuideInfo");

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<GuideInfo>(stream);
        }
    }

    public async Task<ProgramInfoDto> GetLiveTvProgramAsync(String id, String userId)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(id))
        {
            throw new IllegalArgumentException("id");
        }

        QueryStringDictionary dict = new QueryStringDictionary ();
        dict.AddIfNotNullOrEmpty("userId", userId);

        String url = GetApiUrl("LiveTv/Programs/" + id, dict);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<ProgramInfoDto>(stream);
        }
    }

    public Task UpdateLiveTvSeriesTimerAsync(SeriesTimerInfoDto timer)
    {
        if (timer == null)
        {
            throw new IllegalArgumentException("timer");
        }

        String url = GetApiUrl("LiveTv/SeriesTimers/" + timer.Id);

        return PostAsync<SeriesTimerInfoDto, EmptyRequestResult>(url, timer, cancellationToken);
    }

    public Task UpdateLiveTvTimerAsync(TimerInfoDto timer)
    {
        if (timer == null)
        {
            throw new IllegalArgumentException("timer");
        }

        String url = GetApiUrl("LiveTv/Timers/" + timer.Id);

        return PostAsync<TimerInfoDto, EmptyRequestResult>(url, timer, cancellationToken);
    }

    public Task SendString(String sessionId, String text)
    {
        var cmd = new GeneralCommand
        {
            Name = "SendString"
        };

        cmd.Arguments["String"] = text;

        return SendCommandAsync(sessionId, cmd);
    }

    public Task SetAudioStreamIndex(String sessionId, int index)
    {
        var cmd = new GeneralCommand
        {
            Name = "SetAudioStreamIndex"
        };

        cmd.Arguments["Index"] = index.ToString(CultureInfo.InvariantCulture);

        return SendCommandAsync(sessionId, cmd);
    }

    public Task SetSubtitleStreamIndex(String sessionId, int? index)
    {
        var cmd = new GeneralCommand
        {
            Name = "SetSubtitleStreamIndex"
        };

        cmd.Arguments["Index"] = (index ?? -1).ToString(CultureInfo.InvariantCulture);

        return SendCommandAsync(sessionId, cmd);
    }

    public Task SetVolume(String sessionId, int volume)
    {
        var cmd = new GeneralCommand
        {
            Name = "SetVolume"
        };

        cmd.Arguments["Volume"] = volume.ToString(CultureInfo.InvariantCulture);

        return SendCommandAsync(sessionId, cmd);
    }

    public void GetAdditionalParts(String itemId, String userId)
    {
        QueryStringDictionary queryString = new QueryStringDictionary();

        queryString.AddIfNotNullOrEmpty("UserId", userId);

        String url = GetApiUrl("Videos/" + itemId + "/AdditionalParts", queryString);

        using (var stream = await GetSerializedStreamAsync(url, CancellationToken.None).ConfigureAwait(false))
        {
            return DeserializeFromStream<ItemsResult>(stream);
        }
    }

    public async Task<ChannelFeatures> GetChannelFeatures(String channelId)
    {
        String url = GetApiUrl("Channels/" + channelId + "/Features");

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<ChannelFeatures>(stream);
        }
    }

    public async Task<QueryResult<BaseItemDto>> GetChannelItems(ChannelItemQuery query)
    {
        QueryStringDictionary queryString = new QueryStringDictionary();

        queryString.AddIfNotNullOrEmpty("UserId", query.UserId);
        queryString.AddIfNotNull("StartIndex", query.StartIndex);
        queryString.AddIfNotNull("Limit", query.Limit);
        queryString.AddIfNotNullOrEmpty("FolderId", query.FolderId);
        if (query.Fields != null)
        {
            queryString.Add("fields", query.Fields.Select(f => f.ToString()));
        }
        if (query.Filters != null)
        {
            queryString.Add("Filters", query.Filters.Select(f => f.ToString()));
        }
        queryString.AddIfNotNull("SortBy", query.SortBy);
        queryString.Add("SortOrder", query.SortOrder.ToString());

        String url = GetApiUrl("Channels/" + query.ChannelId + "/Items", queryString);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<QueryResult<BaseItemDto>>(stream);
        }
    }

    public async Task<QueryResult<BaseItemDto>> GetChannels(ChannelQuery query)
    {
        QueryStringDictionary queryString = new QueryStringDictionary();

        queryString.AddIfNotNullOrEmpty("UserId", query.UserId);
        queryString.AddIfNotNull("SupportsLatestItems", query.SupportsLatestItems);
        queryString.AddIfNotNull("StartIndex", query.StartIndex);
        queryString.AddIfNotNull("Limit", query.Limit);
        queryString.AddIfNotNull("IsFavorite", query.IsFavorite);

        String url = GetApiUrl("Channels", queryString);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<QueryResult<BaseItemDto>>(stream);
        }
    }

    public async Task<SessionInfoDto> GetCurrentSessionAsync()
    {
        QueryStringDictionary queryString = new QueryStringDictionary();

        queryString.Add("DeviceId", DeviceId);
        String url = GetApiUrl("Sessions", queryString);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            var sessions = DeserializeFromStream<SessionInfoDto[]>(stream);

            return sessions.FirstOrDefault();
        }
    }

    public Task StopTranscodingProcesses(String deviceId)
    {
        QueryStringDictionary queryString = new QueryStringDictionary();

        queryString.Add("DeviceId", DeviceId);
        String url = GetApiUrl("Videos/ActiveEncodings", queryString);

        return HttpClient.DeleteAsync(url, CancellationToken.None);
    }

    public Task<QueryResult<BaseItemDto>> GetLatestChannelItems(AllChannelMediaQuery query)
    {
        throw new NotImplementedException();
    }

    public async Task Logout()
    {
        try
        {
            String url = GetApiUrl("Sessions/Logout");

            await PostAsync<EmptyRequestResult>(url, new Dictionary<String, String>(), CancellationToken.None);
        }
        catch (Exception ex)
        {
            Logger.ErrorException("Error logging out", ex);
        }

        ClearAuthenticationInfo();
    }

    public void GetUserViews(String userId)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(userId))
        {
            throw new IllegalArgumentException("userId");
        }

        String url = GetApiUrl("Users/" + userId + "/Views");

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<ItemsResult>(stream);
        }
    }*/
}
