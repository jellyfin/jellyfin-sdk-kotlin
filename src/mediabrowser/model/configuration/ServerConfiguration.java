package mediabrowser.model.configuration;

import mediabrowser.model.dto.*;
import mediabrowser.model.entities.*;

/** 
 Represents the server configuration.
*/
public class ServerConfiguration extends BaseApplicationConfiguration
{
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

	/** 
	 Gets or sets a value indicating whether [enable user specific user views].
	 
	 <value><c>true</c> if [enable user specific user views]; otherwise, <c>false</c>.</value>
	*/
	private boolean EnableUserSpecificUserViews;
	public final boolean getEnableUserSpecificUserViews()
	{
		return EnableUserSpecificUserViews;
	}
	public final void setEnableUserSpecificUserViews(boolean value)
	{
		EnableUserSpecificUserViews = value;
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
	 Gets or sets a value indicating whether [enable internet providers].
	 
	 <value><c>true</c> if [enable internet providers]; otherwise, <c>false</c>.</value>
	*/
	private boolean EnableInternetProviders;
	public final boolean getEnableInternetProviders()
	{
		return EnableInternetProviders;
	}
	public final void setEnableInternetProviders(boolean value)
	{
		EnableInternetProviders = value;
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
	 Gets or sets the item by name path.
	 
	 <value>The item by name path.</value>
	*/
	private String ItemsByNamePath;
	public final String getItemsByNamePath()
	{
		return ItemsByNamePath;
	}
	public final void setItemsByNamePath(String value)
	{
		ItemsByNamePath = value;
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
	 Gets or sets a value indicating whether [save local meta].
	 
	 <value><c>true</c> if [save local meta]; otherwise, <c>false</c>.</value>
	*/
	private boolean SaveLocalMeta;
	public final boolean getSaveLocalMeta()
	{
		return SaveLocalMeta;
	}
	public final void setSaveLocalMeta(boolean value)
	{
		SaveLocalMeta = value;
	}

	/** 
	 Gets or sets a value indicating whether [enable localized guids].
	 
	 <value><c>true</c> if [enable localized guids]; otherwise, <c>false</c>.</value>
	*/
	private boolean EnableLocalizedGuids;
	public final boolean getEnableLocalizedGuids()
	{
		return EnableLocalizedGuids;
	}
	public final void setEnableLocalizedGuids(boolean value)
	{
		EnableLocalizedGuids = value;
	}

	/** 
	 Gets or sets a value indicating whether [enable library metadata sub folder].
	 
	 <value><c>true</c> if [enable library metadata sub folder]; otherwise, <c>false</c>.</value>
	*/
	private boolean EnableLibraryMetadataSubFolder;
	public final boolean getEnableLibraryMetadataSubFolder()
	{
		return EnableLibraryMetadataSubFolder;
	}
	public final void setEnableLibraryMetadataSubFolder(boolean value)
	{
		EnableLibraryMetadataSubFolder = value;
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
	private int RealtimeLibraryMonitorDelay;
	public final int getRealtimeLibraryMonitorDelay()
	{
		return RealtimeLibraryMonitorDelay;
	}
	public final void setRealtimeLibraryMonitorDelay(int value)
	{
		RealtimeLibraryMonitorDelay = value;
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
	private boolean EnableDashboardResourceMinification;
	public final boolean getEnableDashboardResourceMinification()
	{
		return EnableDashboardResourceMinification;
	}
	public final void setEnableDashboardResourceMinification(boolean value)
	{
		EnableDashboardResourceMinification = value;
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

	private boolean MergeMetadataAndImagesByName;
	public final boolean getMergeMetadataAndImagesByName()
	{
		return MergeMetadataAndImagesByName;
	}
	public final void setMergeMetadataAndImagesByName(boolean value)
	{
		MergeMetadataAndImagesByName = value;
	}
	private boolean EnableStandaloneMetadata;
	public final boolean getEnableStandaloneMetadata()
	{
		return EnableStandaloneMetadata;
	}
	public final void setEnableStandaloneMetadata(boolean value)
	{
		EnableStandaloneMetadata = value;
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

	private PathSubstitution[] PathSubstitutions;
	public final PathSubstitution[] getPathSubstitutions()
	{
		return PathSubstitutions;
	}
	public final void setPathSubstitutions(PathSubstitution[] value)
	{
		PathSubstitutions = value;
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

	private PeopleMetadataOptions PeopleMetadataOptions;
	public final PeopleMetadataOptions getPeopleMetadataOptions()
	{
		return PeopleMetadataOptions;
	}
	public final void setPeopleMetadataOptions(PeopleMetadataOptions value)
	{
		PeopleMetadataOptions = value;
	}
	private boolean FindInternetTrailers;
	public final boolean getFindInternetTrailers()
	{
		return FindInternetTrailers;
	}
	public final void setFindInternetTrailers(boolean value)
	{
		FindInternetTrailers = value;
	}

	private String[] InsecureApps9;
	public final String[] getInsecureApps9()
	{
		return InsecureApps9;
	}
	public final void setInsecureApps9(String[] value)
	{
		InsecureApps9 = value;
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

	private boolean EnableAudioArchiveFiles;
	public final boolean getEnableAudioArchiveFiles()
	{
		return EnableAudioArchiveFiles;
	}
	public final void setEnableAudioArchiveFiles(boolean value)
	{
		EnableAudioArchiveFiles = value;
	}
	private boolean EnableVideoArchiveFiles;
	public final boolean getEnableVideoArchiveFiles()
	{
		return EnableVideoArchiveFiles;
	}
	public final void setEnableVideoArchiveFiles(boolean value)
	{
		EnableVideoArchiveFiles = value;
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

	private boolean DenyIFrameEmbedding;
	public final boolean getDenyIFrameEmbedding()
	{
		return DenyIFrameEmbedding;
	}
	public final void setDenyIFrameEmbedding(boolean value)
	{
		DenyIFrameEmbedding = value;
	}

	private AutoOnOff EnableLibraryMonitor = AutoOnOff.values()[0];
	public final AutoOnOff getEnableLibraryMonitor()
	{
		return EnableLibraryMonitor;
	}
	public final void setEnableLibraryMonitor(AutoOnOff value)
	{
		EnableLibraryMonitor = value;
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

	/** 
	 Initializes a new instance of the <see cref="ServerConfiguration" /> class.
	*/
	public ServerConfiguration()
	{
		super();
		setImageSavingConvention(ImageSavingConvention.Compatible);
		setPublicPort(8096);
		setPublicHttpsPort(8920);
		setHttpServerPortNumber(8096);
		setHttpsPortNumber(8920);
		setEnableHttps(false);
		setEnableDashboardResponseCaching(true);
		setEnableDashboardResourceMinification(true);

		setEnableAutomaticRestart(true);
		setDenyIFrameEmbedding(true);

		setEnableUPnP(true);

		setSharingExpirationDays(30);
		setMinResumePct(5);
		setMaxResumePct(90);

		// 5 minutes
		setMinResumeDurationSeconds(300);

		setEnableLibraryMonitor(AutoOnOff.Auto);
		setRealtimeLibraryMonitorDelay(40);

		setEnableInternetProviders(true);
		setFindInternetTrailers(true);

		setPathSubstitutions(new PathSubstitution[] { });
		setContentTypes(new NameValuePair[] { });

		setPreferredMetadataLanguage("en");
		setMetadataCountryCode("US");

		setSortReplaceCharacters(new String[] {".", "+", "%"});
		setSortRemoveCharacters(new String[] {",", "&", "-", "{", "}", "'"});
		setSortRemoveWords(new String[] {"the", "a", "an"});

		setSeasonZeroDisplayName("Specials");

		setUICulture("en-us");

		setPeopleMetadataOptions(new PeopleMetadataOptions());

		setInsecureApps9(new String[] {"Chromecast", "iOS", "Unknown app", "iPad", "iPhone", "Windows Phone"});

		MetadataOptions tempVar = new MetadataOptions(1, 1280);
		tempVar.setItemType("Book");
		MetadataOptions tempVar2 = new MetadataOptions(1, 1280);
		tempVar2.setItemType("Movie");
		ImageOption tempVar3 = new ImageOption();
		tempVar3.setLimit(2);
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
		tempVar10.setItemType("Series");
		ImageOption tempVar11 = new ImageOption();
		tempVar11.setLimit(2);
		tempVar11.setMinWidth(1280);
		tempVar11.setType(ImageType.Backdrop);
		ImageOption tempVar12 = new ImageOption();
		tempVar12.setLimit(0);
		tempVar12.setType(ImageType.Art);
		ImageOption tempVar13 = new ImageOption();
		tempVar13.setLimit(1);
		tempVar13.setType(ImageType.Primary);
		ImageOption tempVar14 = new ImageOption();
		tempVar14.setLimit(1);
		tempVar14.setType(ImageType.Banner);
		ImageOption tempVar15 = new ImageOption();
		tempVar15.setLimit(1);
		tempVar15.setType(ImageType.Thumb);
		ImageOption tempVar16 = new ImageOption();
		tempVar16.setLimit(1);
		tempVar16.setType(ImageType.Logo);
		tempVar10.setImageOptions(new ImageOption[] {tempVar11, tempVar12, tempVar13, tempVar14, tempVar15, tempVar16});
		MetadataOptions tempVar17 = new MetadataOptions(1, 1280);
		tempVar17.setItemType("MusicAlbum");
		ImageOption tempVar18 = new ImageOption();
		tempVar18.setLimit(0);
		tempVar18.setMinWidth(1280);
		tempVar18.setType(ImageType.Backdrop);
		ImageOption tempVar19 = new ImageOption();
		tempVar19.setLimit(0);
		tempVar19.setType(ImageType.Disc);
		tempVar17.setImageOptions(new ImageOption[] {tempVar18, tempVar19});
		MetadataOptions tempVar20 = new MetadataOptions(1, 1280);
		tempVar20.setItemType("MusicArtist");
		ImageOption tempVar21 = new ImageOption();
		tempVar21.setLimit(1);
		tempVar21.setMinWidth(1280);
		tempVar21.setType(ImageType.Backdrop);
		ImageOption tempVar22 = new ImageOption();
		tempVar22.setLimit(0);
		tempVar22.setType(ImageType.Banner);
		ImageOption tempVar23 = new ImageOption();
		tempVar23.setLimit(0);
		tempVar23.setType(ImageType.Art);
		ImageOption tempVar24 = new ImageOption();
		tempVar24.setLimit(0);
		tempVar24.setType(ImageType.Logo);
		tempVar20.setImageOptions(new ImageOption[] {tempVar21, tempVar22, tempVar23, tempVar24});
		MetadataOptions tempVar25 = new MetadataOptions(0, 1280);
		tempVar25.setItemType("Season");
		setMetadataOptions(new MetadataOptions[] {tempVar, tempVar2, tempVar10, tempVar17, tempVar20, tempVar25});
	}
}