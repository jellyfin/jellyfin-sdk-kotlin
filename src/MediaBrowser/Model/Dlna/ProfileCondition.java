package MediaBrowser.Model.Dlna;

public class ProfileCondition
{
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlAttribute("condition")] public ProfileConditionType Condition {get;set;}
	private ProfileConditionType privateCondition = ProfileConditionType.values()[0];
	public final ProfileConditionType getCondition()
	{
		return privateCondition;
	}
	public final void setCondition(ProfileConditionType value)
	{
		privateCondition = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlAttribute("property")] public ProfileConditionValue Property {get;set;}
	private ProfileConditionValue privateProperty = ProfileConditionValue.values()[0];
	public final ProfileConditionValue getProperty()
	{
		return privateProperty;
	}
	public final void setProperty(ProfileConditionValue value)
	{
		privateProperty = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlAttribute("value")] public string Value {get;set;}
	private String privateValue;
	public final String getValue()
	{
		return privateValue;
	}
	public final void setValue(String value)
	{
		privateValue = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlAttribute("isRequired")] public bool IsRequired {get;set;}
	private boolean privateIsRequired;
	public final boolean getIsRequired()
	{
		return privateIsRequired;
	}
	public final void setIsRequired(boolean value)
	{
		privateIsRequired = value;
	}

	public ProfileCondition()
	{
		setIsRequired(true);
	}
}