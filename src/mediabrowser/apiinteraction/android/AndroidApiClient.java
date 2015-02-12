package mediabrowser.apiinteraction.android;

import mediabrowser.apiinteraction.ApiClient;
import mediabrowser.apiinteraction.ApiEventListener;
import mediabrowser.apiinteraction.device.IDevice;
import mediabrowser.apiinteraction.http.IAsyncHttpClient;
import mediabrowser.apiinteraction.tasks.CancellationToken;
import mediabrowser.apiinteraction.tasks.IProgress;
import mediabrowser.model.devices.LocalFileInfo;
import mediabrowser.model.logging.ILogger;
import mediabrowser.model.serialization.IJsonSerializer;
import mediabrowser.model.session.ClientCapabilities;
import com.android.volley.toolbox.ImageLoader;

import java.io.FileInputStream;
import java.io.IOException;

public class AndroidApiClient extends ApiClient {

    public AndroidApiClient(IAsyncHttpClient httpClient, IJsonSerializer jsonSerializer, ILogger logger, String serverAddress, String accessToken, ApiEventListener apiEventListener, ClientCapabilities capabilities) {
        super(httpClient, jsonSerializer, logger, serverAddress, accessToken, apiEventListener);
    }

    public AndroidApiClient(IAsyncHttpClient httpClient, IJsonSerializer jsonSerializer, ILogger logger, String serverAddress, String clientName, IDevice device, String applicationVersion, ApiEventListener apiEventListener, ClientCapabilities capabilities) {
        super(httpClient, jsonSerializer, logger, serverAddress, clientName, device, applicationVersion, apiEventListener);
    }

    private VolleyHttpClient getAndroidHttpClient(){
        return (VolleyHttpClient)httpClient;
    }

    public ImageLoader getImageLoader() {

        return getAndroidHttpClient().getImageLoader();
    }

    @Override
    public void UploadFile(FileInputStream fileInputStream,
                           LocalFileInfo file,
                           IProgress<Double> progress,
                           CancellationToken cancellationToken) throws IOException {

        Thread thread = new Thread(new UploadFileRunnable(this, fileInputStream, file, progress, cancellationToken));

        thread.start();
    }

    void PerformUploadFile(FileInputStream fileInputStream,
                            LocalFileInfo file,
                            IProgress<Double> progress,
                            CancellationToken cancellationToken) throws IOException {

        UploadFileInternal(fileInputStream, file, progress, cancellationToken);
    }
}
