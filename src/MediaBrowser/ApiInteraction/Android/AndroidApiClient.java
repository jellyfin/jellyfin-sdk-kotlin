package MediaBrowser.apiinteraction.android;

import MediaBrowser.apiinteraction.ApiClient;
import MediaBrowser.apiinteraction.ApiEventListener;
import MediaBrowser.apiinteraction.device.IDevice;
import MediaBrowser.apiinteraction.http.IAsyncHttpClient;
import MediaBrowser.Model.Logging.ILogger;
import MediaBrowser.Model.Serialization.IJsonSerializer;
import MediaBrowser.Model.Session.ClientCapabilities;
import com.android.volley.toolbox.ImageLoader;

public class AndroidApiClient extends ApiClient {

    public AndroidApiClient(IAsyncHttpClient httpClient, IJsonSerializer jsonSerializer, ILogger logger, String serverAddress, String accessToken, ApiEventListener apiEventListener, ClientCapabilities capabilities) {
        super(httpClient, jsonSerializer, logger, serverAddress, accessToken, apiEventListener, capabilities);
    }

    public AndroidApiClient(IAsyncHttpClient httpClient, IJsonSerializer jsonSerializer, ILogger logger, String serverAddress, String clientName, IDevice device, String applicationVersion, ApiEventListener apiEventListener, ClientCapabilities capabilities) {
        super(httpClient, jsonSerializer, logger, serverAddress, clientName, device, applicationVersion, apiEventListener, capabilities);
    }

    private VolleyHttpClient getAndroidHttpClient(){
        return (VolleyHttpClient)httpClient;
    }

    public ImageLoader getImageLoader() {

        return getAndroidHttpClient().getImageLoader();
    }
}
