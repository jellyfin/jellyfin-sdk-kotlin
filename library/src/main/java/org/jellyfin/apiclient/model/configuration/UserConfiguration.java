package org.jellyfin.apiclient.model.configuration;

/** 
 Class UserConfiguration
*/
public class UserConfiguration
{
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

	private String[] GroupedFolders;
	public final String[] getGroupedFolders()
	{
		return GroupedFolders;
	}
	public final void setGroupedFolders(String[] value)
	{
		GroupedFolders = value;
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

	private String[] LatestItemsExcludes;
	public final String[] getLatestItemsExcludes()
	{
		return LatestItemsExcludes;
	}
	public final void setLatestItemsExcludes(String[] value)
	{
		LatestItemsExcludes = value;
	}

	private boolean HidePlayedInLatest;
	public final boolean getHidePlayedInLatest()
	{
		return HidePlayedInLatest;
	}
	public final void setHidePlayedInLatest(boolean value)
	{
		HidePlayedInLatest = value;
	}

	private boolean RememberAudioSelections;
	public final boolean getRememberAudioSelections()
	{
		return RememberAudioSelections;
	}
	public final void setRememberAudioSelections(boolean value)
	{
		RememberAudioSelections = value;
	}
	private boolean RememberSubtitleSelections;
	public final boolean getRememberSubtitleSelections()
	{
		return RememberSubtitleSelections;
	}
	public final void setRememberSubtitleSelections(boolean value)
	{
		RememberSubtitleSelections = value;
	}
	private boolean EnableNextEpisodeAutoPlay;
	public final boolean getEnableNextEpisodeAutoPlay()
	{
		return EnableNextEpisodeAutoPlay;
	}
	public final void setEnableNextEpisodeAutoPlay(boolean value)
	{
		EnableNextEpisodeAutoPlay = value;
	}

	/** 
	 Initializes a new instance of the <see cref="UserConfiguration" /> class.
	*/
	public UserConfiguration()
	{
		setEnableNextEpisodeAutoPlay(true);
		setRememberAudioSelections(true);
		setRememberSubtitleSelections(true);

		setHidePlayedInLatest(true);
		setPlayDefaultAudioTrack(true);
		setDisplayMissingEpisodes(true);

		setLatestItemsExcludes(new String[] { });
		setOrderedViews(new String[] { });

		setGroupedFolders(new String[] { });
	}
}