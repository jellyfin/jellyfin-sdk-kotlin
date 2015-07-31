package mediabrowser.model.dto;

import mediabrowser.model.entities.*;
import mediabrowser.model.extensions.*;
import mediabrowser.model.mediainfo.*;

public class MediaSourceInfo
{
	private MediaProtocol Protocol = MediaProtocol.values()[0];
	public final MediaProtocol getProtocol()
	{
		return Protocol;
	}
	public final void setProtocol(MediaProtocol value)
	{
		Protocol = value;
	}
	private String Id;
	public final String getId()
	{
		return Id;
	}
	public final void setId(String value)
	{
		Id = value;
	}

	private String Path;
	public final String getPath()
	{
		return Path;
	}
	public final void setPath(String value)
	{
		Path = value;
	}

	private MediaSourceType Type = MediaSourceType.values()[0];
	public final MediaSourceType getType()
	{
		return Type;
	}
	public final void setType(MediaSourceType value)
	{
		Type = value;
	}

	private String Container;
	public final String getContainer()
	{
		return Container;
	}
	public final void setContainer(String value)
	{
		Container = value;
	}
	private Long Size = null;
	public final Long getSize()
	{
		return Size;
	}
	public final void setSize(Long value)
	{
		Size = value;
	}

	private String Name;
	public final String getName()
	{
		return Name;
	}
	public final void setName(String value)
	{
		Name = value;
	}

	private Long RunTimeTicks = null;
	public final Long getRunTimeTicks()
	{
		return RunTimeTicks;
	}
	public final void setRunTimeTicks(Long value)
	{
		RunTimeTicks = value;
	}
	private boolean ReadAtNativeFramerate;
	public final boolean getReadAtNativeFramerate()
	{
		return ReadAtNativeFramerate;
	}
	public final void setReadAtNativeFramerate(boolean value)
	{
		ReadAtNativeFramerate = value;
	}
	private boolean SupportsTranscoding;
	public final boolean getSupportsTranscoding()
	{
		return SupportsTranscoding;
	}
	public final void setSupportsTranscoding(boolean value)
	{
		SupportsTranscoding = value;
	}
	private boolean SupportsDirectStream;
	public final boolean getSupportsDirectStream()
	{
		return SupportsDirectStream;
	}
	public final void setSupportsDirectStream(boolean value)
	{
		SupportsDirectStream = value;
	}
	private boolean SupportsDirectPlay;
	public final boolean getSupportsDirectPlay()
	{
		return SupportsDirectPlay;
	}
	public final void setSupportsDirectPlay(boolean value)
	{
		SupportsDirectPlay = value;
	}

	private Boolean RequiresOpening = null;
	public final Boolean getRequiresOpening()
	{
		return RequiresOpening;
	}
	public final void setRequiresOpening(Boolean value)
	{
		RequiresOpening = value;
	}
	private String OpenToken;
	public final String getOpenToken()
	{
		return OpenToken;
	}
	public final void setOpenToken(String value)
	{
		OpenToken = value;
	}
	private Boolean RequiresClosing = null;
	public final Boolean getRequiresClosing()
	{
		return RequiresClosing;
	}
	public final void setRequiresClosing(Boolean value)
	{
		RequiresClosing = value;
	}
	private String LiveStreamId;
	public final String getLiveStreamId()
	{
		return LiveStreamId;
	}
	public final void setLiveStreamId(String value)
	{
		LiveStreamId = value;
	}
	private Integer BufferMs = null;
	public final Integer getBufferMs()
	{
		return BufferMs;
	}
	public final void setBufferMs(Integer value)
	{
		BufferMs = value;
	}

	private VideoType VideoType = null;
	public final VideoType getVideoType()
	{
		return VideoType;
	}
	public final void setVideoType(VideoType value)
	{
		VideoType = value;
	}

	private IsoType IsoType = null;
	public final IsoType getIsoType()
	{
		return IsoType;
	}
	public final void setIsoType(IsoType value)
	{
		IsoType = value;
	}

	private Video3DFormat Video3DFormat = null;
	public final Video3DFormat getVideo3DFormat()
	{
		return Video3DFormat;
	}
	public final void setVideo3DFormat(Video3DFormat value)
	{
		Video3DFormat = value;
	}

	private java.util.ArrayList<MediaStream> MediaStreams;
	public final java.util.ArrayList<MediaStream> getMediaStreams()
	{
		return MediaStreams;
	}
	public final void setMediaStreams(java.util.ArrayList<MediaStream> value)
	{
		MediaStreams = value;
	}
	private java.util.ArrayList<String> PlayableStreamFileNames;
	public final java.util.ArrayList<String> getPlayableStreamFileNames()
	{
		return PlayableStreamFileNames;
	}
	public final void setPlayableStreamFileNames(java.util.ArrayList<String> value)
	{
		PlayableStreamFileNames = value;
	}

	private java.util.ArrayList<String> Formats;
	public final java.util.ArrayList<String> getFormats()
	{
		return Formats;
	}
	public final void setFormats(java.util.ArrayList<String> value)
	{
		Formats = value;
	}

	private Integer Bitrate = null;
	public final Integer getBitrate()
	{
		return Bitrate;
	}
	public final void setBitrate(Integer value)
	{
		Bitrate = value;
	}

	private TransportStreamTimestamp Timestamp = null;
	public final TransportStreamTimestamp getTimestamp()
	{
		return Timestamp;
	}
	public final void setTimestamp(TransportStreamTimestamp value)
	{
		Timestamp = value;
	}
	private java.util.HashMap<String, String> RequiredHttpHeaders;
	public final java.util.HashMap<String, String> getRequiredHttpHeaders()
	{
		return RequiredHttpHeaders;
	}
	public final void setRequiredHttpHeaders(java.util.HashMap<String, String> value)
	{
		RequiredHttpHeaders = value;
	}

	private String TranscodingUrl;
	public final String getTranscodingUrl()
	{
		return TranscodingUrl;
	}
	public final void setTranscodingUrl(String value)
	{
		TranscodingUrl = value;
	}
	private String TranscodingSubProtocol;
	public final String getTranscodingSubProtocol()
	{
		return TranscodingSubProtocol;
	}
	public final void setTranscodingSubProtocol(String value)
	{
		TranscodingSubProtocol = value;
	}
	private String TranscodingContainer;
	public final String getTranscodingContainer()
	{
		return TranscodingContainer;
	}
	public final void setTranscodingContainer(String value)
	{
		TranscodingContainer = value;
	}

	public MediaSourceInfo()
	{
		setFormats(new java.util.ArrayList<String>());
		setMediaStreams(new java.util.ArrayList<MediaStream>());
		setRequiredHttpHeaders(new java.util.HashMap<String, String>());
		setPlayableStreamFileNames(new java.util.ArrayList<String>());
		setSupportsTranscoding(true);
		setSupportsDirectStream(true);
		setSupportsDirectPlay(true);
	}

	private Integer DefaultAudioStreamIndex = null;
	public final Integer getDefaultAudioStreamIndex()
	{
		return DefaultAudioStreamIndex;
	}
	public final void setDefaultAudioStreamIndex(Integer value)
	{
		DefaultAudioStreamIndex = value;
	}
	private Integer DefaultSubtitleStreamIndex = null;
	public final Integer getDefaultSubtitleStreamIndex()
	{
		return DefaultSubtitleStreamIndex;
	}
	public final void setDefaultSubtitleStreamIndex(Integer value)
	{
		DefaultSubtitleStreamIndex = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [IgnoreDataMember] public MediaStream DefaultAudioStream
	public final MediaStream getDefaultAudioStream()
	{
		return GetDefaultAudioStream(getDefaultAudioStreamIndex());
	}

	public final MediaStream GetDefaultAudioStream(Integer defaultIndex)
	{
		if (defaultIndex != null)
		{
			int val = defaultIndex;

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

	public final Integer GetStreamCount(MediaStreamType type)
	{
		int numMatches = 0;
		int numStreams = 0;

		for (MediaStream i : getMediaStreams())
		{
			numStreams++;
			if (i.getType() == type)
			{
				numMatches++;
			}
		}

		if (numStreams == 0)
		{
			return null;
		}

		return numMatches;
	}

	public final Boolean IsSecondaryAudio(MediaStream stream)
	{
		for (MediaStream currentStream : getMediaStreams())
		{
			if (currentStream.getType() == MediaStreamType.Audio)
			{
				return currentStream.getIndex() != stream.getIndex();
			}
		}

		return null;
	}
}