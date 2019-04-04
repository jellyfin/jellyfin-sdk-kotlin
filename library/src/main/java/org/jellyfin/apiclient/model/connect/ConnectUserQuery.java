package org.jellyfin.apiclient.model.connect;

public class ConnectUserQuery
{
	private String Id;
	public final String getId()
	{
		return Id;
	}
	public final void setId(String value)
	{
		Id = value;
	}
	private String Name;
	public final String getName()
	{
		return Name;
	}
	public final void setName(String value)
	{
		Name = value;
	}
	private String Email;
	public final String getEmail()
	{
		return Email;
	}
	public final void setEmail(String value)
	{
		Email = value;
	}
	private String NameOrEmail;
	public final String getNameOrEmail()
	{
		return NameOrEmail;
	}
	public final void setNameOrEmail(String value)
	{
		NameOrEmail = value;
	}
}