package mediabrowser.apiinteraction.sync.data;

import mediabrowser.model.logging.ILogger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Luke on 3/25/2015.
 */
public abstract class ImageFileRepository extends FileRepository implements IImageRepository {

    protected ImageFileRepository(ILogger logger) {
        super(logger);
    }

    private String getImagePath(String itemId){

        return new File(getBasePath(), itemId).getPath();
    }

    private String getImagePath(String itemId, String imageId){

        return new File(getImagePath(itemId), imageId).getPath();
    }

    @Override
    public void saveImage(String itemId, String imageId, InputStream stream) throws Exception {
        saveFile(stream, getImagePath(itemId, imageId));
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
}
