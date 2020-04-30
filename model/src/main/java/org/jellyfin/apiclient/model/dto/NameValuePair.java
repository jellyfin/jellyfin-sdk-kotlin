package org.jellyfin.apiclient.model.dto;

public class NameValuePair
{
	public NameValuePair()
	{

	}

	public NameValuePair(String name, String value)
	{
		setName(name);
		setValue(value);
	}

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
	 Gets or sets the value.
	 
	 <value>The value.</value>
	*/
	private String Value;
	public final String getValue()
	{
		return Value;
	}
	public final void setValue(String value)
	{
		Value = value;
	}
}