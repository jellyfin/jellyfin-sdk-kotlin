package mediabrowser.model.configuration;

public class ChannelOptions
{
	private Integer PreferredStreamingWidth;
	public final Integer getPreferredStreamingWidth()
	{
		return PreferredStreamingWidth;
	}
	public final void setPreferredStreamingWidth(Integer value)
	{
		PreferredStreamingWidth = value;
	}
	private String DownloadPath;
	public final String getDownloadPath()
	{
		return DownloadPath;
	}
	public final void setDownloadPath(String value)
	{
		DownloadPath = value;
	}
}