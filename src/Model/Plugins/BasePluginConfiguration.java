package MediaBrowser.Model.Plugins;

import MediaBrowser.Model.Updates.*;

/** 
 Class BasePluginConfiguration
*/
public class BasePluginConfiguration
{
	/** 
	 Whether or not this plug-in should be automatically updated when a
	 compatible new version is released
	 
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
	 The classification of updates to which to subscribe.
	 Options are: Dev, Beta or Release
	 
	 <value>The update class.</value>
	*/
	private PackageVersionClass privateUpdateClass = PackageVersionClass.values()[0];
	public final PackageVersionClass getUpdateClass()
	{
		return privateUpdateClass;
	}
	public final void setUpdateClass(PackageVersionClass value)
	{
		privateUpdateClass = value;
	}

	/** 
	 Initializes a new instance of the <see cref="BasePluginConfiguration" /> class.
	*/
	public BasePluginConfiguration()
	{
		setEnableAutoUpdate(true);
	}
}