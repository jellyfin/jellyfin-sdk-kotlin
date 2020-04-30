package org.jellyfin.apiclient.model.dlna;

public class TranscodingProfile
{
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlAttribute("container")] public string Container {get;set;}
	private String Container;
	public final String getContainer()
	{
		return Container;
	}
	public final void setContainer(String value)
	{
		Container = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlAttribute("type")] public DlnaProfileType Type {get;set;}
	private DlnaProfileType Type = DlnaProfileType.values()[0];
	public final DlnaProfileType getType()
	{
		return Type;
	}
	public final void setType(DlnaProfileType value)
	{
		Type = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlAttribute("videoCodec")] public string VideoCodec {get;set;}
	private String VideoCodec;
	public final String getVideoCodec()
	{
		return VideoCodec;
	}
	public final void setVideoCodec(String value)
	{
		VideoCodec = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlAttribute("audioCodec")] public string AudioCodec {get;set;}
	private String AudioCodec;
	public final String getAudioCodec()
	{
		return AudioCodec;
	}
	public final void setAudioCodec(String value)
	{
		AudioCodec = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlAttribute("protocol")] public string Protocol {get;set;}
	private String Protocol;
	public final String getProtocol()
	{
		return Protocol;
	}
	public final void setProtocol(String value)
	{
		Protocol = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlAttribute("estimateContentLength")] public bool EstimateContentLength {get;set;}
	private boolean EstimateContentLength;
	public final boolean getEstimateContentLength()
	{
		return EstimateContentLength;
	}
	public final void setEstimateContentLength(boolean value)
	{
		EstimateContentLength = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlAttribute("enableMpegtsM2TsMode")] public bool EnableMpegtsM2TsMode {get;set;}
	private boolean EnableMpegtsM2TsMode;
	public final boolean getEnableMpegtsM2TsMode()
	{
		return EnableMpegtsM2TsMode;
	}
	public final void setEnableMpegtsM2TsMode(boolean value)
	{
		EnableMpegtsM2TsMode = value;
	}

	private boolean BreakOnNonKeyFrames;
	public final boolean BreakOnNonKeyFrames()
	{
		return BreakOnNonKeyFrames;
	}
	public final void setBreakOnNonKeyFrames(boolean value)
	{
		BreakOnNonKeyFrames = value;
	}

	//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlAttribute("transcodeSeekInfo")] public TranscodeSeekInfo TranscodeSeekInfo {get;set;}
	private TranscodeSeekInfo TranscodeSeekInfo = getTranscodeSeekInfo().values()[0];
	public final TranscodeSeekInfo getTranscodeSeekInfo()
	{
		return TranscodeSeekInfo;
	}
	public final void setTranscodeSeekInfo(TranscodeSeekInfo value)
	{
		TranscodeSeekInfo = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlAttribute("copyTimestamps")] public bool CopyTimestamps {get;set;}
	private boolean CopyTimestamps;
	public final boolean getCopyTimestamps()
	{
		return CopyTimestamps;
	}
	public final void setCopyTimestamps(boolean value)
	{
		CopyTimestamps = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlAttribute("context")] public EncodingContext Context {get;set;}
	private EncodingContext Context = EncodingContext.values()[0];
	public final EncodingContext getContext()
	{
		return Context;
	}
	public final void setContext(EncodingContext value)
	{
		Context = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlAttribute("enableSubtitlesInManifest")] public bool EnableSubtitlesInManifest {get;set;}
	private boolean EnableSubtitlesInManifest;
	public final boolean getEnableSubtitlesInManifest()
	{
		return EnableSubtitlesInManifest;
	}
	public final void setEnableSubtitlesInManifest(boolean value)
	{
		EnableSubtitlesInManifest = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlAttribute("maxAudioChannels")] public string MaxAudioChannels {get;set;}
	private String MaxAudioChannels;
	public final String getMaxAudioChannels()
	{
		return MaxAudioChannels;
	}
	public final void setMaxAudioChannels(String value)
	{
		MaxAudioChannels = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlAttribute("minSegments")] public int MinSegments {get;set;}
	private int MinSegments;
	public final int getMinSegments()
	{
		return MinSegments;
	}
	public final void setMinSegments(int value)
	{
		MinSegments = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlAttribute("segmentLength")] public int SegmentLength {get;set;}
	private int SegmentLength;
	public final int getSegmentLength()
	{
		return SegmentLength;
	}
	public final void setSegmentLength(int value)
	{
		SegmentLength = value;
	}

	public final java.util.ArrayList<String> GetAudioCodecs()
	{
		java.util.ArrayList<String> list = new java.util.ArrayList<String>();
		for (String i : ((getAudioCodec() != null) ? getAudioCodec() : "").split("[,]", -1))
		{
			if (i != null && !i.isEmpty())
			{
				list.add(i);
			}
		}
		return list;
	}
}
