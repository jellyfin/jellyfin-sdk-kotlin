package mediabrowser.model.logging;

/** 
 Enum LogSeverity
*/
public enum LogSeverity
{
	/** 
	 The info
	*/
	Info,
	/** 
	 The debug
	*/
	Debug,
	/** 
	 The warn
	*/
	Warn,
	/** 
	 The error
	*/
	Error,
	/** 
	 The fatal
	*/
	Fatal;

	public int getValue()
	{
		return this.ordinal();
	}

	public static LogSeverity forValue(int value)
	{
		return values()[value];
	}
}