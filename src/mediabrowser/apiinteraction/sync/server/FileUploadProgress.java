package mediabrowser.apiinteraction.sync.server;

import mediabrowser.apiinteraction.device.IDevice;
import mediabrowser.apiinteraction.sync.SyncProgress;
import mediabrowser.apiinteraction.tasks.CancellationToken;
import mediabrowser.apiinteraction.tasks.Progress;
import mediabrowser.model.devices.LocalFileInfo;

import java.util.ArrayList;

public class FileUploadProgress extends Progress<Double> {

    private ContentUploader contentUploader;
    private IDevice device;
    private ArrayList<LocalFileInfo> files;
    private int index;
    private SyncProgress progress;
    private CancellationToken cancellationToken;
    private LocalFileInfo file;

    public FileUploadProgress(ContentUploader contentUploader, IDevice device, ArrayList<LocalFileInfo> files, int index, SyncProgress progress, CancellationToken cancellationToken) {
        this.contentUploader = contentUploader;
        this.device = device;
        this.files = files;
        this.index = index;
        this.progress = progress;
        this.cancellationToken = cancellationToken;

        file = files.get(index);
    }

    private void GoNext() {

        double numComplete = index+ 1;
        numComplete /= files.size();
        progress.report(numComplete * 100);

        contentUploader.UploadNext(files, index + 1, device, cancellationToken, progress);
    }

    @Override
    public void onComplete() {

        progress.onFileUploaded(file);
        GoNext();
    }

    @Override
    public void onError(Exception ex) {

        progress.onFileUploadError(file, ex);
        GoNext();
    }

    @Override
    public void onCancelled() {

        GoNext();
    }

    @Override
    public void onProgress(Double value) {

        // TODO: This is progress for the individual file
    }
}
