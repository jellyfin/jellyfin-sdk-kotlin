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

	private boolean ExtractDuringLibraryScan;
	public final boolean getExtractDuringLibraryScan()
	{
		return ExtractDuringLibraryScan;
	}
	public final void setExtractDuringLibraryScan(boolean value)
	{
		ExtractDuringLibraryScan = value;
	}
}