package MediaBrowser.Model.Branding;

public class BrandingOptions
{
	/** 
	 Gets or sets the login disclaimer.
	 
	 <value>The login disclaimer.</value>
	*/
	private String LoginDisclaimer;
	public final String getLoginDisclaimer()
	{
		return LoginDisclaimer;
	}
	public final void setLoginDisclaimer(String value)
	{
		LoginDisclaimer = value;
	}
}