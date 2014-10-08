package MediaBrowser.Model.Entities;

public class MediaInfo
{
	/** 
	 Gets or sets the media streams.
	 
	 <value>The media streams.</value>
	*/
	private java.util.ArrayList<MediaStream> MediaStreams;
	public final java.util.ArrayList<MediaStream> getMediaStreams()
	{
		return MediaStreams;
	}
	public final void setMediaStreams(java.util.ArrayList<MediaStream> value)
	{
		MediaStreams = value;
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

	private Integer TotalBitrate = null;
	public final Integer getTotalBitrate()
	{
		return TotalBitrate;
	}
	public final void setTotalBitrate(Integer value)
	{
		TotalBitrate = value;
	}

	public MediaInfo()
	{
		setMediaStreams(new java.util.ArrayList<MediaStream>());
	}
}