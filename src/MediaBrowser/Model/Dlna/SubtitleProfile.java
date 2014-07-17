package MediaBrowser.Model.Dlna;

public class SubtitleProfile
{
	private String privateFormat;
	public final String getFormat()
	{
		return privateFormat;
	}
	public final void setFormat(String value)
	{
		privateFormat = value;
	}
}