package mediabrowser.model.providers;

public class SubtitleOptions
{
	private boolean SkipIfGraphicalSubtitlesPresent;
	public final boolean getSkipIfGraphicalSubtitlesPresent()
	{
		return SkipIfGraphicalSubtitlesPresent;
	}
	public final void setSkipIfGraphicalSubtitlesPresent(boolean value)
	{
		SkipIfGraphicalSubtitlesPresent = value;
	}
	private boolean SkipIfAudioTrackMatches;
	public final boolean getSkipIfAudioTrackMatches()
	{
		return SkipIfAudioTrackMatches;
	}
	public final void setSkipIfAudioTrackMatches(boolean value)
	{
		SkipIfAudioTrackMatches = value;
	}
	private String[] DownloadLanguages;
	public final String[] getDownloadLanguages()
	{
		return DownloadLanguages;
	}
	public final void setDownloadLanguages(String[] value)
	{
		DownloadLanguages = value;
	}
	private boolean DownloadMovieSubtitles;
	public final boolean getDownloadMovieSubtitles()
	{
		return DownloadMovieSubtitles;
	}
	public final void setDownloadMovieSubtitles(boolean value)
	{
		DownloadMovieSubtitles = value;
	}
	private boolean DownloadEpisodeSubtitles;
	public final boolean getDownloadEpisodeSubtitles()
	{
		return DownloadEpisodeSubtitles;
	}
	public final void setDownloadEpisodeSubtitles(boolean value)
	{
		DownloadEpisodeSubtitles = value;
	}

	private String OpenSubtitlesUsername;
	public final String getOpenSubtitlesUsername()
	{
		return OpenSubtitlesUsername;
	}
	public final void setOpenSubtitlesUsername(String value)
	{
		OpenSubtitlesUsername = value;
	}
	private String OpenSubtitlesPasswordHash;
	public final String getOpenSubtitlesPasswordHash()
	{
		return OpenSubtitlesPasswordHash;
	}
	public final void setOpenSubtitlesPasswordHash(String value)
	{
		OpenSubtitlesPasswordHash = value;
	}
	private boolean IsOpenSubtitleVipAccount;
	public final boolean getIsOpenSubtitleVipAccount()
	{
		return IsOpenSubtitleVipAccount;
	}
	public final void setIsOpenSubtitleVipAccount(boolean value)
	{
		IsOpenSubtitleVipAccount = value;
	}

	public SubtitleOptions()
	{
		setDownloadLanguages(new String[] { });

		setSkipIfAudioTrackMatches(true);
	}
}