package MediaBrowser.Model.System;

import MediaBrowser.Model.Updates.*;

/** 
 Class SystemInfo
*/
public class SystemInfo extends PublicSystemInfo
{
	/** 
	 Gets or sets the operating sytem.
	 
	 <value>The operating sytem.</value>
	*/
	private String privateOperatingSystem;
	public final String getOperatingSystem()
	{
		return privateOperatingSystem;
	}
	public final void setOperatingSystem(String value)
	{
		privateOperatingSystem = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is running as service.
	 
	 <value><c>true</c> if this instance is running as service; otherwise, <c>false</c>.</value>
	*/
	private boolean privateIsRunningAsService;
	public final boolean getIsRunningAsService()
	{
		return privateIsRunningAsService;
	}
	public final void setIsRunningAsService(boolean value)
	{
		privateIsRunningAsService = value;
	}

	/** 
	 Gets or sets a value indicating whether [supports running as service].
	 
	 <value><c>true</c> if [supports running as service]; otherwise, <c>false</c>.</value>
	*/
	private boolean privateSupportsRunningAsService;
	public final boolean getSupportsRunningAsService()
	{
		return privateSupportsRunningAsService;
	}
	public final void setSupportsRunningAsService(boolean value)
	{
		privateSupportsRunningAsService = value;
	}

	/** 
	 Gets or sets the mac address.
	 
	 <value>The mac address.</value>
	*/
	private String privateMacAddress;
	public final String getMacAddress()
	{
		return privateMacAddress;
	}
	public final void setMacAddress(String value)
	{
		privateMacAddress = value;
	}

	/** 
	 Gets or sets the local address.
	 
	 <value>The local address.</value>
	*/
	private String privateLocalAddress;
	public final String getLocalAddress()
	{
		return privateLocalAddress;
	}
	public final void setLocalAddress(String value)
	{
		privateLocalAddress = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance has pending restart.
	 
	 <value><c>true</c> if this instance has pending restart; otherwise, <c>false</c>.</value>
	*/
	private boolean privateHasPendingRestart;
	public final boolean getHasPendingRestart()
	{
		return privateHasPendingRestart;
	}
	public final void setHasPendingRestart(boolean value)
	{
		privateHasPendingRestart = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is network deployed.
	 
	 <value><c>true</c> if this instance is network deployed; otherwise, <c>false</c>.</value>
	*/
	private boolean privateIsNetworkDeployed;
	public final boolean getIsNetworkDeployed()
	{
		return privateIsNetworkDeployed;
	}
	public final void setIsNetworkDeployed(boolean value)
	{
		privateIsNetworkDeployed = value;
	}

	/** 
	 Gets or sets the in progress installations.
	 
	 <value>The in progress installations.</value>
	*/
	private java.util.ArrayList<InstallationInfo> privateInProgressInstallations;
	public final java.util.ArrayList<InstallationInfo> getInProgressInstallations()
	{
		return privateInProgressInstallations;
	}
	public final void setInProgressInstallations(java.util.ArrayList<InstallationInfo> value)
	{
		privateInProgressInstallations = value;
	}

	/** 
	 Gets or sets the web socket port number.
	 
	 <value>The web socket port number.</value>
	*/
	private int privateWebSocketPortNumber;
	public final int getWebSocketPortNumber()
	{
		return privateWebSocketPortNumber;
	}
	public final void setWebSocketPortNumber(int value)
	{
		privateWebSocketPortNumber = value;
	}

	/** 
	 Gets or sets the completed installations.
	 
	 <value>The completed installations.</value>
	*/
	private java.util.ArrayList<InstallationInfo> privateCompletedInstallations;
	public final java.util.ArrayList<InstallationInfo> getCompletedInstallations()
	{
		return privateCompletedInstallations;
	}
	public final void setCompletedInstallations(java.util.ArrayList<InstallationInfo> value)
	{
		privateCompletedInstallations = value;
	}

	/** 
	 Gets or sets a value indicating whether [supports native web socket].
	 
	 <value><c>true</c> if [supports native web socket]; otherwise, <c>false</c>.</value>
	*/
	private boolean privateSupportsNativeWebSocket;
	public final boolean getSupportsNativeWebSocket()
	{
		return privateSupportsNativeWebSocket;
	}
	public final void setSupportsNativeWebSocket(boolean value)
	{
		privateSupportsNativeWebSocket = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance can self restart.
	 
	 <value><c>true</c> if this instance can self restart; otherwise, <c>false</c>.</value>
	*/
	private boolean privateCanSelfRestart;
	public final boolean getCanSelfRestart()
	{
		return privateCanSelfRestart;
	}
	public final void setCanSelfRestart(boolean value)
	{
		privateCanSelfRestart = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance can self update.
	 
	 <value><c>true</c> if this instance can self update; otherwise, <c>false</c>.</value>
	*/
	private boolean privateCanSelfUpdate;
	public final boolean getCanSelfUpdate()
	{
		return privateCanSelfUpdate;
	}
	public final void setCanSelfUpdate(boolean value)
	{
		privateCanSelfUpdate = value;
	}

	/** 
	 Gets or sets plugin assemblies that failed to load.
	 
	 <value>The failed assembly loads.</value>
	*/
	private java.util.ArrayList<String> privateFailedPluginAssemblies;
	public final java.util.ArrayList<String> getFailedPluginAssemblies()
	{
		return privateFailedPluginAssemblies;
	}
	public final void setFailedPluginAssemblies(java.util.ArrayList<String> value)
	{
		privateFailedPluginAssemblies = value;
	}

	/** 
	 Gets or sets the program data path.
	 
	 <value>The program data path.</value>
	*/
	private String privateProgramDataPath;
	public final String getProgramDataPath()
	{
		return privateProgramDataPath;
	}
	public final void setProgramDataPath(String value)
	{
		privateProgramDataPath = value;
	}

	/** 
	 Gets or sets the items by name path.
	 
	 <value>The items by name path.</value>
	*/
	private String privateItemsByNamePath;
	public final String getItemsByNamePath()
	{
		return privateItemsByNamePath;
	}
	public final void setItemsByNamePath(String value)
	{
		privateItemsByNamePath = value;
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
	 Gets or sets the log path.
	 
	 <value>The log path.</value>
	*/
	private String privateLogPath;
	public final String getLogPath()
	{
		return privateLogPath;
	}
	public final void setLogPath(String value)
	{
		privateLogPath = value;
	}

	/** 
	 Gets or sets the internal metadata path.
	 
	 <value>The internal metadata path.</value>
	*/
	private String privateInternalMetadataPath;
	public final String getInternalMetadataPath()
	{
		return privateInternalMetadataPath;
	}
	public final void setInternalMetadataPath(String value)
	{
		privateInternalMetadataPath = value;
	}

	/** 
	 Gets or sets the transcoding temporary path.
	 
	 <value>The transcoding temporary path.</value>
	*/
	private String privateTranscodingTempPath;
	public final String getTranscodingTempPath()
	{
		return privateTranscodingTempPath;
	}
	public final void setTranscodingTempPath(String value)
	{
		privateTranscodingTempPath = value;
	}

	/** 
	 Gets or sets the HTTP server port number.
	 
	 <value>The HTTP server port number.</value>
	*/
	private int privateHttpServerPortNumber;
	public final int getHttpServerPortNumber()
	{
		return privateHttpServerPortNumber;
	}
	public final void setHttpServerPortNumber(int value)
	{
		privateHttpServerPortNumber = value;
	}

	/** 
	 Gets or sets the wan address.
	 
	 <value>The wan address.</value>
	*/
	private String privateWanAddress;
	public final String getWanAddress()
	{
		return privateWanAddress;
	}
	public final void setWanAddress(String value)
	{
		privateWanAddress = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance has update available.
	 
	 <value><c>true</c> if this instance has update available; otherwise, <c>false</c>.</value>
	*/
	private boolean privateHasUpdateAvailable;
	public final boolean getHasUpdateAvailable()
	{
		return privateHasUpdateAvailable;
	}
	public final void setHasUpdateAvailable(boolean value)
	{
		privateHasUpdateAvailable = value;
	}

	/** 
	 Gets or sets a value indicating whether [supports automatic run at startup].
	 
	 <value><c>true</c> if [supports automatic run at startup]; otherwise, <c>false</c>.</value>
	*/
	private boolean privateSupportsAutoRunAtStartup;
	public final boolean getSupportsAutoRunAtStartup()
	{
		return privateSupportsAutoRunAtStartup;
	}
	public final void setSupportsAutoRunAtStartup(boolean value)
	{
		privateSupportsAutoRunAtStartup = value;
	}

	/** 
	 Initializes a new instance of the <see cref="SystemInfo" /> class.
	*/
	public SystemInfo()
	{
		setInProgressInstallations(new java.util.ArrayList<InstallationInfo>());

		setCompletedInstallations(new java.util.ArrayList<InstallationInfo>());

		setFailedPluginAssemblies(new java.util.ArrayList<String>());
	}
}