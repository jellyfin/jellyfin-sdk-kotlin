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
	private String privateOwnerId;
	public final String getOwnerId()
	{
		return privateOwnerId;
	}
	public final void setOwnerId(String value)
	{
		privateOwnerId = value;
	}
}