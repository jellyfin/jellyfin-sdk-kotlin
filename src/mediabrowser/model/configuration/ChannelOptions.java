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
	private Integer MaxDownloadAge;
	public final Integer getMaxDownloadAge()
	{
		return MaxDownloadAge;
	}
	public final void setMaxDownloadAge(Integer value)
	{
		MaxDownloadAge = value;
	}

	private String[] DownloadingChannels;
	public final String[] getDownloadingChannels()
	{
		return DownloadingChannels;
	}
	public final void setDownloadingChannels(String[] value)
	{
		DownloadingChannels = value;
	}

	private Double DownloadSizeLimit;
	public final Double getDownloadSizeLimit()
	{
		return DownloadSizeLimit;
	}
	public final void setDownloadSizeLimit(Double value)
	{
		DownloadSizeLimit = value;
	}

	public ChannelOptions()
	{
		setDownloadingChannels(new String[] { });
		setDownloadSizeLimit(.5);
		setMaxDownloadAge(30);
	}
}