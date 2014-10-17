package MediaBrowser.Model.Devices;

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
	private String FullPath;
	public final String getFullPath()
	{
		return FullPath;
	}
	public final void setFullPath(String value)
	{
		FullPath = value;
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