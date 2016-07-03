package mediabrowser.apiinteraction.android.sync.data;

import android.content.Context;
import android.net.Uri;
import android.provider.DocumentsContract;
import mediabrowser.apiinteraction.sync.data.FileRepository;
import mediabrowser.model.logging.ILogger;
import android.support.v4.provider.DocumentFile;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by Luke on 3/25/2015.
 */
public class AndroidFileRepository extends FileRepository {

    protected Context context;

    public AndroidFileRepository(Context context, ILogger logger) {
        super(logger);
        this.context = context;
    }

    @Override
    protected String getBasePath() {

        File directory = context.getExternalFilesDir(null);

        if (directory == null){
            directory = context.getFilesDir();
        }

        File file = new File(directory, "sync");

        return file.getPath();
    }

    @Override
    public String getFullLocalPath(ArrayList<String> path) {

        if (enableDocumentFile(getBasePath())){

            String[] contents = new String[path.size()];
            return tangible.DotNetToJavaStringHelper.join("/", path.toArray(contents));
        }

        return super.getFullLocalPath(path);
    }

    private DocumentFile getDocumentFile(String path){

        String[] parts = path.split(Pattern.quote("/"));
        return findFileRecursive(Uri.parse(getBasePath()), parts);
    }

    @Override
    public boolean fileExists(String path) {

        if (enableDocumentFile(path)) {

            Logger.Info("Checking if DocumentFile exists: %s", path);
            DocumentFile file = DocumentFile.fromSingleUri(context, Uri.parse(path));

            if (file == null){
                Logger.Info("File is null: %s", path);
                return false;
            } else{
                boolean exists = file.exists();

                Logger.Info("DocumentFile exists: %s: %s", exists, path);

                return exists;
            }
        }
        else {

            return super.fileExists(path);
        }
    }

    @Override
    public void deleteFile(String path) {

        Logger.Info("Deleting file: %s", path);
        if (enableDocumentFile(path)) {

            DocumentFile file = getDocumentFile(path);

            if (file == null){
                Logger.Info("File is null, already deleted: %s", path);
            } else{
                file.delete();
            }
        }
        else {

            super.deleteFile(path);
        }
    }

    @Override
    public void deleteDirectory(String path) {

        Logger.Info("deleteDirectory %s", path);

        if (enableDocumentFile(path)) {

            deleteFileOrFolder(getDocumentFile(path));
        }
        else {

            super.deleteDirectory(path);
        }
    }

    private void deleteFileOrFolder(DocumentFile file){

        if (!file.exists()){
            return;
        }

        if (!file.isDirectory()){
            file.delete();
            return;
        }

        DocumentFile[] filesList = file.listFiles();

        if (filesList != null) {
            for (DocumentFile f : filesList) {
                f.delete();
                deleteFileOrFolder(f);
            }
        }
    }

    @Override
    public String saveFile(InputStream initialStream, String directory, String name, String mimeType) throws IOException {

        if (enableDocumentFile(directory)) {

            if (directory.indexOf("/") != 0){
                directory = "/" + directory;
            }

            Logger.Info("Save file directory: %s", directory);
            Logger.Info("Save file name: %s", name);
            Logger.Info("Save file mimeType: %s", mimeType);

            DocumentFile directoryDocument = ensureDirectory(Uri.parse(getBasePath()), directory.split(Pattern.quote("/")));

            Logger.Info("Save file directory document uri: %s", directoryDocument.getUri().toString());

            DocumentFile newFile = directoryDocument.createFile(mimeType, name);

            Logger.Info("Save file targetDocumentFile: %s", newFile.getUri().toString());

            try (OutputStream outStream = context.getContentResolver().openOutputStream(newFile.getUri())) {

                byte[] buffer = new byte[8 * 1024];
                int bytesRead;
                while ((bytesRead = initialStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }
                outStream.close();
            }

            return newFile.getUri().toString();
        }
        else {

            return super.saveFile(initialStream, directory, name, mimeType);
        }
    }

    private DocumentFile ensureDirectory(Uri treeUri, String[] parts) {

        String path = "";

        Logger.Info("ensureDirectory treeUri: %s", treeUri);

        DocumentFile tree = DocumentFile.fromTreeUri(context, treeUri);

        for (String part : parts){
            if (part.length() == 0){
                continue;
            }

            path += "/" + part;

            Logger.Info("ensureDirectory: %s", path);

            DocumentFile directory = tree.findFile(part);
            if (directory == null || !directory.isDirectory()){

                Logger.Info("ensureDirectory createDocument tree:%s name:%s", tree.getUri(), part);

                tree = tree.createDirectory(part);

            } else{
                tree = directory;
            }

            Logger.Info("ensureDirectory new treeUri: %s", tree.getUri());
        }

        return tree;
    }

    private DocumentFile findFileRecursive(Uri treeUri, String[] parts) {

        String path = "";

        Logger.Info("findFileRecursive treeUri: %s", treeUri);

        DocumentFile tree = DocumentFile.fromTreeUri(context, treeUri);

        for (String part : parts){

            if (part.length() == 0){
                continue;
            }

            path += "/" + part;

            Logger.Info("findFileRecursive: %s", path);

            DocumentFile directory = tree.findFile(part);
            if (directory == null){

                return null;

            } else{
                tree = directory;
            }

            Logger.Info("findFileRecursive new treeUri: %s", tree.getUri());
        }

        return tree;
    }

    protected boolean enableDocumentFile(String path){

        return false;
    }
}
