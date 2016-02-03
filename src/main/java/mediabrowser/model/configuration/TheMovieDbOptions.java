package mediabrowser.model.configuration;

public class TheMovieDbOptions
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
}