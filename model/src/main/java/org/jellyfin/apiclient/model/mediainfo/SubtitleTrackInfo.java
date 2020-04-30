package org.jellyfin.apiclient.model.mediainfo;

public class SubtitleTrackInfo
{
	private java.util.ArrayList<SubtitleTrackEvent> TrackEvents;
	public final java.util.ArrayList<SubtitleTrackEvent> getTrackEvents()
	{
		return TrackEvents;
	}
	public final void setTrackEvents(java.util.ArrayList<SubtitleTrackEvent> value)
	{
		TrackEvents = value;
	}

	public SubtitleTrackInfo()
	{
		setTrackEvents(new java.util.ArrayList<SubtitleTrackEvent>());
	}
}