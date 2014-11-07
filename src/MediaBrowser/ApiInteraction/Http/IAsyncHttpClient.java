package MediaBrowser.apiinteraction.http;

import MediaBrowser.apiinteraction.Response;

public interface IAsyncHttpClient {

    public void Send(HttpRequest request, Response<String> response);
}
