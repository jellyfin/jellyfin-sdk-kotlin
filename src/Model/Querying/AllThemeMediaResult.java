package MediaBrowser.Model.Querying;

public class AllThemeMediaResult
{
	private ThemeMediaResult privateThemeVideosResult;
	public final ThemeMediaResult getThemeVideosResult()
	{
		return privateThemeVideosResult;
	}
	public final void setThemeVideosResult(ThemeMediaResult value)
	{
		privateThemeVideosResult = value;
	}

	private ThemeMediaResult privateThemeSongsResult;
	public final ThemeMediaResult getThemeSongsResult()
	{
		return privateThemeSongsResult;
	}
	public final void setThemeSongsResult(ThemeMediaResult value)
	{
		privateThemeSongsResult = value;
	}

	private ThemeMediaResult privateSoundtrackSongsResult;
	public final ThemeMediaResult getSoundtrackSongsResult()
	{
		return privateSoundtrackSongsResult;
	}
	public final void setSoundtrackSongsResult(ThemeMediaResult value)
	{
		privateSoundtrackSongsResult = value;
	}

	public AllThemeMediaResult()
	{
		setThemeVideosResult(new ThemeMediaResult());

		setThemeSongsResult(new ThemeMediaResult());

		setSoundtrackSongsResult(new ThemeMediaResult());
	}
}