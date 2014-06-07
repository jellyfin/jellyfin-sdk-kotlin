package MediaBrowser.Model.Logging;

public class NullLogger implements ILogger
{
	public final void Info(String message, Object... paramList)
	{
	}

	public final void Error(String message, Object... paramList)
	{
	}

	public final void Warn(String message, Object... paramList)
	{
	}

	public final void Debug(String message, Object... paramList)
	{
	}

	public final void Fatal(String message, Object... paramList)
	{
	}

	public final void FatalException(String message, RuntimeException exception, Object... paramList)
	{
	}

	public final void Log(LogSeverity severity, String message, Object... paramList)
	{
	}

	public final void ErrorException(String message, RuntimeException exception, Object... paramList)
	{
	}

	public final void LogMultiline(String message, LogSeverity severity, StringBuilder additionalContent)
	{
	}
}