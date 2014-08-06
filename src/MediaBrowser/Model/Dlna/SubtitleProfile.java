package MediaBrowser.Model.Dlna;

public class SubtitleProfile
{
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlAttribute("format")] public string Format {get;set;}
	private String privateFormat;
	public final String getFormat()
	{
		return privateFormat;
	}
	public final void setFormat(String value)
	{
		privateFormat = value;
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
//ORIGINAL LINE: [XmlAttribute("method")] public SubtitleDeliveryMethod Method {get;set;}
	private SubtitleDeliveryMethod privateMethod = SubtitleDeliveryMethod.values()[0];
	public final SubtitleDeliveryMethod getMethod()
	{
		return privateMethod;
	}
	public final void setMethod(SubtitleDeliveryMethod value)
	{
		privateMethod = value;
	}
}