package org.jellyfin.apiclient.model.session;

public class GeneralCommand
{
	private String Name;
	public final String getName()
	{
		return Name;
	}
	public final void setName(String value)
	{
		Name = value;
	}

	private String ControllingUserId;
	public final String getControllingUserId()
	{
		return ControllingUserId;
	}
	public final void setControllingUserId(String value)
	{
		ControllingUserId = value;
	}

	private java.util.HashMap<String, String> Arguments;
	public final java.util.HashMap<String, String> getArguments()
	{
		return Arguments;
	}
	public final void setArguments(java.util.HashMap<String, String> value)
	{
		Arguments = value;
	}

	public GeneralCommand()
	{
		setArguments(new java.util.HashMap<String, String>());
	}
}