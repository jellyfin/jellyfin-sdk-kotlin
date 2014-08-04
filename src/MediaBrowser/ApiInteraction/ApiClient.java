package MediaBrowser.ApiInteraction;

import MediaBrowser.Model.Dto.BaseItemDto;
import MediaBrowser.Model.Dto.ItemCounts;
import MediaBrowser.Model.Logging.ILogger;
import MediaBrowser.Model.Querying.ItemCountsQuery;
import MediaBrowser.Model.Querying.ItemsResult;
import MediaBrowser.Model.Querying.QueryResult;
import MediaBrowser.Model.Serialization.IJsonSerializer;

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
}
