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
	private boolean privateEnableDebugLevelLogging;
	public final boolean getEnableDebugLevelLogging()
	{
		return privateEnableDebugLevelLogging;
	}
	public final void setEnableDebugLevelLogging(boolean value)
	{
		privateEnableDebugLevelLogging = value;
	}

	/** 
	 Enable automatically and silently updating of the application
	 
	 <value><c>true</c> if [enable auto update]; otherwise, <c>false</c>.</value>
	*/
	private boolean privateEnableAutoUpdate;
	public final boolean getEnableAutoUpdate()
	{
		return privateEnableAutoUpdate;
	}
	public final void setEnableAutoUpdate(boolean value)
	{
		privateEnableAutoUpdate = value;
	}

	/** 
	 Gets of sets a value indicating the level of system updates (Release, Beta, Dev)
	*/
	private PackageVersionClass privateSystemUpdateLevel = PackageVersionClass.values()[0];
	public final PackageVersionClass getSystemUpdateLevel()
	{
		return privateSystemUpdateLevel;
	}
	public final void setSystemUpdateLevel(PackageVersionClass value)
	{
		privateSystemUpdateLevel = value;
	}

	/** 
	 The number of days we should retain log files
	 
	 <value>The log file retention days.</value>
	*/
	private int privateLogFileRetentionDays;
	public final int getLogFileRetentionDays()
	{
		return privateLogFileRetentionDays;
	}
	public final void setLogFileRetentionDays(int value)
	{
		privateLogFileRetentionDays = value;
	}

	/** 
	 Gets or sets a value indicating whether [run at startup].
	 
	 <value><c>true</c> if [run at startup]; otherwise, <c>false</c>.</value>
	*/
	private boolean privateRunAtStartup;
	public final boolean getRunAtStartup()
	{
		return privateRunAtStartup;
	}
	public final void setRunAtStartup(boolean value)
	{
		privateRunAtStartup = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is first run.
	 
	 <value><c>true</c> if this instance is first run; otherwise, <c>false</c>.</value>
	*/
	private boolean privateIsStartupWizardCompleted;
	public final boolean getIsStartupWizardCompleted()
	{
		return privateIsStartupWizardCompleted;
	}
	public final void setIsStartupWizardCompleted(boolean value)
	{
		privateIsStartupWizardCompleted = value;
	}

	/** 
	 Gets or sets the cache path.
	 
	 <value>The cache path.</value>
	*/
	private String privateCachePath;
	public final String getCachePath()
	{
		return privateCachePath;
	}
	public final void setCachePath(String value)
	{
		privateCachePath = value;
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