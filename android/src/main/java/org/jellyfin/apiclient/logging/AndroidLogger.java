package org.jellyfin.apiclient.logging;

import android.util.Log;

import org.jellyfin.apiclient.model.logging.ILogger;

/**
 * ILogger implementation using the Android Log utility.
 */
public class AndroidLogger implements ILogger {
    private final String tag;

    public AndroidLogger(String tag) {
        this.tag = tag;
    }

    @Override
    public void info(String formatString, Object... paramList) {
        if (paramList.length > 0)
            Log.i(tag, String.format(formatString, paramList));
        else
            Log.i(tag, formatString);
    }

    @Override
    public void error(String formatString, Object... paramList) {
        if (paramList.length > 0)
            Log.e(tag, String.format(formatString, paramList));
        else
            Log.e(tag, formatString);
    }

    @Override
    public void warn(String formatString, Object... paramList) {
        if (paramList.length > 0)
            Log.w(tag, String.format(formatString, paramList));
        else
            Log.w(tag, formatString);
    }

    @Override
    public void debug(String formatString, Object... paramList) {
        if (paramList.length > 0)
            Log.d(tag, String.format(formatString, paramList));
        else
            Log.d(tag, formatString);
    }

    @Override
    public void fatal(String formatString, Object... paramList) {
        if (paramList.length > 0)
            Log.wtf(tag, String.format(formatString, paramList));
        else
            Log.wtf(tag, formatString);
    }

    @Override
    public void fatalException(String formatString, Exception exception, Object... paramList) {
        if (paramList.length > 0)
            Log.wtf(tag, String.format(formatString, paramList), exception);
        else
            Log.wtf(tag, formatString, exception);
    }

    @Override
    public void errorException(String formatString, Exception exception, Object... paramList) {
        if (paramList.length > 0)
            Log.e(tag, String.format(formatString, paramList), exception);
        else
            Log.e(tag, formatString, exception);
    }
}
