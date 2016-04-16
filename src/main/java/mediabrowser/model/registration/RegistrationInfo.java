package mediabrowser.model.registration;

public class RegistrationInfo
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
	 Gets or sets the expiration date.
	 
	 <value>The expiration date.</value>
	*/
	private java.util.Date ExpirationDate = new java.util.Date(0);
	public final java.util.Date getExpirationDate()
	{
		return ExpirationDate;
	}
	public final void setExpirationDate(java.util.Date value)
	{
		ExpirationDate = value;
	}
	/** 
	 Gets or sets a value indicating whether this instance is trial.
	 
	 <value><c>true</c> if this instance is trial; otherwise, <c>false</c>.</value>
	*/
	private boolean IsTrial;
	public final boolean getIsTrial()
	{
		return IsTrial;
	}
	public final void setIsTrial(boolean value)
	{
		IsTrial = value;
	}
	private boolean IsOverLimit;
	public final boolean getIsOverLimit()
	{
		return IsOverLimit;
	}
	public final void setIsOverLimit(boolean value)
	{
		IsOverLimit = value;
	}
	/**
	 Gets or sets a value indicating whether this instance is registered.
	 
	 <value><c>true</c> if this instance is registered; otherwise, <c>false</c>.</value>
	*/
	private boolean IsRegistered;
	public final boolean getIsRegistered()
	{
		return IsRegistered;
	}
	public final void setIsRegistered(boolean value)
	{
		IsRegistered = value;
	}
}