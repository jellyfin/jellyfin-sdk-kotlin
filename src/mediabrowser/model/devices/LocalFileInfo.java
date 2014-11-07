package mediabrowser.model.devices;

public class LocalFileInfo
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
	private String Id;
	public final String getId()
	{
		return Id;
	}
	public final void setId(String value)
	{
		Id = value;
	}
	private String Album;
	public final String getAlbum()
	{
		return Album;
	}
	public final void setAlbum(String value)
	{
		Album = value;
	}
	private String MimeType;
	public final String getMimeType()
	{
		return MimeType;
	}
	public final void setMimeType(String value)
	{
		MimeType = value;
	}
}