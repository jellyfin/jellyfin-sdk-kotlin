package mediabrowser.apiinteraction.android.sync.data;

import mediabrowser.apiinteraction.tasks.Progress;
import mediabrowser.model.sync.DeviceFileInfo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public interface IFileRepository {

    ArrayList<DeviceFileInfo> getFileSystemEntries(String path);

    String saveFile(InputStream stream, String directory, String name, String mimeType, Long totalBytes, Progress<Double> progress) throws IOException;

    void deleteFile(String path);

    void deleteDirectory(String path);

    String getValidFileName(String name);

    boolean fileExists(String path);
}
