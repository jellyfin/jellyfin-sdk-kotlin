package org.jellyfin.apiclient.model.logging;

public class NullLogger implements ILogger
{
    public final void info(String formatString, Object... paramList)
    {
    }

    public final void error(String formatString, Object... paramList)
    {
    }

    public final void warn(String formatString, Object... paramList)
    {
    }

    public final void debug(String formatString, Object... paramList)
    {
    }

    public final void fatal(String formatString, Object... paramList)
    {
    }

    public final void fatalException(String formatString, Exception exception, Object... paramList)
    {
    }

    public final void Log(LogSeverity severity, String message, Object... paramList)
    {
    }

    public final void errorException(String formatString, Exception exception, Object... paramList)
    {
    }

    public final void LogMultiline(String message, LogSeverity severity, StringBuilder additionalContent)
    {
    }
}
