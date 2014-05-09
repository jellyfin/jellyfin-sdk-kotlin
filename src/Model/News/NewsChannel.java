package MediaBrowser.Model.News;

public class NewsChannel
{
	private String privateTitle;
	public final String getTitle()
	{
		return privateTitle;
	}
	public final void setTitle(String value)
	{
		privateTitle = value;
	}
	private String privateLink;
	public final String getLink()
	{
		return privateLink;
	}
	public final void setLink(String value)
	{
		privateLink = value;
	}
	private String privateDescription;
	public final String getDescription()
	{
		return privateDescription;
	}
	public final void setDescription(String value)
	{
		privateDescription = value;
	}
	private java.util.ArrayList<NewsItem> privateItems;
	public final java.util.ArrayList<NewsItem> getItems()
	{
		return privateItems;
	}
	public final void setItems(java.util.ArrayList<NewsItem> value)
	{
		privateItems = value;
	}
}