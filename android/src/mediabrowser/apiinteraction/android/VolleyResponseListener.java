package mediabrowser.apiinteraction.android;

import com.android.volley.Response;
import mediabrowser.model.logging.ILogger;

public class VolleyResponseListener<T> implements Response.Listener<T> {

    private mediabrowser.apiinteraction.Response<T> outerResponse;
    private ILogger logger;
    private String url;

    public VolleyResponseListener(mediabrowser.apiinteraction.Response<T> outerResponse, ILogger logger, String url) {
        this.outerResponse = outerResponse;
        this.logger = logger;
        this.url = url;
    }

    @Override
    public void onResponse(T s) {

        logger.Info("Response received from: %s", url);

        outerResponse.onResponse(s);
    }

}
