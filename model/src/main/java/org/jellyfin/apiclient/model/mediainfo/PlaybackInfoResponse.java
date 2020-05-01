package org.jellyfin.apiclient.model.mediainfo;

import org.jellyfin.apiclient.model.dlna.*;
import org.jellyfin.apiclient.model.dto.*;

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
	 Gets or sets the play session identifier.
	 
	 <value>The play session identifier.</value>
	*/
	private String PlaySessionId;
	public final String getPlaySessionId()
	{
		return PlaySessionId;
	}
	public final void setPlaySessionId(String value)
	{
		PlaySessionId = value;
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