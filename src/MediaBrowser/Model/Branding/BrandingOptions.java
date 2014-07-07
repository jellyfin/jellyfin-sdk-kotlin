package MediaBrowser.Model.Branding;

public class BrandingOptions
{
	/** 
	 Gets or sets the login disclaimer.
	 
	 <value>The login disclaimer.</value>
	*/
	private String privateLoginDisclaimer;
	public final String getLoginDisclaimer()
	{
		return privateLoginDisclaimer;
	}
	public final void setLoginDisclaimer(String value)
	{
		privateLoginDisclaimer = value;
	}
}