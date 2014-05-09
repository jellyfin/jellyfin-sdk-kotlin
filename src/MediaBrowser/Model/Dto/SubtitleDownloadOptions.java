package MediaBrowser.Model.Dto;

public class SubtitleDownloadOptions
{
	/** 
	 Gets or sets the item identifier.
	 
	 <value>The item identifier.</value>
	*/
	private String privateItemId;
	public final String getItemId()
	{
		return privateItemId;
	}
	public final void setItemId(String value)
	{
		privateItemId = value;
	}

	/** 
	 Gets or sets the index of the stream.
	 
	 <value>The index of the stream.</value>
	*/
	private int privateStreamIndex;
	public final int getStreamIndex()
	{
		return privateStreamIndex;
	}
	public final void setStreamIndex(int value)
	{
		privateStreamIndex = value;
	}
}