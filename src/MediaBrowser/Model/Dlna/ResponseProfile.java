package MediaBrowser.Model.Dlna;

public class ResponseProfile
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
//ORIGINAL LINE: [XmlAttribute("orgPn")] public string OrgPn {get;set;}
	private String privateOrgPn;
	public final String getOrgPn()
	{
		return privateOrgPn;
	}
	public final void setOrgPn(String value)
	{
		privateOrgPn = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlAttribute("mimeType")] public string MimeType {get;set;}
	private String privateMimeType;
	public final String getMimeType()
	{
		return privateMimeType;
	}
	public final void setMimeType(String value)
	{
		privateMimeType = value;
	}

	private ProfileCondition[] privateConditions;
	public final ProfileCondition[] getConditions()
	{
		return privateConditions;
	}
	public final void setConditions(ProfileCondition[] value)
	{
		privateConditions = value;
	}

	public ResponseProfile()
	{
		setConditions(new ProfileCondition[] {});
	}

	public final java.util.ArrayList<String> GetContainers()
	{
		java.util.ArrayList<String> list = new java.util.ArrayList<String>();
		for (String i : ((getContainer() != null) ? getContainer() : "").split("[,]", -1))
		{
			if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(i))
			{
				list.add(i);
			}
		}
		return list;
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

	public final java.util.ArrayList<String> GetVideoCodecs()
	{
		java.util.ArrayList<String> list = new java.util.ArrayList<String>();
		for (String i : ((getVideoCodec() != null) ? getVideoCodec() : "").split("[,]", -1))
		{
			if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(i))
			{
				list.add(i);
			}
		}
		return list;
	}
}