package mediabrowser.apiinteraction.sync.data;

import mediabrowser.model.sync.DeviceFileInfo;

import java.io.InputStream;
import java.util.ArrayList;

public interface IFileRepository {

    ArrayList<DeviceFileInfo> getFileSystemEntries(String path);

    void saveFile(InputStream stream, String path);

    void deleteFile(String path);

    void deleteFolder(String path);

    String getValidFileName(String name);

    boolean fileExists(String path);

    String getFullLocalPath(ArrayList<String> path);

    String getParentDirectoryPath(String path);
}
