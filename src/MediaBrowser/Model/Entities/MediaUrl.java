package MediaBrowser.Model.Entities;

public class MediaUrl
{
	private String privateUrl;
	public final String getUrl()
	{
		return privateUrl;
	}
	public final void setUrl(String value)
	{
		privateUrl = value;
	}
	private String privateName;
	public final String getName()
	{
		return privateName;
	}
	public final void setName(String value)
	{
		privateName = value;
	}
	private VideoSize privateVideoSize;
	public final VideoSize getVideoSize()
	{
		return privateVideoSize;
	}
	public final void setVideoSize(VideoSize value)
	{
		privateVideoSize = value;
	}
	private boolean privateIsDirectLink;
	public final boolean getIsDirectLink()
	{
		return privateIsDirectLink;
	}
	public final void setIsDirectLink(boolean value)
	{
		privateIsDirectLink = value;
	}
}