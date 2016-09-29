package mediabrowser.model.configuration;

public class LibraryOptions
{
	private boolean EnableArchiveMediaFiles;
	public final boolean getEnableArchiveMediaFiles()
	{
		return EnableArchiveMediaFiles;
	}
	public final void setEnableArchiveMediaFiles(boolean value)
	{
		EnableArchiveMediaFiles = value;
	}
	private boolean EnablePhotos;
	public final boolean getEnablePhotos()
	{
		return EnablePhotos;
	}
	public final void setEnablePhotos(boolean value)
	{
		EnablePhotos = value;
	}
	private boolean EnableRealtimeMonitor;
	public final boolean getEnableRealtimeMonitor()
	{
		return EnableRealtimeMonitor;
	}
	public final void setEnableRealtimeMonitor(boolean value)
	{
		EnableRealtimeMonitor = value;
	}
	private int SchemaVersion;
	public final int getSchemaVersion()
	{
		return SchemaVersion;
	}
	public final void setSchemaVersion(int value)
	{
		SchemaVersion = value;
	}
	private boolean EnableChapterImageExtraction;
	public final boolean getEnableChapterImageExtraction()
	{
		return EnableChapterImageExtraction;
	}
	public final void setEnableChapterImageExtraction(boolean value)
	{
		EnableChapterImageExtraction = value;
	}
	private boolean ExtractChapterImagesDuringLibraryScan;
	public final boolean getExtractChapterImagesDuringLibraryScan()
	{
		return ExtractChapterImagesDuringLibraryScan;
	}
	public final void setExtractChapterImagesDuringLibraryScan(boolean value)
	{
		ExtractChapterImagesDuringLibraryScan = value;
	}
	private boolean DownloadImagesInAdvance;
	public final boolean getDownloadImagesInAdvance()
	{
		return DownloadImagesInAdvance;
	}
	public final void setDownloadImagesInAdvance(boolean value)
	{
		DownloadImagesInAdvance = value;
	}
	private MediaPathInfo[] PathInfos;
	public final MediaPathInfo[] getPathInfos()
	{
		return PathInfos;
	}
	public final void setPathInfos(MediaPathInfo[] value)
	{
		PathInfos = value;
	}

	public LibraryOptions()
	{
		setEnablePhotos(true);
		setEnableRealtimeMonitor(true);
		setPathInfos(new MediaPathInfo[] { });
	}
}