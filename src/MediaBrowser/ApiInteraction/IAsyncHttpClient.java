package MediaBrowser.ApiInteraction;

import com.android.volley.toolbox.ImageLoader;

public interface IAsyncHttpClient {

    public void Send(HttpRequest request, Response<String> response);

    public ImageLoader getImageLoader();
}
