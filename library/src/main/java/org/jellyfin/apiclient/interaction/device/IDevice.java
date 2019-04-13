package org.jellyfin.apiclient.interaction.device;

import org.jellyfin.apiclient.interaction.ApiClient;
import org.jellyfin.apiclient.interaction.tasks.CancellationToken;
import org.jellyfin.apiclient.interaction.tasks.IProgress;
import org.jellyfin.apiclient.model.devices.LocalFileInfo;

import java.util.ArrayList;

public interface IDevice {

    String getDeviceName();
    String getDeviceId();

    ArrayList<LocalFileInfo> GetLocalPhotos();
    ArrayList<LocalFileInfo> GetLocalVideos();

    void UploadFile(LocalFileInfo file,
                    ApiClient apiClient,
                    IProgress<Double> progress,
                    CancellationToken cancellationToken);
}
