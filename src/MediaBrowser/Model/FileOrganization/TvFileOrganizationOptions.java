package MediaBrowser.Model.FileOrganization;

public class TvFileOrganizationOptions
{
	private boolean IsEnabled;
	public final boolean getIsEnabled()
	{
		return IsEnabled;
	}
	public final void setIsEnabled(boolean value)
	{
		IsEnabled = value;
	}
	private int MinFileSizeMb;
	public final int getMinFileSizeMb()
	{
		return MinFileSizeMb;
	}
	public final void setMinFileSizeMb(int value)
	{
		MinFileSizeMb = value;
	}
	private String[] LeftOverFileExtensionsToDelete;
	public final String[] getLeftOverFileExtensionsToDelete()
	{
		return LeftOverFileExtensionsToDelete;
	}
	public final void setLeftOverFileExtensionsToDelete(String[] value)
	{
		LeftOverFileExtensionsToDelete = value;
	}
	private String[] WatchLocations;
	public final String[] getWatchLocations()
	{
		return WatchLocations;
	}
	public final void setWatchLocations(String[] value)
	{
		WatchLocations = value;
	}

	private String SeasonFolderPattern;
	public final String getSeasonFolderPattern()
	{
		return SeasonFolderPattern;
	}
	public final void setSeasonFolderPattern(String value)
	{
		SeasonFolderPattern = value;
	}

	private String SeasonZeroFolderName;
	public final String getSeasonZeroFolderName()
	{
		return SeasonZeroFolderName;
	}
	public final void setSeasonZeroFolderName(String value)
	{
		SeasonZeroFolderName = value;
	}

	private String EpisodeNamePattern;
	public final String getEpisodeNamePattern()
	{
		return EpisodeNamePattern;
	}
	public final void setEpisodeNamePattern(String value)
	{
		EpisodeNamePattern = value;
	}
	private String MultiEpisodeNamePattern;
	public final String getMultiEpisodeNamePattern()
	{
		return MultiEpisodeNamePattern;
	}
	public final void setMultiEpisodeNamePattern(String value)
	{
		MultiEpisodeNamePattern = value;
	}

	private boolean OverwriteExistingEpisodes;
	public final boolean getOverwriteExistingEpisodes()
	{
		return OverwriteExistingEpisodes;
	}
	public final void setOverwriteExistingEpisodes(boolean value)
	{
		OverwriteExistingEpisodes = value;
	}

	private boolean DeleteEmptyFolders;
	public final boolean getDeleteEmptyFolders()
	{
		return DeleteEmptyFolders;
	}
	public final void setDeleteEmptyFolders(boolean value)
	{
		DeleteEmptyFolders = value;
	}

	private boolean CopyOriginalFile;
	public final boolean getCopyOriginalFile()
	{
		return CopyOriginalFile;
	}
	public final void setCopyOriginalFile(boolean value)
	{
		CopyOriginalFile = value;
	}

	public TvFileOrganizationOptions()
	{
		setMinFileSizeMb(50);

		setLeftOverFileExtensionsToDelete(new String[] { });

		setWatchLocations(new String[] { });

		setEpisodeNamePattern("%sn - %sx%0e - %en.%ext");
		setMultiEpisodeNamePattern("%sn - %sx%0e-x%0ed - %en.%ext");
		setSeasonFolderPattern("Season %s");
		setSeasonZeroFolderName("Season 0");

		setCopyOriginalFile(false);
	}
}