package MediaBrowser.Model.FileOrganization;

public class AutoOrganizeOptions
{
	/** 
	 Gets or sets the tv options.
	 
	 <value>The tv options.</value>
	*/
	private TvFileOrganizationOptions TvOptions;
	public final TvFileOrganizationOptions getTvOptions()
	{
		return TvOptions;
	}
	public final void setTvOptions(TvFileOrganizationOptions value)
	{
		TvOptions = value;
	}

	public AutoOrganizeOptions()
	{
		setTvOptions(new TvFileOrganizationOptions());
	}
}