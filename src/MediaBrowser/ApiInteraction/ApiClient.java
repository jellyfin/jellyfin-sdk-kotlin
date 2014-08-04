package MediaBrowser.ApiInteraction;

import MediaBrowser.Model.Dto.BaseItemDto;
import MediaBrowser.Model.Logging.ILogger;
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
                response.onResponse((BaseItemDto)DeserializeFromString(jsonResponse, BaseItemDto.class));
            }
        };

        _httpClient.GetAsync(url, jsonResponse);
    }
}
