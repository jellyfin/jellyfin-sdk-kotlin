package MediaBrowser.Model.Updates;

/** 
 Class CheckForUpdateResult
*/
public class CheckForUpdateResult
{
	/** 
	 Gets or sets a value indicating whether this instance is update available.
	 
	 <value><c>true</c> if this instance is update available; otherwise, <c>false</c>.</value>
	*/
	private boolean privateIsUpdateAvailable;
	public final boolean getIsUpdateAvailable()
	{
		return privateIsUpdateAvailable;
	}
	public final void setIsUpdateAvailable(boolean value)
	{
		privateIsUpdateAvailable = value;
	}

	/** 
	 Gets or sets the available version.
	 
	 <value>The available version.</value>
	*/
	public final Version getAvailableVersion()
	{
		return getPackage() != null ? getPackage().getversion() : new Version(0, 0);
	}
	public final void setAvailableVersion(Version value)
	{
	}

	/** 
	 Get or sets package information for an available update
	*/
	private PackageVersionInfo privatePackage;
	public final PackageVersionInfo getPackage()
	{
		return privatePackage;
	}
	public final void setPackage(PackageVersionInfo value)
	{
		privatePackage = value;
	}
}