package mediabrowser.model.mediainfo;

import mediabrowser.model.dto.*;

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