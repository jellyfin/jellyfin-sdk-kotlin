package mediabrowser.model.dlna;

public class SubtitleProfile
{
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlAttribute("format")] public string Format {get;set;}
	private String Format;
	public final String getFormat()
	{
		return Format;
	}
	public final void setFormat(String value)
	{
		Format = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlAttribute("method")] public SubtitleDeliveryMethod Method {get;set;}
	private SubtitleDeliveryMethod Method = SubtitleDeliveryMethod.values()[0];
	public final SubtitleDeliveryMethod getMethod()
	{
		return Method;
	}
	public final void setMethod(SubtitleDeliveryMethod value)
	{
		Method = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlAttribute("didlMode")] public string DidlMode {get;set;}
	private String DidlMode;
	public final String getDidlMode()
	{
		return DidlMode;
	}
	public final void setDidlMode(String value)
	{
		DidlMode = value;
	}

}