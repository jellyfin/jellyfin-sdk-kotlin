package MediaBrowser.Model.Dto;

public class ImageByNameInfo
{
	/** 
	 Gets or sets the name.
	 
	 <value>The name.</value>
	*/
	private String privateName;
	public final String getName()
	{
		return privateName;
	}
	public final void setName(String value)
	{
		privateName = value;
	}
	/** 
	 Gets or sets the theme.
	 
	 <value>The theme.</value>
	*/
	private String privateTheme;
	public final String getTheme()
	{
		return privateTheme;
	}
	public final void setTheme(String value)
	{
		privateTheme = value;
	}
	/** 
	 Gets or sets the context.
	 
	 <value>The context.</value>
	*/
	private String privateContext;
	public final String getContext()
	{
		return privateContext;
	}
	public final void setContext(String value)
	{
		privateContext = value;
	}
	/** 
	 Gets or sets the length of the file.
	 
	 <value>The length of the file.</value>
	*/
	private long privateFileLength;
	public final long getFileLength()
	{
		return privateFileLength;
	}
	public final void setFileLength(long value)
	{
		privateFileLength = value;
	}
	/** 
	 Gets or sets the format.
	 
	 <value>The format.</value>
	*/
	private String privateFormat;
	public final String getFormat()
	{
		return privateFormat;
	}
	public final void setFormat(String value)
	{
		privateFormat = value;
	}
}