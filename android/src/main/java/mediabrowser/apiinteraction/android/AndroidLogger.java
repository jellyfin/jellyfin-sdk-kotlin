package mediabrowser.apiinteraction.android;

import android.util.Log;

import mediabrowser.model.logging.ILogger;

/**
 * ILogger implementation using the Android Log utility.
 */
public class AndroidLogger implements ILogger {
    private final String tag;

    public AndroidLogger(String tag) {
        this.tag = tag;
    }

    @Override
    public void Info(String message, Object... paramList) {
        Log.i(tag, String.format(message, paramList));
    }

    @Override
    public void Error(String message, Object... paramList) {
        Log.e(tag, String.format(message, paramList));
    }

    @Override
    public void Warn(String message, Object... paramList) {
        Log.w(tag, String.format(message, paramList));
    }

    @Override
    public void Debug(String message, Object... paramList) {
        Log.d(tag, String.format(message, paramList));
    }

    @Override
    public void Fatal(String message, Object... paramList) {
        Log.wtf(tag, String.format(message, paramList));
    }

    @Override
    public void FatalException(String message, Exception exception, Object... paramList) {
        Log.wtf(tag, String.format(message, paramList), exception);
    }

    @Override
    public void ErrorException(String message, Exception exception, Object... paramList) {
        Log.e(tag, String.format(message, paramList), exception);
    }
}
