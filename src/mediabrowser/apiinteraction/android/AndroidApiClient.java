package mediabrowser.apiinteraction.android;

import mediabrowser.apiinteraction.ApiClient;
import mediabrowser.apiinteraction.ApiEventListener;
import mediabrowser.apiinteraction.device.IDevice;
import mediabrowser.apiinteraction.http.IAsyncHttpClient;
import mediabrowser.model.logging.ILogger;
import mediabrowser.model.serialization.IJsonSerializer;
import mediabrowser.model.session.ClientCapabilities;
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
