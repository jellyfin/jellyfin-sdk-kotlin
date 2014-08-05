package MediaBrowser.ApiInteraction;

import MediaBrowser.Model.Extensions.*;
import MediaBrowser.Model.Drawing.*;
import MediaBrowser.Model.Dto.*;
import MediaBrowser.Model.Entities.*;
import MediaBrowser.Model.LiveTv.*;
import MediaBrowser.Model.Logging.*;
import MediaBrowser.Model.Querying.*;
import MediaBrowser.Model.Serialization.*;

import java.lang.reflect.ParameterizedType;

/** 
 Provides api methods that are usable on all platforms
*/
public abstract class BaseApiClient implements IDisposable
{
    //C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
    //	public event EventHandler ServerLocationChanged;
	private void OnServerLocationChanged()
	{
		//if (ServerLocationChanged != null)
		//{
			//ServerLocationChanged(this, EventArgs.Empty);
		//}
	}

	/** 
	 Gets the logger.
	 
	 <value>The logger.</value>
	*/
	private ILogger privateLogger;
	public final ILogger getLogger()
	{
		return privateLogger;
	}
	public final void setLogger(ILogger value)
	{
		privateLogger = value;
	}

	/** 
	 Gets the json serializer.
	 
	 <value>The json serializer.</value>
	*/
	private IJsonSerializer privateJsonSerializer;
	public final IJsonSerializer getJsonSerializer()
	{
		return privateJsonSerializer;
	}
	public final void setJsonSerializer(IJsonSerializer value)
	{
		privateJsonSerializer = value;
	}

	/** 
	  If specified this will be used as a default when an explicit value is not specified.
	*/
	private Integer privateImageQuality = null;
	public final Integer getImageQuality()
	{
		return privateImageQuality;
	}
	public final void setImageQuality(Integer value)
	{
		privateImageQuality = value;
	}

	protected BaseApiClient(ILogger logger, IJsonSerializer jsonSerializer, String serverAddress, String clientName, String deviceName, String deviceId, String applicationVersion)
	{
		if (logger == null)
		{
			throw new IllegalArgumentException("logger");
		}
		if (jsonSerializer == null)
		{
			throw new IllegalArgumentException("jsonSerializer");
		}
		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(serverAddress))
		{
			throw new IllegalArgumentException("serverAddress");
		}

		setJsonSerializer(jsonSerializer);
		setLogger(logger);

		setClientName(clientName);
		setDeviceName(deviceName);
		setDeviceId(deviceId);
		setApplicationVersion(applicationVersion);
		setServerAddress(serverAddress);
	}

	protected BaseApiClient(ILogger logger, IJsonSerializer jsonSerializer, String serverAddress, String accessToken)
	{
		if (logger == null)
		{
			throw new IllegalArgumentException("logger");
		}
		if (jsonSerializer == null)
		{
			throw new IllegalArgumentException("jsonSerializer");
		}
		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(serverAddress))
		{
			throw new IllegalArgumentException("serverAddress");
		}

		setJsonSerializer(jsonSerializer);
		setLogger(logger);

		setAccessToken(accessToken);
		setServerAddress(serverAddress);
	}

	/** 
	 Gets the name of the server host.
	 
	 <value>The name of the server host.</value>
	*/
	private String privateServerAddress;
	public final String getServerAddress()
	{
		return privateServerAddress;
	}
	private void setServerAddress(String value)
	{
		privateServerAddress = value;
	}

	/** 
	 Changes the server location.
	 
	 @param address The address.
	*/
	public final void ChangeServerLocation(String address)
	{
		setServerAddress(address);

		SetAuthenticationInfo(null, null);

		OnServerLocationChanged();
	}

	/** 
	 Gets or sets the type of the client.
	 
	 <value>The type of the client.</value>
	*/
	private String privateClientName;
	public final String getClientName()
	{
		return privateClientName;
	}
	public final void setClientName(String value)
	{
		privateClientName = value;
	}

	/** 
	 Gets or sets the name of the device.
	 
	 <value>The name of the device.</value>
	*/
	private String privateDeviceName;
	public final String getDeviceName()
	{
		return privateDeviceName;
	}
	public final void setDeviceName(String value)
	{
		privateDeviceName = value;
	}

	/** 
	 Gets or sets the application version.
	 
	 <value>The application version.</value>
	*/
	private String privateApplicationVersion;
	public final String getApplicationVersion()
	{
		return privateApplicationVersion;
	}
	public final void setApplicationVersion(String value)
	{
		privateApplicationVersion = value;
	}

	/** 
	 Gets or sets the device id.
	 
	 <value>The device id.</value>
	*/
	private String privateDeviceId;
	public final String getDeviceId()
	{
		return privateDeviceId;
	}
	public final void setDeviceId(String value)
	{
		privateDeviceId = value;
	}

	/** 
	 Gets or sets the access token.
	 
	 <value>The access token.</value>
	*/
	private String privateAccessToken;
	public final String getAccessToken()
	{
		return privateAccessToken;
	}
	private void setAccessToken(String value)
	{
		privateAccessToken = value;
	}

	/** 
	 Gets or sets the current user id.
	 
	 <value>The current user id.</value>
	*/
	private String privateCurrentUserId;
	public final String getCurrentUserId()
	{
		return privateCurrentUserId;
	}
	private void setCurrentUserId(String value)
	{
		privateCurrentUserId = value;
	}

	/** 
	 Gets the current api url based on hostname and port.
	 
	 <value>The API URL.</value>
	*/
	public final String getApiUrl()
	{
		return getServerAddress() + "/mediabrowser";
	}

	/** 
	 Gets the name of the slug.
	 
	 @param name The name.
	 @return System.String.
	*/
	protected final String GetSlugName(String name)
	{
		return name.replace('/', '-').replace('?', '-');
	}

	/** 
	 Gets the name of the authorization scheme.
	 
	 <value>The name of the authorization scheme.</value>
	*/
	protected final String getAuthorizationScheme()
	{
		return "MediaBrowser";
	}

	/** 
	 Gets the authorization header parameter.
	 
	 <value>The authorization header parameter.</value>
	*/
	protected final String getAuthorizationParameter()
	{
		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(getClientName()) && tangible.DotNetToJavaStringHelper.isNullOrEmpty(getDeviceId()) && tangible.DotNetToJavaStringHelper.isNullOrEmpty(getDeviceName()))
		{
			return "";
		}

        //C# TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java:
		String header = String.format("Client=\"%1$s\", DeviceId=\"%2$s\", Device=\"%3$s\", Version=\"%4$s\"", getClientName(), getDeviceId(), getDeviceName(), getApplicationVersion());

		if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(getCurrentUserId()))
		{
			header += String.format(", UserId=\"%1$s\"", getCurrentUserId());
		}

		return header;
	}

	/** 
	 Gets the API URL.
	 
	 @param handler The handler.
	 @return System.String.
	 @exception System.ArgumentNullException handler
	*/
	public final String GetApiUrl(String handler)
	{
		return GetApiUrl(handler, new QueryStringDictionary());
	}

	public final void SetAuthenticationInfo(String accessToken, String userId)
	{
		setCurrentUserId(userId);
		setAccessToken(accessToken);
		ResetHttpHeaders();
	}

	public final void ClearAuthenticationInfo()
	{
		setCurrentUserId(null);
		setAccessToken(null);
		ResetHttpHeaders();
	}

	public final void SetAuthenticationInfo(String accessToken)
	{
		setCurrentUserId(null);
		setAccessToken(accessToken);
		ResetHttpHeaders();
	}

	protected final void ResetHttpHeaders()
	{
		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(getAccessToken()))
		{
			ClearHttpRequestHeader("X-MediaBrowser-Token");
		}
		else
		{
			SetHttpRequestHeader("X-MediaBrowser-Token", getAccessToken());
		}

		String authValue = getAuthorizationParameter();

		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(authValue))
		{
			ClearHttpRequestHeader("Authorization");
		}
		else
		{
			SetAuthorizationHttpRequestHeader(getAuthorizationScheme(), authValue);
		}
	}

	protected abstract void SetAuthorizationHttpRequestHeader(String scheme, String parameter);
	protected abstract void SetHttpRequestHeader(String name, String value);
	protected abstract void ClearHttpRequestHeader(String name);

	/** 
	 Gets the API URL.
	 
	 @param handler The handler.
	 @param queryString The query string.
	 @return System.String.
	 @exception System.ArgumentNullException handler
	*/
	protected final String GetApiUrl(String handler, QueryStringDictionary queryString)
	{
		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(handler))
		{
			throw new IllegalArgumentException("handler");
		}

		if (queryString == null)
		{
			throw new IllegalArgumentException("queryString");
		}

		return queryString.GetUrl(getApiUrl() + "/" + handler);
	}

	public final String GetSubtitleUrl(SubtitleDownloadOptions options)
	{
		if (options == null)
		{
			throw new IllegalArgumentException("options");
		}
		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(options.getMediaSourceId()))
		{
			throw new IllegalArgumentException("options");
		}
		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(options.getItemId()))
		{
			throw new IllegalArgumentException("options");
		}
		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(options.getFormat()))
		{
			throw new IllegalArgumentException("options");
		}

		return GetApiUrl("Videos/" + options.getItemId() + "/" + options.getMediaSourceId() + "/Subtitles/" + options.getStreamIndex() + "/Stream." + options.getFormat());
	}

	/** 
	 Creates a url to return a list of items
	 
	 @param query The query.
	 @return System.String.
	 @exception System.ArgumentNullException query
	*/
	protected final String GetItemListUrl(ItemQuery query)
	{
		if (query == null)
		{
			throw new IllegalArgumentException("query");
		}

		QueryStringDictionary dict = new QueryStringDictionary ();

		dict.AddIfNotNullOrEmpty("ParentId", query.getParentId());

		dict.AddIfNotNull("StartIndex", query.getStartIndex());

		dict.AddIfNotNull("Limit", query.getLimit());

		dict.AddIfNotNull("SortBy", query.getSortBy());

        dict.AddIfNotNull("sortOrder", query.getSortOrder());

        dict.AddIfNotNull("SeriesStatuses", query.getSeriesStatuses());
        dict.AddIfNotNull("Fields", query.getFields());
        dict.AddIfNotNull("Filters", query.getFilters());
        dict.AddIfNotNull("ImageTypes", query.getImageTypes());

		dict.AddIfNotNull("Is3D", query.getIs3D());

        dict.AddIfNotNull("AirDays", query.getAirDays());
        dict.AddIfNotNull("VideoTypes", query.getVideoTypes());

		dict.AddIfNotNullOrEmpty("MinOfficialRating", query.getMinOfficialRating());
		dict.AddIfNotNullOrEmpty("MaxOfficialRating", query.getMaxOfficialRating());

		dict.Add("recursive", query.getRecursive());

		dict.AddIfNotNull("MinIndexNumber", query.getMinIndexNumber());

		dict.AddIfNotNull("MediaTypes", query.getMediaTypes());
		dict.AddIfNotNull("Genres", query.getGenres(), "|");
		dict.AddIfNotNull("Genres", query.getAllGenres(), "|");
		dict.AddIfNotNull("Ids", query.getIds());
		dict.AddIfNotNull("Studios", query.getStudios(), "|");
		dict.AddIfNotNull("ExcludeItemTypes", query.getExcludeItemTypes());
		dict.AddIfNotNull("IncludeItemTypes", query.getIncludeItemTypes());
		dict.AddIfNotNull("Artists", query.getArtists());

		dict.AddIfNotNull("IsPlayed", query.getIsPlayed());
		dict.AddIfNotNull("IsInBoxSet", query.getIsInBoxSet());

		dict.AddIfNotNullOrEmpty("Person", query.getPerson());
		dict.AddIfNotNull("PersonTypes", query.getPersonTypes());

		dict.AddIfNotNull("Years", query.getYears());

		dict.AddIfNotNull("ParentIndexNumber", query.getParentIndexNumber());
		dict.AddIfNotNull("IsHD", query.getIsHD());
		dict.AddIfNotNull("HasParentalRating", query.getHasParentalRating());

		dict.AddIfNotNullOrEmpty("SearchTerm", query.getSearchTerm());

		dict.AddIfNotNull("MinCriticRating", query.getMinCriticRating());
		dict.AddIfNotNull("MinCommunityRating", query.getMinCommunityRating());

		dict.AddIfNotNull("MinPlayers", query.getMinPlayers());
		dict.AddIfNotNull("MaxPlayers", query.getMaxPlayers());
		dict.AddIfNotNullOrEmpty("NameStartsWithOrGreater", query.getNameStartsWithOrGreater());
		dict.AddIfNotNullOrEmpty("AlbumArtistStartsWithOrGreater", query.getAlbumArtistStartsWithOrGreater());

		dict.AddIfNotNull("LocationTypes", query.getLocationTypes());
        dict.AddIfNotNull("ExcludeLocationTypes", query.getExcludeLocationTypes());

		dict.AddIfNotNull("IsMissing", query.getIsMissing());
		dict.AddIfNotNull("IsUnaired", query.getIsUnaired());
		dict.AddIfNotNull("IsVirtualUnaired", query.getIsVirtualUnaired());

		dict.AddIfNotNull("AiredDuringSeason", query.getAiredDuringSeason());

		return GetApiUrl("Users/" + query.getUserId() + "/Items", dict);
	}

	/** 
	 Gets the next up.
	 
	 @param query The query.
	 @return System.String.
	 @exception System.ArgumentNullException query
	*/
	protected final String GetNextUpUrl(NextUpQuery query)
	{
		if (query == null)
		{
			throw new IllegalArgumentException("query");
		}

		QueryStringDictionary dict = new QueryStringDictionary ();

		dict.AddIfNotNull("Fields", query.getFields());

		dict.AddIfNotNull("Limit", query.getLimit());

		dict.AddIfNotNull("StartIndex", query.getStartIndex());

		dict.Add("UserId", query.getUserId());

		return GetApiUrl("Shows/NextUp", dict);
	}

	/** 
	 Gets the similar item list URL.
	 
	 @param query The query.
	 @param type The type.
	 @return System.String.
	 @exception System.ArgumentNullException
	 query
	 or
	 type
	 
	*/
	protected final String GetSimilarItemListUrl(SimilarItemsQuery query, String type)
	{
		if (query == null)
		{
			throw new IllegalArgumentException("query");
		}
		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(type))
		{
			throw new IllegalArgumentException("type");
		}

		QueryStringDictionary dict = new QueryStringDictionary ();

		dict.AddIfNotNull("Limit", query.getLimit());
		dict.AddIfNotNullOrEmpty("UserId", query.getUserId());

		dict.AddIfNotNull("Fields", query.getFields());

		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(query.getId()))
		{
			throw new IllegalArgumentException("query");
		}

		return GetApiUrl(type + "/" + query.getId() + "/Similar", dict);
	}

	/** 
	 Gets the instant mix URL.
	 
	 @param query The query.
	 @param type The type.
	 @return System.String.
	 @exception System.ArgumentNullException
	 query
	 or
	 type
	 
	*/
	protected final String GetInstantMixUrl(SimilarItemsQuery query, String type)
	{
		if (query == null)
		{
			throw new IllegalArgumentException("query");
		}
		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(type))
		{
			throw new IllegalArgumentException("type");
		}

		QueryStringDictionary dict = new QueryStringDictionary ();

		dict.AddIfNotNull("Limit", query.getLimit());
		dict.AddIfNotNullOrEmpty("UserId", query.getUserId());

		dict.AddIfNotNull("Fields", query.getFields());

		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(query.getId()))
		{
			throw new IllegalArgumentException("query");
		}

		return GetApiUrl(type + "/" + query.getId() + "/InstantMix", dict);
	}

	/** 
	 Gets the instant mix by name URL.
	 
	 @param query The query.
	 @param type The type.
	 @return System.String.
	 @exception System.ArgumentNullException
	 query
	 or
	 type
	 
	*/
	protected final String GetInstantMixByNameUrl(SimilarItemsByNameQuery query, String type)
	{
		if (query == null)
		{
			throw new IllegalArgumentException("query");
		}
		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(type))
		{
			throw new IllegalArgumentException("type");
		}

		QueryStringDictionary dict = new QueryStringDictionary ();

		dict.AddIfNotNull("Limit", query.getLimit());
		dict.AddIfNotNullOrEmpty("UserId", query.getUserId());

        dict.AddIfNotNull("Fields", query.getFields());

		return GetApiUrl(type + "/" + GetSlugName(query.getName()) + "/InstantMix", dict);
	}

	/** 
	 Gets the item by name list URL.
	 
	 @param type The type.
	 @param query The query.
	 @return System.String.
	 @exception System.ArgumentNullException query
	*/
	protected final String GetItemByNameListUrl(String type, ItemsByNameQuery query)
	{
		if (query == null)
		{
			throw new IllegalArgumentException("query");
		}

		QueryStringDictionary dict = new QueryStringDictionary();

		dict.AddIfNotNullOrEmpty("ParentId", query.getParentId());

		dict.Add("UserId", query.getUserId());
		dict.AddIfNotNull("StartIndex", query.getStartIndex());

		dict.AddIfNotNull("Limit", query.getLimit());

		dict.AddIfNotNull("SortBy", query.getSortBy());

		dict.AddIfNotNull("sortOrder", query.getSortOrder());

		dict.AddIfNotNull("IsPlayed", query.getIsPlayed());

        dict.AddIfNotNull("Fields", query.getFields());

        dict.AddIfNotNull("Filters", query.getFields());
        dict.AddIfNotNull("ImageTypes", query.getImageTypes());

		dict.Add("recursive", query.getRecursive());

		dict.AddIfNotNull("MediaTypes", query.getMediaTypes());
		dict.AddIfNotNull("ExcludeItemTypes", query.getExcludeItemTypes());
		dict.AddIfNotNull("IncludeItemTypes", query.getIncludeItemTypes());

		dict.AddIfNotNullOrEmpty("NameLessThan", query.getNameLessThan());
		dict.AddIfNotNullOrEmpty("NameStartsWithOrGreater", query.getNameStartsWithOrGreater());

		return GetApiUrl(type, dict);
	}

	/** 
	 Gets the image URL.
	 
	 @param baseUrl The base URL.
	 @param options The options.
	 @param queryParams The query params.
	 @return System.String.
	 @exception System.ArgumentNullException options
	*/
	private String GetImageUrl(String baseUrl, ImageOptions options, QueryStringDictionary queryParams)
	{
		if (options == null)
		{
			throw new IllegalArgumentException("options");
		}

		if (queryParams == null)
		{
			throw new IllegalArgumentException("queryParams");
		}

		if (options.getImageIndex() != null)
		{
			baseUrl += "/" + options.getImageIndex().intValue();
		}

		queryParams.AddIfNotNull("Width", options.getWidth());
		queryParams.AddIfNotNull("Height", options.getHeight());
		queryParams.AddIfNotNull("MaxWidth", options.getMaxWidth());
		queryParams.AddIfNotNull("MaxHeight", options.getMaxHeight());
		queryParams.AddIfNotNull("Quality", (options.getQuality() != null) ? options.getQuality() : getImageQuality());

		queryParams.AddIfNotNullOrEmpty("Tag", options.getTag());

		queryParams.AddIfNotNull("CropWhitespace", options.getCropWhitespace());
		queryParams.Add("EnableImageEnhancers", options.getEnableImageEnhancers());

		if (options.getFormat() != ImageOutputFormat.Original)
		{
			queryParams.Add("Format", options.getFormat());
		}

		if (options.getAddPlayedIndicator())
		{
			queryParams.Add("AddPlayedIndicator", true);
		}
		queryParams.AddIfNotNull("PercentPlayed", options.getPercentPlayed());
		queryParams.AddIfNotNullOrEmpty("BackgroundColor", options.getBackgroundColor());

		return GetApiUrl(baseUrl, queryParams);
	}

	/** 
	 Gets the image URL.
	 
	 @param item The item.
	 @param options The options.
	 @return System.String.
	 @exception System.ArgumentNullException item
	*/
	public final String GetImageUrl(BaseItemDto item, ImageOptions options)
	{
		if (item == null)
		{
			throw new IllegalArgumentException("item");
		}

		if (options == null)
		{
			throw new IllegalArgumentException("options");
		}

		options.setTag(GetImageTag(item, options));

		return GetImageUrl(item.getId(), options);
	}

	public final String GetImageUrl(RecordingInfoDto item, ImageOptions options)
	{
		if (item == null)
		{
			throw new IllegalArgumentException("item");
		}

		if (options == null)
		{
			throw new IllegalArgumentException("options");
		}

		options.setTag(item.getImageTags().get(options.getImageType()));

		return GetImageUrl(item.getId(), options);
	}

	public final String GetImageUrl(ChannelInfoDto item, ImageOptions options)
	{
		if (item == null)
		{
			throw new IllegalArgumentException("item");
		}

		if (options == null)
		{
			throw new IllegalArgumentException("options");
		}

        options.setTag(item.getImageTags().get(options.getImageType()));

		return GetImageUrl(item.getId(), options);
	}

	public final String GetImageUrl(ProgramInfoDto item, ImageOptions options)
	{
		if (item == null)
		{
			throw new IllegalArgumentException("item");
		}

		if (options == null)
		{
			throw new IllegalArgumentException("options");
		}

        options.setTag(item.getImageTags().get(options.getImageType()));

		return GetImageUrl(item.getId(), options);
	}

	/** 
	 Gets an image url that can be used to download an image from the api
	 
	 @param itemId The Id of the item
	 @param options The options.
	 @return System.String.
	 @exception System.ArgumentNullException itemId
	*/
	public final String GetImageUrl(String itemId, ImageOptions options)
	{
		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(itemId))
		{
			throw new IllegalArgumentException("itemId");
		}

		String url = "Items/" + itemId + "/Images/" + options.getImageType();

		return GetImageUrl(url, options, new QueryStringDictionary());
	}

	/** 
	 Gets the user image URL.
	 
	 @param user The user.
	 @param options The options.
	 @return System.String.
	 @exception System.ArgumentNullException user
	*/
	public final String GetUserImageUrl(UserDto user, ImageOptions options)
	{
		if (user == null)
		{
			throw new IllegalArgumentException("user");
		}

		if (options == null)
		{
			throw new IllegalArgumentException("options");
		}

		options.setTag(user.getPrimaryImageTag());

		return GetUserImageUrl(user.getId(), options);
	}

	/** 
	 Gets an image url that can be used to download an image from the api
	 
	 @param userId The Id of the user
	 @param options The options.
	 @return System.String.
	 @exception System.ArgumentNullException userId
	*/
	public final String GetUserImageUrl(String userId, ImageOptions options)
	{
		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(userId))
		{
			throw new IllegalArgumentException("userId");
		}

		String url = "Users/" + userId + "/Images/" + options.getImageType();

		return GetImageUrl(url, options, new QueryStringDictionary());
	}

	/** 
	 Gets the person image URL.
	 
	 @param item The item.
	 @param options The options.
	 @return System.String.
	 @exception System.ArgumentNullException item
	*/
	public final String GetPersonImageUrl(BaseItemPerson item, ImageOptions options)
	{
		if (item == null)
		{
			throw new IllegalArgumentException("item");
		}

		if (options == null)
		{
			throw new IllegalArgumentException("options");
		}

		options.setTag(item.getPrimaryImageTag());

		return GetPersonImageUrl(item.getName(), options);
	}

	/** 
	 Gets the image tag.
	 
	 @param item The item.
	 @param options The options.
	 @return System.String.
	*/
	private String GetImageTag(BaseItemDto item, ImageOptions options)
	{
		if (options.getImageType() == ImageType.Backdrop)
		{
			return item.getBackdropImageTags().get((options.getImageIndex() != null) ? options.getImageIndex() : 0);
		}

		if (options.getImageType() == ImageType.Screenshot)
		{
            return item.getScreenshotImageTags().get((options.getImageIndex() != null) ? options.getImageIndex() : 0);
		}

		if (options.getImageType() == ImageType.Chapter)
		{
			return item.getChapters().get((options.getImageIndex() != null) ? options.getImageIndex() : 0).getImageTag();
		}

		return item.getImageTags().get(options.getImageType());
	}

	/** 
	 Gets an image url that can be used to download an image from the api
	 
	 @param name The name of the person
	 @param options The options.
	 @return System.String.
	 @exception System.ArgumentNullException name
	*/
	public final String GetPersonImageUrl(String name, ImageOptions options)
	{
		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(name))
		{
			throw new IllegalArgumentException("name");
		}

		String url = "Persons/" + GetSlugName(name) + "/Images/" + options.getImageType();

		return GetImageUrl(url, options, new QueryStringDictionary());
	}

	/** 
	 Gets an image url that can be used to download an image from the api
	 
	 @param year The year.
	 @param options The options.
	 @return System.String.
	*/
	public final String GetYearImageUrl(int year, ImageOptions options)
	{
		String url = "Years/" + year + "/Images/" + options.getImageType();

		return GetImageUrl(url, options, new QueryStringDictionary());
	}

	/** 
	 Gets an image url that can be used to download an image from the api
	 
	 @param name The name.
	 @param options The options.
	 @return System.String.
	 @exception System.ArgumentNullException name
	*/
	public final String GetGenreImageUrl(String name, ImageOptions options)
	{
		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(name))
		{
			throw new IllegalArgumentException("name");
		}

		String url = "Genres/" + GetSlugName(name) + "/Images/" + options.getImageType();

		return GetImageUrl(url, options, new QueryStringDictionary());
	}

	/** 
	 Gets the music genre image URL.
	 
	 @param name The name.
	 @param options The options.
	 @return System.String.
	 @exception System.ArgumentNullException name
	*/
	public final String GetMusicGenreImageUrl(String name, ImageOptions options)
	{
		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(name))
		{
			throw new IllegalArgumentException("name");
		}

		String url = "MusicGenres/" + GetSlugName(name) + "/Images/" + options.getImageType();

		return GetImageUrl(url, options, new QueryStringDictionary());
	}

	/** 
	 Gets the game genre image URL.
	 
	 @param name The name.
	 @param options The options.
	 @return System.String.
	 @exception System.ArgumentNullException name
	*/
	public final String GetGameGenreImageUrl(String name, ImageOptions options)
	{
		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(name))
		{
			throw new IllegalArgumentException("name");
		}

		String url = "GameGenres/" + GetSlugName(name) + "/Images/" + options.getImageType();

		return GetImageUrl(url, options, new QueryStringDictionary());
	}

	/** 
	 Gets an image url that can be used to download an image from the api
	 
	 @param name The name.
	 @param options The options.
	 @return System.String.
	 @exception System.ArgumentNullException name
	*/
	public final String GetStudioImageUrl(String name, ImageOptions options)
	{
		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(name))
		{
			throw new IllegalArgumentException("name");
		}

		String url = "Studios/" + GetSlugName(name) + "/Images/" + options.getImageType();

		return GetImageUrl(url, options, new QueryStringDictionary());
	}

	/** 
	 Gets the artist image URL.
	 
	 @param name The name.
	 @param options The options.
	 @return System.String.
	 @exception System.ArgumentNullException name
	*/
	public final String GetArtistImageUrl(String name, ImageOptions options)
	{
		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(name))
		{
			throw new IllegalArgumentException("name");
		}

		String url = "Artists/" + GetSlugName(name) + "/Images/" + options.getImageType();

		return GetImageUrl(url, options, new QueryStringDictionary());
	}

	/** 
	 This is a helper to get a list of backdrop url's from a given ApiBaseItemWrapper. If the actual item does not have any backdrops it will return backdrops from the first parent that does.
	 
	 @param item A given item.
	 @param options The options.
	 @return System.String[][].
	 @exception System.ArgumentNullException item
	*/
	public final String[] GetBackdropImageUrls(BaseItemDto item, ImageOptions options)
	{
		if (item == null)
		{
			throw new IllegalArgumentException("item");
		}

		if (options == null)
		{
			throw new IllegalArgumentException("options");
		}

		options.setImageType(ImageType.Backdrop);

		String backdropItemId;
		java.util.ArrayList<String> backdropImageTags;

		if (item.getBackdropCount() == 0)
		{
			backdropItemId = item.getParentBackdropItemId();
			backdropImageTags = item.getParentBackdropImageTags();
		}
		else
		{
			backdropItemId = item.getId();
			backdropImageTags = item.getBackdropImageTags();
		}

		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(backdropItemId))
		{
			return new String[] { };
		}

		String[] files = new String[backdropImageTags.size()];

		for (int i = 0; i < backdropImageTags.size(); i++)
		{
			options.setImageIndex(i);
			options.setTag(backdropImageTags.get(i));

			files[i] = GetImageUrl(backdropItemId, options);
		}

		return files;
	}

	/** 
	 This is a helper to get the logo image url from a given ApiBaseItemWrapper. If the actual item does not have a logo, it will return the logo from the first parent that does, or null.
	 
	 @param item A given item.
	 @param options The options.
	 @return System.String.
	 @exception System.ArgumentNullException item
	*/
	public final String GetLogoImageUrl(BaseItemDto item, ImageOptions options)
	{
		if (item == null)
		{
			throw new IllegalArgumentException("item");
		}

		if (options == null)
		{
			throw new IllegalArgumentException("options");
		}

		options.setImageType(ImageType.Logo);

        //C# TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java:
		String logoItemId = item.getHasLogo() ? item.getId() : item.getParentLogoItemId();
        //C# TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java:
        String imageTag = item.getHasLogo() ? item.getImageTags().get(ImageType.Logo) : item.getParentLogoImageTag();

		if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(logoItemId))
		{
			options.setTag(imageTag);

			return GetImageUrl(logoItemId, options);
		}

		return null;
	}

	public final String GetThumbImageUrl(BaseItemDto item, ImageOptions options)
	{
		if (item == null)
		{
			throw new IllegalArgumentException("item");
		}

		if (options == null)
		{
			throw new IllegalArgumentException("options");
		}

		options.setImageType(ImageType.Thumb);

        //C# TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java:
        String itemId = item.getHasThumb() ? item.getId() : item.getSeriesThumbImageTag() != null ? item.getSeriesId() : item.getParentThumbItemId();
        //C# TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java:
        String imageTag = item.getHasThumb() ? item.getImageTags().get(ImageType.Thumb) : (item.getSeriesThumbImageTag() != null) ? item.getSeriesThumbImageTag() : item.getParentThumbImageTag();

		if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(itemId))
		{
            options.setTag(imageTag);

			return GetImageUrl(itemId, options);
		}

		return null;
	}

	/** 
	 This is a helper to get the art image url from a given BaseItemDto. If the actual item does not have a logo, it will return the logo from the first parent that does, or null.
	 
	 @param item A given item.
	 @param options The options.
	 @return System.String.
	 @exception System.ArgumentNullException item
	*/
	public final String GetArtImageUrl(BaseItemDto item, ImageOptions options)
	{
		if (item == null)
		{
			throw new IllegalArgumentException("item");
		}

		if (options == null)
		{
			throw new IllegalArgumentException("options");
		}

		options.setImageType(ImageType.Art);

        //C# TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java:
        String artItemId = item.getHasArtImage() ? item.getId() : item.getParentArtItemId();
        //C# TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java:
        String imageTag = item.getHasArtImage() ? item.getImageTags().get(ImageType.Art) : item.getParentArtImageTag();

		if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(artItemId))
		{
            options.setTag(imageTag);

			return GetImageUrl(artItemId, options);
		}

		return null;
	}

	/** 
	 Deserializes from stream.
	 
	 @param stream The stream.
	 @param type The type.
	 @return System.Object.
	 @exception System.NotImplementedException
	*/
	protected final Object DeserializeFromString(String stream, java.lang.Class type)
	{
		return getJsonSerializer().DeserializeFromString(stream, type);
	}

    protected final <T> T DeserializeFromString(String stream)
    {
        return getJsonSerializer().DeserializeFromString(stream);
    }

	/** 
	 Serializers to json.
	 
	 @param obj The obj.
	 @return System.String.
	*/
	protected final String SerializeToJson(Object obj)
	{
		return getJsonSerializer().SerializeToString(obj);
	}

	/** 
	 Adds the data format.
	 
	 @param url The URL.
	 @return System.String.
	*/
	protected final String AddDataFormat(String url)
	{
		final String format = "json";

		if (url.indexOf('?') == -1)
		{
			url += "?format=" + format;
		}
		else
		{
			url += "&format=" + format;
		}

		return url;
	}

	/** 
	 Performs application-defined tasks associated with freeing, releasing, or resetting unmanaged resources.
	*/
	public final void Dispose()
	{
		Dispose(true);
	}

	/** 
	 Releases unmanaged and - optionally - managed resources.
	 
	 @param disposing <c>true</c> to release both managed and unmanaged resources; <c>false</c> to release only unmanaged resources.
	*/
	protected void Dispose(boolean disposing)
	{

	}
}