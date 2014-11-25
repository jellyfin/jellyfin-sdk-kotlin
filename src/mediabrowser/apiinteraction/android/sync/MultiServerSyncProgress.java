package mediabrowser.apiinteraction.android.sync;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.OperationApplicationException;
import android.content.SyncResult;
import android.os.RemoteException;
import mediabrowser.apiinteraction.sync.SyncProgress;
import mediabrowser.model.devices.LocalFileInfo;

import java.util.ArrayList;

public class MultiServerSyncProgress extends SyncProgress {

    private SyncResult syncResult;
    private ContentResolver contentResolver;

    public MultiServerSyncProgress(SyncResult syncResult, ContentResolver contentResolver) {
        this.syncResult = syncResult;
        this.contentResolver = contentResolver;
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

        // Entire process completed
        ArrayList<ContentProviderOperation> batch = new ArrayList<ContentProviderOperation>();


        try {
            contentResolver.applyBatch(SyncAccountManager.AUTHORITY, batch);

            //contentResolver.notifyChange(
            //        FeedContract.Entry.CONTENT_URI, // URI where data was modified
            //        null,                           // No local observer
            //        false);                         // IMPORTANT: Do not sync to network
            // This sample doesn't support uploads, but if *your* code does, make sure you set
            // syncToNetwork=false in the line above to prevent duplicate syncs.

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Exception ex) {

        // Entire process failed
    }

    @Override
    public void onFileUploadError(LocalFileInfo file, Exception ex) {

        syncResult.stats.numIoExceptions++;
    }
}
