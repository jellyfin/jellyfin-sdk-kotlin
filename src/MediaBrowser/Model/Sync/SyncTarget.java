package MediaBrowser.Model.Sync;

public class SyncTarget
{
	/** 
	 Gets or sets the name.
	 
	 <value>The name.</value>
	*/
	private String privateName;
	public final String getName()
	{
		return privateName;
	}
	public final void setName(String value)
	{
		privateName = value;
	}
	/** 
	 Gets or sets the identifier.
	 
	 <value>The identifier.</value>
	*/
	private String privateId;
	public final String getId()
	{
		return privateId;
	}
	public final void setId(String value)
	{
		privateId = value;
	}
}