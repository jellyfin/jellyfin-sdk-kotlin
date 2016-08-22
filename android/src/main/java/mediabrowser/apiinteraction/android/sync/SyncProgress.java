package mediabrowser.apiinteraction.android.sync;

import mediabrowser.apiinteraction.tasks.Progress;
import mediabrowser.model.devices.LocalFileInfo;

public class SyncProgress extends Progress<Double> {

    public void onFileUploaded(LocalFileInfo file){

    }

    public void onFileUploadError(LocalFileInfo file, Exception ex){

    }
}
