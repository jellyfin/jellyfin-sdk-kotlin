package mediabrowser.apiinteraction.android;

import android.graphics.Bitmap;
import mediabrowser.apiinteraction.android.images.ImageCacheManager;
import mediabrowser.apiinteraction.android.volley.GetBitmapResponse;
import mediabrowser.apiinteraction.http.HttpRequest;
import mediabrowser.apiinteraction.http.IAsyncHttpClient;
import mediabrowser.apiinteraction.Response;
import mediabrowser.model.extensions.StringHelper;
import mediabrowser.model.logging.ILogger;
import android.content.Context;

import com.android.volley.*;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;

public class VolleyHttpClient implements IAsyncHttpClient {

    /**
     * Log or request TAG
     */
    public static final String TAG = "VolleyHttpClient";

    /**
     * Default maximum disk usage in bytes.
     */
    private static final int DEFAULT_DISK_USAGE_BYTES = 100 * 1024 * 1024; // 100 MB

    /**
     * Global request queue for Volley.
     */
    private RequestQueue requestQueue;

    private ILogger logger;
    private Context context;

    private ImageCacheManager cacheManager;

    public VolleyHttpClient(ILogger logger, Context context) {
        this.logger = logger;
        this.context = context;
    }

    public RequestQueue getRequestQueue() {
        // lazy initialize the request queue, the queue instance will be
        // created when it is accessed for the first time
        if (requestQueue == null) {
            Cache cache = new DiskBasedCache(context.getCacheDir(), DEFAULT_DISK_USAGE_BYTES);
            Network network = new BasicNetwork(new HurlStack());
            requestQueue = new RequestQueue(cache, network);
            requestQueue.start();
        }

        return requestQueue;
    }

    public ImageLoader getImageLoader() {

        if (cacheManager == null) {

            cacheManager = new ImageCacheManager(context, logger, getRequestQueue(), "MediaBrowser");
        }

        return cacheManager.getImageLoader();
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
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }

    public void Send(final HttpRequest request, final Response<String> response)
    {
        int method = Request.Method.GET;

        if (StringHelper.EqualsIgnoreCase(request.getMethod(), "POST")){
            method = Request.Method.POST;
        }
        else if (StringHelper.EqualsIgnoreCase(request.getMethod(), "DELETE")){
            method = Request.Method.DELETE;
        }

        final String url = request.getUrl();

        StringRequest req = new VolleyStringRequest(method, url, new VolleyResponseListener(response, logger, url), new VolleyErrorListener(response, logger), request);

        if (method != Request.Method.GET) {
            req.setShouldCache(false);
        }

        if (!request.getEnableCaching()) {
            req.setShouldCache(false);
        }

        req.setRetryPolicy(new DefaultRetryPolicy(
                request.getTimeout(), // timeout in ms
                0, // num of retries
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));

        // add the request object to the queue to be executed
        addToRequestQueue(req);
    }

    public void getBitmap(String url, final Response<Bitmap> outerResponse) {

        getImageLoader().get(url, new GetBitmapResponse(outerResponse));
    }
}
