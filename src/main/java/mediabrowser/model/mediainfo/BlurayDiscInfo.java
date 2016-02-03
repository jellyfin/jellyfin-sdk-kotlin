package mediabrowser.model.mediainfo;

import mediabrowser.model.entities.*;

/** 
 Represents the result of BDInfo output
*/
public class BlurayDiscInfo
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
	 Gets or sets the run time ticks.
	 
	 <value>The run time ticks.</value>
	*/
	private Long RunTimeTicks = null;
	public final Long getRunTimeTicks()
	{
		return RunTimeTicks;
	}
	public final void setRunTimeTicks(Long value)
	{
		RunTimeTicks = value;
	}

	/** 
	 Gets or sets the files.
	 
	 <value>The files.</value>
	*/
	private java.util.ArrayList<String> Files;
	public final java.util.ArrayList<String> getFiles()
	{
		return Files;
	}
	public final void setFiles(java.util.ArrayList<String> value)
	{
		Files = value;
	}

	private String PlaylistName;
	public final String getPlaylistName()
	{
		return PlaylistName;
	}
	public final void setPlaylistName(String value)
	{
		PlaylistName = value;
	}

	/** 
	 Gets or sets the chapters.
	 
	 <value>The chapters.</value>
	*/
	private java.util.ArrayList<Double> Chapters;
	public final java.util.ArrayList<Double> getChapters()
	{
		return Chapters;
	}
	public final void setChapters(java.util.ArrayList<Double> value)
	{
		Chapters = value;
	}
}