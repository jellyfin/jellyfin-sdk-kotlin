package mediabrowser.apiinteraction.sync.data;

import mediabrowser.model.logging.ILogger;
import mediabrowser.model.sync.DeviceFileInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

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
    public String saveFile(InputStream initialStream, String directory, String name, String mimeType) throws IOException {

        File targetFile = new File(getBasePath());

        String[] parts = directory.split(Pattern.quote("/"));
        for (String part : parts){
            targetFile = new File(targetFile, part);
        }

        targetFile = new File(targetFile, name);

        String targetFilePath = targetFile.getAbsolutePath();

        Logger.Info("Saving file to %s", targetFile);
        File parentFile = new File(targetFilePath).getParentFile();

        Logger.Info("Creating parent directory %s", parentFile.getAbsolutePath());
        boolean success = parentFile.mkdirs();

        if (success){
            Logger.Info("Creating parent directory succeeded: %s", parentFile.getAbsolutePath());
        } else{
            Logger.Info("Creating parent directory failed: %s", parentFile.getAbsolutePath());
        }

        try (OutputStream outStream = new FileOutputStream(targetFilePath)) {

            byte[] buffer = new byte[8 * 1024];
            int bytesRead;
            while ((bytesRead = initialStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            outStream.close();
        }

        return targetFilePath;
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
}
