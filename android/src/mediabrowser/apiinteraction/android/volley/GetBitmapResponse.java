package mediabrowser.apiinteraction.android.volley;

import android.graphics.Bitmap;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import mediabrowser.apiinteraction.Response;

/**
 * Created by Luke on 6/11/2015.
 */
public class GetBitmapResponse implements ImageLoader.ImageListener {

    private Response<Bitmap> outerResponse;

    public GetBitmapResponse(Response<Bitmap> outerResponse) {
        this.outerResponse = outerResponse;
    }

    @Override
    public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
        outerResponse.onResponse(response.getBitmap());
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        outerResponse.onError(error);
    }

}
