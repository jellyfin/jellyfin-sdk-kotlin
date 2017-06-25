package mediabrowser.model.dto;

public class ImageByNameInfo
{
	/** 
	 Gets or sets the name.
	 
	 <value>The name.</value>
	*/
	private String Name;
	public final String getName()
	{
		return Name;
	}
	public final void setName(String value)
	{
		Name = value;
	}
	/** 
	 Gets or sets the theme.
	 
	 <value>The theme.</value>
	*/
	private String Theme;
	public final String getTheme()
	{
		return Theme;
	}
	public final void setTheme(String value)
	{
		Theme = value;
	}
	/** 
	 Gets or sets the context.
	 
	 <value>The context.</value>
	*/
	private String Context;
	public final String getContext()
	{
		return Context;
	}
	public final void setContext(String value)
	{
		Context = value;
	}
	/** 
	 Gets or sets the length of the file.
	 
	 <value>The length of the file.</value>
	*/
	private long FileLength;
	public final long getFileLength()
	{
		return FileLength;
	}
	public final void setFileLength(long value)
	{
		FileLength = value;
	}
	/** 
	 Gets or sets the format.
	 
	 <value>The format.</value>
	*/
	private String Format;
	public final String getFormat()
	{
		return Format;
	}
	public final void setFormat(String value)
	{
		Format = value;
	}
}