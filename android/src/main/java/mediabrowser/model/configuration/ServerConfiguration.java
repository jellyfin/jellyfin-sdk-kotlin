package mediabrowser.model.configuration;

import mediabrowser.model.dto.*;
import mediabrowser.model.entities.*;

/** 
 Represents the server configuration.
*/
public class ServerConfiguration extends BaseApplicationConfiguration
{
	public static final int DefaultHttpPort = 8096;
	public static final int DefaultHttpsPort = 8920;

	/** 
	 Gets or sets a value indicating whether [enable u pn p].
	 
	 <value><c>true</c> if [enable u pn p]; otherwise, <c>false</c>.</value>
	*/
	private boolean EnableUPnP;
	public final boolean getEnableUPnP()
	{
		return EnableUPnP;
	}
	public final void setEnableUPnP(boolean value)
	{
		EnableUPnP = value;
	}

	/** 
	 Gets or sets the public mapped port.
	 
	 <value>The public mapped port.</value>
	*/
	private int PublicPort;
	public final int getPublicPort()
	{
		return PublicPort;
	}
	public final void setPublicPort(int value)
	{
		PublicPort = value;
	}

	/** 
	 Gets or sets the public HTTPS port.
	 
	 <value>The public HTTPS port.</value>
	*/
	private int PublicHttpsPort;
	public final int getPublicHttpsPort()
	{
		return PublicHttpsPort;
	}
	public final void setPublicHttpsPort(int value)
	{
		PublicHttpsPort = value;
	}

	/** 
	 Gets or sets the HTTP server port number.
	 
	 <value>The HTTP server port number.</value>
	*/
	private int HttpServerPortNumber;
	public final int getHttpServerPortNumber()
	{
		return HttpServerPortNumber;
	}
	public final void setHttpServerPortNumber(int value)
	{
		HttpServerPortNumber = value;
	}

	/** 
	 Gets or sets the HTTPS server port number.
	 
	 <value>The HTTPS server port number.</value>
	*/
	private int HttpsPortNumber;
	public final int getHttpsPortNumber()
	{
		return HttpsPortNumber;
	}
	public final void setHttpsPortNumber(int value)
	{
		HttpsPortNumber = value;
	}

	/** 
	 Gets or sets a value indicating whether [use HTTPS].
	 
	 <value><c>true</c> if [use HTTPS]; otherwise, <c>false</c>.</value>
	*/
	private boolean EnableHttps;
	public final boolean getEnableHttps()
	{
		return EnableHttps;
	}
	public final void setEnableHttps(boolean value)
	{
		EnableHttps = value;
	}
	private boolean EnableSeriesPresentationUniqueKey;
	public final boolean getEnableSeriesPresentationUniqueKey()
	{
		return EnableSeriesPresentationUniqueKey;
	}
	public final void setEnableSeriesPresentationUniqueKey(boolean value)
	{
		EnableSeriesPresentationUniqueKey = value;
	}
	private boolean EnableLocalizedGuids;
	public final boolean getEnableLocalizedGuids()
	{
		return EnableLocalizedGuids;
	}
	public final void setEnableLocalizedGuids(boolean value)
	{
		EnableLocalizedGuids = value;
	}
	private boolean EnableNormalizedItemByNameIds;
	public final boolean getEnableNormalizedItemByNameIds()
	{
		return EnableNormalizedItemByNameIds;
	}
	public final void setEnableNormalizedItemByNameIds(boolean value)
	{
		EnableNormalizedItemByNameIds = value;
	}

	/** 
	 Gets or sets the value pointing to the file system where the ssl certiifcate is located..
	 
	 <value>The value pointing to the file system where the ssl certiifcate is located..</value>
	*/
	private String CertificatePath;
	public final String getCertificatePath()
	{
		return CertificatePath;
	}
	public final void setCertificatePath(String value)
	{
		CertificatePath = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is port authorized.
	 
	 <value><c>true</c> if this instance is port authorized; otherwise, <c>false</c>.</value>
	*/
	private boolean IsPortAuthorized;
	public final boolean getIsPortAuthorized()
	{
		return IsPortAuthorized;
	}
	public final void setIsPortAuthorized(boolean value)
	{
		IsPortAuthorized = value;
	}

	/** 
	 Gets or sets a value indicating whether [enable case sensitive item ids].
	 
	 <value><c>true</c> if [enable case sensitive item ids]; otherwise, <c>false</c>.</value>
	*/
	private boolean EnableCaseSensitiveItemIds;
	public final boolean getEnableCaseSensitiveItemIds()
	{
		return EnableCaseSensitiveItemIds;
	}
	public final void setEnableCaseSensitiveItemIds(boolean value)
	{
		EnableCaseSensitiveItemIds = value;
	}

	/** 
	 Gets or sets the metadata path.
	 
	 <value>The metadata path.</value>
	*/
	private String MetadataPath;
	public final String getMetadataPath()
	{
		return MetadataPath;
	}
	public final void setMetadataPath(String value)
	{
		MetadataPath = value;
	}
	private String MetadataNetworkPath;
	public final String getMetadataNetworkPath()
	{
		return MetadataNetworkPath;
	}
	public final void setMetadataNetworkPath(String value)
	{
		MetadataNetworkPath = value;
	}

	private String LastVersion;
	public final String getLastVersion()
	{
		return LastVersion;
	}
	public final void setLastVersion(String value)
	{
		LastVersion = value;
	}

	/** 
	 Gets or sets the display name of the season zero.
	 
	 <value>The display name of the season zero.</value>
	*/
	private String SeasonZeroDisplayName;
	public final String getSeasonZeroDisplayName()
	{
		return SeasonZeroDisplayName;
	}
	public final void setSeasonZeroDisplayName(String value)
	{
		SeasonZeroDisplayName = value;
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

	/** 
	 Characters to be replaced with a ' ' in strings to create a sort name
	 
	 <value>The sort replace characters.</value>
	*/
	private String[] SortReplaceCharacters;
	public final String[] getSortReplaceCharacters()
	{
		return SortReplaceCharacters;
	}
	public final void setSortReplaceCharacters(String[] value)
	{
		SortReplaceCharacters = value;
	}

	/** 
	 Characters to be removed from strings to create a sort name
	 
	 <value>The sort remove characters.</value>
	*/
	private String[] SortRemoveCharacters;
	public final String[] getSortRemoveCharacters()
	{
		return SortRemoveCharacters;
	}
	public final void setSortRemoveCharacters(String[] value)
	{
		SortRemoveCharacters = value;
	}

	/** 
	 Words to be removed from strings to create a sort name
	 
	 <value>The sort remove words.</value>
	*/
	private String[] SortRemoveWords;
	public final String[] getSortRemoveWords()
	{
		return SortRemoveWords;
	}
	public final void setSortRemoveWords(String[] value)
	{
		SortRemoveWords = value;
	}

	/** 
	 Gets or sets the minimum percentage of an item that must be played in order for playstate to be updated.
	 
	 <value>The min resume PCT.</value>
	*/
	private int MinResumePct;
	public final int getMinResumePct()
	{
		return MinResumePct;
	}
	public final void setMinResumePct(int value)
	{
		MinResumePct = value;
	}

	/** 
	 Gets or sets the maximum percentage of an item that can be played while still saving playstate. If this percentage is crossed playstate will be reset to the beginning and the item will be marked watched.
	 
	 <value>The max resume PCT.</value>
	*/
	private int MaxResumePct;
	public final int getMaxResumePct()
	{
		return MaxResumePct;
	}
	public final void setMaxResumePct(int value)
	{
		MaxResumePct = value;
	}

	/** 
	 Gets or sets the minimum duration that an item must have in order to be eligible for playstate updates..
	 
	 <value>The min resume duration seconds.</value>
	*/
	private int MinResumeDurationSeconds;
	public final int getMinResumeDurationSeconds()
	{
		return MinResumeDurationSeconds;
	}
	public final void setMinResumeDurationSeconds(int value)
	{
		MinResumeDurationSeconds = value;
	}

	/** 
	 The delay in seconds that we will wait after a file system change to try and discover what has been added/removed
	 Some delay is necessary with some items because their creation is not atomic.  It involves the creation of several
	 different directories and files.
	 
	 <value>The file watcher delay.</value>
	*/
	private int LibraryMonitorDelay;
	public final int getLibraryMonitorDelay()
	{
		return LibraryMonitorDelay;
	}
	public final void setLibraryMonitorDelay(int value)
	{
		LibraryMonitorDelay = value;
	}

	/** 
	 Gets or sets a value indicating whether [enable dashboard response caching].
	 Allows potential contributors without visual studio to modify production dashboard code and test changes.
	 
	 <value><c>true</c> if [enable dashboard response caching]; otherwise, <c>false</c>.</value>
	*/
	private boolean EnableDashboardResponseCaching;
	public final boolean getEnableDashboardResponseCaching()
	{
		return EnableDashboardResponseCaching;
	}
	public final void setEnableDashboardResponseCaching(boolean value)
	{
		EnableDashboardResponseCaching = value;
	}

	/** 
	 Allows the dashboard to be served from a custom path.
	 
	 <value>The dashboard source path.</value>
	*/
	private String DashboardSourcePath;
	public final String getDashboardSourcePath()
	{
		return DashboardSourcePath;
	}
	public final void setDashboardSourcePath(String value)
	{
		DashboardSourcePath = value;
	}

	/** 
	 Gets or sets the image saving convention.
	 
	 <value>The image saving convention.</value>
	*/
	private ImageSavingConvention ImageSavingConvention = getImageSavingConvention().values()[0];
	public final ImageSavingConvention getImageSavingConvention()
	{
		return ImageSavingConvention;
	}
	public final void setImageSavingConvention(ImageSavingConvention value)
	{
		ImageSavingConvention = value;
	}

	private MetadataOptions[] MetadataOptions;
	public final MetadataOptions[] getMetadataOptions()
	{
		return MetadataOptions;
	}
	public final void setMetadataOptions(MetadataOptions[] value)
	{
		MetadataOptions = value;
	}

	private boolean EnableAutomaticRestart;
	public final boolean getEnableAutomaticRestart()
	{
		return EnableAutomaticRestart;
	}
	public final void setEnableAutomaticRestart(boolean value)
	{
		EnableAutomaticRestart = value;
	}
	private boolean SkipDeserializationForBasicTypes;
	public final boolean getSkipDeserializationForBasicTypes()
	{
		return SkipDeserializationForBasicTypes;
	}
	public final void setSkipDeserializationForBasicTypes(boolean value)
	{
		SkipDeserializationForBasicTypes = value;
	}
	private boolean SkipDeserializationForPrograms;
	public final boolean getSkipDeserializationForPrograms()
	{
		return SkipDeserializationForPrograms;
	}
	public final void setSkipDeserializationForPrograms(boolean value)
	{
		SkipDeserializationForPrograms = value;
	}
	private boolean SkipDeserializationForAudio;
	public final boolean getSkipDeserializationForAudio()
	{
		return SkipDeserializationForAudio;
	}
	public final void setSkipDeserializationForAudio(boolean value)
	{
		SkipDeserializationForAudio = value;
	}

	private String ServerName;
	public final String getServerName()
	{
		return ServerName;
	}
	public final void setServerName(String value)
	{
		ServerName = value;
	}
	private String WanDdns;
	public final String getWanDdns()
	{
		return WanDdns;
	}
	public final void setWanDdns(String value)
	{
		WanDdns = value;
	}

	private String UICulture;
	public final String getUICulture()
	{
		return UICulture;
	}
	public final void setUICulture(String value)
	{
		UICulture = value;
	}

	private boolean SaveMetadataHidden;
	public final boolean getSaveMetadataHidden()
	{
		return SaveMetadataHidden;
	}
	public final void setSaveMetadataHidden(boolean value)
	{
		SaveMetadataHidden = value;
	}

	private NameValuePair[] ContentTypes;
	public final NameValuePair[] getContentTypes()
	{
		return ContentTypes;
	}
	public final void setContentTypes(NameValuePair[] value)
	{
		ContentTypes = value;
	}

	private int RemoteClientBitrateLimit;
	public final int getRemoteClientBitrateLimit()
	{
		return RemoteClientBitrateLimit;
	}
	public final void setRemoteClientBitrateLimit(int value)
	{
		RemoteClientBitrateLimit = value;
	}

	private int SharingExpirationDays;
	public final int getSharingExpirationDays()
	{
		return SharingExpirationDays;
	}
	public final void setSharingExpirationDays(int value)
	{
		SharingExpirationDays = value;
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

	private boolean EnableAnonymousUsageReporting;
	public final boolean getEnableAnonymousUsageReporting()
	{
		return EnableAnonymousUsageReporting;
	}
	public final void setEnableAnonymousUsageReporting(boolean value)
	{
		EnableAnonymousUsageReporting = value;
	}
	private boolean EnableStandaloneMusicKeys;
	public final boolean getEnableStandaloneMusicKeys()
	{
		return EnableStandaloneMusicKeys;
	}
	public final void setEnableStandaloneMusicKeys(boolean value)
	{
		EnableStandaloneMusicKeys = value;
	}
	private boolean EnableFolderView;
	public final boolean getEnableFolderView()
	{
		return EnableFolderView;
	}
	public final void setEnableFolderView(boolean value)
	{
		EnableFolderView = value;
	}
	private boolean EnableGroupingIntoCollections;
	public final boolean getEnableGroupingIntoCollections()
	{
		return EnableGroupingIntoCollections;
	}
	public final void setEnableGroupingIntoCollections(boolean value)
	{
		EnableGroupingIntoCollections = value;
	}
	private boolean DisplaySpecialsWithinSeasons;
	public final boolean getDisplaySpecialsWithinSeasons()
	{
		return DisplaySpecialsWithinSeasons;
	}
	public final void setDisplaySpecialsWithinSeasons(boolean value)
	{
		DisplaySpecialsWithinSeasons = value;
	}
	private boolean DisplayCollectionsView;
	public final boolean getDisplayCollectionsView()
	{
		return DisplayCollectionsView;
	}
	public final void setDisplayCollectionsView(boolean value)
	{
		DisplayCollectionsView = value;
	}
	private String[] LocalNetworkAddresses;
	public final String[] getLocalNetworkAddresses()
	{
		return LocalNetworkAddresses;
	}
	public final void setLocalNetworkAddresses(String[] value)
	{
		LocalNetworkAddresses = value;
	}
	private String[] CodecsUsed;
	public final String[] getCodecsUsed()
	{
		return CodecsUsed;
	}
	public final void setCodecsUsed(String[] value)
	{
		CodecsUsed = value;
	}
	private String[] Migrations;
	public final String[] getMigrations()
	{
		return Migrations;
	}
	public final void setMigrations(String[] value)
	{
		Migrations = value;
	}
	private boolean EnableChannelView;
	public final boolean getEnableChannelView()
	{
		return EnableChannelView;
	}
	public final void setEnableChannelView(boolean value)
	{
		EnableChannelView = value;
	}
	private boolean EnableExternalContentInSuggestions;
	public final boolean getEnableExternalContentInSuggestions()
	{
		return EnableExternalContentInSuggestions;
	}
	public final void setEnableExternalContentInSuggestions(boolean value)
	{
		EnableExternalContentInSuggestions = value;
	}

	private int ImageExtractionTimeoutMs;
	public final int getImageExtractionTimeoutMs()
	{
		return ImageExtractionTimeoutMs;
	}
	public final void setImageExtractionTimeoutMs(int value)
	{
		ImageExtractionTimeoutMs = value;
	}

	private PathSubstitution[] PathSubstitutions;
	public final PathSubstitution[] getPathSubstitutions()
	{
		return PathSubstitutions;
	}
	public final void setPathSubstitutions(PathSubstitution[] value)
	{
		PathSubstitutions = value;
	}
	private boolean EnableSimpleArtistDetection;
	public final boolean getEnableSimpleArtistDetection()
	{
		return EnableSimpleArtistDetection;
	}
	public final void setEnableSimpleArtistDetection(boolean value)
	{
		EnableSimpleArtistDetection = value;
	}

	/** 
	 Initializes a new instance of the <see cref="ServerConfiguration" /> class.
	*/
	public ServerConfiguration()
	{
		setLocalNetworkAddresses(new String[] { });
		setCodecsUsed(new String[] { });
		setMigrations(new String[] { });
		setImageExtractionTimeoutMs(0);
		setEnableLocalizedGuids(true);
		setPathSubstitutions(new PathSubstitution[] { });
		setEnableSimpleArtistDetection(true);

		setDisplaySpecialsWithinSeasons(true);
		setEnableExternalContentInSuggestions(true);

		setImageSavingConvention(ImageSavingConvention.Compatible);
		setPublicPort(DefaultHttpPort);
		setPublicHttpsPort(DefaultHttpsPort);
		setHttpServerPortNumber(DefaultHttpPort);
		setHttpsPortNumber(DefaultHttpsPort);
		setEnableHttps(false);
		setEnableDashboardResponseCaching(true);
		setEnableAnonymousUsageReporting(true);

		setEnableAutomaticRestart(true);
		setEnableFolderView(true);

		setEnableUPnP(true);
		setSharingExpirationDays(30);
		setMinResumePct(5);
		setMaxResumePct(90);

		// 5 minutes
		setMinResumeDurationSeconds(300);

		setLibraryMonitorDelay(60);

		setContentTypes(new NameValuePair[] { });

		setPreferredMetadataLanguage("en");
		setMetadataCountryCode("US");

		setSortReplaceCharacters(new String[] {".", "+", "%"});
		setSortRemoveCharacters(new String[] {",", "&", "-", "{", "}", "'"});
		setSortRemoveWords(new String[] {"the", "a", "an"});

		setSeasonZeroDisplayName("Specials");

		setUICulture("en-us");

		MetadataOptions tempVar = new MetadataOptions(1, 1280);
		tempVar.setItemType("Book");
		MetadataOptions tempVar2 = new MetadataOptions(1, 1280);
		tempVar2.setItemType("Movie");
		ImageOption tempVar3 = new ImageOption();
		tempVar3.setLimit(1);
		tempVar3.setMinWidth(1280);
		tempVar3.setType(ImageType.Backdrop);
		ImageOption tempVar4 = new ImageOption();
		tempVar4.setLimit(0);
		tempVar4.setType(ImageType.Art);
		ImageOption tempVar5 = new ImageOption();
		tempVar5.setLimit(0);
		tempVar5.setType(ImageType.Disc);
		ImageOption tempVar6 = new ImageOption();
		tempVar6.setLimit(1);
		tempVar6.setType(ImageType.Primary);
		ImageOption tempVar7 = new ImageOption();
		tempVar7.setLimit(0);
		tempVar7.setType(ImageType.Banner);
		ImageOption tempVar8 = new ImageOption();
		tempVar8.setLimit(1);
		tempVar8.setType(ImageType.Thumb);
		ImageOption tempVar9 = new ImageOption();
		tempVar9.setLimit(1);
		tempVar9.setType(ImageType.Logo);
		tempVar2.setImageOptions(new ImageOption[] {tempVar3, tempVar4, tempVar5, tempVar6, tempVar7, tempVar8, tempVar9});
		MetadataOptions tempVar10 = new MetadataOptions(1, 1280);
		tempVar10.setItemType("MusicVideo");
		ImageOption tempVar11 = new ImageOption();
		tempVar11.setLimit(1);
		tempVar11.setMinWidth(1280);
		tempVar11.setType(ImageType.Backdrop);
		ImageOption tempVar12 = new ImageOption();
		tempVar12.setLimit(0);
		tempVar12.setType(ImageType.Art);
		ImageOption tempVar13 = new ImageOption();
		tempVar13.setLimit(0);
		tempVar13.setType(ImageType.Disc);
		ImageOption tempVar14 = new ImageOption();
		tempVar14.setLimit(1);
		tempVar14.setType(ImageType.Primary);
		ImageOption tempVar15 = new ImageOption();
		tempVar15.setLimit(0);
		tempVar15.setType(ImageType.Banner);
		ImageOption tempVar16 = new ImageOption();
		tempVar16.setLimit(1);
		tempVar16.setType(ImageType.Thumb);
		ImageOption tempVar17 = new ImageOption();
		tempVar17.setLimit(1);
		tempVar17.setType(ImageType.Logo);
		tempVar10.setImageOptions(new ImageOption[] {tempVar11, tempVar12, tempVar13, tempVar14, tempVar15, tempVar16, tempVar17});
		MetadataOptions tempVar18 = new MetadataOptions(1, 1280);
		tempVar18.setItemType("Series");
		ImageOption tempVar19 = new ImageOption();
		tempVar19.setLimit(1);
		tempVar19.setMinWidth(1280);
		tempVar19.setType(ImageType.Backdrop);
		ImageOption tempVar20 = new ImageOption();
		tempVar20.setLimit(0);
		tempVar20.setType(ImageType.Art);
		ImageOption tempVar21 = new ImageOption();
		tempVar21.setLimit(1);
		tempVar21.setType(ImageType.Primary);
		ImageOption tempVar22 = new ImageOption();
		tempVar22.setLimit(1);
		tempVar22.setType(ImageType.Banner);
		ImageOption tempVar23 = new ImageOption();
		tempVar23.setLimit(1);
		tempVar23.setType(ImageType.Thumb);
		ImageOption tempVar24 = new ImageOption();
		tempVar24.setLimit(1);
		tempVar24.setType(ImageType.Logo);
		tempVar18.setImageOptions(new ImageOption[] {tempVar19, tempVar20, tempVar21, tempVar22, tempVar23, tempVar24});
		MetadataOptions tempVar25 = new MetadataOptions(1, 1280);
		tempVar25.setItemType("MusicAlbum");
		ImageOption tempVar26 = new ImageOption();
		tempVar26.setLimit(0);
		tempVar26.setMinWidth(1280);
		tempVar26.setType(ImageType.Backdrop);
		ImageOption tempVar27 = new ImageOption();
		tempVar27.setLimit(0);
		tempVar27.setType(ImageType.Disc);
		tempVar25.setImageOptions(new ImageOption[] {tempVar26, tempVar27});
		tempVar25.setDisabledMetadataFetchers(new String[]{"TheAudioDB"});
		MetadataOptions tempVar28 = new MetadataOptions(1, 1280);
		tempVar28.setItemType("MusicArtist");
		ImageOption tempVar29 = new ImageOption();
		tempVar29.setLimit(1);
		tempVar29.setMinWidth(1280);
		tempVar29.setType(ImageType.Backdrop);
		ImageOption tempVar30 = new ImageOption();
		tempVar30.setLimit(0);
		tempVar30.setType(ImageType.Banner);
		ImageOption tempVar31 = new ImageOption();
		tempVar31.setLimit(0);
		tempVar31.setType(ImageType.Art);
		ImageOption tempVar32 = new ImageOption();
		tempVar32.setLimit(1);
		tempVar32.setType(ImageType.Logo);
		tempVar28.setImageOptions(new ImageOption[] {tempVar29, tempVar30, tempVar31, tempVar32});
		tempVar28.setDisabledMetadataFetchers(new String[]{"TheAudioDB"});
		MetadataOptions tempVar33 = new MetadataOptions(1, 1280);
		tempVar33.setItemType("BoxSet");
		ImageOption tempVar34 = new ImageOption();
		tempVar34.setLimit(1);
		tempVar34.setMinWidth(1280);
		tempVar34.setType(ImageType.Backdrop);
		ImageOption tempVar35 = new ImageOption();
		tempVar35.setLimit(1);
		tempVar35.setType(ImageType.Primary);
		ImageOption tempVar36 = new ImageOption();
		tempVar36.setLimit(1);
		tempVar36.setType(ImageType.Thumb);
		ImageOption tempVar37 = new ImageOption();
		tempVar37.setLimit(1);
		tempVar37.setType(ImageType.Logo);
		ImageOption tempVar38 = new ImageOption();
		tempVar38.setLimit(0);
		tempVar38.setType(ImageType.Art);
		ImageOption tempVar39 = new ImageOption();
		tempVar39.setLimit(0);
		tempVar39.setType(ImageType.Disc);
		ImageOption tempVar40 = new ImageOption();
		tempVar40.setLimit(0);
		tempVar40.setType(ImageType.Banner);
		tempVar33.setImageOptions(new ImageOption[] {tempVar34, tempVar35, tempVar36, tempVar37, tempVar38, tempVar39, tempVar40});
		MetadataOptions tempVar41 = new MetadataOptions(0, 1280);
		tempVar41.setItemType("Season");
		ImageOption tempVar42 = new ImageOption();
		tempVar42.setLimit(0);
		tempVar42.setMinWidth(1280);
		tempVar42.setType(ImageType.Backdrop);
		ImageOption tempVar43 = new ImageOption();
		tempVar43.setLimit(1);
		tempVar43.setType(ImageType.Primary);
		ImageOption tempVar44 = new ImageOption();
		tempVar44.setLimit(0);
		tempVar44.setType(ImageType.Banner);
		ImageOption tempVar45 = new ImageOption();
		tempVar45.setLimit(0);
		tempVar45.setType(ImageType.Thumb);
		tempVar41.setImageOptions(new ImageOption[] {tempVar42, tempVar43, tempVar44, tempVar45});
		tempVar41.setDisabledMetadataFetchers(new String[]{"TheMovieDb"});
		MetadataOptions tempVar46 = new MetadataOptions(0, 1280);
		tempVar46.setItemType("Episode");
		ImageOption tempVar47 = new ImageOption();
		tempVar47.setLimit(0);
		tempVar47.setMinWidth(1280);
		tempVar47.setType(ImageType.Backdrop);
		ImageOption tempVar48 = new ImageOption();
		tempVar48.setLimit(1);
		tempVar48.setType(ImageType.Primary);
		tempVar46.setImageOptions(new ImageOption[] {tempVar47, tempVar48});
		tempVar46.setDisabledMetadataFetchers(new String[]{"The Open Movie Database", "TheMovieDb"});
		tempVar46.setDisabledImageFetchers(new String[]{"The Open Movie Database", "TheMovieDb"});
		setMetadataOptions(new MetadataOptions[] {tempVar, tempVar2, tempVar10, tempVar18, tempVar25, tempVar28, tempVar33, tempVar41, tempVar46});
	}
}