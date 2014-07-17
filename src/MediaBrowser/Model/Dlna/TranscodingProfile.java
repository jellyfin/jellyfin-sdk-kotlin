package MediaBrowser.Model.Dlna;

public class TranscodingProfile
{
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlAttribute("container")] public string Container {get;set;}
	private String privateContainer;
	public final String getContainer()
	{
		return privateContainer;
	}
	public final void setContainer(String value)
	{
		privateContainer = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlAttribute("type")] public DlnaProfileType Type {get;set;}
	private DlnaProfileType privateType = DlnaProfileType.values()[0];
	public final DlnaProfileType getType()
	{
		return privateType;
	}
	public final void setType(DlnaProfileType value)
	{
		privateType = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlAttribute("videoCodec")] public string VideoCodec {get;set;}
	private String privateVideoCodec;
	public final String getVideoCodec()
	{
		return privateVideoCodec;
	}
	public final void setVideoCodec(String value)
	{
		privateVideoCodec = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlAttribute("audioCodec")] public string AudioCodec {get;set;}
	private String privateAudioCodec;
	public final String getAudioCodec()
	{
		return privateAudioCodec;
	}
	public final void setAudioCodec(String value)
	{
		privateAudioCodec = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlAttribute("protocol")] public string Protocol {get;set;}
	private String privateProtocol;
	public final String getProtocol()
	{
		return privateProtocol;
	}
	public final void setProtocol(String value)
	{
		privateProtocol = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlAttribute("estimateContentLength")] public bool EstimateContentLength {get;set;}
	private boolean privateEstimateContentLength;
	public final boolean getEstimateContentLength()
	{
		return privateEstimateContentLength;
	}
	public final void setEstimateContentLength(boolean value)
	{
		privateEstimateContentLength = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlAttribute("enableMpegtsM2TsMode")] public bool EnableMpegtsM2TsMode {get;set;}
	private boolean privateEnableMpegtsM2TsMode;
	public final boolean getEnableMpegtsM2TsMode()
	{
		return privateEnableMpegtsM2TsMode;
	}
	public final void setEnableMpegtsM2TsMode(boolean value)
	{
		privateEnableMpegtsM2TsMode = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlAttribute("transcodeSeekInfo")] public TranscodeSeekInfo TranscodeSeekInfo {get;set;}
	private TranscodeSeekInfo privateTranscodeSeekInfo = getTranscodeSeekInfo().values()[0];
	public final TranscodeSeekInfo getTranscodeSeekInfo()
	{
		return privateTranscodeSeekInfo;
	}
	public final void setTranscodeSeekInfo(TranscodeSeekInfo value)
	{
		privateTranscodeSeekInfo = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlAttribute("videoProfile")] public string VideoProfile {get;set;}
	private String privateVideoProfile;
	public final String getVideoProfile()
	{
		return privateVideoProfile;
	}
	public final void setVideoProfile(String value)
	{
		privateVideoProfile = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlAttribute("context")] public EncodingContext Context {get;set;}
	private EncodingContext privateContext = EncodingContext.values()[0];
	public final EncodingContext getContext()
	{
		return privateContext;
	}
	public final void setContext(EncodingContext value)
	{
		privateContext = value;
	}

	public final java.util.ArrayList<String> GetAudioCodecs()
	{
		java.util.ArrayList<String> list = new java.util.ArrayList<String>();
		for (String i : ((getAudioCodec() != null) ? getAudioCodec() : "").split("[,]", -1))
		{
			if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(i))
			{
				list.add(i);
			}
		}
		return list;
	}
}