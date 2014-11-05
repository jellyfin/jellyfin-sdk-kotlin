package MediaBrowser.ApiInteraction.Android;

import MediaBrowser.ApiInteraction.HttpHeaders;
import MediaBrowser.ApiInteraction.HttpRequest;
import MediaBrowser.ApiInteraction.IAsyncHttpClient;
import MediaBrowser.ApiInteraction.Response;
import MediaBrowser.Model.Logging.ILogger;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import com.android.volley.*;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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
            mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
                private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);
                public void putBitmap(String url, Bitmap bitmap) {
                    mCache.put(url, bitmap);
                }
                public Bitmap getBitmap(String url) {
                    return mCache.get(url);
                }
            });
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

    private void AddHeaders(Map<String, String> headers, HttpRequest request)
    {
        HttpHeaders requestHeaders = request.getRequestHeaders();

        for (String key : requestHeaders.keySet()){
            headers.put(key, requestHeaders.get(key));
        }

        if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(request.getRequestContentType()))
        {
            headers.put("Content-Type", request.getRequestContentType());
        }

        String parameter = requestHeaders.getAuthorizationParameter();

        if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(parameter))
        {
            String value = requestHeaders.getAuthorizationScheme() + " " + parameter;

            headers.put("Authorization", value);
        }
    }

    private void AddData(Map<String, String> postParams, HttpRequest request)
    {
        if (request.getPostData() == null){
            return;
        }

        for (String key : request.getPostData().keySet()){
            postParams.put(key, request.getPostData().get(key));
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

        String url = request.getUrl();

        StringRequest req = new StringRequest(method, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String stringResponse) {

                logger.Info("Response:%n %s", stringResponse);
                response.onResponse(stringResponse);
            }

        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if (error instanceof TimeoutError) {
                    logger.Error("VolleyError TimeoutError: ", error.getMessage());
                } else if (error instanceof NoConnectionError) {
                    logger.Error("VolleyError NoConnectionError: ", error.getMessage());
                } else if (error instanceof AuthFailureError) {
                    logger.Error("VolleyError AuthFailureError: ", error.getMessage());
                } else if (error instanceof ServerError) {
                    logger.Error("VolleyError ServerError: ", error.getMessage());
                } else if (error instanceof NetworkError) {
                    logger.Error("VolleyError NetworkError: ", error.getMessage());
                } else if (error instanceof ParseError) {
                    logger.Error("VolleyError ParseError: ", error.getMessage());
                }

                response.onError();
            }
        }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String,String>();
                AddHeaders(headers, request);
                return headers;
            }

            @Override
            public Map<String, String> getParams() throws AuthFailureError {

                if (request.getPostData() == null){
                    super.getParams();
                }

                Map<String, String> data = new HashMap<String,String>();
                AddData(data, request);
                return data;
            }

            @Override
            public String getBodyContentType() {

                if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(request.getRequestContentType()))
                {
                    return request.getRequestContentType();
                }

                return super.getBodyContentType();
            }

            @Override
            /**
             * Returns the raw POST or PUT body to be sent.
             * @throws AuthFailureError in the event of auth failure
             */
            public byte[] getBody() throws AuthFailureError {

                String postContent = request.getRequestContent();

                if (postContent == null){
                    return super.getBody();
                }

                return postContent.getBytes();
            }
        };

        // add the request object to the queue to be executed
        addToRequestQueue(req);
    }
}
