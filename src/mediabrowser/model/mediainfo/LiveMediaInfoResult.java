package mediabrowser.model.mediainfo;

import mediabrowser.model.dto.*;

public class LiveMediaInfoResult
{
	/** 
	 Gets or sets the media sources.
	 
	 <value>The media sources.</value>
	*/
	private java.util.ArrayList<MediaSourceInfo> MediaSources;
	public final java.util.ArrayList<MediaSourceInfo> getMediaSources()
	{
		return MediaSources;
	}
	public final void setMediaSources(java.util.ArrayList<MediaSourceInfo> value)
	{
		MediaSources = value;
	}

	/** 
	 Gets or sets the live stream identifier.
	 
	 <value>The live stream identifier.</value>
	*/
	private String LiveStreamId;
	public final String getLiveStreamId()
	{
		return LiveStreamId;
	}
	public final void setLiveStreamId(String value)
	{
		LiveStreamId = value;
	}

	public LiveMediaInfoResult()
	{
		setMediaSources(new java.util.ArrayList<MediaSourceInfo>());
	}
}