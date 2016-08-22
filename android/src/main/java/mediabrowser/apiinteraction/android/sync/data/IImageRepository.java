package mediabrowser.apiinteraction.android.sync.data;

import java.io.IOException;
import java.io.InputStream;

public interface IImageRepository {

    void saveImage(String itemId, String imageId, InputStream stream, String mimeType) throws Exception;

    void deleteImage(String itemId, String imageId);

    boolean hasImage(String itemId, String imageId);

    void deleteImages(String itemId);
}
