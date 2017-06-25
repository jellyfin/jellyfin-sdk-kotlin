package mediabrowser.model.session;

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
	private java.util.ArrayList<String> QueueableMediaTypes;
	public final java.util.ArrayList<String> getQueueableMediaTypes()
	{
		return QueueableMediaTypes;
	}
	public final void setQueueableMediaTypes(java.util.ArrayList<String> value)
	{
		QueueableMediaTypes = value;
	}
}