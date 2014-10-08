package MediaBrowser.Model.News;

public class NewsChannel
{
	private String Title;
	public final String getTitle()
	{
		return Title;
	}
	public final void setTitle(String value)
	{
		Title = value;
	}
	private String Link;
	public final String getLink()
	{
		return Link;
	}
	public final void setLink(String value)
	{
		Link = value;
	}
	private String Description;
	public final String getDescription()
	{
		return Description;
	}
	public final void setDescription(String value)
	{
		Description = value;
	}
	private java.util.ArrayList<NewsItem> Items;
	public final java.util.ArrayList<NewsItem> getItems()
	{
		return Items;
	}
	public final void setItems(java.util.ArrayList<NewsItem> value)
	{
		Items = value;
	}
}