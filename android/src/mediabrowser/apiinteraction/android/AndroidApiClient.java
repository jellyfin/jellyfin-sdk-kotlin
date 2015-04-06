package mediabrowser.apiinteraction.android;

import android.os.AsyncTask;
import mediabrowser.apiinteraction.ApiClient;
import mediabrowser.apiinteraction.ApiEventListener;
import mediabrowser.apiinteraction.Response;
import mediabrowser.apiinteraction.device.IDevice;
import mediabrowser.apiinteraction.http.IAsyncHttpClient;
import mediabrowser.apiinteraction.tasks.CancellationToken;
import mediabrowser.apiinteraction.tasks.IProgress;
import mediabrowser.model.devices.LocalFileInfo;
import mediabrowser.model.logging.ILogger;
import mediabrowser.model.serialization.IJsonSerializer;
import mediabrowser.model.session.ClientCapabilities;
import com.android.volley.toolbox.ImageLoader;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class AndroidApiClient extends ApiClient {

    public AndroidApiClient(IAsyncHttpClient httpClient, IJsonSerializer jsonSerializer, ILogger logger, String serverAddress, String accessToken, ApiEventListener apiEventListener, ClientCapabilities capabilities) {
        super(httpClient, jsonSerializer, logger, serverAddress, accessToken, apiEventListener);
    }

    public AndroidApiClient(IAsyncHttpClient httpClient, IJsonSerializer jsonSerializer, ILogger logger, String serverAddress, String clientName, IDevice device, String applicationVersion, ApiEventListener apiEventListener, ClientCapabilities capabilities) {
        super(httpClient, jsonSerializer, logger, serverAddress, clientName, applicationVersion, device, apiEventListener);
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

    @Override
    public void getResponseStream(final String address, final Response<InputStream> response){

        Logger.Debug("Getting response stream from " + address);

        new AsyncTask(){

            @Override
            protected Object doInBackground(Object[] params) {
                try
                {
                    URL url = new URL(address);

                    response.onResponse(new BufferedInputStream(url.openStream()));

                }
                catch (Exception ex)
                {
                    response.onError(ex);
                }

                return null;
            }
        };
    }


}
