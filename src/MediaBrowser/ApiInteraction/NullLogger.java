package MediaBrowser.ApiInteraction;

import MediaBrowser.Model.Logging.*;

/** 
 Class NullLogger
*/
public class NullLogger implements ILogger
{
	/** 
	 Debugs the specified message.
	 
	 @param message The message.
	 @param paramList The param list.
	*/
	public final void Debug(String message, Object... paramList)
	{
	}

	/** 
	 Errors the specified message.
	 
	 @param message The message.
	 @param paramList The param list.
	*/
	public final void Error(String message, Object... paramList)
	{
	}

	/** 
	 Errors the exception.
	 
	 @param message The message.
	 @param exception The exception.
	 @param paramList The param list.
	*/
	public final void ErrorException(String message, RuntimeException exception, Object... paramList)
	{
	}

	/** 
	 Fatals the specified message.
	 
	 @param message The message.
	 @param paramList The param list.
	*/
	public final void Fatal(String message, Object... paramList)
	{
	}

	/** 
	 Fatals the exception.
	 
	 @param message The message.
	 @param exception The exception.
	 @param paramList The param list.
	*/
	public final void FatalException(String message, RuntimeException exception, Object... paramList)
	{
	}

	/** 
	 Infoes the specified message.
	 
	 @param message The message.
	 @param paramList The param list.
	*/
	public final void Info(String message, Object... paramList)
	{
	}

	/** 
	 Logs the specified severity.
	 
	 @param severity The severity.
	 @param message The message.
	 @param paramList The param list.
	*/
	public final void Log(LogSeverity severity, String message, Object... paramList)
	{
	}

	/** 
	 Logs the multiline.
	 
	 @param message The message.
	 @param severity The severity.
	 @param additionalContent Content of the additional.
	*/
	public final void LogMultiline(String message, LogSeverity severity, StringBuilder additionalContent)
	{
	}

	/** 
	 Warns the specified message.
	 
	 @param message The message.
	 @param paramList The param list.
	*/
	public final void Warn(String message, Object... paramList)
	{
	}
}