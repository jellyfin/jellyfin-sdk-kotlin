package mediabrowser.model.branding;

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
	/** 
	 Gets or sets the custom CSS.
	 
	 <value>The custom CSS.</value>
	*/
	private String CustomCss;
	public final String getCustomCss()
	{
		return CustomCss;
	}
	public final void setCustomCss(String value)
	{
		CustomCss = value;
	}
}