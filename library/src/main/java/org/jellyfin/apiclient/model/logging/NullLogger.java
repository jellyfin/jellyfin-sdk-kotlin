package org.jellyfin.apiclient.model.logging;

public class NullLogger implements ILogger
{
    public final void Info(String formatString, Object... paramList)
    {
    }

    public final void Error(String formatString, Object... paramList)
    {
    }

    public final void Warn(String formatString, Object... paramList)
    {
    }

    public final void Debug(String formatString, Object... paramList)
    {
    }

    public final void Fatal(String formatString, Object... paramList)
    {
    }

    public final void FatalException(String formatString, Exception exception, Object... paramList)
    {
    }

    public final void Log(LogSeverity severity, String message, Object... paramList)
    {
    }

    public final void ErrorException(String formatString, Exception exception, Object... paramList)
    {
    }

    public final void LogMultiline(String message, LogSeverity severity, StringBuilder additionalContent)
    {
    }
}