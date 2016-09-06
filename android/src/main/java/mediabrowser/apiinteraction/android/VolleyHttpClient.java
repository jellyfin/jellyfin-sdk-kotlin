package mediabrowser.apiinteraction.android;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.android.volley.toolbox.DiskBasedCache;
import com.squareup.okhttp.OkHttpClient;
import de.duenndns.ssl.MemorizingTrustManager;
import mediabrowser.apiinteraction.android.images.ImageCacheManager;
import mediabrowser.apiinteraction.android.volley.GetBitmapResponse;
import mediabrowser.apiinteraction.http.HttpRequest;
import mediabrowser.apiinteraction.http.IAsyncHttpClient;
import mediabrowser.apiinteraction.Response;
import mediabrowser.model.extensions.StringHelper;
import mediabrowser.model.logging.ILogger;
import android.content.Context;
import com.android.volley.*;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import java.io.File;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

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

    private ImageCacheManager cacheManager;

    public VolleyHttpClient(ILogger logger, Context context) {
        this.logger = logger;
        this.context = context;
    }

    /** Default maximum disk usage in bytes. */
    private static final int DEFAULT_DISK_USAGE_BYTES = 100 * 1024 * 1024;

    /**
     * @return The Volley Request queue, the queue will be created if it is null
     */
    public RequestQueue getRequestQueueOld() {
        // lazy initialize the request queue, the queue instance will be
        // created when it is accessed for the first time
        if (mRequestQueue == null) {

            mRequestQueue = Volley.newRequestQueue(context, new OkHttpStack(), DEFAULT_DISK_USAGE_BYTES);
            //mRequestQueue = Volley.newRequestQueue(context, new HttpClientStack(new DefaultHttpClient()));
            //mRequestQueue = Volley.newRequestQueue(context);
        }

        return mRequestQueue;
    }

    public RequestQueue getRequestQueue() {
        // lazy initialize the request queue, the queue instance will be
        // created when it is accessed for the first time
        if (mRequestQueue == null) {
            // register MemorizingTrustManager for HTTPS
            try {
                SSLContext sc;
                sc = SSLContext.getInstance("TLS");
                MemorizingTrustManager mtm = new MemorizingTrustManager(context);
                sc.init(null, new X509TrustManager[]{mtm}, new java.security.SecureRandom());

                HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
                HttpsURLConnection.setDefaultHostnameVerifier(
                        mtm.wrapHostnameVerifier(HttpsURLConnection.getDefaultHostnameVerifier()));

                OkHttpClient okClient = new OkHttpClient();
                okClient.setSslSocketFactory(sc.getSocketFactory());
                OkHttpStack okStack = new OkHttpStack(okClient);

                mRequestQueue = Volley.newRequestQueue(context, okStack);
                //mRequestQueue = Volley.newRequestQueue(context, new HttpClientStack(new DefaultHttpClient()));
                //mRequestQueue = Volley.newRequestQueue(context);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                mRequestQueue = Volley.newRequestQueue(context, new OkHttpStack(), DEFAULT_DISK_USAGE_BYTES);
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }
        }

        return mRequestQueue;
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
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
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
