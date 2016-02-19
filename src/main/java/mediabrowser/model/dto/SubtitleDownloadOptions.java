package mediabrowser.model.dto;

public class SubtitleDownloadOptions
{
	/** 
	 Gets or sets the item identifier.
	 
	 <value>The item identifier.</value>
	*/
	private String ItemId;
	public final String getItemId()
	{
		return ItemId;
	}
	public final void setItemId(String value)
	{
		ItemId = value;
	}

	/** 
	 Gets or sets the media source identifier.
	 
	 <value>The media source identifier.</value>
	*/
	private String MediaSourceId;
	public final String getMediaSourceId()
	{
		return MediaSourceId;
	}
	public final void setMediaSourceId(String value)
	{
		MediaSourceId = value;
	}

	/** 
	 Gets or sets the index of the stream.
	 
	 <value>The index of the stream.</value>
	*/
	private int StreamIndex;
	public final int getStreamIndex()
	{
		return StreamIndex;
	}
	public final void setStreamIndex(int value)
	{
		StreamIndex = value;
	}

	/** 
	 Gets or sets the format.
	 
	 <value>The format.</value>
	*/
	private String Format;
	public final String getFormat()
	{
		return Format;
	}
	public final void setFormat(String value)
	{
		Format = value;
	}
}