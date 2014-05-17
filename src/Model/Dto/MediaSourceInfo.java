package MediaBrowser.Model.Dto;

import MediaBrowser.Model.Entities.*;
import MediaBrowser.Model.MediaInfo.*;

public class MediaSourceInfo
{
	private String privateId;
	public final String getId()
	{
		return privateId;
	}
	public final void setId(String value)
	{
		privateId = value;
	}

	private String privatePath;
	public final String getPath()
	{
		return privatePath;
	}
	public final void setPath(String value)
	{
		privatePath = value;
	}

	private String privateContainer;
	public final String getContainer()
	{
		return privateContainer;
	}
	public final void setContainer(String value)
	{
		privateContainer = value;
	}
	private Long privateSize = new Long();
	public final Long getSize()
	{
		return privateSize;
	}
	public final void setSize(Long value)
	{
		privateSize = value;
	}

	private LocationType privateLocationType = getLocationType().values()[0];
	public final LocationType getLocationType()
	{
		return privateLocationType;
	}
	public final void setLocationType(LocationType value)
	{
		privateLocationType = value;
	}

	private String privateName;
	public final String getName()
	{
		return privateName;
	}
	public final void setName(String value)
	{
		privateName = value;
	}

	private Long privateRunTimeTicks = new Long();
	public final Long getRunTimeTicks()
	{
		return privateRunTimeTicks;
	}
	public final void setRunTimeTicks(Long value)
	{
		privateRunTimeTicks = value;
	}

	private VideoType privateVideoType = new VideoType();
	public final VideoType getVideoType()
	{
		return privateVideoType;
	}
	public final void setVideoType(VideoType value)
	{
		privateVideoType = value;
	}

	private IsoType privateIsoType = new IsoType();
	public final IsoType getIsoType()
	{
		return privateIsoType;
	}
	public final void setIsoType(IsoType value)
	{
		privateIsoType = value;
	}

	private Video3DFormat privateVideo3DFormat = new Video3DFormat();
	public final Video3DFormat getVideo3DFormat()
	{
		return privateVideo3DFormat;
	}
	public final void setVideo3DFormat(Video3DFormat value)
	{
		privateVideo3DFormat = value;
	}

	private java.util.ArrayList<MediaStream> privateMediaStreams;
	public final java.util.ArrayList<MediaStream> getMediaStreams()
	{
		return privateMediaStreams;
	}
	public final void setMediaStreams(java.util.ArrayList<MediaStream> value)
	{
		privateMediaStreams = value;
	}

	private java.util.ArrayList<String> privateFormats;
	public final java.util.ArrayList<String> getFormats()
	{
		return privateFormats;
	}
	public final void setFormats(java.util.ArrayList<String> value)
	{
		privateFormats = value;
	}

	private Integer privateBitrate = new Integer();
	public final Integer getBitrate()
	{
		return privateBitrate;
	}
	public final void setBitrate(Integer value)
	{
		privateBitrate = value;
	}

	private TransportStreamTimestamp privateTimestamp = new TransportStreamTimestamp();
	public final TransportStreamTimestamp getTimestamp()
	{
		return privateTimestamp;
	}
	public final void setTimestamp(TransportStreamTimestamp value)
	{
		privateTimestamp = value;
	}

	public MediaSourceInfo()
	{
		setFormats(new java.util.ArrayList<String>());
		setMediaStreams(new java.util.ArrayList<MediaStream>());
	}

	private Integer privateDefaultAudioStreamIndex = new Integer();
	public final Integer getDefaultAudioStreamIndex()
	{
		return privateDefaultAudioStreamIndex;
	}
	public final void setDefaultAudioStreamIndex(Integer value)
	{
		privateDefaultAudioStreamIndex = value;
	}
	private Integer privateDefaultSubtitleStreamIndex = new Integer();
	public final Integer getDefaultSubtitleStreamIndex()
	{
		return privateDefaultSubtitleStreamIndex;
	}
	public final void setDefaultSubtitleStreamIndex(Integer value)
	{
		privateDefaultSubtitleStreamIndex = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [IgnoreDataMember] public MediaStream DefaultAudioStream
	public final MediaStream getDefaultAudioStream()
	{
		if (getDefaultAudioStreamIndex() != null)
		{
			int val = getDefaultAudioStreamIndex();

			for (MediaStream i : getMediaStreams())
			{
				if (i.getType() == MediaStreamType.Audio && i.getIndex() == val)
				{
					return i;
				}
			}
		}

		for (MediaStream i : getMediaStreams())
		{
			if (i.getType() == MediaStreamType.Audio && i.getIsDefault())
			{
				return i;
			}
		}

		for (MediaStream i : getMediaStreams())
		{
			if (i.getType() == MediaStreamType.Audio)
			{
				return i;
			}
		}

		return null;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [IgnoreDataMember] public MediaStream VideoStream
	public final MediaStream getVideoStream()
	{
		for (MediaStream i : getMediaStreams())
		{
			String tempVar = i.getCodec();
			if (i.getType() == MediaStreamType.Video && ((tempVar != null) ? tempVar : "").indexOf("jpeg", StringComparison.OrdinalIgnoreCase) == -1)
			{
				return i;
			}
		}

		return null;
	}
}