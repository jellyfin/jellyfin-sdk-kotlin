package mediabrowser.model.connect;

public class ConnectUser
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
	private boolean IsActive;
	public final boolean getIsActive()
	{
		return IsActive;
	}
	public final void setIsActive(boolean value)
	{
		IsActive = value;
	}
	private boolean IsSupporter;
	public final boolean getIsSupporter()
	{
		return IsSupporter;
	}
	public final void setIsSupporter(boolean value)
	{
		IsSupporter = value;
	}
	private String ImageUrl;
	public final String getImageUrl()
	{
		return ImageUrl;
	}
	public final void setImageUrl(String value)
	{
		ImageUrl = value;
	}
}