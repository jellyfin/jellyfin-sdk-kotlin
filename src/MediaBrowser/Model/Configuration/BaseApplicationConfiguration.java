package MediaBrowser.Model.Configuration;

import MediaBrowser.Model.Updates.*;

/** 
 Serves as a common base class for the Server and UI application Configurations
 ProtoInclude tells Protobuf about subclasses,
 The number 50 can be any number, so long as it doesn't clash with any of the ProtoMember numbers either here or in subclasses.
*/
public class BaseApplicationConfiguration
{
	/** 
	 Gets or sets a value indicating whether [enable debug level logging].
	 
	 <value><c>true</c> if [enable debug level logging]; otherwise, <c>false</c>.</value>
	*/
	private boolean EnableDebugLevelLogging;
	public final boolean getEnableDebugLevelLogging()
	{
		return EnableDebugLevelLogging;
	}
	public final void setEnableDebugLevelLogging(boolean value)
	{
		EnableDebugLevelLogging = value;
	}

	/** 
	 Enable automatically and silently updating of the application
	 
	 <value><c>true</c> if [enable auto update]; otherwise, <c>false</c>.</value>
	*/
	private boolean EnableAutoUpdate;
	public final boolean getEnableAutoUpdate()
	{
		return EnableAutoUpdate;
	}
	public final void setEnableAutoUpdate(boolean value)
	{
		EnableAutoUpdate = value;
	}

	/** 
	 Gets of sets a value indicating the level of system updates (Release, Beta, Dev)
	*/
	private PackageVersionClass SystemUpdateLevel = PackageVersionClass.values()[0];
	public final PackageVersionClass getSystemUpdateLevel()
	{
		return SystemUpdateLevel;
	}
	public final void setSystemUpdateLevel(PackageVersionClass value)
	{
		SystemUpdateLevel = value;
	}

	/** 
	 The number of days we should retain log files
	 
	 <value>The log file retention days.</value>
	*/
	private int LogFileRetentionDays;
	public final int getLogFileRetentionDays()
	{
		return LogFileRetentionDays;
	}
	public final void setLogFileRetentionDays(int value)
	{
		LogFileRetentionDays = value;
	}

	/** 
	 Gets or sets a value indicating whether [run at startup].
	 
	 <value><c>true</c> if [run at startup]; otherwise, <c>false</c>.</value>
	*/
	private boolean RunAtStartup;
	public final boolean getRunAtStartup()
	{
		return RunAtStartup;
	}
	public final void setRunAtStartup(boolean value)
	{
		RunAtStartup = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is first run.
	 
	 <value><c>true</c> if this instance is first run; otherwise, <c>false</c>.</value>
	*/
	private boolean IsStartupWizardCompleted;
	public final boolean getIsStartupWizardCompleted()
	{
		return IsStartupWizardCompleted;
	}
	public final void setIsStartupWizardCompleted(boolean value)
	{
		IsStartupWizardCompleted = value;
	}

	/** 
	 Gets or sets the cache path.
	 
	 <value>The cache path.</value>
	*/
	private String CachePath;
	public final String getCachePath()
	{
		return CachePath;
	}
	public final void setCachePath(String value)
	{
		CachePath = value;
	}

	/** 
	 Initializes a new instance of the <see cref="BaseApplicationConfiguration" /> class.
	*/
	public BaseApplicationConfiguration()
	{
		setEnableAutoUpdate(true);
		setLogFileRetentionDays(3);

//C# TO JAVA CONVERTER TODO TASK: There is no preprocessor in Java:
//#if (DEBUG)
		setEnableDebugLevelLogging(true);
//#endif
	}
}