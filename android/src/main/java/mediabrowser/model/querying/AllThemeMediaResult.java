package mediabrowser.model.querying;

public class AllThemeMediaResult
{
	private ThemeMediaResult ThemeVideosResult;
	public final ThemeMediaResult getThemeVideosResult()
	{
		return ThemeVideosResult;
	}
	public final void setThemeVideosResult(ThemeMediaResult value)
	{
		ThemeVideosResult = value;
	}

	private ThemeMediaResult ThemeSongsResult;
	public final ThemeMediaResult getThemeSongsResult()
	{
		return ThemeSongsResult;
	}
	public final void setThemeSongsResult(ThemeMediaResult value)
	{
		ThemeSongsResult = value;
	}

	private ThemeMediaResult SoundtrackSongsResult;
	public final ThemeMediaResult getSoundtrackSongsResult()
	{
		return SoundtrackSongsResult;
	}
	public final void setSoundtrackSongsResult(ThemeMediaResult value)
	{
		SoundtrackSongsResult = value;
	}

	public AllThemeMediaResult()
	{
		setThemeVideosResult(new ThemeMediaResult());

		setThemeSongsResult(new ThemeMediaResult());

		setSoundtrackSongsResult(new ThemeMediaResult());
	}
}