package mediabrowser.model.logging;

/** 
 Interface ILogManager
*/
public interface ILogManager
{
	/** 
	 Gets or sets the log level.
	 
	 <value>The log level.</value>
	*/
	LogSeverity getLogSeverity();
	void setLogSeverity(LogSeverity value);

	/** 
	 Gets the logger.
	 
	 @param name The name.
	 @return ILogger.
	*/
	ILogger GetLogger(String name);

	/** 
	 Reloads the logger.
	*/
	void ReloadLogger(LogSeverity severity);

	/** 
	 Gets the log file path.
	 
	 <value>The log file path.</value>
	*/
	String getLogFilePath();

	/** 
	 Occurs when [logger loaded].
	*/
//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	event EventHandler LoggerLoaded;

	/** 
	 Flushes this instance.
	*/
	void Flush();

	/** 
	 Adds the console output.
	*/
	void AddConsoleOutput();

	/** 
	 Removes the console output.
	*/
	void RemoveConsoleOutput();
}