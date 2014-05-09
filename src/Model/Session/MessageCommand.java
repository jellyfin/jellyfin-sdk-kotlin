package MediaBrowser.Model.Session;

public class MessageCommand
{
	private String privateHeader;
	public final String getHeader()
	{
		return privateHeader;
	}
	public final void setHeader(String value)
	{
		privateHeader = value;
	}

	private String privateText;
	public final String getText()
	{
		return privateText;
	}
	public final void setText(String value)
	{
		privateText = value;
	}

	private Long privateTimeoutMs;
	public final Long getTimeoutMs()
	{
		return privateTimeoutMs;
	}
	public final void setTimeoutMs(Long value)
	{
		privateTimeoutMs = value;
	}
}