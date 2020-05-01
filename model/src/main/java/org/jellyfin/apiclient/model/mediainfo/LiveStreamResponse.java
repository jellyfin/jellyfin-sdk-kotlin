package org.jellyfin.apiclient.model.mediainfo;

import org.jellyfin.apiclient.model.dto.*;

public class LiveStreamResponse
{
	private MediaSourceInfo MediaSource;
	public final MediaSourceInfo getMediaSource()
	{
		return MediaSource;
	}
	public final void setMediaSource(MediaSourceInfo value)
	{
		MediaSource = value;
	}
}