package MediaBrowser.ApiInteraction;

public interface IAsyncHttpClient {

    public void SetAuthorizationHeader(String scheme, String parameter);

    public void SetHttpRequestHeader(String name, String value);

    public void ClearHttpRequestHeader(String name);

    public void GetAsync(String url, Response<String> response);

    public void DeleteAsync(String url, Response<String> response);

    public void PostAsync(String url, String contentType, String postContent, Response<String> response);
}
