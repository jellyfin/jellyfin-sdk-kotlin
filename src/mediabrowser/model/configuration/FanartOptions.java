package mediabrowser.model.configuration;

public class FanartOptions
{
	/** 
	 Gets or sets a value indicating whether [enable automatic updates].
	 
	 <value><c>true</c> if [enable automatic updates]; otherwise, <c>false</c>.</value>
	*/
	private boolean EnableAutomaticUpdates;
	public final boolean getEnableAutomaticUpdates()
	{
		return EnableAutomaticUpdates;
	}
	public final void setEnableAutomaticUpdates(boolean value)
	{
		EnableAutomaticUpdates = value;
	}
	/** 
	 Gets or sets the user API key.
	 
	 <value>The user API key.</value>
	*/
	private String UserApiKey;
	public final String getUserApiKey()
	{
		return UserApiKey;
	}
	public final void setUserApiKey(String value)
	{
		UserApiKey = value;
	}
}