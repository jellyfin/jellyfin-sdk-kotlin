package MediaBrowser.ApiInteraction;

import MediaBrowser.Model.Dto.BaseItemDto;
import MediaBrowser.Model.Dto.ItemCounts;
import MediaBrowser.Model.Dto.UserDto;
import MediaBrowser.Model.Logging.ILogger;
import MediaBrowser.Model.Querying.*;
import MediaBrowser.Model.Serialization.IJsonSerializer;
import MediaBrowser.Model.Session.SessionInfoDto;

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

    protected ApiClient(ILogger logger, IJsonSerializer jsonSerializer, String serverAddress, String clientName, String deviceName, String deviceId, String applicationVersion) {
        super(logger, jsonSerializer, serverAddress, clientName, deviceName, deviceId, applicationVersion);
    }

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

/*    public Task<Stream> GetImageStreamAsync(string url, CancellationToken cancellationToken)
    {
        if (string.IsNullOrEmpty(url))
        {
            throw new ArgumentNullException("url");
        }

        return HttpClient.GetAsync(url, cancellationToken);
    }*/

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

        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                BaseItemDto obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
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

        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                QueryResult<BaseItemDto> obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
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

        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                BaseItemDto obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
    }

    public void GetUsersAsync(UserQuery query, final Response<UserDto[]> response)
    {
        QueryStringDictionary queryString = new QueryStringDictionary();

        queryString.AddIfNotNull("IsDisabled", query.getIsDisabled());
        queryString.AddIfNotNull("IsHidden", query.getIsHidden());

        String url = GetApiUrl("Users", queryString);

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

        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                SessionInfoDto[] obj = DeserializeFromString(jsonResponse);
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

        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                QueryResult<BaseItemDto> obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
    }

    /// <summary>
    /// Gets the next up async.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    /// <exception cref="System.ArgumentNullException">query</exception>
    public void GetNextUpEpisodesAsync(NextUpQuery query, final Response<QueryResult<BaseItemDto>> response)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        String url = GetNextUpUrl(query);

        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                QueryResult<BaseItemDto> obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
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

        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                QueryResult<BaseItemDto> obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
    }

    /// <summary>
    /// Gets the similar movies async.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    /// <exception cref="System.ArgumentNullException">query</exception>
    public void GetSimilarMoviesAsync(SimilarItemsQuery query, final Response<QueryResult<BaseItemDto>> response)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        String url = GetSimilarItemListUrl(query, "Movies");

        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                QueryResult<BaseItemDto> obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
    }

    /// <summary>
    /// Gets the similar trailers async.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    /// <exception cref="System.ArgumentNullException">query</exception>
    public void GetSimilarTrailersAsync(SimilarItemsQuery query, final Response<QueryResult<BaseItemDto>> response)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        String url = GetSimilarItemListUrl(query, "Trailers");

        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                QueryResult<BaseItemDto> obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
    }

    /// <summary>
    /// Gets the similar series async.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    /// <exception cref="System.ArgumentNullException">query</exception>
    public void GetSimilarSeriesAsync(SimilarItemsQuery query, final Response<QueryResult<BaseItemDto>> response)
    {
        if (query == null)
        {
            throw new IllegalArgumentException("query");
        }

        String url = GetSimilarItemListUrl(query, "Shows");

        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                QueryResult<BaseItemDto> obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
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

        Response<String> jsonResponse = new Response<String>(){

            @Override
            public void onResponse(String jsonResponse) {

                QueryResult<BaseItemDto> obj = DeserializeFromString(jsonResponse);
                response.onResponse(obj);
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
    }

/*    public async Task<ItemsResult> GetSeasonsAsync(SeasonQuery query, CancellationToken cancellationToken)
    {
        if (query == null)
        {
            throw new ArgumentNullException("query");
        }

        var dict = new QueryStringDictionary { };

        dict.AddIfNotNullOrEmpty("UserId", query.UserId);

        if (query.Fields != null)
        {
            dict.Add("Fields", query.Fields.Select(f => f.ToString()));
        }

        dict.AddIfNotNull("IsMissing", query.IsMissing);
        dict.AddIfNotNull("IsVirtualUnaired", query.IsVirtualUnaired);
        dict.AddIfNotNull("IsSpecialSeason", query.IsSpecialSeason);

        var url = GetApiUrl("Shows/" + query.SeriesId + "/Seasons", dict);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<ItemsResult>(stream);
        }
    }

    /// <summary>
    /// Gets the similar games async.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    /// <exception cref="System.ArgumentNullException">query</exception>
    public async Task<ItemsResult> GetSimilarGamesAsync(SimilarItemsQuery query, CancellationToken cancellationToken)
    {
        if (query == null)
        {
            throw new ArgumentNullException("query");
        }

        var url = GetSimilarItemListUrl(query, "Games");

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<ItemsResult>(stream);
        }
    }

    /// <summary>
    /// Gets the similar albums async.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    /// <exception cref="System.ArgumentNullException">query</exception>
    public async Task<ItemsResult> GetSimilarAlbumsAsync(SimilarItemsQuery query, CancellationToken cancellationToken)
    {
        if (query == null)
        {
            throw new ArgumentNullException("query");
        }

        var url = GetSimilarItemListUrl(query, "Albums");

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<ItemsResult>(stream);
        }
    }

    /// <summary>
    /// Gets the people async.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    /// <exception cref="System.ArgumentNullException">userId</exception>
    public async Task<ItemsResult> GetPeopleAsync(PersonsQuery query, CancellationToken cancellationToken)
    {
        var url = GetItemByNameListUrl("Persons", query);

        if (query.PersonTypes != null && query.PersonTypes.Length > 0)
        {
            url += "&PersonTypes=" + string.Join(",", query.PersonTypes);
        }

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<ItemsResult>(stream);
        }
    }

    /// <summary>
    /// Gets the instant mix from album async.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    /// <exception cref="System.ArgumentNullException">query</exception>
    public async Task<ItemsResult> GetInstantMixFromAlbumAsync(SimilarItemsQuery query)
    {
        if (query == null)
        {
            throw new ArgumentNullException("query");
        }

        var url = GetInstantMixUrl(query, "Albums");

        using (var stream = await GetSerializedStreamAsync(url).ConfigureAwait(false))
        {
            return DeserializeFromStream<ItemsResult>(stream);
        }
    }

    /// <summary>
    /// Gets the instant mix from artist async.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    /// <exception cref="System.ArgumentNullException">query</exception>
    public async Task<ItemsResult> GetInstantMixFromArtistAsync(SimilarItemsByNameQuery query)
    {
        if (query == null)
        {
            throw new ArgumentNullException("query");
        }

        var url = GetInstantMixByNameUrl(query, "Artists");

        using (var stream = await GetSerializedStreamAsync(url).ConfigureAwait(false))
        {
            return DeserializeFromStream<ItemsResult>(stream);
        }
    }

    /// <summary>
    /// Gets the instant mix from music genre async.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    /// <exception cref="System.ArgumentNullException">query</exception>
    public async Task<ItemsResult> GetInstantMixFromMusicGenreAsync(SimilarItemsByNameQuery query)
    {
        if (query == null)
        {
            throw new ArgumentNullException("query");
        }

        var url = GetInstantMixByNameUrl(query, "MusicGenres");

        using (var stream = await GetSerializedStreamAsync(url).ConfigureAwait(false))
        {
            return DeserializeFromStream<ItemsResult>(stream);
        }
    }

    /// <summary>
    /// Gets the instant mix from song async.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    /// <exception cref="System.ArgumentNullException">query</exception>
    public async Task<ItemsResult> GetInstantMixFromSongAsync(SimilarItemsQuery query)
    {
        if (query == null)
        {
            throw new ArgumentNullException("query");
        }

        var url = GetInstantMixUrl(query, "Songs");

        using (var stream = await GetSerializedStreamAsync(url).ConfigureAwait(false))
        {
            return DeserializeFromStream<ItemsResult>(stream);
        }
    }

    /// <summary>
    /// Gets the game genres async.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    public async Task<ItemsResult> GetGameGenresAsync(ItemsByNameQuery query)
    {
        var url = GetItemByNameListUrl("GameGenres", query);

        using (var stream = await GetSerializedStreamAsync(url).ConfigureAwait(false))
        {
            return DeserializeFromStream<ItemsResult>(stream);
        }
    }

    /// <summary>
    /// Gets the genres async.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    public async Task<ItemsResult> GetGenresAsync(ItemsByNameQuery query)
    {
        var url = GetItemByNameListUrl("Genres", query);

        using (var stream = await GetSerializedStreamAsync(url).ConfigureAwait(false))
        {
            return DeserializeFromStream<ItemsResult>(stream);
        }
    }

    /// <summary>
    /// Gets the music genres async.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    public async Task<ItemsResult> GetMusicGenresAsync(ItemsByNameQuery query)
    {
        var url = GetItemByNameListUrl("MusicGenres", query);

        using (var stream = await GetSerializedStreamAsync(url).ConfigureAwait(false))
        {
            return DeserializeFromStream<ItemsResult>(stream);
        }
    }

    /// <summary>
    /// Gets the studios async.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    public async Task<ItemsResult> GetStudiosAsync(ItemsByNameQuery query)
    {
        var url = GetItemByNameListUrl("Studios", query);

        using (var stream = await GetSerializedStreamAsync(url).ConfigureAwait(false))
        {
            return DeserializeFromStream<ItemsResult>(stream);
        }
    }

    /// <summary>
    /// Gets the artists.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    /// <exception cref="System.ArgumentNullException">userId</exception>
    public async Task<ItemsResult> GetArtistsAsync(ArtistsQuery query)
    {
        var url = GetItemByNameListUrl("Artists", query);

        using (var stream = await GetSerializedStreamAsync(url).ConfigureAwait(false))
        {
            return DeserializeFromStream<ItemsResult>(stream);
        }
    }

    /// <summary>
    /// Gets the artists.
    /// </summary>
    /// <param name="query">The query.</param>
    /// <returns>Task{ItemsResult}.</returns>
    /// <exception cref="System.ArgumentNullException">userId</exception>
    public async Task<ItemsResult> GetAlbumArtistsAsync(ArtistsQuery query)
    {
        var url = GetItemByNameListUrl("Artists/AlbumArtists", query);

        using (var stream = await GetSerializedStreamAsync(url).ConfigureAwait(false))
        {
            return DeserializeFromStream<ItemsResult>(stream);
        }
    }

    /// <summary>
    /// Gets a studio
    /// </summary>
    /// <param name="name">The name.</param>
    /// <param name="userId">The user id.</param>
    /// <returns>Task{BaseItemDto}.</returns>
    /// <exception cref="System.ArgumentNullException">userId</exception>
    public async Task<BaseItemDto> GetStudioAsync(string name, string userId)
    {
        if (string.IsNullOrEmpty(name))
        {
            throw new ArgumentNullException("name");
        }
        if (string.IsNullOrEmpty(userId))
        {
            throw new ArgumentNullException("userId");
        }

        var dict = new QueryStringDictionary();

        dict.Add("userId", userId);

        var url = GetApiUrl("Studios/" + GetSlugName(name), dict);

        using (var stream = await GetSerializedStreamAsync(url).ConfigureAwait(false))
        {
            return DeserializeFromStream<BaseItemDto>(stream);
        }
    }

    /// <summary>
    /// Gets a genre
    /// </summary>
    /// <param name="name">The name.</param>
    /// <param name="userId">The user id.</param>
    /// <returns>Task{BaseItemDto}.</returns>
    /// <exception cref="System.ArgumentNullException">userId</exception>
    public async Task<BaseItemDto> GetGenreAsync(string name, string userId)
    {
        if (string.IsNullOrEmpty(name))
        {
            throw new ArgumentNullException("name");
        }
        if (string.IsNullOrEmpty(userId))
        {
            throw new ArgumentNullException("userId");
        }

        var dict = new QueryStringDictionary();

        dict.Add("userId", userId);

        var url = GetApiUrl("Genres/" + GetSlugName(name), dict);

        using (var stream = await GetSerializedStreamAsync(url).ConfigureAwait(false))
        {
            return DeserializeFromStream<BaseItemDto>(stream);
        }
    }

    public async Task<BaseItemDto> GetMusicGenreAsync(string name, string userId)
    {
        if (string.IsNullOrEmpty(name))
        {
            throw new ArgumentNullException("name");
        }
        if (string.IsNullOrEmpty(userId))
        {
            throw new ArgumentNullException("userId");
        }

        var dict = new QueryStringDictionary();

        dict.Add("userId", userId);

        var url = GetApiUrl("MusicGenres/" + GetSlugName(name), dict);

        using (var stream = await GetSerializedStreamAsync(url).ConfigureAwait(false))
        {
            return DeserializeFromStream<BaseItemDto>(stream);
        }
    }

    public async Task<BaseItemDto> GetGameGenreAsync(string name, string userId)
    {
        if (string.IsNullOrEmpty(name))
        {
            throw new ArgumentNullException("name");
        }
        if (string.IsNullOrEmpty(userId))
        {
            throw new ArgumentNullException("userId");
        }

        var dict = new QueryStringDictionary();

        dict.Add("userId", userId);

        var url = GetApiUrl("GameGenres/" + GetSlugName(name), dict);

        using (var stream = await GetSerializedStreamAsync(url).ConfigureAwait(false))
        {
            return DeserializeFromStream<BaseItemDto>(stream);
        }
    }

    /// <summary>
    /// Gets the music genre async.
    /// </summary>
    /// <param name="name">The name.</param>
    /// <returns>Task{BaseItemDto}.</returns>
    /// <exception cref="System.ArgumentNullException">name</exception>
    public async Task<BaseItemDto> GetMusicGenreAsync(string name)
    {
        if (string.IsNullOrEmpty(name))
        {
            throw new ArgumentNullException("name");
        }

        var url = GetApiUrl("MusicGenres/" + GetSlugName(name));

        using (var stream = await GetSerializedStreamAsync(url).ConfigureAwait(false))
        {
            return DeserializeFromStream<BaseItemDto>(stream);
        }
    }*/

/*    /// <summary>
    /// Gets the artist async.
    /// </summary>
    /// <param name="name">The name.</param>
    /// <param name="userId">The user id.</param>
    /// <returns>Task{BaseItemDto}.</returns>
    /// <exception cref="System.ArgumentNullException">name</exception>
    public async Task<BaseItemDto> GetArtistAsync(string name, string userId)
    {
        if (string.IsNullOrEmpty(name))
        {
            throw new ArgumentNullException("name");
        }
        if (string.IsNullOrEmpty(userId))
        {
            throw new ArgumentNullException("userId");
        }

        var dict = new QueryStringDictionary();

        dict.Add("userId", userId);

        var url = GetApiUrl("Artists/" + GetSlugName(name), dict);

        using (var stream = await GetSerializedStreamAsync(url).ConfigureAwait(false))
        {
            return DeserializeFromStream<BaseItemDto>(stream);
        }
    }

    /// <summary>
    /// Restarts the server async.
    /// </summary>
    /// <returns>Task.</returns>
    public Task RestartServerAsync()
    {
        var url = GetApiUrl("System/Restart");

        return PostAsync<EmptyRequestResult>(url, new QueryStringDictionary(), CancellationToken.None);
    }

    /// <summary>
    /// Gets the system status async.
    /// </summary>
    /// <returns>Task{SystemInfo}.</returns>
    public async Task<SystemInfo> GetSystemInfoAsync(CancellationToken cancellationToken)
    {
        var url = GetApiUrl("System/Info");

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<SystemInfo>(stream);
        }
    }

    /// <summary>
    /// get public system information as an asynchronous operation.
    /// </summary>
    /// <param name="cancellationToken">The cancellation token.</param>
    /// <returns>Task&lt;PublicSystemInfo&gt;.</returns>
    public async Task<PublicSystemInfo> GetPublicSystemInfoAsync(CancellationToken cancellationToken)
    {
        var url = GetApiUrl("System/Info/Public");

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<PublicSystemInfo>(stream);
        }
    }

    /// <summary>
    /// Gets a person
    /// </summary>
    /// <param name="name">The name.</param>
    /// <returns>Task{BaseItemDto}.</returns>
    /// <exception cref="System.ArgumentNullException">userId</exception>
    public async Task<BaseItemDto> GetPersonAsync(string name, string userId)
    {
        if (string.IsNullOrEmpty(name))
        {
            throw new ArgumentNullException("name");
        }
        if (string.IsNullOrEmpty(userId))
        {
            throw new ArgumentNullException("userId");
        }

        var dict = new QueryStringDictionary();

        dict.Add("userId", userId);

        var url = GetApiUrl("Persons/" + GetSlugName(name), dict);

        using (var stream = await GetSerializedStreamAsync(url).ConfigureAwait(false))
        {
            return DeserializeFromStream<BaseItemDto>(stream);
        }
    }

    /// <summary>
    /// Gets a list of plugins installed on the server
    /// </summary>
    /// <returns>Task{PluginInfo[]}.</returns>
    public async Task<PluginInfo[]> GetInstalledPluginsAsync()
    {
        var url = GetApiUrl("Plugins");

        using (var stream = await GetSerializedStreamAsync(url).ConfigureAwait(false))
        {
            return DeserializeFromStream<PluginInfo[]>(stream);
        }
    }

    /// <summary>
    /// Gets the current server configuration
    /// </summary>
    /// <returns>Task{ServerConfiguration}.</returns>
    public async Task<ServerConfiguration> GetServerConfigurationAsync()
    {
        var url = GetApiUrl("System/Configuration");

        using (var stream = await GetSerializedStreamAsync(url).ConfigureAwait(false))
        {
            return DeserializeFromStream<ServerConfiguration>(stream);
        }
    }

    /// <summary>
    /// Gets the scheduled tasks.
    /// </summary>
    /// <returns>Task{TaskInfo[]}.</returns>
    public async Task<TaskInfo[]> GetScheduledTasksAsync()
    {
        var url = GetApiUrl("ScheduledTasks");

        using (var stream = await GetSerializedStreamAsync(url).ConfigureAwait(false))
        {
            return DeserializeFromStream<TaskInfo[]>(stream);
        }
    }

    /// <summary>
    /// Gets the scheduled task async.
    /// </summary>
    /// <param name="id">The id.</param>
    /// <returns>Task{TaskInfo}.</returns>
    /// <exception cref="System.ArgumentNullException">id</exception>
    public async Task<TaskInfo> GetScheduledTaskAsync(string id)
    {
        if (string.IsNullOrEmpty(id))
        {
            throw new ArgumentNullException("id");
        }

        var url = GetApiUrl("ScheduledTasks/" + id);

        using (var stream = await GetSerializedStreamAsync(url).ConfigureAwait(false))
        {
            return DeserializeFromStream<TaskInfo>(stream);
        }
    }

    /// <summary>
    /// Gets a user by id
    /// </summary>
    /// <param name="id">The id.</param>
    /// <returns>Task{UserDto}.</returns>
    /// <exception cref="System.ArgumentNullException">id</exception>
    public async Task<UserDto> GetUserAsync(string id)
    {
        if (string.IsNullOrEmpty(id))
        {
            throw new ArgumentNullException("id");
        }

        var url = GetApiUrl("Users/" + id);

        using (var stream = await GetSerializedStreamAsync(url).ConfigureAwait(false))
        {
            return DeserializeFromStream<UserDto>(stream);
        }
    }

    /// <summary>
    /// Gets the parental ratings async.
    /// </summary>
    /// <returns>Task{List{ParentalRating}}.</returns>
    public async Task<List<ParentalRating>> GetParentalRatingsAsync()
    {
        var url = GetApiUrl("Localization/ParentalRatings");

        using (var stream = await GetSerializedStreamAsync(url).ConfigureAwait(false))
        {
            return DeserializeFromStream<List<ParentalRating>>(stream);
        }
    }

    /// <summary>
    /// Gets local trailers for an item
    /// </summary>
    /// <param name="userId">The user id.</param>
    /// <param name="itemId">The item id.</param>
    /// <returns>Task{ItemsResult}.</returns>
    /// <exception cref="System.ArgumentNullException">query</exception>
    public async Task<BaseItemDto[]> GetLocalTrailersAsync(string userId, string itemId)
    {
        if (string.IsNullOrEmpty(userId))
        {
            throw new ArgumentNullException("userId");
        }
        if (string.IsNullOrEmpty(itemId))
        {
            throw new ArgumentNullException("itemId");
        }

        var url = GetApiUrl("Users/" + userId + "/Items/" + itemId + "/LocalTrailers");

        using (var stream = await GetSerializedStreamAsync(url).ConfigureAwait(false))
        {
            return DeserializeFromStream<BaseItemDto[]>(stream);
        }
    }

    /// <summary>
    /// Gets special features for an item
    /// </summary>
    /// <param name="userId">The user id.</param>
    /// <param name="itemId">The item id.</param>
    /// <returns>Task{BaseItemDto[]}.</returns>
    /// <exception cref="System.ArgumentNullException">userId</exception>
    public async Task<BaseItemDto[]> GetSpecialFeaturesAsync(string userId, string itemId)
    {
        if (string.IsNullOrEmpty(userId))
        {
            throw new ArgumentNullException("userId");
        }
        if (string.IsNullOrEmpty(itemId))
        {
            throw new ArgumentNullException("itemId");
        }

        var url = GetApiUrl("Users/" + userId + "/Items/" + itemId + "/SpecialFeatures");

        using (var stream = await GetSerializedStreamAsync(url).ConfigureAwait(false))
        {
            return DeserializeFromStream<BaseItemDto[]>(stream);
        }
    }

    /// <summary>
    /// Gets the cultures async.
    /// </summary>
    /// <returns>Task{CultureDto[]}.</returns>
    public async Task<CultureDto[]> GetCulturesAsync()
    {
        var url = GetApiUrl("Localization/Cultures");

        using (var stream = await GetSerializedStreamAsync(url).ConfigureAwait(false))
        {
            return DeserializeFromStream<CultureDto[]>(stream);
        }
    }

    /// <summary>
    /// Gets the countries async.
    /// </summary>
    /// <returns>Task{CountryInfo[]}.</returns>
    public async Task<CountryInfo[]> GetCountriesAsync()
    {
        var url = GetApiUrl("Localization/Countries");

        using (var stream = await GetSerializedStreamAsync(url).ConfigureAwait(false))
        {
            return DeserializeFromStream<CountryInfo[]>(stream);
        }
    }

    /// <summary>
    /// Gets the game system summaries async.
    /// </summary>
    /// <returns>Task{List{GameSystemSummary}}.</returns>
    public async Task<List<GameSystemSummary>> GetGameSystemSummariesAsync(CancellationToken cancellationToken)
    {
        var url = GetApiUrl("Games/SystemSummaries");

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<List<GameSystemSummary>>(stream);
        }
    }

    public Task<UserItemDataDto> MarkPlayedAsync(string itemId, string userId, DateTime? datePlayed)
    {
        if (string.IsNullOrEmpty(itemId))
        {
            throw new ArgumentNullException("itemId");
        }
        if (string.IsNullOrEmpty(userId))
        {
            throw new ArgumentNullException("userId");
        }

        var dict = new QueryStringDictionary();

        if (datePlayed.HasValue)
        {
            dict.Add("DatePlayed", datePlayed.Value.ToString("yyyyMMddHHmmss"));
        }

        var url = GetApiUrl("Users/" + userId + "/PlayedItems/" + itemId, dict);

        return PostAsync<UserItemDataDto>(url, new Dictionary<string, string>(), CancellationToken.None);
    }

    /// <summary>
    /// Marks the unplayed async.
    /// </summary>
    /// <param name="itemId">The item id.</param>
    /// <param name="userId">The user id.</param>
    /// <returns>Task{UserItemDataDto}.</returns>
    /// <exception cref="System.ArgumentNullException">
    /// itemId
    /// or
    /// userId
    /// </exception>
    public Task<UserItemDataDto> MarkUnplayedAsync(string itemId, string userId)
    {
        if (string.IsNullOrEmpty(itemId))
        {
            throw new ArgumentNullException("itemId");
        }
        if (string.IsNullOrEmpty(userId))
        {
            throw new ArgumentNullException("userId");
        }

        var url = GetApiUrl("Users/" + userId + "/PlayedItems/" + itemId);

        return DeleteAsync<UserItemDataDto>(url, CancellationToken.None);
    }

    /// <summary>
    /// Updates the favorite status async.
    /// </summary>
    /// <param name="itemId">The item id.</param>
    /// <param name="userId">The user id.</param>
    /// <param name="isFavorite">if set to <c>true</c> [is favorite].</param>
    /// <returns>Task.</returns>
    /// <exception cref="System.ArgumentNullException">itemId</exception>
    public Task<UserItemDataDto> UpdateFavoriteStatusAsync(string itemId, string userId, bool isFavorite)
    {
        if (string.IsNullOrEmpty(itemId))
        {
            throw new ArgumentNullException("itemId");
        }
        if (string.IsNullOrEmpty(userId))
        {
            throw new ArgumentNullException("userId");
        }

        var url = GetApiUrl("Users/" + userId + "/FavoriteItems/" + itemId);

        if (isFavorite)
        {
            return PostAsync<UserItemDataDto>(url, new Dictionary<string, string>(), CancellationToken.None);
        }

        return DeleteAsync<UserItemDataDto>(url, CancellationToken.None);
    }

    /// <summary>
    /// Reports to the server that the user has begun playing an item
    /// </summary>
    /// <param name="info">The information.</param>
    /// <returns>Task{UserItemDataDto}.</returns>
    /// <exception cref="System.ArgumentNullException">itemId</exception>
    public Task ReportPlaybackStartAsync(PlaybackStartInfo info)
    {
        if (info == null)
        {
            throw new ArgumentNullException("info");
        }

        Logger.Debug("ReportPlaybackStart: Item {0}", info.ItemId);

        if (WebSocketConnection != null && WebSocketConnection.IsConnected)
        {
            return WebSocketConnection.SendAsync("ReportPlaybackStart", JsonSerializer.SerializeToString(info));
        }

        var url = GetApiUrl("Sessions/Playing");

        return PostAsync<PlaybackStartInfo, EmptyRequestResult>(url, info, CancellationToken.None);
    }

    /// <summary>
    /// Reports playback progress to the server
    /// </summary>
    /// <param name="info">The information.</param>
    /// <returns>Task{UserItemDataDto}.</returns>
    /// <exception cref="System.ArgumentNullException">itemId</exception>
    public Task ReportPlaybackProgressAsync(PlaybackProgressInfo info)
    {
        if (info == null)
        {
            throw new ArgumentNullException("info");
        }

        if (WebSocketConnection != null && WebSocketConnection.IsConnected)
        {
            return WebSocketConnection.SendAsync("ReportPlaybackProgress", JsonSerializer.SerializeToString(info));
        }

        var url = GetApiUrl("Sessions/Playing/Progress");

        return PostAsync<PlaybackProgressInfo, EmptyRequestResult>(url, info, CancellationToken.None);
    }

    /// <summary>
    /// Reports to the server that the user has stopped playing an item
    /// </summary>
    /// <param name="info">The information.</param>
    /// <returns>Task{UserItemDataDto}.</returns>
    /// <exception cref="System.ArgumentNullException">itemId</exception>
    public Task ReportPlaybackStoppedAsync(PlaybackStopInfo info)
    {
        if (info == null)
        {
            throw new ArgumentNullException("info");
        }

        if (WebSocketConnection != null && WebSocketConnection.IsConnected)
        {
            return WebSocketConnection.SendAsync("ReportPlaybackStopped", JsonSerializer.SerializeToString(info));
        }

        var url = GetApiUrl("Sessions/Playing/Stopped");

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
    /// <exception cref="System.ArgumentNullException">sessionId
    /// or
    /// itemId
    /// or
    /// itemName
    /// or
    /// itemType</exception>
    public Task SendBrowseCommandAsync(string sessionId, string itemId, string itemName, string itemType)
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
    /// <exception cref="System.ArgumentNullException">sessionId
    /// or
    /// request</exception>
    public Task SendPlayCommandAsync(string sessionId, PlayRequest request)
    {
        if (string.IsNullOrEmpty(sessionId))
        {
            throw new ArgumentNullException("sessionId");
        }
        if (request == null)
        {
            throw new ArgumentNullException("request");
        }

        var dict = new QueryStringDictionary();
        dict.Add("ItemIds", request.ItemIds);
        dict.AddIfNotNull("StartPositionTicks", request.StartPositionTicks);
        dict.Add("PlayCommand", request.PlayCommand.ToString());

        var url = GetApiUrl("Sessions/" + sessionId + "/Playing", dict);

        return PostAsync<EmptyRequestResult>(url, new Dictionary<string, string>(), CancellationToken.None);
    }

    public Task SendMessageCommandAsync(string sessionId, MessageCommand command)
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
    /// <exception cref="System.ArgumentNullException">sessionId</exception>
    public Task SendCommandAsync(string sessionId, GeneralCommand command)
    {
        if (string.IsNullOrEmpty(sessionId))
        {
            throw new ArgumentNullException("sessionId");
        }

        var url = GetApiUrl("Sessions/" + sessionId + "/Command");

        return PostAsync<GeneralCommand, EmptyRequestResult>(url, command, CancellationToken.None);
    }

    /// <summary>
    /// Sends the playstate command async.
    /// </summary>
    /// <param name="sessionId">The session id.</param>
    /// <param name="request">The request.</param>
    /// <returns>Task.</returns>
    public Task SendPlaystateCommandAsync(string sessionId, PlaystateRequest request)
    {
        var dict = new QueryStringDictionary();
        dict.AddIfNotNull("SeekPositionTicks", request.SeekPositionTicks);

        var url = GetApiUrl("Sessions/" + sessionId + "/Playing/" + request.Command.ToString(), dict);

        return PostAsync<EmptyRequestResult>(url, new Dictionary<string, string>(), CancellationToken.None);
    }

    /// <summary>
    /// Clears a user's rating for an item
    /// </summary>
    /// <param name="itemId">The item id.</param>
    /// <param name="userId">The user id.</param>
    /// <returns>Task{UserItemDataDto}.</returns>
    /// <exception cref="System.ArgumentNullException">itemId</exception>
    public Task<UserItemDataDto> ClearUserItemRatingAsync(string itemId, string userId)
    {
        if (string.IsNullOrEmpty(itemId))
        {
            throw new ArgumentNullException("itemId");
        }

        if (string.IsNullOrEmpty(userId))
        {
            throw new ArgumentNullException("userId");
        }

        var url = GetApiUrl("Users/" + userId + "/Items/" + itemId + "/Rating");

        return DeleteAsync<UserItemDataDto>(url, CancellationToken.None);
    }

    /// <summary>
    /// Updates a user's rating for an item, based on likes or dislikes
    /// </summary>
    /// <param name="itemId">The item id.</param>
    /// <param name="userId">The user id.</param>
    /// <param name="likes">if set to <c>true</c> [likes].</param>
    /// <returns>Task.</returns>
    /// <exception cref="System.ArgumentNullException">itemId</exception>
    public Task<UserItemDataDto> UpdateUserItemRatingAsync(string itemId, string userId, bool likes)
    {
        if (string.IsNullOrEmpty(itemId))
        {
            throw new ArgumentNullException("itemId");
        }

        if (string.IsNullOrEmpty(userId))
        {
            throw new ArgumentNullException("userId");
        }

        var dict = new QueryStringDictionary { };

        dict.Add("likes", likes);

        var url = GetApiUrl("Users/" + userId + "/Items/" + itemId + "/Rating", dict);

        return PostAsync<UserItemDataDto>(url, new Dictionary<string, string>(), CancellationToken.None);
    }

    /// <summary>
    /// Authenticates a user and returns the result
    /// </summary>
    /// <param name="username">The username.</param>
    /// <param name="sha1Hash">The sha1 hash.</param>
    /// <returns>Task.</returns>
    /// <exception cref="System.ArgumentNullException">userId</exception>
    public async Task<AuthenticationResult> AuthenticateUserAsync(string username, byte[] sha1Hash)
    {
        if (string.IsNullOrEmpty(username))
        {
            throw new ArgumentNullException("username");
        }

        var password = BitConverter.ToString(sha1Hash).Replace("-", string.Empty);
        var url = GetApiUrl("Users/AuthenticateByName");

        var args = new Dictionary<string, string>();

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
    /// <exception cref="System.ArgumentNullException">configuration</exception>
    public Task UpdateServerConfigurationAsync(ServerConfiguration configuration)
    {
        if (configuration == null)
        {
            throw new ArgumentNullException("configuration");
        }

        var url = GetApiUrl("System/Configuration");

        return PostAsync<ServerConfiguration, EmptyRequestResult>(url, configuration, CancellationToken.None);
    }*/

/*    /// <summary>
    /// Updates the scheduled task triggers.
    /// </summary>
    /// <param name="id">The id.</param>
    /// <param name="triggers">The triggers.</param>
    /// <returns>Task{RequestResult}.</returns>
    /// <exception cref="System.ArgumentNullException">id</exception>
    public Task UpdateScheduledTaskTriggersAsync(string id, TaskTriggerInfo[] triggers)
    {
        if (string.IsNullOrEmpty(id))
        {
            throw new ArgumentNullException("id");
        }

        if (triggers == null)
        {
            throw new ArgumentNullException("triggers");
        }

        var url = GetApiUrl("ScheduledTasks/" + id + "/Triggers");

        return PostAsync<TaskTriggerInfo[], EmptyRequestResult>(url, triggers, CancellationToken.None);
    }

    /// <summary>
    /// Gets the display preferences.
    /// </summary>
    /// <param name="id">The id.</param>
    /// <param name="userId">The user id.</param>
    /// <param name="client">The client.</param>
    /// <returns>Task{BaseItemDto}.</returns>
    public async Task<DisplayPreferences> GetDisplayPreferencesAsync(string id, string userId, string client, CancellationToken cancellationToken)
    {
        var dict = new QueryStringDictionary();

        dict.Add("userId", userId);
        dict.Add("client", client);

        var url = GetApiUrl("DisplayPreferences/" + id, dict);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<DisplayPreferences>(stream);
        }
    }

    /// <summary>
    /// Updates display preferences for a user
    /// </summary>
    /// <param name="displayPreferences">The display preferences.</param>
    /// <returns>Task{DisplayPreferences}.</returns>
    /// <exception cref="System.ArgumentNullException">userId</exception>
    public Task UpdateDisplayPreferencesAsync(DisplayPreferences displayPreferences, string userId, string client, CancellationToken cancellationToken)
    {
        if (displayPreferences == null)
        {
            throw new ArgumentNullException("displayPreferences");
        }

        var dict = new QueryStringDictionary();

        dict.Add("userId", userId);
        dict.Add("client", client);

        var url = GetApiUrl("DisplayPreferences/" + displayPreferences.Id, dict);

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
    public async Task<T> PostAsync<T>(string url, Dictionary<string, string> args, CancellationToken cancellationToken)
    where T : class
    {
        url = AddDataFormat(url);

        // Create the post body
        var strings = args.Keys.Select(key => string.Format("{0}={1}", key, args[key]));
        var postContent = string.Join("&", strings.ToArray());

        const string contentType = "application/x-www-form-urlencoded";

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
    private async Task<T> DeleteAsync<T>(string url, CancellationToken cancellationToken)
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
    private async Task<TOutputType> PostAsync<TInputType, TOutputType>(string url, TInputType obj, CancellationToken cancellationToken)
    where TOutputType : class
    {
        url = AddDataFormat(url);

        const string contentType = "application/json";

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
    public Task<Stream> GetSerializedStreamAsync(string url, CancellationToken cancellationToken)
    {
        url = AddDataFormat(url);

        return HttpClient.GetAsync(url, cancellationToken);
    }

    public Task<Stream> GetSerializedStreamAsync(string url)
    {
        return GetSerializedStreamAsync(url, CancellationToken.None);
    }

    public async Task<NotificationsSummary> GetNotificationsSummary(string userId)
    {
        var url = GetApiUrl("Notifications/" + userId + "/Summary");

        using (var stream = await GetSerializedStreamAsync(url).ConfigureAwait(false))
        {
            return DeserializeFromStream<NotificationsSummary>(stream);
        }
    }

    public Task MarkNotificationsRead(string userId, IEnumerable<string> notificationIdList, bool isRead)
    {
        var url = "Notifications/" + userId;

        url += isRead ? "/Read" : "/Unread";

        var dict = new QueryStringDictionary();

        var ids = notificationIdList.ToArray();

        dict.Add("Ids", string.Join(",", ids));

        url = GetApiUrl(url, dict);

        return PostAsync<EmptyRequestResult>(url, new Dictionary<string, string>(), CancellationToken.None);
    }

    public async Task<NotificationResult> GetNotificationsAsync(NotificationQuery query)
    {
        var url = "Notifications/" + query.UserId;

        var dict = new QueryStringDictionary();
        dict.AddIfNotNull("ItemIds", query.IsRead);
        dict.AddIfNotNull("StartIndex", query.StartIndex);
        dict.AddIfNotNull("Limit", query.Limit);

        url = GetApiUrl(url, dict);

        using (var stream = await GetSerializedStreamAsync(url).ConfigureAwait(false))
        {
            return DeserializeFromStream<NotificationResult>(stream);
        }
    }

    public async Task<AllThemeMediaResult> GetAllThemeMediaAsync(string userId, string itemId, bool inheritFromParent, CancellationToken cancellationToken)
    {
        var queryString = new QueryStringDictionary();

        queryString.Add("InheritFromParent", inheritFromParent);
        queryString.AddIfNotNullOrEmpty("UserId", userId);

        var url = GetApiUrl("Items/" + itemId + "/ThemeMedia", queryString);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<AllThemeMediaResult>(stream);
        }
    }

    public async Task<SearchHintResult> GetSearchHintsAsync(SearchQuery query)
    {
        if (query == null || string.IsNullOrEmpty(query.SearchTerm))
        {
            throw new ArgumentNullException("query");
        }

        var queryString = new QueryStringDictionary();

        queryString.AddIfNotNullOrEmpty("SearchTerm", query.SearchTerm);
        queryString.AddIfNotNullOrEmpty("UserId", query.UserId);
        queryString.AddIfNotNull("StartIndex", query.StartIndex);
        queryString.AddIfNotNull("Limit", query.Limit);

        queryString.Add("IncludeArtists", query.IncludeArtists);
        queryString.Add("IncludeGenres", query.IncludeGenres);
        queryString.Add("IncludeMedia", query.IncludeMedia);
        queryString.Add("IncludePeople", query.IncludePeople);
        queryString.Add("IncludeStudios", query.IncludeStudios);

        var url = GetApiUrl("Search/Hints", queryString);

        using (var stream = await GetSerializedStreamAsync(url).ConfigureAwait(false))
        {
            return DeserializeFromStream<SearchHintResult>(stream);
        }
    }

    public async Task<ThemeMediaResult> GetThemeSongsAsync(string userId, string itemId, bool inheritFromParent, CancellationToken cancellationToken)
    {
        var queryString = new QueryStringDictionary();

        queryString.Add("InheritFromParent", inheritFromParent);
        queryString.AddIfNotNullOrEmpty("UserId", userId);

        var url = GetApiUrl("Items/" + itemId + "/ThemeSongs", queryString);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<ThemeMediaResult>(stream);
        }
    }

    public async Task<ThemeMediaResult> GetThemeVideosAsync(string userId, string itemId, bool inheritFromParent, CancellationToken cancellationToken)
    {
        var queryString = new QueryStringDictionary();

        queryString.Add("InheritFromParent", inheritFromParent);
        queryString.AddIfNotNullOrEmpty("UserId", userId);

        var url = GetApiUrl("Items/" + itemId + "/ThemeVideos", queryString);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<ThemeMediaResult>(stream);
        }
    }

    /// <summary>
    /// Gets the critic reviews.
    /// </summary>
    /// <param name="itemId">The item id.</param>
    /// <param name="startIndex">The start index.</param>
    /// <param name="limit">The limit.</param>
    /// <returns>Task{ItemReviewsResult}.</returns>
    /// <exception cref="System.ArgumentNullException">
    /// id
    /// or
    /// userId
    /// </exception>
    public async Task<QueryResult<ItemReview>> GetCriticReviews(string itemId, CancellationToken cancellationToken, int? startIndex = null, int? limit = null)
    {
        if (string.IsNullOrEmpty(itemId))
        {
            throw new ArgumentNullException("itemId");
        }

        var queryString = new QueryStringDictionary();

        queryString.AddIfNotNull("startIndex", startIndex);
        queryString.AddIfNotNull("limit", limit);

        var url = GetApiUrl("Items/" + itemId + "/CriticReviews", queryString);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<QueryResult<ItemReview>>(stream);
        }
    }

    public async Task<T> GetAsync<T>(string url, CancellationToken cancellationToken)
    where T : class
    {
        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<T>(stream);
        }
    }

    /// <summary>
    /// Gets the index of the game player.
    /// </summary>
    /// <param name="userId">The user id.</param>
    /// <param name="cancellationToken">The cancellation token.</param>
    /// <returns>Task{List{ItemIndex}}.</returns>
    public async Task<List<ItemIndex>> GetGamePlayerIndex(string userId, CancellationToken cancellationToken)
    {
        var queryString = new QueryStringDictionary();

        queryString.AddIfNotNullOrEmpty("UserId", userId);

        var url = GetApiUrl("Games/PlayerIndex", queryString);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<List<ItemIndex>>(stream);
        }
    }

    /// <summary>
    /// Gets the index of the year.
    /// </summary>
    /// <param name="userId">The user id.</param>
    /// <param name="includeItemTypes">The include item types.</param>
    /// <param name="cancellationToken">The cancellation token.</param>
    /// <returns>Task{List{ItemIndex}}.</returns>
    public async Task<List<ItemIndex>> GetYearIndex(string userId, string[] includeItemTypes, CancellationToken cancellationToken)
    {
        var queryString = new QueryStringDictionary();

        queryString.AddIfNotNullOrEmpty("UserId", userId);
        queryString.AddIfNotNull("IncludeItemTypes", includeItemTypes);

        var url = GetApiUrl("Items/YearIndex", queryString);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<List<ItemIndex>>(stream);
        }
    }

    public Task ReportCapabilities(ClientCapabilities capabilities, CancellationToken cancellationToken)
    {
        if (capabilities == null)
        {
            throw new ArgumentNullException("capabilities");
        }

        var dict = new QueryStringDictionary();
        dict.AddIfNotNull("PlayableMediaTypes", capabilities.PlayableMediaTypes);
        dict.AddIfNotNull("SupportedCommands", capabilities.SupportedCommands);

        var url = GetApiUrl("Sessions/Capabilities", dict);

        return PostAsync<EmptyRequestResult>(url, dict, cancellationToken);
    }

    public async Task<LiveTvInfo> GetLiveTvInfoAsync(CancellationToken cancellationToken)
    {
        var url = GetApiUrl("LiveTv/Info");

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<LiveTvInfo>(stream);
        }
    }

    public async Task<QueryResult<RecordingGroupDto>> GetLiveTvRecordingGroupsAsync(RecordingGroupQuery query, CancellationToken cancellationToken)
    {
        if (query == null)
        {
            throw new ArgumentNullException("query");
        }

        var dict = new QueryStringDictionary { };

        dict.AddIfNotNullOrEmpty("UserId", query.UserId);

        var url = GetApiUrl("LiveTv/Recordings/Groups", dict);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<QueryResult<RecordingGroupDto>>(stream);
        }
    }

    public async Task<QueryResult<RecordingInfoDto>> GetLiveTvRecordingsAsync(RecordingQuery query, CancellationToken cancellationToken)
    {
        if (query == null)
        {
            throw new ArgumentNullException("query");
        }

        var dict = new QueryStringDictionary { };

        dict.AddIfNotNullOrEmpty("UserId", query.UserId);
        dict.AddIfNotNullOrEmpty("ChannelId", query.ChannelId);
        dict.AddIfNotNullOrEmpty("GroupId", query.GroupId);
        dict.AddIfNotNullOrEmpty("Id", query.Id);
        dict.AddIfNotNullOrEmpty("SeriesTimerId", query.SeriesTimerId);
        dict.AddIfNotNull("IsInProgress", query.IsInProgress);
        dict.AddIfNotNull("StartIndex", query.StartIndex);
        dict.AddIfNotNull("Limit", query.Limit);

        var url = GetApiUrl("LiveTv/Recordings", dict);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<QueryResult<RecordingInfoDto>>(stream);
        }
    }

    public async Task<QueryResult<ChannelInfoDto>> GetLiveTvChannelsAsync(LiveTvChannelQuery query, CancellationToken cancellationToken)
    {
        if (query == null)
        {
            throw new ArgumentNullException("query");
        }

        var dict = new QueryStringDictionary { };

        dict.AddIfNotNullOrEmpty("UserId", query.UserId);
        dict.AddIfNotNull("StartIndex", query.StartIndex);
        dict.AddIfNotNull("Limit", query.Limit);

        if (query.ChannelType.HasValue)
        {
            dict.Add("ChannelType", query.ChannelType.Value.ToString());
        }

        var url = GetApiUrl("LiveTv/Channels", dict);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<QueryResult<ChannelInfoDto>>(stream);
        }
    }

    public Task CancelLiveTvSeriesTimerAsync(string id, CancellationToken cancellationToken)
    {
        if (string.IsNullOrEmpty(id))
        {
            throw new ArgumentNullException("id");
        }

        var dict = new QueryStringDictionary { };

        var url = GetApiUrl("LiveTv/SeriesTimers/" + id, dict);

        return HttpClient.DeleteAsync(url, cancellationToken);
    }

    public Task CancelLiveTvTimerAsync(string id, CancellationToken cancellationToken)
    {
        if (string.IsNullOrEmpty(id))
        {
            throw new ArgumentNullException("id");
        }

        var dict = new QueryStringDictionary { };

        var url = GetApiUrl("LiveTv/Timers/" + id, dict);

        return HttpClient.DeleteAsync(url, cancellationToken);
    }

    public Task DeleteLiveTvRecordingAsync(string id, CancellationToken cancellationToken)
    {
        if (string.IsNullOrEmpty(id))
        {
            throw new ArgumentNullException("id");
        }

        var dict = new QueryStringDictionary { };

        var url = GetApiUrl("LiveTv/Recordings/" + id, dict);

        return HttpClient.DeleteAsync(url, cancellationToken);
    }*/

/*    public async Task<ChannelInfoDto> GetLiveTvChannelAsync(string id, string userId, CancellationToken cancellationToken)
    {
        if (string.IsNullOrEmpty(id))
        {
            throw new ArgumentNullException("id");
        }

        var dict = new QueryStringDictionary { };
        dict.AddIfNotNullOrEmpty("userId", userId);

        var url = GetApiUrl("LiveTv/Channels/" + id, dict);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<ChannelInfoDto>(stream);
        }
    }

    public async Task<RecordingInfoDto> GetLiveTvRecordingAsync(string id, string userId, CancellationToken cancellationToken)
    {
        if (string.IsNullOrEmpty(id))
        {
            throw new ArgumentNullException("id");
        }

        var dict = new QueryStringDictionary { };
        dict.AddIfNotNullOrEmpty("userId", userId);

        var url = GetApiUrl("LiveTv/Recordings/" + id, dict);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<RecordingInfoDto>(stream);
        }
    }

    public async Task<RecordingGroupDto> GetLiveTvRecordingGroupAsync(string id, string userId, CancellationToken cancellationToken)
    {
        if (string.IsNullOrEmpty(id))
        {
            throw new ArgumentNullException("id");
        }

        var dict = new QueryStringDictionary { };
        dict.AddIfNotNullOrEmpty("userId", userId);

        var url = GetApiUrl("LiveTv/Recordings/Groups/" + id, dict);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<RecordingGroupDto>(stream);
        }
    }

    public async Task<SeriesTimerInfoDto> GetLiveTvSeriesTimerAsync(string id, CancellationToken cancellationToken)
    {
        if (string.IsNullOrEmpty(id))
        {
            throw new ArgumentNullException("id");
        }

        var dict = new QueryStringDictionary { };

        var url = GetApiUrl("LiveTv/SeriesTimers/" + id, dict);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<SeriesTimerInfoDto>(stream);
        }
    }

    public async Task<QueryResult<SeriesTimerInfoDto>> GetLiveTvSeriesTimersAsync(SeriesTimerQuery query, CancellationToken cancellationToken)
    {
        if (query == null)
        {
            throw new ArgumentNullException("query");
        }

        var dict = new QueryStringDictionary { };

        dict.AddIfNotNullOrEmpty("SortBy", query.SortBy);
        dict.Add("SortOrder", query.SortOrder.ToString());

        var url = GetApiUrl("LiveTv/SeriesTimers", dict);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<QueryResult<SeriesTimerInfoDto>>(stream);
        }
    }

    public async Task<TimerInfoDto> GetLiveTvTimerAsync(string id, CancellationToken cancellationToken)
    {
        if (string.IsNullOrEmpty(id))
        {
            throw new ArgumentNullException("id");
        }

        var dict = new QueryStringDictionary { };

        var url = GetApiUrl("LiveTv/Timers/" + id, dict);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<TimerInfoDto>(stream);
        }
    }

    public async Task<QueryResult<TimerInfoDto>> GetLiveTvTimersAsync(TimerQuery query, CancellationToken cancellationToken)
    {
        if (query == null)
        {
            throw new ArgumentNullException("query");
        }

        var dict = new QueryStringDictionary { };

        dict.AddIfNotNullOrEmpty("ChannelId", query.ChannelId);
        dict.AddIfNotNullOrEmpty("SeriesTimerId", query.SeriesTimerId);

        var url = GetApiUrl("LiveTv/Timers", dict);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<QueryResult<TimerInfoDto>>(stream);
        }
    }

    public async Task<QueryResult<ProgramInfoDto>> GetLiveTvProgramsAsync(ProgramQuery query, CancellationToken cancellationToken)
    {
        if (query == null)
        {
            throw new ArgumentNullException("query");
        }

        var dict = new QueryStringDictionary { };

        const string isoDateFormat = "o";

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
            dict.Add("ChannelIds", string.Join(",", query.ChannelIdList));
        }

        // TODO: This endpoint supports POST if the query string is too long
        var url = GetApiUrl("LiveTv/Programs", dict);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<QueryResult<ProgramInfoDto>>(stream);
        }
    }

    public async Task<QueryResult<ProgramInfoDto>> GetRecommendedLiveTvProgramsAsync(RecommendedProgramQuery query, CancellationToken cancellationToken)
    {
        if (query == null)
        {
            throw new ArgumentNullException("query");
        }

        var dict = new QueryStringDictionary { };

        dict.AddIfNotNullOrEmpty("UserId", query.UserId);
        dict.AddIfNotNull("Limit", query.Limit);
        dict.AddIfNotNull("HasAired", query.HasAired);
        dict.AddIfNotNull("IsAiring", query.IsAiring);

        var url = GetApiUrl("LiveTv/Programs/Recommended", dict);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<QueryResult<ProgramInfoDto>>(stream);
        }
    }

    public Task CreateLiveTvSeriesTimerAsync(SeriesTimerInfoDto timer, CancellationToken cancellationToken)
    {
        if (timer == null)
        {
            throw new ArgumentNullException("timer");
        }

        var url = GetApiUrl("LiveTv/SeriesTimers");

        return PostAsync<SeriesTimerInfoDto, EmptyRequestResult>(url, timer, cancellationToken);
    }

    public Task CreateLiveTvTimerAsync(BaseTimerInfoDto timer, CancellationToken cancellationToken)
    {
        if (timer == null)
        {
            throw new ArgumentNullException("timer");
        }

        var url = GetApiUrl("LiveTv/Timers");

        return PostAsync<BaseTimerInfoDto, EmptyRequestResult>(url, timer, cancellationToken);
    }

    public async Task<SeriesTimerInfoDto> GetDefaultLiveTvTimerInfo(string programId, CancellationToken cancellationToken)
    {
        if (string.IsNullOrWhiteSpace(programId))
        {
            throw new ArgumentNullException("programId");
        }

        var dict = new QueryStringDictionary { };

        dict.AddIfNotNullOrEmpty("programId", programId);

        var url = GetApiUrl("LiveTv/Timers/Defaults", dict);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<SeriesTimerInfoDto>(stream);
        }
    }

    public async Task<SeriesTimerInfoDto> GetDefaultLiveTvTimerInfo(CancellationToken cancellationToken)
    {
        var url = GetApiUrl("LiveTv/Timers/Defaults");

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<SeriesTimerInfoDto>(stream);
        }
    }

    public async Task<GuideInfo> GetLiveTvGuideInfo(CancellationToken cancellationToken)
    {
        var url = GetApiUrl("LiveTv/GuideInfo");

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<GuideInfo>(stream);
        }
    }

    public async Task<ProgramInfoDto> GetLiveTvProgramAsync(string id, string userId, CancellationToken cancellationToken)
    {
        if (string.IsNullOrEmpty(id))
        {
            throw new ArgumentNullException("id");
        }

        var dict = new QueryStringDictionary { };
        dict.AddIfNotNullOrEmpty("userId", userId);

        var url = GetApiUrl("LiveTv/Programs/" + id, dict);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<ProgramInfoDto>(stream);
        }
    }

    public Task UpdateLiveTvSeriesTimerAsync(SeriesTimerInfoDto timer, CancellationToken cancellationToken)
    {
        if (timer == null)
        {
            throw new ArgumentNullException("timer");
        }

        var url = GetApiUrl("LiveTv/SeriesTimers/" + timer.Id);

        return PostAsync<SeriesTimerInfoDto, EmptyRequestResult>(url, timer, cancellationToken);
    }

    public Task UpdateLiveTvTimerAsync(TimerInfoDto timer, CancellationToken cancellationToken)
    {
        if (timer == null)
        {
            throw new ArgumentNullException("timer");
        }

        var url = GetApiUrl("LiveTv/Timers/" + timer.Id);

        return PostAsync<TimerInfoDto, EmptyRequestResult>(url, timer, cancellationToken);
    }

    public Task SendString(string sessionId, string text)
    {
        var cmd = new GeneralCommand
        {
            Name = "SendString"
        };

        cmd.Arguments["String"] = text;

        return SendCommandAsync(sessionId, cmd);
    }

    public Task SetAudioStreamIndex(string sessionId, int index)
    {
        var cmd = new GeneralCommand
        {
            Name = "SetAudioStreamIndex"
        };

        cmd.Arguments["Index"] = index.ToString(CultureInfo.InvariantCulture);

        return SendCommandAsync(sessionId, cmd);
    }

    public Task SetSubtitleStreamIndex(string sessionId, int? index)
    {
        var cmd = new GeneralCommand
        {
            Name = "SetSubtitleStreamIndex"
        };

        cmd.Arguments["Index"] = (index ?? -1).ToString(CultureInfo.InvariantCulture);

        return SendCommandAsync(sessionId, cmd);
    }

    public Task SetVolume(string sessionId, int volume)
    {
        var cmd = new GeneralCommand
        {
            Name = "SetVolume"
        };

        cmd.Arguments["Volume"] = volume.ToString(CultureInfo.InvariantCulture);

        return SendCommandAsync(sessionId, cmd);
    }

    public async Task<ItemsResult> GetAdditionalParts(string itemId, string userId)
    {
        var queryString = new QueryStringDictionary();

        queryString.AddIfNotNullOrEmpty("UserId", userId);

        var url = GetApiUrl("Videos/" + itemId + "/AdditionalParts", queryString);

        using (var stream = await GetSerializedStreamAsync(url, CancellationToken.None).ConfigureAwait(false))
        {
            return DeserializeFromStream<ItemsResult>(stream);
        }
    }

    public async Task<ChannelFeatures> GetChannelFeatures(string channelId, CancellationToken cancellationToken)
    {
        var url = GetApiUrl("Channels/" + channelId + "/Features");

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<ChannelFeatures>(stream);
        }
    }

    public async Task<QueryResult<BaseItemDto>> GetChannelItems(ChannelItemQuery query, CancellationToken cancellationToken)
    {
        var queryString = new QueryStringDictionary();

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

        var url = GetApiUrl("Channels/" + query.ChannelId + "/Items", queryString);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<QueryResult<BaseItemDto>>(stream);
        }
    }

    public async Task<QueryResult<BaseItemDto>> GetChannels(ChannelQuery query, CancellationToken cancellationToken)
    {
        var queryString = new QueryStringDictionary();

        queryString.AddIfNotNullOrEmpty("UserId", query.UserId);
        queryString.AddIfNotNull("SupportsLatestItems", query.SupportsLatestItems);
        queryString.AddIfNotNull("StartIndex", query.StartIndex);
        queryString.AddIfNotNull("Limit", query.Limit);
        queryString.AddIfNotNull("IsFavorite", query.IsFavorite);

        var url = GetApiUrl("Channels", queryString);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<QueryResult<BaseItemDto>>(stream);
        }
    }

    public async Task<SessionInfoDto> GetCurrentSessionAsync(CancellationToken cancellationToken)
    {
        var queryString = new QueryStringDictionary();

        queryString.Add("DeviceId", DeviceId);
        var url = GetApiUrl("Sessions", queryString);

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            var sessions = DeserializeFromStream<SessionInfoDto[]>(stream);

            return sessions.FirstOrDefault();
        }
    }

    public Task StopTranscodingProcesses(string deviceId)
    {
        var queryString = new QueryStringDictionary();

        queryString.Add("DeviceId", DeviceId);
        var url = GetApiUrl("Videos/ActiveEncodings", queryString);

        return HttpClient.DeleteAsync(url, CancellationToken.None);
    }

    public Task<QueryResult<BaseItemDto>> GetLatestChannelItems(AllChannelMediaQuery query, CancellationToken cancellationToken)
    {
        throw new NotImplementedException();
    }

    public async Task Logout()
    {
        try
        {
            var url = GetApiUrl("Sessions/Logout");

            await PostAsync<EmptyRequestResult>(url, new Dictionary<string, string>(), CancellationToken.None);
        }
        catch (Exception ex)
        {
            Logger.ErrorException("Error logging out", ex);
        }

        ClearAuthenticationInfo();
    }

    public async Task<ItemsResult> GetUserViews(string userId, CancellationToken cancellationToken)
    {
        if (string.IsNullOrEmpty(userId))
        {
            throw new ArgumentNullException("userId");
        }

        var url = GetApiUrl("Users/" + userId + "/Views");

        using (var stream = await GetSerializedStreamAsync(url, cancellationToken).ConfigureAwait(false))
        {
            return DeserializeFromStream<ItemsResult>(stream);
        }
    }*/
}
