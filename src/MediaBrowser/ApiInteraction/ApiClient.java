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
}
