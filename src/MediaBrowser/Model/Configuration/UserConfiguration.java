package MediaBrowser.Model.Configuration;

/** 
 Class UserConfiguration
*/
public class UserConfiguration
{
	/** 
	 Gets or sets the max parental rating.
	 
	 <value>The max parental rating.</value>
	*/
	private Integer MaxParentalRating;
	public final Integer getMaxParentalRating()
	{
		return MaxParentalRating;
	}
	public final void setMaxParentalRating(Integer value)
	{
		MaxParentalRating = value;
	}

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

	/** 
	 Gets or sets a value indicating whether this instance is hidden.
	 
	 <value><c>true</c> if this instance is hidden; otherwise, <c>false</c>.</value>
	*/
	private boolean IsHidden;
	public final boolean getIsHidden()
	{
		return IsHidden;
	}
	public final void setIsHidden(boolean value)
	{
		IsHidden = value;
	}

	private boolean IsDisabled;
	public final boolean getIsDisabled()
	{
		return IsDisabled;
	}
	public final void setIsDisabled(boolean value)
	{
		IsDisabled = value;
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
	private boolean EnableRemoteControlOfOtherUsers;
	public final boolean getEnableRemoteControlOfOtherUsers()
	{
		return EnableRemoteControlOfOtherUsers;
	}
	public final void setEnableRemoteControlOfOtherUsers(boolean value)
	{
		EnableRemoteControlOfOtherUsers = value;
	}

	private boolean EnableLiveTvManagement;
	public final boolean getEnableLiveTvManagement()
	{
		return EnableLiveTvManagement;
	}
	public final void setEnableLiveTvManagement(boolean value)
	{
		EnableLiveTvManagement = value;
	}
	private boolean EnableLiveTvAccess;
	public final boolean getEnableLiveTvAccess()
	{
		return EnableLiveTvAccess;
	}
	public final void setEnableLiveTvAccess(boolean value)
	{
		EnableLiveTvAccess = value;
	}

	private boolean EnableMediaPlayback;
	public final boolean getEnableMediaPlayback()
	{
		return EnableMediaPlayback;
	}
	public final void setEnableMediaPlayback(boolean value)
	{
		EnableMediaPlayback = value;
	}
	private boolean EnableContentDeletion;
	public final boolean getEnableContentDeletion()
	{
		return EnableContentDeletion;
	}
	public final void setEnableContentDeletion(boolean value)
	{
		EnableContentDeletion = value;
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

	private String[] BlockedMediaFolders;
	public final String[] getBlockedMediaFolders()
	{
		return BlockedMediaFolders;
	}
	public final void setBlockedMediaFolders(String[] value)
	{
		BlockedMediaFolders = value;
	}
	private String[] BlockedChannels;
	public final String[] getBlockedChannels()
	{
		return BlockedChannels;
	}
	public final void setBlockedChannels(String[] value)
	{
		BlockedChannels = value;
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

	private UnratedItem[] BlockUnratedItems;
	public final UnratedItem[] getBlockUnratedItems()
	{
		return BlockUnratedItems;
	}
	public final void setBlockUnratedItems(UnratedItem[] value)
	{
		BlockUnratedItems = value;
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

	private boolean SyncConnectName;
	public final boolean getSyncConnectName()
	{
		return SyncConnectName;
	}
	public final void setSyncConnectName(boolean value)
	{
		SyncConnectName = value;
	}
	private boolean SyncConnectImage;
	public final boolean getSyncConnectImage()
	{
		return SyncConnectImage;
	}
	public final void setSyncConnectImage(boolean value)
	{
		SyncConnectImage = value;
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

	private AccessSchedule[] AccessSchedules;
	public final AccessSchedule[] getAccessSchedules()
	{
		return AccessSchedules;
	}
	public final void setAccessSchedules(AccessSchedule[] value)
	{
		AccessSchedules = value;
	}

	private boolean EnableUserPreferenceAccess;
	public final boolean getEnableUserPreferenceAccess()
	{
		return EnableUserPreferenceAccess;
	}
	public final void setEnableUserPreferenceAccess(boolean value)
	{
		EnableUserPreferenceAccess = value;
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

	/** 
	 Initializes a new instance of the <see cref="UserConfiguration" /> class.
	*/
	public UserConfiguration()
	{
		setPlayDefaultAudioTrack(true);
		setEnableLiveTvManagement(true);
		setEnableMediaPlayback(true);
		setEnableLiveTvAccess(true);

		setLatestItemsExcludes(new String[] { });
		setOrderedViews(new String[] { });
		setBlockedMediaFolders(new String[] { });
		setDisplayChannelsWithinViews(new String[] { });
		setBlockedChannels(new String[] { });
		setBlockUnratedItems(new UnratedItem[] { });

		setExcludeFoldersFromGrouping(new String[] { });
		setDisplayCollectionsView(true);
		setDisplayFoldersView(true);

		setSyncConnectName(true);
		setSyncConnectImage(true);
		setIncludeTrailersInSuggestions(true);
		setEnableCinemaMode(true);
		setEnableUserPreferenceAccess(true);

		setAccessSchedules(new AccessSchedule[] { });
	}
}