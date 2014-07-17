package MediaBrowser.Model.Configuration;

public class SubtitleOptions
{
	private boolean privateSkipIfGraphicalSubtitlesPresent;
	public final boolean getSkipIfGraphicalSubtitlesPresent()
	{
		return privateSkipIfGraphicalSubtitlesPresent;
	}
	public final void setSkipIfGraphicalSubtitlesPresent(boolean value)
	{
		privateSkipIfGraphicalSubtitlesPresent = value;
	}
	private boolean privateSkipIfAudioTrackMatches;
	public final boolean getSkipIfAudioTrackMatches()
	{
		return privateSkipIfAudioTrackMatches;
	}
	public final void setSkipIfAudioTrackMatches(boolean value)
	{
		privateSkipIfAudioTrackMatches = value;
	}
	private String[] privateDownloadLanguages;
	public final String[] getDownloadLanguages()
	{
		return privateDownloadLanguages;
	}
	public final void setDownloadLanguages(String[] value)
	{
		privateDownloadLanguages = value;
	}
	private boolean privateDownloadMovieSubtitles;
	public final boolean getDownloadMovieSubtitles()
	{
		return privateDownloadMovieSubtitles;
	}
	public final void setDownloadMovieSubtitles(boolean value)
	{
		privateDownloadMovieSubtitles = value;
	}
	private boolean privateDownloadEpisodeSubtitles;
	public final boolean getDownloadEpisodeSubtitles()
	{
		return privateDownloadEpisodeSubtitles;
	}
	public final void setDownloadEpisodeSubtitles(boolean value)
	{
		privateDownloadEpisodeSubtitles = value;
	}

	private String privateOpenSubtitlesUsername;
	public final String getOpenSubtitlesUsername()
	{
		return privateOpenSubtitlesUsername;
	}
	public final void setOpenSubtitlesUsername(String value)
	{
		privateOpenSubtitlesUsername = value;
	}
	private String privateOpenSubtitlesPasswordHash;
	public final String getOpenSubtitlesPasswordHash()
	{
		return privateOpenSubtitlesPasswordHash;
	}
	public final void setOpenSubtitlesPasswordHash(String value)
	{
		privateOpenSubtitlesPasswordHash = value;
	}
	private boolean privateIsOpenSubtitleVipAccount;
	public final boolean getIsOpenSubtitleVipAccount()
	{
		return privateIsOpenSubtitleVipAccount;
	}
	public final void setIsOpenSubtitleVipAccount(boolean value)
	{
		privateIsOpenSubtitleVipAccount = value;
	}

	public SubtitleOptions()
	{
		setDownloadLanguages(new String[] { });

		setSkipIfAudioTrackMatches(true);
	}
}