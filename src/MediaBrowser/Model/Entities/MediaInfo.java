package MediaBrowser.Model.Entities;

public class MediaInfo
{
	/** 
	 Gets or sets the media streams.
	 
	 <value>The media streams.</value>
	*/
	private java.util.ArrayList<MediaStream> privateMediaStreams;
	public final java.util.ArrayList<MediaStream> getMediaStreams()
	{
		return privateMediaStreams;
	}
	public final void setMediaStreams(java.util.ArrayList<MediaStream> value)
	{
		privateMediaStreams = value;
	}

	/** 
	 Gets or sets the format.
	 
	 <value>The format.</value>
	*/
	private String privateFormat;
	public final String getFormat()
	{
		return privateFormat;
	}
	public final void setFormat(String value)
	{
		privateFormat = value;
	}

	private Integer privateTotalBitrate = null;
	public final Integer getTotalBitrate()
	{
		return privateTotalBitrate;
	}
	public final void setTotalBitrate(Integer value)
	{
		privateTotalBitrate = value;
	}

	public MediaInfo()
	{
		setMediaStreams(new java.util.ArrayList<MediaStream>());
	}
}