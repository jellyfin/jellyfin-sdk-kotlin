package org.jellyfin.apiclient.interaction;

import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import org.jellyfin.apiclient.logging.ILogger;
import org.jellyfin.apiclient.model.net.HttpException;

public class VolleyErrorListener<T> implements Response.ErrorListener {

    private org.jellyfin.apiclient.interaction.Response<T> outerResponse;
    private ILogger logger;

    public VolleyErrorListener(org.jellyfin.apiclient.interaction.Response<T> outerResponse, ILogger logger) {
        this.outerResponse = outerResponse;
        this.logger = logger;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        String messagePrefix = "VolleyError " + error.getClass().getName() + ": ";
        logger.error(messagePrefix + error.getMessage(), error);

        HttpException httpException = new HttpException(messagePrefix, error);

        if (error.networkResponse != null) {
            httpException.setStatusCode(error.networkResponse.statusCode);
            httpException.setHeaders(error.networkResponse.headers);
        }

        if (error instanceof TimeoutError) {
            httpException.setIsTimedOut(true);
        }

        outerResponse.onError(httpException);
    }
}
