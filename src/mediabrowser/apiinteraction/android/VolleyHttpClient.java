package mediabrowser.apiinteraction.android;

import mediabrowser.apiinteraction.http.HttpHeaders;
import mediabrowser.apiinteraction.http.HttpRequest;
import mediabrowser.apiinteraction.http.IAsyncHttpClient;
import mediabrowser.apiinteraction.Response;
import mediabrowser.model.extensions.StringHelper;
import mediabrowser.model.logging.ILogger;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import com.android.volley.*;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class VolleyHttpClient implements IAsyncHttpClient {

    /**
     * Log or request TAG
     */
    public static final String TAG = "VolleyHttpClient";

    /**
     * Global request queue for Volley
     */
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private ILogger logger;
    private Context context;

    public VolleyHttpClient(ILogger logger, Context context) {
        this.logger = logger;
        this.context = context;
    }

    /**
     * @return The Volley Request queue, the queue will be created if it is null
     */
    public RequestQueue getRequestQueue() {
        // lazy initialize the request queue, the queue instance will be
        // created when it is accessed for the first time
        if (mRequestQueue == null) {

            mRequestQueue = Volley.newRequestQueue(context, new OkHttpStack());
            //mRequestQueue = Volley.newRequestQueue(context, new HttpClientStack(new DefaultHttpClient()));
            //mRequestQueue = Volley.newRequestQueue(context);
        }

        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {

        getRequestQueue();
        if (mImageLoader == null) {

            ImageLoader.ImageCache cache;

            try {
                int byteSize = 500000000;
                cache = new DiskLruImageCache(context, "MediaBrowser", byteSize, logger);
            }
            catch (IOException ex){
                logger.ErrorException("Failed to load DiskLruImageCache. Reverting to LruBitmapCache.", ex);
                cache = new LruBitmapCache();
            }

            mImageLoader = new ImageLoader(this.mRequestQueue, cache);
        }
        return this.mImageLoader;
    }

    /**
     * Adds the specified request to the global queue using the Default TAG.
     *
     * @param req
     */
    public <T> void addToRequestQueue(Request<T> req) {

        // set the default tag if tag is empty
        //req.setTag(TAG);

        logger.Debug("Adding request to queue: %s", req.getUrl());

        getRequestQueue().add(req);
    }

    /**
     * Cancels all pending requests by the specified TAG, it is important
     * to specify a TAG so that the pending/ongoing requests can be cancelled.
     *
     * @param tag
     */
    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public void Send(final HttpRequest request, final Response<String> response)
    {
        int method = Request.Method.GET;

        if (request.getMethod() == "POST"){
            method = Request.Method.POST;
        }
        else if (request.getMethod() == "DELETE"){
            method = Request.Method.DELETE;
        }

        final String url = request.getUrl();

        StringRequest req = new VolleyStringRequest(method, url, new VolleyStringListener(response, logger, url), new VolleyErrorListener(response, logger), request);

        // add the request object to the queue to be executed
        addToRequestQueue(req);
    }
}
