package org.jellyfin.apiclient.logging;

public class NullLogger implements ILogger {
    @Override
    public final void info(String formatString, Object... paramList) {
    }

    @Override
    public final void error(String formatString, Object... paramList) {
    }

    @Override
    public final void debug(String formatString, Object... paramList) {
    }

    @Override
    public final void exception(String formatString, Exception exception, Object... paramList) {
    }
}
