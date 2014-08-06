package MediaBrowser.Model.FileOrganization;

public class TvFileOrganizationOptions
{
	private boolean privateIsEnabled;
	public final boolean getIsEnabled()
	{
		return privateIsEnabled;
	}
	public final void setIsEnabled(boolean value)
	{
		privateIsEnabled = value;
	}
	private int privateMinFileSizeMb;
	public final int getMinFileSizeMb()
	{
		return privateMinFileSizeMb;
	}
	public final void setMinFileSizeMb(int value)
	{
		privateMinFileSizeMb = value;
	}
	private String[] privateLeftOverFileExtensionsToDelete;
	public final String[] getLeftOverFileExtensionsToDelete()
	{
		return privateLeftOverFileExtensionsToDelete;
	}
	public final void setLeftOverFileExtensionsToDelete(String[] value)
	{
		privateLeftOverFileExtensionsToDelete = value;
	}
	private String[] privateWatchLocations;
	public final String[] getWatchLocations()
	{
		return privateWatchLocations;
	}
	public final void setWatchLocations(String[] value)
	{
		privateWatchLocations = value;
	}

	private String privateSeasonFolderPattern;
	public final String getSeasonFolderPattern()
	{
		return privateSeasonFolderPattern;
	}
	public final void setSeasonFolderPattern(String value)
	{
		privateSeasonFolderPattern = value;
	}

	private String privateSeasonZeroFolderName;
	public final String getSeasonZeroFolderName()
	{
		return privateSeasonZeroFolderName;
	}
	public final void setSeasonZeroFolderName(String value)
	{
		privateSeasonZeroFolderName = value;
	}

	private String privateEpisodeNamePattern;
	public final String getEpisodeNamePattern()
	{
		return privateEpisodeNamePattern;
	}
	public final void setEpisodeNamePattern(String value)
	{
		privateEpisodeNamePattern = value;
	}
	private String privateMultiEpisodeNamePattern;
	public final String getMultiEpisodeNamePattern()
	{
		return privateMultiEpisodeNamePattern;
	}
	public final void setMultiEpisodeNamePattern(String value)
	{
		privateMultiEpisodeNamePattern = value;
	}

	private boolean privateOverwriteExistingEpisodes;
	public final boolean getOverwriteExistingEpisodes()
	{
		return privateOverwriteExistingEpisodes;
	}
	public final void setOverwriteExistingEpisodes(boolean value)
	{
		privateOverwriteExistingEpisodes = value;
	}

	private boolean privateDeleteEmptyFolders;
	public final boolean getDeleteEmptyFolders()
	{
		return privateDeleteEmptyFolders;
	}
	public final void setDeleteEmptyFolders(boolean value)
	{
		privateDeleteEmptyFolders = value;
	}

	private boolean privateCopyOriginalFile;
	public final boolean getCopyOriginalFile()
	{
		return privateCopyOriginalFile;
	}
	public final void setCopyOriginalFile(boolean value)
	{
		privateCopyOriginalFile = value;
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