package MediaBrowser.Model.Dlna;

public class ContainerProfile
{
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

	public ContainerProfile()
	{
		setConditions(new ProfileCondition[] { });
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
}