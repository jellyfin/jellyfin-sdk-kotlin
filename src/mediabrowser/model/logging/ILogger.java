package mediabrowser.model.logging;

/** 
 Interface ILogger
*/
public interface ILogger
{
	/** 
	 Infoes the specified message.
	 
	 @param message The message.
	 @param paramList The param list.
	*/
	void Info(String message, Object... paramList);

	/** 
	 Errors the specified message.
	 
	 @param message The message.
	 @param paramList The param list.
	*/
	void Error(String message, Object... paramList);

	/** 
	 Warns the specified message.
	 
	 @param message The message.
	 @param paramList The param list.
	*/
	void Warn(String message, Object... paramList);

	/** 
	 Debugs the specified message.
	 
	 @param message The message.
	 @param paramList The param list.
	*/
	void Debug(String message, Object... paramList);

	/** 
	 Fatals the specified message.
	 
	 @param message The message.
	 @param paramList The param list.
	*/
	void Fatal(String message, Object... paramList);

	/** 
	 Fatals the exception.
	 
	 @param message The message.
	 @param exception The exception.
	 @param paramList The param list.
	*/
	void FatalException(String message, RuntimeException exception, Object... paramList);

	/** 
	 Logs the exception.
	 
	 @param message The message.
	 @param exception The exception.
	 @param paramList The param list.
	*/
	void ErrorException(String message, RuntimeException exception, Object... paramList);

	/** 
	 Logs the multiline.
	 
	 @param message The message.
	 @param severity The severity.
	 @param additionalContent Content of the additional.
	*/
	void LogMultiline(String message, LogSeverity severity, StringBuilder additionalContent);

	/** 
	 Logs the specified severity.
	 
	 @param severity The severity.
	 @param message The message.
	 @param paramList The parameter list.
	*/
	void Log(LogSeverity severity, String message, Object... paramList);
}