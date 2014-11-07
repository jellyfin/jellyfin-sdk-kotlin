package mediabrowser.apiinteraction.http;

import mediabrowser.apiinteraction.Response;

public interface IAsyncHttpClient {

    public void Send(HttpRequest request, Response<String> response);
}
