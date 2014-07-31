package MediaBrowser.Model.Dto;

import MediaBrowser.Model.Entities.*;
import MediaBrowser.Model.Extensions.*;
import MediaBrowser.Model.MediaInfo.*;

public class MediaSourceInfo
{
	private MediaProtocol privateProtocol = MediaProtocol.values()[0];
	public final MediaProtocol getProtocol()
	{
		return privateProtocol;
	}
	public final void setProtocol(MediaProtocol value)
	{
		privateProtocol = value;
	}
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

	private MediaSourceType privateType = MediaSourceType.values()[0];
	public final MediaSourceType getType()
	{
		return privateType;
	}
	public final void setType(MediaSourceType value)
	{
		privateType = value;
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
	private Long privateSize = null;
	public final Long getSize()
	{
		return privateSize;
	}
	public final void setSize(Long value)
	{
		privateSize = value;
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

	private Long privateRunTimeTicks = null;
	public final Long getRunTimeTicks()
	{
		return privateRunTimeTicks;
	}
	public final void setRunTimeTicks(Long value)
	{
		privateRunTimeTicks = value;
	}

	private VideoType privateVideoType = null;
	public final VideoType getVideoType()
	{
		return privateVideoType;
	}
	public final void setVideoType(VideoType value)
	{
		privateVideoType = value;
	}

	private IsoType privateIsoType = null;
	public final IsoType getIsoType()
	{
		return privateIsoType;
	}
	public final void setIsoType(IsoType value)
	{
		privateIsoType = value;
	}

	private Video3DFormat privateVideo3DFormat = null;
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
	private java.util.ArrayList<String> privatePlayableStreamFileNames;
	public final java.util.ArrayList<String> getPlayableStreamFileNames()
	{
		return privatePlayableStreamFileNames;
	}
	public final void setPlayableStreamFileNames(java.util.ArrayList<String> value)
	{
		privatePlayableStreamFileNames = value;
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

	private Integer privateBitrate = null;
	public final Integer getBitrate()
	{
		return privateBitrate;
	}
	public final void setBitrate(Integer value)
	{
		privateBitrate = value;
	}

	private TransportStreamTimestamp privateTimestamp = null;
	public final TransportStreamTimestamp getTimestamp()
	{
		return privateTimestamp;
	}
	public final void setTimestamp(TransportStreamTimestamp value)
	{
		privateTimestamp = value;
	}
	private java.util.HashMap<String, String> privateRequiredHttpHeaders;
	public final java.util.HashMap<String, String> getRequiredHttpHeaders()
	{
		return privateRequiredHttpHeaders;
	}
	public final void setRequiredHttpHeaders(java.util.HashMap<String, String> value)
	{
		privateRequiredHttpHeaders = value;
	}

	public MediaSourceInfo()
	{
		setFormats(new java.util.ArrayList<String>());
		setMediaStreams(new java.util.ArrayList<MediaStream>());
		setRequiredHttpHeaders(new java.util.HashMap<String, String>());
		setPlayableStreamFileNames(new java.util.ArrayList<String>());
	}

	private Integer privateDefaultAudioStreamIndex = null;
	public final Integer getDefaultAudioStreamIndex()
	{
		return privateDefaultAudioStreamIndex;
	}
	public final void setDefaultAudioStreamIndex(Integer value)
	{
		privateDefaultAudioStreamIndex = value;
	}
	private Integer privateDefaultSubtitleStreamIndex = null;
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
			if (i.getType() == MediaStreamType.Video && StringHelper.IndexOfIgnoreCase(((tempVar != null) ? tempVar : ""), "jpeg") == -1)
			{
				return i;
			}
		}

		return null;
	}

	public final MediaStream GetMediaStream(MediaStreamType type, int index)
	{
		for (MediaStream i : getMediaStreams())
		{
			if (i.getType() == type && i.getIndex() == index)
			{
				return i;
			}
		}

		return null;
	}
}