package org.jellyfin.apiclient.interaction;

import org.jellyfin.apiclient.interaction.device.IDevice;
import org.jellyfin.apiclient.interaction.http.IAsyncHttpClient;
import org.jellyfin.apiclient.interaction.tasks.CancellationToken;
import org.jellyfin.apiclient.interaction.tasks.IProgress;
import org.jellyfin.apiclient.model.devices.LocalFileInfo;
import org.jellyfin.apiclient.model.logging.ILogger;
import org.jellyfin.apiclient.model.serialization.IJsonSerializer;

import java.io.FileInputStream;
import java.io.IOException;

public class AndroidApiClient extends ApiClient {

    public AndroidApiClient(IAsyncHttpClient httpClient, IJsonSerializer jsonSerializer, ILogger logger, String serverAddress, String accessToken, ApiEventListener apiEventListener) {
        super(httpClient, jsonSerializer, logger, serverAddress, accessToken, apiEventListener);
    }

    public AndroidApiClient(IAsyncHttpClient httpClient, IJsonSerializer jsonSerializer, ILogger logger, String serverAddress, String clientName, IDevice device, String applicationVersion, ApiEventListener apiEventListener) {
        super(httpClient, jsonSerializer, logger, serverAddress, clientName, applicationVersion, device, apiEventListener);
    }

    private VolleyHttpClient getAndroidHttpClient() {
        return (VolleyHttpClient)httpClient;
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
    public void getResponseStream(final String address, final Response<ResponseStreamInfo> response) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                getResponseStreamInternal(address, response);
            }
        });

        thread.start();
    }

    @Override
    protected void detectBitrate(final long downloadBytes, final Response<Long> response) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                detectBitrateInternal(downloadBytes, response);
            }
        });

        thread.start();
    }
}
