package mediabrowser.apiinteraction.android.sync.server;

import mediabrowser.apiinteraction.Response;
import mediabrowser.apiinteraction.android.sync.SyncProgress;
import mediabrowser.apiinteraction.tasks.CancellationToken;
import mediabrowser.model.devices.ContentUploadHistory;

public class ContentUploaderHistoryResponse extends Response<ContentUploadHistory> {

    private ContentUploader contentUploader;
    private SyncProgress progress;
    private CancellationToken cancellationToken;

    public ContentUploaderHistoryResponse(ContentUploader contentUploader, SyncProgress progress, CancellationToken cancellationToken) {
        this.contentUploader = contentUploader;
        this.progress = progress;
        this.cancellationToken = cancellationToken;
    }

    @Override
    public void onResponse(ContentUploadHistory history) {

        contentUploader.UploadImagesInternal(history, cancellationToken, progress);
    }

    @Override
    public void onError(Exception ex) {

        progress.reportError(ex);
    }
}
