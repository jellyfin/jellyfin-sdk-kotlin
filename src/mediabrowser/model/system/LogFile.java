package mediabrowser.model.system;

public class LogFile
{
	/** 
	 Gets or sets the date created.
	 
	 <value>The date created.</value>
	*/
	private java.util.Date DateCreated = new java.util.Date(0);
	public final java.util.Date getDateCreated()
	{
		return DateCreated;
	}
	public final void setDateCreated(java.util.Date value)
	{
		DateCreated = value;
	}

	/** 
	 Gets or sets the date modified.
	 
	 <value>The date modified.</value>
	*/
	private java.util.Date DateModified = new java.util.Date(0);
	public final java.util.Date getDateModified()
	{
		return DateModified;
	}
	public final void setDateModified(java.util.Date value)
	{
		DateModified = value;
	}

	/** 
	 Gets or sets the size.
	 
	 <value>The size.</value>
	*/
	private long Size;
	public final long getSize()
	{
		return Size;
	}
	public final void setSize(long value)
	{
		Size = value;
	}

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
}