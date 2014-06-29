package MediaBrowser.Model.Configuration;

public class ChapterOptions
{
	private boolean privateEnableMovieChapterImageExtraction;
	public final boolean getEnableMovieChapterImageExtraction()
	{
		return privateEnableMovieChapterImageExtraction;
	}
	public final void setEnableMovieChapterImageExtraction(boolean value)
	{
		privateEnableMovieChapterImageExtraction = value;
	}
	private boolean privateEnableEpisodeChapterImageExtraction;
	public final boolean getEnableEpisodeChapterImageExtraction()
	{
		return privateEnableEpisodeChapterImageExtraction;
	}
	public final void setEnableEpisodeChapterImageExtraction(boolean value)
	{
		privateEnableEpisodeChapterImageExtraction = value;
	}
	private boolean privateEnableOtherVideoChapterImageExtraction;
	public final boolean getEnableOtherVideoChapterImageExtraction()
	{
		return privateEnableOtherVideoChapterImageExtraction;
	}
	public final void setEnableOtherVideoChapterImageExtraction(boolean value)
	{
		privateEnableOtherVideoChapterImageExtraction = value;
	}

	private boolean privateDownloadMovieChapters;
	public final boolean getDownloadMovieChapters()
	{
		return privateDownloadMovieChapters;
	}
	public final void setDownloadMovieChapters(boolean value)
	{
		privateDownloadMovieChapters = value;
	}
	private boolean privateDownloadEpisodeChapters;
	public final boolean getDownloadEpisodeChapters()
	{
		return privateDownloadEpisodeChapters;
	}
	public final void setDownloadEpisodeChapters(boolean value)
	{
		privateDownloadEpisodeChapters = value;
	}

	private String[] privateFetcherOrder;
	public final String[] getFetcherOrder()
	{
		return privateFetcherOrder;
	}
	public final void setFetcherOrder(String[] value)
	{
		privateFetcherOrder = value;
	}
	private String[] privateDisabledFetchers;
	public final String[] getDisabledFetchers()
	{
		return privateDisabledFetchers;
	}
	public final void setDisabledFetchers(String[] value)
	{
		privateDisabledFetchers = value;
	}

	public ChapterOptions()
	{
		setEnableMovieChapterImageExtraction(true);
		setEnableEpisodeChapterImageExtraction(false);
		setEnableOtherVideoChapterImageExtraction(false);

		setDownloadMovieChapters(true);

		setDisabledFetchers(new String[] { });
		setFetcherOrder(new String[] { });
	}
}