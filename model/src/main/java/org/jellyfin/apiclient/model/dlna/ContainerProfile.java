package org.jellyfin.apiclient.model.dlna;

import org.jellyfin.apiclient.model.extensions.*;

public class ContainerProfile
{
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
	private ProfileCondition[] Conditions;
	public final ProfileCondition[] getConditions()
	{
		return Conditions;
	}
	public final void setConditions(ProfileCondition[] value)
	{
		Conditions = value;
	}

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

	public ContainerProfile()
	{
		setConditions(new ProfileCondition[] { });
	}

	public final java.util.ArrayList<String> GetContainers()
	{
		java.util.ArrayList<String> list = new java.util.ArrayList<String>();
		for (String i : ((getContainer() != null) ? getContainer() : "").split("[,]", -1))
		{
			if (i != null && !i.isEmpty())
			{
				list.add(i);
			}
		}
		return list;
	}

	public final boolean ContainsContainer(String container)
	{
		java.util.ArrayList<String> containers = GetContainers();

		return containers.isEmpty() || ListHelper.ContainsIgnoreCase(containers, (container != null) ? container : "");
	}
}
