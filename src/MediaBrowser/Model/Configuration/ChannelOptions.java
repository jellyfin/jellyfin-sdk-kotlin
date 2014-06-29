package MediaBrowser.Model.Configuration;

public class ChannelOptions
{
	private Integer privatePreferredStreamingWidth;
	public final Integer getPreferredStreamingWidth()
	{
		return privatePreferredStreamingWidth;
	}
	public final void setPreferredStreamingWidth(Integer value)
	{
		privatePreferredStreamingWidth = value;
	}

	private String privateDownloadPath;
	public final String getDownloadPath()
	{
		return privateDownloadPath;
	}
	public final void setDownloadPath(String value)
	{
		privateDownloadPath = value;
	}
	private Integer privateMaxDownloadAge;
	public final Integer getMaxDownloadAge()
	{
		return privateMaxDownloadAge;
	}
	public final void setMaxDownloadAge(Integer value)
	{
		privateMaxDownloadAge = value;
	}

	private String[] privateDownloadingChannels;
	public final String[] getDownloadingChannels()
	{
		return privateDownloadingChannels;
	}
	public final void setDownloadingChannels(String[] value)
	{
		privateDownloadingChannels = value;
	}

	public ChannelOptions()
	{
		setDownloadingChannels(new String[] { });
		setMaxDownloadAge(30);
	}
}