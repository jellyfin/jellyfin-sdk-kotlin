package org.jellyfin.apiclient.logging;

/**
 * ILogger implementation that does nothing
 */
public class NullLogger implements ILogger {
    @Override
    public final void info(String formatString, Object... paramList) {
        // Do nothing
    }

    @Override
    public final void error(String formatString, Object... paramList) {
        // Do nothing
    }

    @Override
    public final void debug(String formatString, Object... paramList) {
        // Do nothing
    }

    @Override
    public final void exception(String formatString, Exception exception, Object... paramList) {
        // Do nothing
    }
}
