package mediabrowser.model.updates;

/** 
 Class CheckForUpdateResult
*/
public class CheckForUpdateResult
{
	/** 
	 Gets or sets a value indicating whether this instance is update available.
	 
	 <value><c>true</c> if this instance is update available; otherwise, <c>false</c>.</value>
	*/
	private boolean IsUpdateAvailable;
	public final boolean getIsUpdateAvailable()
	{
		return IsUpdateAvailable;
	}
	public final void setIsUpdateAvailable(boolean value)
	{
		IsUpdateAvailable = value;
	}

	/** 
	 Gets or sets the available version.
	 
	 <value>The available version.</value>
	*/
	public final String getAvailableVersion()
	{
		return getPackage() != null ? getPackage().getversionStr() : "0.0.0.1";
	}
	public final void setAvailableVersion(String value)
	{
	}

	/** 
	 Get or sets package information for an available update
	*/
	private PackageVersionInfo Package;
	public final PackageVersionInfo getPackage()
	{
		return Package;
	}
	public final void setPackage(PackageVersionInfo value)
	{
		Package = value;
	}
}