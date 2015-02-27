package mediabrowser.model.apiclient;

public class NetworkStatus
{
	/** 
	 Gets or sets a value indicating whether this instance is network available.
	 
	 <value><c>true</c> if this instance is network available; otherwise, <c>false</c>.</value>
	*/
	private boolean IsNetworkAvailable;
	public final boolean getIsNetworkAvailable()
	{
		return IsNetworkAvailable;
	}
	public final void setIsNetworkAvailable(boolean value)
	{
		IsNetworkAvailable = value;
	}
	/** 
	 Gets or sets a value indicating whether this instance is local network available.
	 
	 <value><c>null</c> if [is local network available] contains no value, <c>true</c> if [is local network available]; otherwise, <c>false</c>.</value>
	*/
	private Boolean IsLocalNetworkAvailable;
	public final Boolean getIsLocalNetworkAvailable()
	{
		return IsLocalNetworkAvailable;
	}
	public final void setIsLocalNetworkAvailable(Boolean value)
	{
		IsLocalNetworkAvailable = value;
	}
	/** 
	 Gets the is any local network available.
	 
	 @return <c>true</c> if XXXX, <c>false</c> otherwise.
	*/
	public final boolean GetIsAnyLocalNetworkAvailable()
	{
		if (getIsLocalNetworkAvailable() == null)
		{
			return getIsNetworkAvailable();
		}

		return getIsLocalNetworkAvailable();
	}
}