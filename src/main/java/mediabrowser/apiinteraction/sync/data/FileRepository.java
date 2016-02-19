package mediabrowser.apiinteraction.sync.data;

import com.google.common.io.Files;
import mediabrowser.model.logging.ILogger;
import mediabrowser.model.sync.DeviceFileInfo;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Luke on 3/24/2015.
 */
public abstract class FileRepository implements IFileRepository {

    protected ILogger Logger;

    protected  FileRepository(ILogger logger){
        this.Logger = logger;
    }

    @Override
    public ArrayList<DeviceFileInfo> getFileSystemEntries(String path) {
        return new ArrayList<>();
    }

    @Override
    public void saveFile(InputStream initialStream, String targetFile) throws IOException {

        Files.createParentDirs(new File(targetFile));

        try (OutputStream outStream = new FileOutputStream(targetFile)) {

            byte[] buffer = new byte[8 * 1024];
            int bytesRead;
            while ((bytesRead = initialStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            outStream.close();
        }
    }

    @Override
    public void deleteFile(String path) {
        Logger.Info("Deleting file: %s", path);
        new File(path).delete();
    }

    @Override
    public void deleteDirectory(String path) {
        deleteFileOrFolder(new File(path));
    }

    private void deleteFileOrFolder(File file){

        File[] filesList = file.listFiles();

        if (filesList != null) {
            for (File f : filesList) {
                f.delete();
                deleteFileOrFolder(f);
            }
        }
    }

    @Override
    public String getValidFileName(String name) {

        if (name == null) {
            throw new IllegalArgumentException("name");
        }
        return name.replaceAll("[^a-zA-Z0-9.-]", "_");
    }

    @Override
    public boolean fileExists(String path) {
        return new File(path).exists();
    }

    protected abstract String getBasePath();

    @Override
    public String getFullLocalPath(ArrayList<String> path) {

        File file = new File(getBasePath());

        for (String part : path){
            file = new File(file, part);
        }

        return file.getPath();
    }

    @Override
    public String getParentDirectoryPath(String path) {
        return new File(path).getParent();
    }
}
