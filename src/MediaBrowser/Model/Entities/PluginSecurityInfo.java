package MediaBrowser.Model.Entities;

/** 
 Class PluginSecurityInfo
*/
public class PluginSecurityInfo
{
	/** 
	 Gets or sets the supporter key.
	 
	 <value>The supporter key.</value>
	*/
	private String privateSupporterKey;
	public final String getSupporterKey()
	{
		return privateSupporterKey;
	}
	public final void setSupporterKey(String value)
	{
		privateSupporterKey = value;
	}

	/** 
	 Gets or sets the legacy supporter key.
	 
	 <value><c>The legacy supporter key</value>
	*/
	private String privateLegacyKey;
	public final String getLegacyKey()
	{
		return privateLegacyKey;
	}
	public final void setLegacyKey(String value)
	{
		privateLegacyKey = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is MB supporter.
	 
	 <value><c>true</c> if this instance is MB supporter; otherwise, <c>false</c>.</value>
	*/
	private boolean privateIsMBSupporter;
	public final boolean getIsMBSupporter()
	{
		return privateIsMBSupporter;
	}
	public final void setIsMBSupporter(boolean value)
	{
		privateIsMBSupporter = value;
	}
}