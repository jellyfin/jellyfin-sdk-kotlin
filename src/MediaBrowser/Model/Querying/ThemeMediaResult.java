package MediaBrowser.Model.Querying;

/** 
 Class ThemeMediaResult
*/
public class ThemeMediaResult extends ItemsResult
{
	/** 
	 Gets or sets the owner id.
	 
	 <value>The owner id.</value>
	*/
	private String OwnerId;
	public final String getOwnerId()
	{
		return OwnerId;
	}
	public final void setOwnerId(String value)
	{
		OwnerId = value;
	}
}