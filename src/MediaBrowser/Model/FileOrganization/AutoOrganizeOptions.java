package MediaBrowser.Model.FileOrganization;

public class AutoOrganizeOptions
{
	/** 
	 Gets or sets the tv options.
	 
	 <value>The tv options.</value>
	*/
	private TvFileOrganizationOptions privateTvOptions;
	public final TvFileOrganizationOptions getTvOptions()
	{
		return privateTvOptions;
	}
	public final void setTvOptions(TvFileOrganizationOptions value)
	{
		privateTvOptions = value;
	}

	public AutoOrganizeOptions()
	{
		setTvOptions(new TvFileOrganizationOptions());
	}
}