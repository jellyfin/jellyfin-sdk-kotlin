package MediaBrowser.ApiInteraction;

public interface IAsyncHttpClient {

    public void Send(HttpRequest request, Response<String> response);
}
