package mediabrowser.apiinteraction.android.sync;

import android.content.ContentResolver;
import android.content.SyncResult;
import mediabrowser.apiinteraction.IConnectionManager;
import mediabrowser.apiinteraction.sync.MultiServerSync;
import mediabrowser.apiinteraction.tasks.CancellationToken;
import mediabrowser.model.logging.ILogger;

public class SyncAdapterRunnable implements Runnable {

    private IConnectionManager connectionManager;
    private ILogger logger;
    private SyncResult syncResult;
    private CancellationToken cancellationToken;
    private ContentResolver contentResolver;

    public SyncAdapterRunnable(IConnectionManager connectionManager, ILogger logger, SyncResult syncResult, CancellationToken cancellationToken, ContentResolver contentResolver) {
        this.connectionManager = connectionManager;
        this.logger = logger;
        this.syncResult = syncResult;
        this.cancellationToken = cancellationToken;
        this.contentResolver = contentResolver;
    }

    @Override
    public void run() {
        new MultiServerSync(connectionManager, logger).Sync(cancellationToken, new MultiServerSyncProgress(syncResult, contentResolver, logger));
    }
}
