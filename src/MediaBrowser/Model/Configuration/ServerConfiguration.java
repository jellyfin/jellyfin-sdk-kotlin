package MediaBrowser.Model.Configuration;

import MediaBrowser.Model.Weather.*;

/** 
 Represents the server configuration.
*/
public class ServerConfiguration extends BaseApplicationConfiguration
{
	/** 
	 Gets or sets the zip code to use when displaying weather
	 
	 <value>The weather location.</value>
	*/
	private String privateWeatherLocation;
	public final String getWeatherLocation()
	{
		return privateWeatherLocation;
	}
	public final void setWeatherLocation(String value)
	{
		privateWeatherLocation = value;
	}

	/** 
	 Gets or sets the weather unit to use when displaying weather
	 
	 <value>The weather unit.</value>
	*/
	private WeatherUnits privateWeatherUnit = WeatherUnits.values()[0];
	public final WeatherUnits getWeatherUnit()
	{
		return privateWeatherUnit;
	}
	public final void setWeatherUnit(WeatherUnits value)
	{
		privateWeatherUnit = value;
	}

	/** 
	 Gets or sets a value indicating whether [enable HTTP level logging].
	 
	 <value><c>true</c> if [enable HTTP level logging]; otherwise, <c>false</c>.</value>
	*/
	private boolean privateEnableHttpLevelLogging;
	public final boolean getEnableHttpLevelLogging()
	{
		return privateEnableHttpLevelLogging;
	}
	public final void setEnableHttpLevelLogging(boolean value)
	{
		privateEnableHttpLevelLogging = value;
	}

	/** 
	 Gets or sets a value indicating whether [enable u pn p].
	 
	 <value><c>true</c> if [enable u pn p]; otherwise, <c>false</c>.</value>
	*/
	private boolean privateEnableUPnP;
	public final boolean getEnableUPnP()
	{
		return privateEnableUPnP;
	}
	public final void setEnableUPnP(boolean value)
	{
		privateEnableUPnP = value;
	}

	/** 
	 Gets or sets the HTTP server port number.
	 
	 <value>The HTTP server port number.</value>
	*/
	private int privateHttpServerPortNumber;
	public final int getHttpServerPortNumber()
	{
		return privateHttpServerPortNumber;
	}
	public final void setHttpServerPortNumber(int value)
	{
		privateHttpServerPortNumber = value;
	}

	/** 
	 Gets or sets the legacy web socket port number.
	 
	 <value>The legacy web socket port number.</value>
	*/
	private int privateLegacyWebSocketPortNumber;
	public final int getLegacyWebSocketPortNumber()
	{
		return privateLegacyWebSocketPortNumber;
	}
	public final void setLegacyWebSocketPortNumber(int value)
	{
		privateLegacyWebSocketPortNumber = value;
	}

	/** 
	 Gets or sets a value indicating whether [enable internet providers].
	 
	 <value><c>true</c> if [enable internet providers]; otherwise, <c>false</c>.</value>
	*/
	private boolean privateEnableInternetProviders;
	public final boolean getEnableInternetProviders()
	{
		return privateEnableInternetProviders;
	}
	public final void setEnableInternetProviders(boolean value)
	{
		privateEnableInternetProviders = value;
	}

	/** 
	 Gets or sets the item by name path.
	 
	 <value>The item by name path.</value>
	*/
	private String privateItemsByNamePath;
	public final String getItemsByNamePath()
	{
		return privateItemsByNamePath;
	}
	public final void setItemsByNamePath(String value)
	{
		privateItemsByNamePath = value;
	}

	/** 
	 Gets or sets the metadata path.
	 
	 <value>The metadata path.</value>
	*/
	private String privateMetadataPath;
	public final String getMetadataPath()
	{
		return privateMetadataPath;
	}
	public final void setMetadataPath(String value)
	{
		privateMetadataPath = value;
	}

	/** 
	 Gets or sets the display name of the season zero.
	 
	 <value>The display name of the season zero.</value>
	*/
	private String privateSeasonZeroDisplayName;
	public final String getSeasonZeroDisplayName()
	{
		return privateSeasonZeroDisplayName;
	}
	public final void setSeasonZeroDisplayName(String value)
	{
		privateSeasonZeroDisplayName = value;
	}

	/** 
	 Gets or sets the metadata refresh days.
	 
	 <value>The metadata refresh days.</value>
	*/
	private int privateMetadataRefreshDays;
	public final int getMetadataRefreshDays()
	{
		return privateMetadataRefreshDays;
	}
	public final void setMetadataRefreshDays(int value)
	{
		privateMetadataRefreshDays = value;
	}

	/** 
	 Gets or sets a value indicating whether [save local meta].
	 
	 <value><c>true</c> if [save local meta]; otherwise, <c>false</c>.</value>
	*/
	private boolean privateSaveLocalMeta;
	public final boolean getSaveLocalMeta()
	{
		return privateSaveLocalMeta;
	}
	public final void setSaveLocalMeta(boolean value)
	{
		privateSaveLocalMeta = value;
	}

	/** 
	 Gets or sets a value indicating whether [refresh item images].
	 
	 <value><c>true</c> if [refresh item images]; otherwise, <c>false</c>.</value>
	*/
	private boolean privateRefreshItemImages;
	public final boolean getRefreshItemImages()
	{
		return privateRefreshItemImages;
	}
	public final void setRefreshItemImages(boolean value)
	{
		privateRefreshItemImages = value;
	}

	/** 
	 Gets or sets the preferred metadata language.
	 
	 <value>The preferred metadata language.</value>
	*/
	private String privatePreferredMetadataLanguage;
	public final String getPreferredMetadataLanguage()
	{
		return privatePreferredMetadataLanguage;
	}
	public final void setPreferredMetadataLanguage(String value)
	{
		privatePreferredMetadataLanguage = value;
	}

	/** 
	 Gets or sets the metadata country code.
	 
	 <value>The metadata country code.</value>
	*/
	private String privateMetadataCountryCode;
	public final String getMetadataCountryCode()
	{
		return privateMetadataCountryCode;
	}
	public final void setMetadataCountryCode(String value)
	{
		privateMetadataCountryCode = value;
	}

	/** 
	 Characters to be replaced with a ' ' in strings to create a sort name
	 
	 <value>The sort replace characters.</value>
	*/
	private String[] privateSortReplaceCharacters;
	public final String[] getSortReplaceCharacters()
	{
		return privateSortReplaceCharacters;
	}
	public final void setSortReplaceCharacters(String[] value)
	{
		privateSortReplaceCharacters = value;
	}

	/** 
	 Characters to be removed from strings to create a sort name
	 
	 <value>The sort remove characters.</value>
	*/
	private String[] privateSortRemoveCharacters;
	public final String[] getSortRemoveCharacters()
	{
		return privateSortRemoveCharacters;
	}
	public final void setSortRemoveCharacters(String[] value)
	{
		privateSortRemoveCharacters = value;
	}

	/** 
	 Words to be removed from strings to create a sort name
	 
	 <value>The sort remove words.</value>
	*/
	private String[] privateSortRemoveWords;
	public final String[] getSortRemoveWords()
	{
		return privateSortRemoveWords;
	}
	public final void setSortRemoveWords(String[] value)
	{
		privateSortRemoveWords = value;
	}

	/** 
	 Show an output log window for debugging
	 
	 <value><c>true</c> if [show log window]; otherwise, <c>false</c>.</value>
	*/
	private boolean privateShowLogWindow;
	public final boolean getShowLogWindow()
	{
		return privateShowLogWindow;
	}
	public final void setShowLogWindow(boolean value)
	{
		privateShowLogWindow = value;
	}

	/** 
	 Gets or sets the minimum percentage of an item that must be played in order for playstate to be updated.
	 
	 <value>The min resume PCT.</value>
	*/
	private int privateMinResumePct;
	public final int getMinResumePct()
	{
		return privateMinResumePct;
	}
	public final void setMinResumePct(int value)
	{
		privateMinResumePct = value;
	}

	/** 
	 Gets or sets the maximum percentage of an item that can be played while still saving playstate. If this percentage is crossed playstate will be reset to the beginning and the item will be marked watched.
	 
	 <value>The max resume PCT.</value>
	*/
	private int privateMaxResumePct;
	public final int getMaxResumePct()
	{
		return privateMaxResumePct;
	}
	public final void setMaxResumePct(int value)
	{
		privateMaxResumePct = value;
	}

	/** 
	 Gets or sets the minimum duration that an item must have in order to be eligible for playstate updates..
	 
	 <value>The min resume duration seconds.</value>
	*/
	private int privateMinResumeDurationSeconds;
	public final int getMinResumeDurationSeconds()
	{
		return privateMinResumeDurationSeconds;
	}
	public final void setMinResumeDurationSeconds(int value)
	{
		privateMinResumeDurationSeconds = value;
	}

	/** 
	 The delay in seconds that we will wait after a file system change to try and discover what has been added/removed
	 Some delay is necessary with some items because their creation is not atomic.  It involves the creation of several
	 different directories and files.
	 
	 <value>The file watcher delay.</value>
	*/
	private int privateRealtimeMonitorDelay;
	public final int getRealtimeMonitorDelay()
	{
		return privateRealtimeMonitorDelay;
	}
	public final void setRealtimeMonitorDelay(int value)
	{
		privateRealtimeMonitorDelay = value;
	}

	/** 
	 Gets or sets a value indicating whether [enable dashboard response caching].
	 Allows potential contributors without visual studio to modify production dashboard code and test changes.
	 
	 <value><c>true</c> if [enable dashboard response caching]; otherwise, <c>false</c>.</value>
	*/
	private boolean privateEnableDashboardResponseCaching;
	public final boolean getEnableDashboardResponseCaching()
	{
		return privateEnableDashboardResponseCaching;
	}
	public final void setEnableDashboardResponseCaching(boolean value)
	{
		privateEnableDashboardResponseCaching = value;
	}

	/** 
	 Allows the dashboard to be served from a custom path.
	 
	 <value>The dashboard source path.</value>
	*/
	private String privateDashboardSourcePath;
	public final String getDashboardSourcePath()
	{
		return privateDashboardSourcePath;
	}
	public final void setDashboardSourcePath(String value)
	{
		privateDashboardSourcePath = value;
	}

	/** 
	 Gets or sets a value indicating whether [enable tv db updates].
	 
	 <value><c>true</c> if [enable tv db updates]; otherwise, <c>false</c>.</value>
	*/
	private boolean privateEnableTvDbUpdates;
	public final boolean getEnableTvDbUpdates()
	{
		return privateEnableTvDbUpdates;
	}
	public final void setEnableTvDbUpdates(boolean value)
	{
		privateEnableTvDbUpdates = value;
	}
	private boolean privateEnableTmdbUpdates;
	public final boolean getEnableTmdbUpdates()
	{
		return privateEnableTmdbUpdates;
	}
	public final void setEnableTmdbUpdates(boolean value)
	{
		privateEnableTmdbUpdates = value;
	}
	private boolean privateEnableFanArtUpdates;
	public final boolean getEnableFanArtUpdates()
	{
		return privateEnableFanArtUpdates;
	}
	public final void setEnableFanArtUpdates(boolean value)
	{
		privateEnableFanArtUpdates = value;
	}

	private boolean privateRequireMobileManualLogin;
	public final boolean getRequireMobileManualLogin()
	{
		return privateRequireMobileManualLogin;
	}
	public final void setRequireMobileManualLogin(boolean value)
	{
		privateRequireMobileManualLogin = value;
	}
	private boolean privateRequireNonMobileManualLogin;
	public final boolean getRequireNonMobileManualLogin()
	{
		return privateRequireNonMobileManualLogin;
	}
	public final void setRequireNonMobileManualLogin(boolean value)
	{
		privateRequireNonMobileManualLogin = value;
	}

	/** 
	 Gets or sets the image saving convention.
	 
	 <value>The image saving convention.</value>
	*/
	private ImageSavingConvention privateImageSavingConvention = getImageSavingConvention().values()[0];
	public final ImageSavingConvention getImageSavingConvention()
	{
		return privateImageSavingConvention;
	}
	public final void setImageSavingConvention(ImageSavingConvention value)
	{
		privateImageSavingConvention = value;
	}

	/** 
	 Gets or sets a value indicating whether [enable people prefix sub folders].
	 
	 <value><c>true</c> if [enable people prefix sub folders]; otherwise, <c>false</c>.</value>
	*/
	private boolean privateEnablePeoplePrefixSubFolders;
	public final boolean getEnablePeoplePrefixSubFolders()
	{
		return privateEnablePeoplePrefixSubFolders;
	}
	public final void setEnablePeoplePrefixSubFolders(boolean value)
	{
		privateEnablePeoplePrefixSubFolders = value;
	}

	/** 
	 Gets or sets the encoding quality.
	 
	 <value>The encoding quality.</value>
	*/
	private EncodingQuality privateMediaEncodingQuality = EncodingQuality.values()[0];
	public final EncodingQuality getMediaEncodingQuality()
	{
		return privateMediaEncodingQuality;
	}
	public final void setMediaEncodingQuality(EncodingQuality value)
	{
		privateMediaEncodingQuality = value;
	}

	private boolean privateAllowVideoUpscaling;
	public final boolean getAllowVideoUpscaling()
	{
		return privateAllowVideoUpscaling;
	}
	public final void setAllowVideoUpscaling(boolean value)
	{
		privateAllowVideoUpscaling = value;
	}

	private MetadataOptions[] privateMetadataOptions;
	public final MetadataOptions[] getMetadataOptions()
	{
		return privateMetadataOptions;
	}
	public final void setMetadataOptions(MetadataOptions[] value)
	{
		privateMetadataOptions = value;
	}

	private boolean privateEnableDebugEncodingLogging;
	public final boolean getEnableDebugEncodingLogging()
	{
		return privateEnableDebugEncodingLogging;
	}
	public final void setEnableDebugEncodingLogging(boolean value)
	{
		privateEnableDebugEncodingLogging = value;
	}
	private String privateTranscodingTempPath;
	public final String getTranscodingTempPath()
	{
		return privateTranscodingTempPath;
	}
	public final void setTranscodingTempPath(String value)
	{
		privateTranscodingTempPath = value;
	}

	private boolean privateEnableAutomaticRestart;
	public final boolean getEnableAutomaticRestart()
	{
		return privateEnableAutomaticRestart;
	}
	public final void setEnableAutomaticRestart(boolean value)
	{
		privateEnableAutomaticRestart = value;
	}

	private TvFileOrganizationOptions privateTvFileOrganizationOptions;
	public final TvFileOrganizationOptions getTvFileOrganizationOptions()
	{
		return privateTvFileOrganizationOptions;
	}
	public final void setTvFileOrganizationOptions(TvFileOrganizationOptions value)
	{
		privateTvFileOrganizationOptions = value;
	}
	private LiveTvOptions privateLiveTvOptions;
	public final LiveTvOptions getLiveTvOptions()
	{
		return privateLiveTvOptions;
	}
	public final void setLiveTvOptions(LiveTvOptions value)
	{
		privateLiveTvOptions = value;
	}

	private boolean privateEnableRealtimeMonitor;
	public final boolean getEnableRealtimeMonitor()
	{
		return privateEnableRealtimeMonitor;
	}
	public final void setEnableRealtimeMonitor(boolean value)
	{
		privateEnableRealtimeMonitor = value;
	}
	private PathSubstitution[] privatePathSubstitutions;
	public final PathSubstitution[] getPathSubstitutions()
	{
		return privatePathSubstitutions;
	}
	public final void setPathSubstitutions(PathSubstitution[] value)
	{
		privatePathSubstitutions = value;
	}

	private String privateServerName;
	public final String getServerName()
	{
		return privateServerName;
	}
	public final void setServerName(String value)
	{
		privateServerName = value;
	}
	private String privateWanDdns;
	public final String getWanDdns()
	{
		return privateWanDdns;
	}
	public final void setWanDdns(String value)
	{
		privateWanDdns = value;
	}

	private String privateUICulture;
	public final String getUICulture()
	{
		return privateUICulture;
	}
	public final void setUICulture(String value)
	{
		privateUICulture = value;
	}

	private DlnaOptions privateDlnaOptions;
	public final DlnaOptions getDlnaOptions()
	{
		return privateDlnaOptions;
	}
	public final void setDlnaOptions(DlnaOptions value)
	{
		privateDlnaOptions = value;
	}

	private double privateDownMixAudioBoost;
	public final double getDownMixAudioBoost()
	{
		return privateDownMixAudioBoost;
	}
	public final void setDownMixAudioBoost(double value)
	{
		privateDownMixAudioBoost = value;
	}

	private NotificationOptions privateNotificationOptions;
	public final NotificationOptions getNotificationOptions()
	{
		return privateNotificationOptions;
	}
	public final void setNotificationOptions(NotificationOptions value)
	{
		privateNotificationOptions = value;
	}

	private SubtitleOptions privateSubtitleOptions;
	public final SubtitleOptions getSubtitleOptions()
	{
		return privateSubtitleOptions;
	}
	public final void setSubtitleOptions(SubtitleOptions value)
	{
		privateSubtitleOptions = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [Obsolete] public string[] ManualLoginClients {get;set;}
	private String[] privateManualLoginClients;
	@Deprecated
	public final String[] getManualLoginClients()
	{
		return privateManualLoginClients;
	}
	@Deprecated
	public final void setManualLoginClients(String[] value)
	{
		privateManualLoginClients = value;
	}

	private ChannelOptions privateChannelOptions;
	public final ChannelOptions getChannelOptions()
	{
		return privateChannelOptions;
	}
	public final void setChannelOptions(ChannelOptions value)
	{
		privateChannelOptions = value;
	}
	private ChapterOptions privateChapterOptions;
	public final ChapterOptions getChapterOptions()
	{
		return privateChapterOptions;
	}
	public final void setChapterOptions(ChapterOptions value)
	{
		privateChapterOptions = value;
	}

	/** 
	 Initializes a new instance of the <see cref="ServerConfiguration" /> class.
	*/
	public ServerConfiguration()
	{
		super();
		setMediaEncodingQuality(EncodingQuality.Auto);
		setImageSavingConvention(ImageSavingConvention.Compatible);
		setHttpServerPortNumber(8096);
		setLegacyWebSocketPortNumber(8945);
		setEnableHttpLevelLogging(true);
		setEnableDashboardResponseCaching(true);

		setEnableAutomaticRestart(true);
		setEnablePeoplePrefixSubFolders(true);

		setEnableUPnP(true);
		setDownMixAudioBoost(2);

		setMinResumePct(5);
		setMaxResumePct(90);

		// 5 minutes
		setMinResumeDurationSeconds(300);

		setRealtimeMonitorDelay(30);

		setEnableInternetProviders(true); //initial installs will need these

		setPathSubstitutions(new PathSubstitution[] { });

		setMetadataRefreshDays(30);
		setPreferredMetadataLanguage("en");
		setMetadataCountryCode("US");

		setSortReplaceCharacters(new String[] {".", "+", "%"});
		setSortRemoveCharacters(new String[] {",", "&", "-", "{", "}", "'"});
		setSortRemoveWords(new String[] {"the", "a", "an"});

		setManualLoginClients(new String[] { });

		setSeasonZeroDisplayName("Specials");

		setLiveTvOptions(new LiveTvOptions());

		setTvFileOrganizationOptions(new TvFileOrganizationOptions());

		setEnableRealtimeMonitor(true);

		MetadataOptions tempVar = new MetadataOptions(1, 1280);
		tempVar.setItemType("Book");
		MetadataOptions tempVar2 = new MetadataOptions(1, 1280);
		tempVar2.setItemType("MusicAlbum");
		MetadataOptions tempVar3 = new MetadataOptions(1, 1280);
		tempVar3.setItemType("MusicArtist");
		MetadataOptions tempVar4 = new MetadataOptions(0, 1280);
		tempVar4.setItemType("Season");
		java.util.ArrayList<MetadataOptions> options = new java.util.ArrayList<MetadataOptions>(java.util.Arrays.asList(new MetadataOptions[] {tempVar, tempVar2, tempVar3, tempVar4}));

		setMetadataOptions(options.toArray(new MetadataOptions[0]));

		setDlnaOptions(new DlnaOptions());

		setUICulture("en-us");

		setNotificationOptions(new NotificationOptions());

		setSubtitleOptions(new SubtitleOptions());

		setChannelOptions(new ChannelOptions());
		setChapterOptions(new ChapterOptions());
	}
}