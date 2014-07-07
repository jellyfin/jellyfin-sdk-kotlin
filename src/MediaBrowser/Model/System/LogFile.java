package MediaBrowser.Model.System;

public class LogFile
{
	/** 
	 Gets or sets the date created.
	 
	 <value>The date created.</value>
	*/
	private java.util.Date privateDateCreated = new java.util.Date(0);
	public final java.util.Date getDateCreated()
	{
		return privateDateCreated;
	}
	public final void setDateCreated(java.util.Date value)
	{
		privateDateCreated = value;
	}

	/** 
	 Gets or sets the date modified.
	 
	 <value>The date modified.</value>
	*/
	private java.util.Date privateDateModified = new java.util.Date(0);
	public final java.util.Date getDateModified()
	{
		return privateDateModified;
	}
	public final void setDateModified(java.util.Date value)
	{
		privateDateModified = value;
	}

	/** 
	 Gets or sets the size.
	 
	 <value>The size.</value>
	*/
	private long privateSize;
	public final long getSize()
	{
		return privateSize;
	}
	public final void setSize(long value)
	{
		privateSize = value;
	}

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
}