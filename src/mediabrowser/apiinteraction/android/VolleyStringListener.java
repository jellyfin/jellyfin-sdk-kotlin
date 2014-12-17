package mediabrowser.apiinteraction.android;

import com.android.volley.Response;
import mediabrowser.model.logging.ILogger;

public class VolleyStringListener implements Response.Listener<String> {

    private mediabrowser.apiinteraction.Response<String> outerResponse;
    private ILogger logger;
    private String url;

    public VolleyStringListener(mediabrowser.apiinteraction.Response<String> outerResponse, ILogger logger, String url) {
        this.outerResponse = outerResponse;
        this.logger = logger;
        this.url = url;
    }

    @Override
    public void onResponse(String s) {

        logger.Info("Response received from: ", url);

        outerResponse.onResponse(s);
    }

}
