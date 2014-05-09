package MediaBrowser.Model.Dlna;

public class CodecProfile
{
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlAttribute("type")] public CodecType Type {get;set;}
	private CodecType privateType = CodecType.values()[0];
	public final CodecType getType()
	{
		return privateType;
	}
	public final void setType(CodecType value)
	{
		privateType = value;
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

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlAttribute("codec")] public string Codec {get;set;}
	private String privateCodec;
	public final String getCodec()
	{
		return privateCodec;
	}
	public final void setCodec(String value)
	{
		privateCodec = value;
	}

	public CodecProfile()
	{
		setConditions(new ProfileCondition[] {});
	}

	public final java.util.ArrayList<String> GetCodecs()
	{
		java.util.ArrayList<String> list = new java.util.ArrayList<String>();
		for (String i : ((getCodec() != null) ? getCodec() : "").split("[,]", -1))
		{
			if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(i))
			{
				list.add(i);
			}
		}
		return list;
	}

	public final boolean ContainsCodec(String codec)
	{
		java.util.ArrayList<String> codecs = GetCodecs();

		return codecs.isEmpty() || codecs.contains(codec, StringComparer.OrdinalIgnoreCase);
	}
}