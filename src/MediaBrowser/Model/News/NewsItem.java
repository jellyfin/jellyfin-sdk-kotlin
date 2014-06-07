package MediaBrowser.Model.News;

public class NewsItem
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
	private String privateDescriptionHtml;
	public final String getDescriptionHtml()
	{
		return privateDescriptionHtml;
	}
	public final void setDescriptionHtml(String value)
	{
		privateDescriptionHtml = value;
	}
	private String privateGuid;
	public final String getGuid()
	{
		return privateGuid;
	}
	public final void setGuid(String value)
	{
		privateGuid = value;
	}
	private java.util.Date privateDate = new java.util.Date(0);
	public final java.util.Date getDate()
	{
		return privateDate;
	}
	public final void setDate(java.util.Date value)
	{
		privateDate = value;
	}
}