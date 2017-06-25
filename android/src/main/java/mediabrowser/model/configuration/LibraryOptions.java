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

	private boolean SaveLocalMetadata;
	public final boolean getSaveLocalMetadata()
	{
		return SaveLocalMetadata;
	}
	public final void setSaveLocalMetadata(boolean value)
	{
		SaveLocalMetadata = value;
	}
	private boolean EnableInternetProviders;
	public final boolean getEnableInternetProviders()
	{
		return EnableInternetProviders;
	}
	public final void setEnableInternetProviders(boolean value)
	{
		EnableInternetProviders = value;
	}
	private boolean ImportMissingEpisodes;
	public final boolean getImportMissingEpisodes()
	{
		return ImportMissingEpisodes;
	}
	public final void setImportMissingEpisodes(boolean value)
	{
		ImportMissingEpisodes = value;
	}
	private boolean EnableAutomaticSeriesGrouping;
	public final boolean getEnableAutomaticSeriesGrouping()
	{
		return EnableAutomaticSeriesGrouping;
	}
	public final void setEnableAutomaticSeriesGrouping(boolean value)
	{
		EnableAutomaticSeriesGrouping = value;
	}
	private boolean EnableEmbeddedTitles;
	public final boolean getEnableEmbeddedTitles()
	{
		return EnableEmbeddedTitles;
	}
	public final void setEnableEmbeddedTitles(boolean value)
	{
		EnableEmbeddedTitles = value;
	}

	private int AutomaticRefreshIntervalDays;
	public final int getAutomaticRefreshIntervalDays()
	{
		return AutomaticRefreshIntervalDays;
	}
	public final void setAutomaticRefreshIntervalDays(int value)
	{
		AutomaticRefreshIntervalDays = value;
	}

	/** 
	 Gets or sets the preferred metadata language.
	 
	 <value>The preferred metadata language.</value>
	*/
	private String PreferredMetadataLanguage;
	public final String getPreferredMetadataLanguage()
	{
		return PreferredMetadataLanguage;
	}
	public final void setPreferredMetadataLanguage(String value)
	{
		PreferredMetadataLanguage = value;
	}

	/** 
	 Gets or sets the metadata country code.
	 
	 <value>The metadata country code.</value>
	*/
	private String MetadataCountryCode;
	public final String getMetadataCountryCode()
	{
		return MetadataCountryCode;
	}
	public final void setMetadataCountryCode(String value)
	{
		MetadataCountryCode = value;
	}

	public LibraryOptions()
	{
		setEnablePhotos(true);
		setEnableRealtimeMonitor(true);
		setPathInfos(new MediaPathInfo[] { });
		setEnableInternetProviders(true);
		setEnableAutomaticSeriesGrouping(true);
	}
}