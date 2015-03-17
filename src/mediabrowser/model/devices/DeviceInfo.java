package mediabrowser.model.devices;

import mediabrowser.model.session.*;

public class DeviceInfo
{
	/** 
	 Gets or sets the name of the reported.
	 
	 <value>The name of the reported.</value>
	*/
	private String ReportedName;
	public final String getReportedName()
	{
		return ReportedName;
	}
	public final void setReportedName(String value)
	{
		ReportedName = value;
	}
	/** 
	 Gets or sets the name of the custom.
	 
	 <value>The name of the custom.</value>
	*/
	private String CustomName;
	public final String getCustomName()
	{
		return CustomName;
	}
	public final void setCustomName(String value)
	{
		CustomName = value;
	}
	/** 
	 Gets or sets the camera upload path.
	 
	 <value>The camera upload path.</value>
	*/
	private String CameraUploadPath;
	public final String getCameraUploadPath()
	{
		return CameraUploadPath;
	}
	public final void setCameraUploadPath(String value)
	{
		CameraUploadPath = value;
	}

	/** 
	 Gets the name.
	 
	 <value>The name.</value>
	*/
	public final String getName()
	{
		return tangible.DotNetToJavaStringHelper.isNullOrEmpty(getCustomName()) ? getReportedName() : getCustomName();
	}

	/** 
	 Gets or sets the identifier.
	 
	 <value>The identifier.</value>
	*/
	private String Id;
	public final String getId()
	{
		return Id;
	}
	public final void setId(String value)
	{
		Id = value;
	}
	/** 
	 Gets or sets the last name of the user.
	 
	 <value>The last name of the user.</value>
	*/
	private String LastUserName;
	public final String getLastUserName()
	{
		return LastUserName;
	}
	public final void setLastUserName(String value)
	{
		LastUserName = value;
	}
	/** 
	 Gets or sets the name of the application.
	 
	 <value>The name of the application.</value>
	*/
	private String AppName;
	public final String getAppName()
	{
		return AppName;
	}
	public final void setAppName(String value)
	{
		AppName = value;
	}
	/** 
	 Gets or sets the application version.
	 
	 <value>The application version.</value>
	*/
	private String AppVersion;
	public final String getAppVersion()
	{
		return AppVersion;
	}
	public final void setAppVersion(String value)
	{
		AppVersion = value;
	}
	/** 
	 Gets or sets the last user identifier.
	 
	 <value>The last user identifier.</value>
	*/
	private String LastUserId;
	public final String getLastUserId()
	{
		return LastUserId;
	}
	public final void setLastUserId(String value)
	{
		LastUserId = value;
	}
	/** 
	 Gets or sets the date last modified.
	 
	 <value>The date last modified.</value>
	*/
	private java.util.Date DateLastModified = new java.util.Date(0);
	public final java.util.Date getDateLastModified()
	{
		return DateLastModified;
	}
	public final void setDateLastModified(java.util.Date value)
	{
		DateLastModified = value;
	}
	/** 
	 Gets or sets the capabilities.
	 
	 <value>The capabilities.</value>
	*/
	private ClientCapabilities Capabilities;
	public final ClientCapabilities getCapabilities()
	{
		return Capabilities;
	}
	public final void setCapabilities(ClientCapabilities value)
	{
		Capabilities = value;
	}

	public DeviceInfo()
	{
		setCapabilities(new ClientCapabilities());
	}
}