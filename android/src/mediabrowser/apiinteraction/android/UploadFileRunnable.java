package mediabrowser.apiinteraction.android;

import mediabrowser.apiinteraction.tasks.CancellationToken;
import mediabrowser.apiinteraction.tasks.IProgress;
import mediabrowser.model.devices.LocalFileInfo;

import java.io.FileInputStream;
import java.io.IOException;

public class UploadFileRunnable implements Runnable {

    private AndroidApiClient apiClient;
    private FileInputStream fileInputStream;
    private LocalFileInfo file;
    private IProgress<Double> progress;
    private CancellationToken cancellationToken;

    public UploadFileRunnable(AndroidApiClient apiClient, FileInputStream fileInputStream, LocalFileInfo file, IProgress<Double> progress, CancellationToken cancellationToken) {
        this.apiClient = apiClient;
        this.fileInputStream = fileInputStream;
        this.file = file;
        this.progress = progress;
        this.cancellationToken = cancellationToken;
    }

    @Override
    public void run() {

        try {
            apiClient.PerformUploadFile(fileInputStream, file, progress, cancellationToken);
        } catch (IOException e) {
            progress.reportError(e);
        }
    }
}
