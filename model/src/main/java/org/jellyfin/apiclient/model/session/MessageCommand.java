package org.jellyfin.apiclient.model.session;

public class MessageCommand
{
	private String Header;
	public final String getHeader()
	{
		return Header;
	}
	public final void setHeader(String value)
	{
		Header = value;
	}

	private String Text;
	public final String getText()
	{
		return Text;
	}
	public final void setText(String value)
	{
		Text = value;
	}

	private Long TimeoutMs;
	public final Long getTimeoutMs()
	{
		return TimeoutMs;
	}
	public final void setTimeoutMs(Long value)
	{
		TimeoutMs = value;
	}
}