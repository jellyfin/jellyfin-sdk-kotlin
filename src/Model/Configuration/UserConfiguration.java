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
	private Integer privateMaxParentalRating;
	public final Integer getMaxParentalRating()
	{
		return privateMaxParentalRating;
	}
	public final void setMaxParentalRating(Integer value)
	{
		privateMaxParentalRating = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is administrator.
	 
	 <value><c>true</c> if this instance is administrator; otherwise, <c>false</c>.</value>
	*/
	private boolean privateIsAdministrator;
	public final boolean getIsAdministrator()
	{
		return privateIsAdministrator;
	}
	public final void setIsAdministrator(boolean value)
	{
		privateIsAdministrator = value;
	}

	/** 
	 Gets or sets the audio language preference.
	 
	 <value>The audio language preference.</value>
	*/
	private String privateAudioLanguagePreference;
	public final String getAudioLanguagePreference()
	{
		return privateAudioLanguagePreference;
	}
	public final void setAudioLanguagePreference(String value)
	{
		privateAudioLanguagePreference = value;
	}

	/** 
	 Gets or sets a value indicating whether [play default audio track].
	 
	 <value><c>true</c> if [play default audio track]; otherwise, <c>false</c>.</value>
	*/
	private boolean privatePlayDefaultAudioTrack;
	public final boolean getPlayDefaultAudioTrack()
	{
		return privatePlayDefaultAudioTrack;
	}
	public final void setPlayDefaultAudioTrack(boolean value)
	{
		privatePlayDefaultAudioTrack = value;
	}

	/** 
	 Gets or sets the subtitle language preference.
	 
	 <value>The subtitle language preference.</value>
	*/
	private String privateSubtitleLanguagePreference;
	public final String getSubtitleLanguagePreference()
	{
		return privateSubtitleLanguagePreference;
	}
	public final void setSubtitleLanguagePreference(String value)
	{
		privateSubtitleLanguagePreference = value;
	}

	/** 
	 Gets or sets a value indicating whether [use forced subtitles only].
	 
	 <value><c>true</c> if [use forced subtitles only]; otherwise, <c>false</c>.</value>
	*/
	private boolean privateUseForcedSubtitlesOnly;
	public final boolean getUseForcedSubtitlesOnly()
	{
		return privateUseForcedSubtitlesOnly;
	}
	public final void setUseForcedSubtitlesOnly(boolean value)
	{
		privateUseForcedSubtitlesOnly = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is hidden.
	 
	 <value><c>true</c> if this instance is hidden; otherwise, <c>false</c>.</value>
	*/
	private boolean privateIsHidden;
	public final boolean getIsHidden()
	{
		return privateIsHidden;
	}
	public final void setIsHidden(boolean value)
	{
		privateIsHidden = value;
	}

	private boolean privateIsDisabled;
	public final boolean getIsDisabled()
	{
		return privateIsDisabled;
	}
	public final void setIsDisabled(boolean value)
	{
		privateIsDisabled = value;
	}

	private boolean privateDisplayMissingEpisodes;
	public final boolean getDisplayMissingEpisodes()
	{
		return privateDisplayMissingEpisodes;
	}
	public final void setDisplayMissingEpisodes(boolean value)
	{
		privateDisplayMissingEpisodes = value;
	}
	private boolean privateDisplayUnairedEpisodes;
	public final boolean getDisplayUnairedEpisodes()
	{
		return privateDisplayUnairedEpisodes;
	}
	public final void setDisplayUnairedEpisodes(boolean value)
	{
		privateDisplayUnairedEpisodes = value;
	}
	private boolean privateEnableRemoteControlOfOtherUsers;
	public final boolean getEnableRemoteControlOfOtherUsers()
	{
		return privateEnableRemoteControlOfOtherUsers;
	}
	public final void setEnableRemoteControlOfOtherUsers(boolean value)
	{
		privateEnableRemoteControlOfOtherUsers = value;
	}

	private boolean privateEnableLiveTvManagement;
	public final boolean getEnableLiveTvManagement()
	{
		return privateEnableLiveTvManagement;
	}
	public final void setEnableLiveTvManagement(boolean value)
	{
		privateEnableLiveTvManagement = value;
	}
	private boolean privateEnableLiveTvAccess;
	public final boolean getEnableLiveTvAccess()
	{
		return privateEnableLiveTvAccess;
	}
	public final void setEnableLiveTvAccess(boolean value)
	{
		privateEnableLiveTvAccess = value;
	}

	private boolean privateEnableMediaPlayback;
	public final boolean getEnableMediaPlayback()
	{
		return privateEnableMediaPlayback;
	}
	public final void setEnableMediaPlayback(boolean value)
	{
		privateEnableMediaPlayback = value;
	}
	private boolean privateEnableContentDeletion;
	public final boolean getEnableContentDeletion()
	{
		return privateEnableContentDeletion;
	}
	public final void setEnableContentDeletion(boolean value)
	{
		privateEnableContentDeletion = value;
	}

	private boolean privateGroupMoviesIntoBoxSets;
	public final boolean getGroupMoviesIntoBoxSets()
	{
		return privateGroupMoviesIntoBoxSets;
	}
	public final void setGroupMoviesIntoBoxSets(boolean value)
	{
		privateGroupMoviesIntoBoxSets = value;
	}

	private String[] privateBlockedMediaFolders;
	public final String[] getBlockedMediaFolders()
	{
		return privateBlockedMediaFolders;
	}
	public final void setBlockedMediaFolders(String[] value)
	{
		privateBlockedMediaFolders = value;
	}
	private String[] privateBlockedChannels;
	public final String[] getBlockedChannels()
	{
		return privateBlockedChannels;
	}
	public final void setBlockedChannels(String[] value)
	{
		privateBlockedChannels = value;
	}

	private UnratedItem[] privateBlockUnratedItems;
	public final UnratedItem[] getBlockUnratedItems()
	{
		return privateBlockUnratedItems;
	}
	public final void setBlockUnratedItems(UnratedItem[] value)
	{
		privateBlockUnratedItems = value;
	}

	private SubtitlePlaybackMode privateSubtitleMode = SubtitlePlaybackMode.values()[0];
	public final SubtitlePlaybackMode getSubtitleMode()
	{
		return privateSubtitleMode;
	}
	public final void setSubtitleMode(SubtitlePlaybackMode value)
	{
		privateSubtitleMode = value;
	}

	/** 
	 Initializes a new instance of the <see cref="UserConfiguration" /> class.
	*/
	public UserConfiguration()
	{
		setIsAdministrator(true);

		setPlayDefaultAudioTrack(true);
		setEnableRemoteControlOfOtherUsers(true);
		setEnableLiveTvManagement(true);
		setEnableMediaPlayback(true);
		setEnableLiveTvAccess(true);
		setGroupMoviesIntoBoxSets(true);

		setBlockedMediaFolders(new String[] { });
		setBlockedChannels(new String[] { });
		setBlockUnratedItems(new UnratedItem[] { });
	}
}