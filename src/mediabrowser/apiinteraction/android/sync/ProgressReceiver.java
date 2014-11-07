package mediabrowser.apiinteraction.android.sync;

import mediabrowser.apiinteraction.android.ProgressState;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

public class ProgressReceiver extends ResultReceiver {

    public ProgressReceiver(Handler handler) {
        super(handler);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {

        super.onReceiveResult(resultCode, resultData);

        if (resultCode == ProgressState.UPDATE_PROGRESS) {

            double progressPercent = resultData.getDouble("progress");
            onProgress(progressPercent);
        }
        else if (resultCode == ProgressState.UPDATE_COMPLETE) {

            onComplete();
        }
        else if (resultCode == ProgressState.UPDATE_CANCEL) {

            onCancel();
        }
    }

    protected void onProgress(double progress){

    }

    protected void onComplete(){

    }

    protected void onCancel(){

    }

    protected void onError(Exception ex){

    }
}