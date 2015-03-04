package mediabrowser.model.configuration;

/** 
 Class UserConfiguration
*/
public class UserConfiguration
{
	/** 
	 Gets or sets a value indicating whether this instance is administrator.
	 
	 <value><c>true</c> if this instance is administrator; otherwise, <c>false</c>.</value>
	*/
	private boolean IsAdministrator;
	public final boolean getIsAdministrator()
	{
		return IsAdministrator;
	}
	public final void setIsAdministrator(boolean value)
	{
		IsAdministrator = value;
	}

	/** 
	 Gets or sets the audio language preference.
	 
	 <value>The audio language preference.</value>
	*/
	private String AudioLanguagePreference;
	public final String getAudioLanguagePreference()
	{
		return AudioLanguagePreference;
	}
	public final void setAudioLanguagePreference(String value)
	{
		AudioLanguagePreference = value;
	}

	/** 
	 Gets or sets a value indicating whether [play default audio track].
	 
	 <value><c>true</c> if [play default audio track]; otherwise, <c>false</c>.</value>
	*/
	private boolean PlayDefaultAudioTrack;
	public final boolean getPlayDefaultAudioTrack()
	{
		return PlayDefaultAudioTrack;
	}
	public final void setPlayDefaultAudioTrack(boolean value)
	{
		PlayDefaultAudioTrack = value;
	}

	/** 
	 Gets or sets the subtitle language preference.
	 
	 <value>The subtitle language preference.</value>
	*/
	private String SubtitleLanguagePreference;
	public final String getSubtitleLanguagePreference()
	{
		return SubtitleLanguagePreference;
	}
	public final void setSubtitleLanguagePreference(String value)
	{
		SubtitleLanguagePreference = value;
	}

	private boolean DisplayMissingEpisodes;
	public final boolean getDisplayMissingEpisodes()
	{
		return DisplayMissingEpisodes;
	}
	public final void setDisplayMissingEpisodes(boolean value)
	{
		DisplayMissingEpisodes = value;
	}
	private boolean DisplayUnairedEpisodes;
	public final boolean getDisplayUnairedEpisodes()
	{
		return DisplayUnairedEpisodes;
	}
	public final void setDisplayUnairedEpisodes(boolean value)
	{
		DisplayUnairedEpisodes = value;
	}

	private boolean GroupMoviesIntoBoxSets;
	public final boolean getGroupMoviesIntoBoxSets()
	{
		return GroupMoviesIntoBoxSets;
	}
	public final void setGroupMoviesIntoBoxSets(boolean value)
	{
		GroupMoviesIntoBoxSets = value;
	}

	private String[] DisplayChannelsWithinViews;
	public final String[] getDisplayChannelsWithinViews()
	{
		return DisplayChannelsWithinViews;
	}
	public final void setDisplayChannelsWithinViews(String[] value)
	{
		DisplayChannelsWithinViews = value;
	}

	private String[] ExcludeFoldersFromGrouping;
	public final String[] getExcludeFoldersFromGrouping()
	{
		return ExcludeFoldersFromGrouping;
	}
	public final void setExcludeFoldersFromGrouping(String[] value)
	{
		ExcludeFoldersFromGrouping = value;
	}

	private SubtitlePlaybackMode SubtitleMode = SubtitlePlaybackMode.values()[0];
	public final SubtitlePlaybackMode getSubtitleMode()
	{
		return SubtitleMode;
	}
	public final void setSubtitleMode(SubtitlePlaybackMode value)
	{
		SubtitleMode = value;
	}
	private boolean DisplayCollectionsView;
	public final boolean getDisplayCollectionsView()
	{
		return DisplayCollectionsView;
	}
	public final void setDisplayCollectionsView(boolean value)
	{
		DisplayCollectionsView = value;
	}
	private boolean DisplayFoldersView;
	public final boolean getDisplayFoldersView()
	{
		return DisplayFoldersView;
	}
	public final void setDisplayFoldersView(boolean value)
	{
		DisplayFoldersView = value;
	}

	private boolean EnableLocalPassword;
	public final boolean getEnableLocalPassword()
	{
		return EnableLocalPassword;
	}
	public final void setEnableLocalPassword(boolean value)
	{
		EnableLocalPassword = value;
	}

	private String[] OrderedViews;
	public final String[] getOrderedViews()
	{
		return OrderedViews;
	}
	public final void setOrderedViews(String[] value)
	{
		OrderedViews = value;
	}

	private boolean IncludeTrailersInSuggestions;
	public final boolean getIncludeTrailersInSuggestions()
	{
		return IncludeTrailersInSuggestions;
	}
	public final void setIncludeTrailersInSuggestions(boolean value)
	{
		IncludeTrailersInSuggestions = value;
	}

	private boolean EnableCinemaMode;
	public final boolean getEnableCinemaMode()
	{
		return EnableCinemaMode;
	}
	public final void setEnableCinemaMode(boolean value)
	{
		EnableCinemaMode = value;
	}

	private String[] LatestItemsExcludes;
	public final String[] getLatestItemsExcludes()
	{
		return LatestItemsExcludes;
	}
	public final void setLatestItemsExcludes(String[] value)
	{
		LatestItemsExcludes = value;
	}

	private boolean HasMigratedToPolicy;
	public final boolean getHasMigratedToPolicy()
	{
		return HasMigratedToPolicy;
	}
	public final void setHasMigratedToPolicy(boolean value)
	{
		HasMigratedToPolicy = value;
	}

	/** 
	 Initializes a new instance of the <see cref="UserConfiguration" /> class.
	*/
	public UserConfiguration()
	{
		setPlayDefaultAudioTrack(true);

		setLatestItemsExcludes(new String[] { });
		setOrderedViews(new String[] { });
		setDisplayChannelsWithinViews(new String[] { });

		setExcludeFoldersFromGrouping(new String[] { });
		setDisplayCollectionsView(true);

		setIncludeTrailersInSuggestions(true);
		setEnableCinemaMode(true);
	}
}