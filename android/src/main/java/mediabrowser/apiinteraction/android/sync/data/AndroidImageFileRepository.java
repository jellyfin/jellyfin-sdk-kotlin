package mediabrowser.apiinteraction.android.sync.data;

import android.content.Context;
import android.os.Build;
import mediabrowser.apiinteraction.sync.data.IImageRepository;
import mediabrowser.model.logging.ILogger;

import java.io.File;
import java.io.InputStream;

/**
 * Created by Luke on 3/25/2015.
 */
public class AndroidImageFileRepository extends AndroidFileRepository implements IImageRepository {

    public AndroidImageFileRepository(Context context, ILogger logger) {
        super(context, logger);
    }

    private String getImagePath(String itemId){

        return new File(getBasePath(), itemId).getPath();
    }

    private String getImagePath(String itemId, String imageId){

        return new File(getImagePath(itemId), imageId).getPath();
    }

    @Override
    public void saveImage(String itemId, String imageId, InputStream stream, String mimeType) throws Exception {

        File file = new File(getImagePath(itemId, imageId));

        saveFile(stream, file.getParent(), file.getName(), mimeType);
    }

    @Override
    public void deleteImage(String itemId, String imageId) {
        deleteFile(getImagePath(itemId, imageId));
    }

    @Override
    public boolean hasImage(String itemId, String imageId) {
        return fileExists(getImagePath(itemId, imageId));
    }

    @Override
    public void deleteImages(String itemId) {
        deleteDirectory(getImagePath(itemId));
    }

    @Override
    protected String getBasePath() {
        File directory = context.getExternalFilesDir(null);

        if (directory == null){
            directory = context.getFilesDir();
        }

        File file = new File(directory, "images");

        return file.getPath();
    }

    @Override
    protected boolean enableDocumentFile(String path){
        return false;
    }
}
