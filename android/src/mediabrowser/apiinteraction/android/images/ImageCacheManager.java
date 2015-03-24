package mediabrowser.apiinteraction.android.images;

import android.content.Context;
import android.graphics.Bitmap;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import mediabrowser.apiinteraction.android.images.DiskLruImageCache;
import mediabrowser.apiinteraction.android.images.LruBitmapCache;
import mediabrowser.model.logging.ILogger;

import java.io.IOException;

/**
 * Implementation of volley's ImageCache interface. This manager tracks the application image loader and cache.
 *
 * Volley recommends an L1 non-blocking cache which is the default MEMORY CacheType.
 * @author Trey Robinson
 *
 */
public class ImageCacheManager{

    public ImageCacheManager(Context context, ILogger logger, RequestQueue queue, String name) {
        this.context = context;
        this.logger = logger;
        this.queue = queue;
        this.uniqueName = name;
    }

    /**
     * Volley recommends in-memory L1 cache but both a disk and memory cache are provided.
     * Volley includes a L2 disk cache out of the box but you can technically use a disk cache as an L1 cache provided
     * you can live with potential i/o blocking.
     *
     */
    public enum CacheType {
        DISK
        , MEMORY
    }

    /**
     * Volley image loader
     */
    private ImageLoader mImageLoader;

    /**
     * Image cache implementation
     */
    private ImageLoader.ImageCache mImageCache;

    private Context context;
    private ILogger logger;
    private RequestQueue queue;
    private String uniqueName;

    private void init(){

        CacheType type = CacheType.MEMORY;

        switch (type) {
            case DISK:
                try {
                    int byteSize = 500000000;
                    mImageCache = new DiskLruImageCache(context, uniqueName, byteSize, logger);
                }
                catch (IOException ex){
                    logger.ErrorException("Failed to load DiskLruImageCache. Reverting to LruBitmapCache.", ex);
                    mImageCache = new LruBitmapCache();
                }
                break;
            case MEMORY:
                mImageCache = new LruBitmapCache();
            default:
                mImageCache = new LruBitmapCache();
                break;
        }

        mImageLoader = new ImageLoader(queue, mImageCache);
    }

    public Bitmap getBitmap(String url) {
        try {
            return mImageCache.getBitmap(createKey(url));
        } catch (NullPointerException e) {
            throw new IllegalStateException("Disk Cache Not initialized");
        }
    }

    public void putBitmap(String url, Bitmap bitmap) {
        try {
            mImageCache.putBitmap(createKey(url), bitmap);
        } catch (NullPointerException e) {
            throw new IllegalStateException("Disk Cache Not initialized");
        }
    }


    /**
     * 	Executes and image load
     * @param url
     * 		location of image
     * @param listener
     * 		Listener for completion
     */
    public void getImage(String url, ImageLoader.ImageListener listener){
        mImageLoader.get(url, listener);
    }

    /**
     * @return
     * 		instance of the image loader
     */
    public ImageLoader getImageLoader() {

        if (mImageLoader == null){
            init();
        }
        return mImageLoader;
    }

    /**
     * Creates a unique cache key based on a url value
     * @param url
     * 		url to be used in key creation
     * @return
     * 		cache key value
     */
    private String createKey(String url){
        return String.valueOf(url.hashCode());
    }


}