package mediabrowser.model.dlna;

public class DeviceProfileInfo
{
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
	 Gets or sets the type.
	 
	 <value>The type.</value>
	*/
	private DeviceProfileType Type = DeviceProfileType.values()[0];
	public final DeviceProfileType getType()
	{
		return Type;
	}
	public final void setType(DeviceProfileType value)
	{
		Type = value;
	}
}