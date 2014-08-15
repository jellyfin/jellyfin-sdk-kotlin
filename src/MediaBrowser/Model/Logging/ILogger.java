package MediaBrowser.Model.Logging;

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
	void FatalException(String message, Exception exception, Object... paramList);

	/** 
	 Logs the exception.
	 
	 @param message The message.
	 @param exception The exception.
	 @param paramList The param list.
	*/
	void ErrorException(String message, Exception exception, Object... paramList);
}