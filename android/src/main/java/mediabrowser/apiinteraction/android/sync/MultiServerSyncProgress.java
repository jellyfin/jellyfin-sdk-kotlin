package mediabrowser.apiinteraction.android.sync;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.OperationApplicationException;
import android.content.SyncResult;
import android.net.Uri;
import android.os.RemoteException;
import mediabrowser.apiinteraction.sync.SyncProgress;
import mediabrowser.model.devices.LocalFileInfo;
import mediabrowser.model.logging.ILogger;

import java.util.ArrayList;

public class MultiServerSyncProgress extends SyncProgress {

    private SyncResult syncResult;
    private ContentResolver contentResolver;
    private ILogger logger;

    /**
     * Base URI. (content://com.example.android.network.sync.basicsyncadapter)
     */
    final Uri BASE_CONTENT_URI = Uri.parse("content://" + AuthenticatorService.AUTHORITY);

    /**
     * Path component for "entry"-type resources..
     */
    final String PATH_ENTRIES = "entries";


    /**
     * Fully qualified URI for "entry" resources.
     */
    final Uri CONTENT_URI =
            BASE_CONTENT_URI.buildUpon().appendPath(PATH_ENTRIES).build();

    public MultiServerSyncProgress(SyncResult syncResult, ContentResolver contentResolver, ILogger logger) {
        this.syncResult = syncResult;
        this.contentResolver = contentResolver;
        this.logger = logger;
    }

    @Override
    public void onProgress(Double percent) {


    }

    @Override
    public void onFileUploaded(LocalFileInfo file) {

        syncResult.stats.numEntries++;
    }

    @Override
    public void onComplete() {

        logger.Info("MultiServerSync complete");

        // Entire process completed
        ArrayList<ContentProviderOperation> batch = new ArrayList<ContentProviderOperation>();

        try {
            contentResolver.applyBatch(AuthenticatorService.AUTHORITY, batch);

            contentResolver.notifyChange(
                    CONTENT_URI, // URI where data was modified
                    null,                           // No local observer
                    false);                         // IMPORTANT: Do not sync to network
            // This sample doesn't support uploads, but if *your* code does, make sure you set
            // syncToNetwork=false in the line above to prevent duplicate syncs.

        } catch (RemoteException e) {
            logger.ErrorException("Error calling contentResolver.applyBatch", e);
        } catch (OperationApplicationException e) {
            logger.ErrorException("Error calling contentResolver.applyBatch", e);
        }
    }

    @Override
    public void onError(Exception ex) {

        // Entire process failed
        logger.ErrorException("MultiServerSync failed", ex);

        // Is this appropriate? Need to find out how to report that the process failed
        onComplete();
    }

    @Override
    public void onFileUploadError(LocalFileInfo file, Exception ex) {

        syncResult.stats.numIoExceptions++;
    }
}
