package MediaBrowser.Model.Configuration;

import MediaBrowser.Model.Entities.*;
import MediaBrowser.Model.FileOrganization.*;
import MediaBrowser.Model.LiveTv.*;

/** 
 Represents the server configuration.
*/
public class ServerConfiguration extends BaseApplicationConfiguration
{
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

	private boolean privateDefaultMetadataSettingsApplied;
	public final boolean getDefaultMetadataSettingsApplied()
	{
		return privateDefaultMetadataSettingsApplied;
	}
	public final void setDefaultMetadataSettingsApplied(boolean value)
	{
		privateDefaultMetadataSettingsApplied = value;
	}

	private PeopleMetadataOptions privatePeopleMetadataOptions;
	public final PeopleMetadataOptions getPeopleMetadataOptions()
	{
		return privatePeopleMetadataOptions;
	}
	public final void setPeopleMetadataOptions(PeopleMetadataOptions value)
	{
		privatePeopleMetadataOptions = value;
	}

	private String[] privateSecureApps1;
	public final String[] getSecureApps1()
	{
		return privateSecureApps1;
	}
	public final void setSecureApps1(String[] value)
	{
		privateSecureApps1 = value;
	}

	private boolean privateSaveMetadataHidden;
	public final boolean getSaveMetadataHidden()
	{
		return privateSaveMetadataHidden;
	}
	public final void setSaveMetadataHidden(boolean value)
	{
		privateSaveMetadataHidden = value;
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

		setEnableInternetProviders(true);

		setPathSubstitutions(new PathSubstitution[] { });

		setPreferredMetadataLanguage("en");
		setMetadataCountryCode("US");

		setSortReplaceCharacters(new String[] {".", "+", "%"});
		setSortRemoveCharacters(new String[] {",", "&", "-", "{", "}", "'"});
		setSortRemoveWords(new String[] {"the", "a", "an"});

		setSeasonZeroDisplayName("Specials");

		setEnableRealtimeMonitor(true);

		setUICulture("en-us");

		setPeopleMetadataOptions(new PeopleMetadataOptions());

		setSecureApps1(new String[] {"Dashboard", "MBKinect", "NuVue", "Media Browser Theater"});

		MetadataOptions tempVar = new MetadataOptions(1, 1280);
		tempVar.setItemType("Book");
		MetadataOptions tempVar2 = new MetadataOptions(1, 1280);
		tempVar2.setItemType("MusicAlbum");

		MetadataOptions tempVar4 = new MetadataOptions(0, 1280);
		tempVar4.setItemType("Season");
	}
}