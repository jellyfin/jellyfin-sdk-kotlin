package mediabrowser.apiinteraction.android.sync;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import mediabrowser.apiinteraction.ApiClient;
import mediabrowser.apiinteraction.ApiEventListener;
import mediabrowser.apiinteraction.ConsoleLogger;
import mediabrowser.apiinteraction.android.AndroidApiClient;
import mediabrowser.apiinteraction.android.GsonJsonSerializer;
import mediabrowser.apiinteraction.android.VolleyHttpClient;
import mediabrowser.apiinteraction.http.IAsyncHttpClient;
import mediabrowser.apiinteraction.sync.ContentUploader;
import mediabrowser.apiinteraction.tasks.CancellationTokenSource;
import mediabrowser.apiinteraction.tasks.Progress;
import mediabrowser.model.logging.ILogger;
import mediabrowser.model.serialization.IJsonSerializer;
import mediabrowser.model.session.ClientCapabilities;

public class MediaSyncAdapter extends AbstractThreadedSyncAdapter {

    public MediaSyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);

    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {

        String serverAddress = "";
        String accessToken = "";
        ILogger logger = new ConsoleLogger();
        Context context = getContext();

        IAsyncHttpClient httpClient = new VolleyHttpClient(logger, context);
        IJsonSerializer jsonSerializer = new GsonJsonSerializer();
        ApiEventListener listener = new ApiEventListener();
        ClientCapabilities capabilities = new ClientCapabilities();

        ApiClient apiClient = new AndroidApiClient(httpClient,
                jsonSerializer,
                logger,
                serverAddress,
                accessToken,
                listener,
                capabilities);

        Sync(apiClient, logger, syncResult);
    }

    private void Sync(ApiClient apiClient, ILogger logger, SyncResult syncResult){

        CancellationTokenSource source = new CancellationTokenSource();

        new ContentUploader(apiClient, logger).UploadImages(new Progress<Double>(){

            @Override
            public void onProgress(Double percent) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onCancelled() {

            }

            @Override
            public void onError(Exception ex) {

            }

        }, source.getToken());
    }
}