package MediaBrowser.Model.Configuration;

public class MetadataConfiguration
{
	private boolean UseFileCreationTimeForDateAdded;
	public final boolean getUseFileCreationTimeForDateAdded()
	{
		return UseFileCreationTimeForDateAdded;
	}
	public final void setUseFileCreationTimeForDateAdded(boolean value)
	{
		UseFileCreationTimeForDateAdded = value;
	}
}