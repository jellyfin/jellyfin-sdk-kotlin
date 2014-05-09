package MediaBrowser.Model.MediaInfo;

import MediaBrowser.Model.Entities.*;

/** 
 Represents the result of BDInfo output
*/
public class BlurayDiscInfo
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
	 Gets or sets the run time ticks.
	 
	 <value>The run time ticks.</value>
	*/
	private Long privateRunTimeTicks = new Long();
	public final Long getRunTimeTicks()
	{
		return privateRunTimeTicks;
	}
	public final void setRunTimeTicks(Long value)
	{
		privateRunTimeTicks = value;
	}

	/** 
	 Gets or sets the files.
	 
	 <value>The files.</value>
	*/
	private java.util.ArrayList<String> privateFiles;
	public final java.util.ArrayList<String> getFiles()
	{
		return privateFiles;
	}
	public final void setFiles(java.util.ArrayList<String> value)
	{
		privateFiles = value;
	}

	private String privatePlaylistName;
	public final String getPlaylistName()
	{
		return privatePlaylistName;
	}
	public final void setPlaylistName(String value)
	{
		privatePlaylistName = value;
	}

	/** 
	 Gets or sets the chapters.
	 
	 <value>The chapters.</value>
	*/
	private java.util.ArrayList<Double> privateChapters;
	public final java.util.ArrayList<Double> getChapters()
	{
		return privateChapters;
	}
	public final void setChapters(java.util.ArrayList<Double> value)
	{
		privateChapters = value;
	}
}