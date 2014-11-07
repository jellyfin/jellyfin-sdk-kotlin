package mediabrowser.model.configuration;

public class ChapterOptions
{
	private boolean EnableMovieChapterImageExtraction;
	public final boolean getEnableMovieChapterImageExtraction()
	{
		return EnableMovieChapterImageExtraction;
	}
	public final void setEnableMovieChapterImageExtraction(boolean value)
	{
		EnableMovieChapterImageExtraction = value;
	}
	private boolean EnableEpisodeChapterImageExtraction;
	public final boolean getEnableEpisodeChapterImageExtraction()
	{
		return EnableEpisodeChapterImageExtraction;
	}
	public final void setEnableEpisodeChapterImageExtraction(boolean value)
	{
		EnableEpisodeChapterImageExtraction = value;
	}
	private boolean EnableOtherVideoChapterImageExtraction;
	public final boolean getEnableOtherVideoChapterImageExtraction()
	{
		return EnableOtherVideoChapterImageExtraction;
	}
	public final void setEnableOtherVideoChapterImageExtraction(boolean value)
	{
		EnableOtherVideoChapterImageExtraction = value;
	}

	private boolean DownloadMovieChapters;
	public final boolean getDownloadMovieChapters()
	{
		return DownloadMovieChapters;
	}
	public final void setDownloadMovieChapters(boolean value)
	{
		DownloadMovieChapters = value;
	}
	private boolean DownloadEpisodeChapters;
	public final boolean getDownloadEpisodeChapters()
	{
		return DownloadEpisodeChapters;
	}
	public final void setDownloadEpisodeChapters(boolean value)
	{
		DownloadEpisodeChapters = value;
	}

	private String[] FetcherOrder;
	public final String[] getFetcherOrder()
	{
		return FetcherOrder;
	}
	public final void setFetcherOrder(String[] value)
	{
		FetcherOrder = value;
	}
	private String[] DisabledFetchers;
	public final String[] getDisabledFetchers()
	{
		return DisabledFetchers;
	}
	public final void setDisabledFetchers(String[] value)
	{
		DisabledFetchers = value;
	}

	private boolean ExtractDuringLibraryScan;
	public final boolean getExtractDuringLibraryScan()
	{
		return ExtractDuringLibraryScan;
	}
	public final void setExtractDuringLibraryScan(boolean value)
	{
		ExtractDuringLibraryScan = value;
	}

	public ChapterOptions()
	{
		setDownloadMovieChapters(true);
		setExtractDuringLibraryScan(true);

		setDisabledFetchers(new String[] { });
		setFetcherOrder(new String[] { });
	}
}