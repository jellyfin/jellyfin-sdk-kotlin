package org.jellyfin.apiclient.model.dto;

import org.jellyfin.apiclient.model.entities.PersonType;

/**
 This is used by the api to get information about a Person within a BaseItem
*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [DebuggerDisplay("Name = {Name}, Role = {Role}, Type = {Type}")] public class BaseItemPerson
public class BaseItemPerson
{
	/** 
	 Gets or sets the name.
	 
	 <value>The name.</value>
	*/
	private String Name;
	public final String getName()
	{
		return Name;
	}
	public final void setName(String value)
	{
		Name = value;
	}

	/** 
	 Gets or sets the identifier.
	 
	 <value>The identifier.</value>
	*/
	private String Id;
	public final String getId()
	{
		return Id;
	}
	public final void setId(String value)
	{
		Id = value;
	}

	/** 
	 Gets or sets the role.
	 
	 <value>The role.</value>
	*/
	private String Role;
	public final String getRole()
	{
		return Role;
	}
	public final void setRole(String value)
	{
		Role = value;
	}

	/** 
	 Gets or sets the type.
	 
	 <value>The type.</value>
	*/
	private String Type;
	@Deprecated
	public final String getType()
	{
		return Type;
	}

	@Deprecated
	public final void setType(String value)
	{
		Type = value;
	}

	public final PersonType getPersonType() {
		if (Type == null) return PersonType.Other;

		try {
			return PersonType.valueOf(Type);
		} catch (IllegalArgumentException ex) {
			return PersonType.Other;
		}
	}


	public final void setPersonType(PersonType toSet) {
		this.Type = toSet.name();
	}

	/** 
	 Gets or sets the primary image tag.
	 
	 <value>The primary image tag.</value>
	*/
	private String PrimaryImageTag;
	public final String getPrimaryImageTag()
	{
		return PrimaryImageTag;
	}
	public final void setPrimaryImageTag(String value)
	{
		PrimaryImageTag = value;
	}

	/** 
	 Gets a value indicating whether this instance has primary image.
	 
	 <value><c>true</c> if this instance has primary image; otherwise, <c>false</c>.</value>
	*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [IgnoreDataMember] public bool HasPrimaryImage
	public final boolean getHasPrimaryImage()
	{
		return getPrimaryImageTag() != null;
	}
}