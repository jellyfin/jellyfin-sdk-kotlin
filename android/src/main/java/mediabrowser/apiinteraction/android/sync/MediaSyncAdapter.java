package mediabrowser.apiinteraction.android.sync;

import android.accounts.Account;
import android.content.*;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import mediabrowser.apiinteraction.*;
import mediabrowser.apiinteraction.android.*;
import mediabrowser.apiinteraction.android.sync.data.AndroidAssetManager;
import mediabrowser.apiinteraction.http.IAsyncHttpClient;
import mediabrowser.apiinteraction.sync.MultiServerSync;
import mediabrowser.apiinteraction.sync.data.ILocalAssetManager;
import mediabrowser.apiinteraction.tasks.CancellationTokenSource;
import mediabrowser.logging.ConsoleLogger;
import mediabrowser.model.extensions.ListHelper;
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

        logger.Debug("Checking network connection");

        SharedPreferences syncPreferences = context.getSharedPreferences("Sync", Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
        boolean syncOnlyOnWifi = syncPreferences.getBoolean("syncOnlyOnWifi", true);
        String cameraUploadServersString = syncPreferences.getString("cameraUploadServers", null);

        if (syncOnlyOnWifi){
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();

            if (info != null){
                if(info.getType()==ConnectivityManager.TYPE_WIFI || info.getType()==ConnectivityManager.TYPE_ETHERNET)
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
            }
        }

        logger.Info("MediaSyncAdapter starting");

        IJsonSerializer jsonSerializer = new GsonJsonSerializer();

        IAsyncHttpClient volleyHttpClient = new VolleyHttpClient(logger, context);

        ApiEventListener apiEventListener = new ApiEventListener();

        SharedPreferences connectionManagerPreferences = context.getSharedPreferences("AndroidConnectionManager", Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);

        String appName = connectionManagerPreferences.getString("appName", null);
        String appVersion = connectionManagerPreferences.getString("appVersion", null);
        String capabilitiesJson = connectionManagerPreferences.getString("capabilities", null);
        String deviceName = connectionManagerPreferences.getString("deviceName", null);
        String deviceId = connectionManagerPreferences.getString("deviceId", null);

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

        ILocalAssetManager localAssetManager = new AndroidAssetManager(context, logger, jsonSerializer);

        CancellationTokenSource source = new CancellationTokenSource();

        String[] cameraUploadServers = cameraUploadServersString == null || cameraUploadServersString.length() == 0 ? new String[]{} : cameraUploadServersString.split(",");

        new MultiServerSync(connectionManager, logger, localAssetManager, cameraUploadServers).Sync(source.getToken(), new MultiServerSyncProgress(syncResult, context.getContentResolver(), logger));
    }

    public static void updateSyncPreferences(Context context, String basePath, boolean syncOnlyOnWifi, String[] cameraUploadServers) {

        SharedPreferences syncPreferences = context.getSharedPreferences("Sync", Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = syncPreferences.edit();

        editor.putString("syncPath", basePath);
        editor.putBoolean("syncOnlyOnWifi", syncOnlyOnWifi);

        editor.putString("cameraUploadServers", tangible.DotNetToJavaStringHelper.join(",", cameraUploadServers));

        // Commit the edits!
        boolean saved = editor.commit();
    }

    public static boolean isSyncActive() {

        return ContentResolver.isSyncActive(AuthenticatorService.GetAccount(), AuthenticatorService.AUTHORITY);
    }

    public static boolean isSyncPending() {

        return ContentResolver.isSyncPending(AuthenticatorService.GetAccount(), AuthenticatorService.AUTHORITY);
    }
}