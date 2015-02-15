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
	 Gets or sets a value indicating whether this instance is wired network available.
	 
	 <value><c>null</c> if [is wired network available] contains no value, <c>true</c> if [is wired network available]; otherwise, <c>false</c>.</value>
	*/
	private Boolean IsEthernetNetworkAvailable;
	public final Boolean getIsEthernetNetworkAvailable()
	{
		return IsEthernetNetworkAvailable;
	}
	public final void setIsEthernetNetworkAvailable(Boolean value)
	{
		IsEthernetNetworkAvailable = value;
	}
	/** 
	 Gets or sets a value indicating whether this instance is wifi network available.
	 
	 <value><c>null</c> if [is wifi network available] contains no value, <c>true</c> if [is wifi network available]; otherwise, <c>false</c>.</value>
	*/
	private Boolean IsWifiNetworkAvailable;
	public final Boolean getIsWifiNetworkAvailable()
	{
		return IsWifiNetworkAvailable;
	}
	public final void setIsWifiNetworkAvailable(Boolean value)
	{
		IsWifiNetworkAvailable = value;
	}
	/** 
	 Gets the is any local network available.
	 
	 @return <c>true</c> if XXXX, <c>false</c> otherwise.
	*/
	public final boolean GetIsAnyLocalNetworkAvailable()
	{
		if (getIsEthernetNetworkAvailable() == null && getIsWifiNetworkAvailable() == null)
		{
			return getIsNetworkAvailable();
		}

		Boolean tempVar = getIsEthernetNetworkAvailable();
		Boolean tempVar2 = getIsWifiNetworkAvailable();
		return ((tempVar != null) ? tempVar : false) || ((tempVar2 != null) ? tempVar2 : false);
	}
}