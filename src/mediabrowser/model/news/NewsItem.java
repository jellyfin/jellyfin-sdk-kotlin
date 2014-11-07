package mediabrowser.model.news;

public class NewsItem
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
	private String DescriptionHtml;
	public final String getDescriptionHtml()
	{
		return DescriptionHtml;
	}
	public final void setDescriptionHtml(String value)
	{
		DescriptionHtml = value;
	}
	private String Guid;
	public final String getGuid()
	{
		return Guid;
	}
	public final void setGuid(String value)
	{
		Guid = value;
	}
	private java.util.Date Date = new java.util.Date(0);
	public final java.util.Date getDate()
	{
		return Date;
	}
	public final void setDate(java.util.Date value)
	{
		Date = value;
	}
}