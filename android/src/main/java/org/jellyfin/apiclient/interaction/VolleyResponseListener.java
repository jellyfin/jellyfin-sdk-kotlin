package org.jellyfin.apiclient.interaction;

import com.android.volley.Response;

import org.jellyfin.apiclient.model.logging.ILogger;

public class VolleyResponseListener<T> implements Response.Listener<T> {

    private org.jellyfin.apiclient.interaction.Response<T> outerResponse;
    private ILogger logger;
    private String url;

    public VolleyResponseListener(org.jellyfin.apiclient.interaction.Response<T> outerResponse, ILogger logger, String url) {
        this.outerResponse = outerResponse;
        this.logger = logger;
        this.url = url;
    }

    @Override
    public void onResponse(T s) {
        logger.info("Response received from: %s", url);
        outerResponse.onResponse(s);
    }
}
