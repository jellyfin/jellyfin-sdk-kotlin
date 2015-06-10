package mediabrowser.apiinteraction.android.sync;

import android.accounts.Account;
import android.content.*;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import mediabrowser.apiinteraction.*;
import mediabrowser.apiinteraction.android.*;
import mediabrowser.apiinteraction.android.sync.data.AndroidAssetManager;
import mediabrowser.apiinteraction.android.sync.data.AndroidFileRepository;
import mediabrowser.apiinteraction.device.Device;
import mediabrowser.apiinteraction.http.IAsyncHttpClient;
import mediabrowser.apiinteraction.sync.MultiServerSync;
import mediabrowser.apiinteraction.sync.data.IFileRepository;
import mediabrowser.apiinteraction.sync.data.ILocalAssetManager;
import mediabrowser.apiinteraction.tasks.CancellationTokenSource;
import mediabrowser.logging.ConsoleLogger;
import mediabrowser.model.logging.ILogger;
import mediabrowser.model.serialization.IJsonSerializer;
import mediabrowser.model.session.ClientCapabilities;

public class MediaSyncAdapter extends AbstractThreadedSyncAdapter {

    public static ISyncLoggerFactory LoggerFactory;
    public static ISyncRepositoryFactory SyncRepositoryFactory;

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

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();

        if(info.getType()==ConnectivityManager.TYPE_WIFI ||
                info.getType()==ConnectivityManager.TYPE_ETHERNET)
        {
            logger.Debug("Local Network Connected");
        }
        else
        {
            logger.Debug("WiFi not connected");
            if (extras.getBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, false) == false){
                logger.Debug("Skipping sync because we're not connected to wifi");
                return;
            }
        }

        logger.Info("MediaSyncAdapter starting");

        IJsonSerializer jsonSerializer = new GsonJsonSerializer();

        IAsyncHttpClient volleyHttpClient = new VolleyHttpClient(logger, context);

        ApiEventListener apiEventListener = new ApiEventListener();

        SharedPreferences preferences = context.getSharedPreferences("AndroidConnectionManager", Context.MODE_PRIVATE);

        String appName = preferences.getString("appName", null);
        String appVersion = preferences.getString("appVersion", null);
        String capabilitiesJson = preferences.getString("capabilities", null);
        String deviceName = preferences.getString("deviceName", null);
        String deviceId = preferences.getString("deviceId", null);

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

        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(capabilitiesJson))
        {
            logger.Info("capabilitiesJson not set yet. Skipping sync.");
            return;
        }

        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(deviceName))
        {
            logger.Info("deviceName not set yet. Skipping sync.");
            return;
        }

        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(deviceId))
        {
            logger.Info("deviceId not set yet. Skipping sync.");
            return;
        }

        ClientCapabilities capabilities = jsonSerializer.DeserializeFromString(capabilitiesJson, ClientCapabilities.class);
        capabilities.setSupportsContentUploading(true);

        IConnectionManager connectionManager = new AndroidConnectionManager(context,
                jsonSerializer,
                logger,
                volleyHttpClient,
                appName,
                appVersion,
                new AndroidDevice(context, deviceId, deviceName),
                capabilities,
                apiEventListener);

        IFileRepository fileRepository;

        if (SyncRepositoryFactory != null){
            fileRepository= SyncRepositoryFactory.getFileRepository();
        }
        else{
            fileRepository= new AndroidFileRepository(context, logger);
        }

        ILocalAssetManager localAssetManager = new AndroidAssetManager(context, logger, jsonSerializer, fileRepository);

        CancellationTokenSource source = new CancellationTokenSource();

        new MultiServerSync(connectionManager, logger, localAssetManager).Sync(source.getToken(), new MultiServerSyncProgress(syncResult, context.getContentResolver(), logger));
    }
}