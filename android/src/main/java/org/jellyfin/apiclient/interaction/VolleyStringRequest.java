package org.jellyfin.apiclient.interaction;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import org.jellyfin.apiclient.interaction.http.HttpHeaders;
import org.jellyfin.apiclient.interaction.http.HttpRequest;

import java.util.HashMap;
import java.util.Map;

public class VolleyStringRequest extends StringRequest {

    private HttpRequest request;

    public VolleyStringRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener, HttpRequest request) {
        super(method, url, listener, errorListener);
        this.request = request;
    }

    public VolleyStringRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener, HttpRequest request) {
        super(url, listener, errorListener);
        this.request = request;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<String,String>();
        AddHeaders(headers, request);
        return headers;
    }

    @Override
    public Map<String, String> getParams() throws AuthFailureError {

        if (request.getPostData() == null) {
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

        if (postContent == null) {
            return super.getBody();
        }

        return postContent.getBytes();
    }

    @Override
    protected com.android.volley.Response<String> parseNetworkResponse(NetworkResponse response) {

        String contentType = response.headers.get("Content-Type");

        // This is a hack to make volley decode in UTF-8
        if (contentType.equalsIgnoreCase("application/json")) {
            response.headers.put("Content-Type", contentType + "; charset=UTF-8");
        }
        else if (contentType.equalsIgnoreCase("text/plain")) {
            response.headers.put("Content-Type", contentType + "; charset=UTF-8");
        }
        else if (contentType.equalsIgnoreCase("text/vtt")) {
            response.headers.put("Content-Type", contentType + "; charset=UTF-8");
        }

        return super.parseNetworkResponse(response);
    }

    private void AddHeaders(Map<String, String> headers, HttpRequest request)
    {
        HttpHeaders requestHeaders = request.getRequestHeaders();

        for (String key : requestHeaders.keySet()) {
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

            headers.put("X-Emby-Authorization", value);
        }
    }

    private void AddData(Map<String, String> postParams, HttpRequest request)
    {
        if (request.getPostData() == null) {
            return;
        }

        for (String key : request.getPostData().keySet()) {
            postParams.put(key, request.getPostData().get(key));
        }
    }
}
