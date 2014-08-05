package MediaBrowser.ApiInteraction;

import MediaBrowser.Model.Logging.ILogger;
import android.content.Context;
import android.text.TextUtils;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
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

    private ILogger logger;
    private Context context;

    HashMap<String, String> globalHeaders = new HashMap<String, String>();

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
        }

        return mRequestQueue;
    }

    /**
     * Adds the specified request to the global queue, if tag is specified
     * then it is used else Default TAG is used.
     *
     * @param req
     * @param tag
     */
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);

        logger.Debug("Adding request to queue: %s", req.getUrl());

        getRequestQueue().add(req);
    }

    /**
     * Adds the specified request to the global queue using the Default TAG.
     *
     * @param req
     * @param tag
     */
    public <T> void addToRequestQueue(Request<T> req) {

        // set the default tag if tag is empty
        //req.setTag(TAG);

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

    @Override
    public void SetAuthorizationHeader(String scheme, String parameter) {

        String value = scheme + " " + parameter;

        SetHttpRequestHeader("Authorization", value);
    }

    @Override
    public void SetHttpRequestHeader(String name, String value) {
        globalHeaders.put(name, value);
    }

    @Override
    public void ClearHttpRequestHeader(String name) {
        globalHeaders.remove(name);
    }

    private void AddHeaders(HashMap<String, String> headers)
    {
        for (String key : globalHeaders.keySet()){
            headers.put(key, globalHeaders.get(key));
        }
    }

    private void SendAsync(int method, String url, final Response<String> response)
    {
        StringRequest req = new StringRequest(method, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String stringResponse) {
                logger.Debug("Response:%n %s", stringResponse);
                response.onResponse(stringResponse);
            }

        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                logger.Error("Error: ", error.getMessage());
                response.onError();
            }
        }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                AddHeaders(headers);
                return headers;
            }
        };

        // add the request object to the queue to be executed
        addToRequestQueue(req);
    }


    @Override
    public void GetAsync(String url, final Response<String> response) {

        SendAsync(Request.Method.GET, url, response);
    }

    @Override
    public void DeleteAsync(String url, Response<String> response) {
        SendAsync(Request.Method.DELETE, url, response);
    }

    @Override
    public void PostAsync(String url, String contentType, String postContent, Response<String> response) {

    }

}
