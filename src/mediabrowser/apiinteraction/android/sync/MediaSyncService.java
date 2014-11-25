package mediabrowser.apiinteraction.android.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import mediabrowser.logging.ConsoleLogger;
import mediabrowser.model.logging.ILogger;

public class MediaSyncService extends Service {

    private static final Object sSyncAdapterLock = new Object();
    private static MediaSyncAdapter sSyncAdapter = null;

    private ILogger logger = new ConsoleLogger();

    /**
     * Thread-safe constructor, creates static {@link MediaSyncAdapter} instance.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        logger.Info("MediaSyncService created");
        synchronized (sSyncAdapterLock) {
            if (sSyncAdapter == null) {
                sSyncAdapter = new MediaSyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Override
    /**
     * Logging-only destructor.
     */
    public void onDestroy() {
        super.onDestroy();
        logger.Info("MediaSyncService destroyed");
    }

    /**
     * Return Binder handle for IPC communication with {@link MediaSyncAdapter}.
     *
     * <p>New sync requests will be sent directly to the SyncAdapter using this channel.
     *
     * @param intent Calling intent
     * @return Binder handle for {@link MediaSyncAdapter}
     */
    @Override
    public IBinder onBind(Intent intent) {
        return sSyncAdapter.getSyncAdapterBinder();
    }
}
