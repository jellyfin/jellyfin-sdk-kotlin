package mediabrowser.apiinteraction.android.sync;

import android.accounts.Account;
import android.content.*;
import android.os.Bundle;
import mediabrowser.apiinteraction.*;
import mediabrowser.apiinteraction.android.*;
import mediabrowser.apiinteraction.android.sync.data.AndroidAssetManager;
import mediabrowser.apiinteraction.http.IAsyncHttpClient;
import mediabrowser.apiinteraction.sync.MultiServerSync;
import mediabrowser.apiinteraction.sync.data.ILocalAssetManager;
import mediabrowser.apiinteraction.tasks.CancellationTokenSource;
import mediabrowser.logging.ConsoleLogger;
import mediabrowser.model.logging.ILogger;
import mediabrowser.model.serialization.IJsonSerializer;
import mediabrowser.model.session.ClientCapabilities;

public class MediaSyncAdapter extends AbstractThreadedSyncAdapter {

    public static ISyncLoggerFactory LoggerFactory;

    public MediaSyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);

    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, final SyncResult syncResult) {

        Context context = getContext();

        ILogger logger;

        if (LoggerFactory != null){
            logger = LoggerFactory.getNewLogger();
        }
        else{
            logger = new ConsoleLogger();
        }

        logger.Info("MediaSyncAdapter starting");

        IJsonSerializer jsonSerializer = new GsonJsonSerializer();

        IAsyncHttpClient volleyHttpClient = new VolleyHttpClient(logger, context);

        ClientCapabilities capabilities = new ClientCapabilities();
        capabilities.setSupportsContentUploading(true);

        ApiEventListener apiEventListener = new ApiEventListener();

        SharedPreferences preferences = context.getSharedPreferences("AndroidConnectionManager", Context.MODE_PRIVATE);

        String appName = preferences.getString("appName", null);
        String appVersion = preferences.getString("appVersion", null);

        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(appName))
        {
            logger.Info("appName not set yet. Skipping sync.");
            return;
        }

        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(appVersion))
        {
            logger.Info("appVersion not set yet. Skipping sync.");
            return;
        }

        IConnectionManager connectionManager = new AndroidConnectionManager(context,
                jsonSerializer,
                logger,
                volleyHttpClient,
                appName,
                appVersion,
                capabilities,
                apiEventListener);

        ILocalAssetManager localAssetManager = new AndroidAssetManager(context, logger, jsonSerializer);

        CancellationTokenSource source = new CancellationTokenSource();

        new MultiServerSync(connectionManager, logger, localAssetManager).Sync(source.getToken(), new MultiServerSyncProgress(syncResult, context.getContentResolver(), logger));
    }
}