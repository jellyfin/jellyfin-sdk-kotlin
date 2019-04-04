package org.jellyfin.apiclient.interaction;

import org.jellyfin.apiclient.interaction.tasks.CancellationToken;
import org.jellyfin.apiclient.interaction.tasks.IProgress;
import org.jellyfin.apiclient.model.devices.LocalFileInfo;

import java.io.FileInputStream;

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
        } catch (Exception e) {
            progress.reportError(e);
        }
    }
}
