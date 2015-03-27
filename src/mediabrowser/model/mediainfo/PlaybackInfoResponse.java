package mediabrowser.model.mediainfo;

import mediabrowser.model.dlna.*;
import mediabrowser.model.dto.*;

public class PlaybackInfoResponse
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
	 Gets or sets the stream identifier.
	 
	 <value>The stream identifier.</value>
	*/
	private String StreamId;
	public final String getStreamId()
	{
		return StreamId;
	}
	public final void setStreamId(String value)
	{
		StreamId = value;
	}

	/** 
	 Gets or sets the error code.
	 
	 <value>The error code.</value>
	*/
	private PlaybackErrorCode ErrorCode = null;
	public final PlaybackErrorCode getErrorCode()
	{
		return ErrorCode;
	}
	public final void setErrorCode(PlaybackErrorCode value)
	{
		ErrorCode = value;
	}

	public PlaybackInfoResponse()
	{
		setMediaSources(new java.util.ArrayList<MediaSourceInfo>());
	}
}