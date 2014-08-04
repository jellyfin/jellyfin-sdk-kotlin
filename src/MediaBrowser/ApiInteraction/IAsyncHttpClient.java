package MediaBrowser.ApiInteraction;

public interface IAsyncHttpClient {

    public void SetAuthorizationHeader(String scheme, String paraneter);

    public void SetHttpRequestHeader(String name, String value);

    public void ClearHttpRequestHeader(String name);

    public void GetAsync(String url);

    public void DeleteAsync(String url);

    public void PostAsync(String url, String contentType, String postContent);
}
