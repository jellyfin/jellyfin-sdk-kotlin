package mediabrowser.model.dlna;

public class SubtitleStreamInfo
{
	private String Url;
	public final String getUrl()
	{
		return Url;
	}
	public final void setUrl(String value)
	{
		Url = value;
	}
	private String Language;
	public final String getLanguage()
	{
		return Language;
	}
	public final void setLanguage(String value)
	{
		Language = value;
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
	private boolean IsForced;
	public final boolean getIsForced()
	{
		return IsForced;
	}
	public final void setIsForced(boolean value)
	{
		IsForced = value;
	}
	private String Format;
	public final String getFormat()
	{
		return Format;
	}
	public final void setFormat(String value)
	{
		Format = value;
	}
	private int Index;
	public final int getIndex()
	{
		return Index;
	}
	public final void setIndex(int value)
	{
		Index = value;
	}
}