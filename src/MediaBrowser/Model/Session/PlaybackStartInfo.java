package MediaBrowser.Model.Session;

/** 
 Class PlaybackStartInfo.
*/
public class PlaybackStartInfo extends PlaybackProgressInfo
{
	public PlaybackStartInfo()
	{
		setQueueableMediaTypes(new java.util.ArrayList<String>());
	}

	/** 
	 Gets or sets the queueable media types.
	 
	 <value>The queueable media types.</value>
	*/
	private java.util.ArrayList<String> privateQueueableMediaTypes;
	public final java.util.ArrayList<String> getQueueableMediaTypes()
	{
		return privateQueueableMediaTypes;
	}
	public final void setQueueableMediaTypes(java.util.ArrayList<String> value)
	{
		privateQueueableMediaTypes = value;
	}
}