package MediaBrowser.ApiInteraction.Http;

import MediaBrowser.ApiInteraction.Response;

public interface IAsyncHttpClient {

    public void Send(HttpRequest request, Response<String> response);
}
