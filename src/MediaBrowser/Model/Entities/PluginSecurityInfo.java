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
	private String SupporterKey;
	public final String getSupporterKey()
	{
		return SupporterKey;
	}
	public final void setSupporterKey(String value)
	{
		SupporterKey = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is MB supporter.
	 
	 <value><c>true</c> if this instance is MB supporter; otherwise, <c>false</c>.</value>
	*/
	private boolean IsMBSupporter;
	public final boolean getIsMBSupporter()
	{
		return IsMBSupporter;
	}
	public final void setIsMBSupporter(boolean value)
	{
		IsMBSupporter = value;
	}
}