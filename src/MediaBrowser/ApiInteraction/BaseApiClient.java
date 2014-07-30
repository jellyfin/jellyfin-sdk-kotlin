package MediaBrowser.ApiInteraction;

import MediaBrowser.Model.Extensions.*;
import MediaBrowser.Model.Drawing.*;
import MediaBrowser.Model.Dto.*;
import MediaBrowser.Model.Entities.*;
import MediaBrowser.Model.LiveTv.*;
import MediaBrowser.Model.Logging.*;
import MediaBrowser.Model.Querying.*;
import MediaBrowser.Model.Serialization.*;

/** 
 Provides api methods that are usable on all platforms
*/
public abstract class BaseApiClient implements IDisposable
{
//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	public event EventHandler ServerLocationChanged;
	private void OnServerLocationChanged()
	{
		if (ServerLocationChanged != null)
		{
			ServerLocationChanged(this, EventArgs.Empty);
		}
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
		var header = String.format("Client=\"%1$s\", DeviceId=\"%2$s\", Device=\"%3$s\", Version=\"%4$s\"", getClientName(), getDeviceId(), getDeviceName(), getApplicationVersion());

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
		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(options.MediaSourceId))
		{
			throw new IllegalArgumentException("options");
		}
		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(options.ItemId))
		{
			throw new IllegalArgumentException("options");
		}
		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(options.Format))
		{
			throw new IllegalArgumentException("options");
		}

		return GetApiUrl("Videos/" + options.ItemId + "/" + options.MediaSourceId + "/Subtitles/" + options.StreamIndex + "/Stream." + options.Format);
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

		QueryStringDictionary dict = new QueryStringDictionary { };

		dict.AddIfNotNullOrEmpty("ParentId", query.ParentId);

		dict.AddIfNotNull("StartIndex", query.StartIndex);

		dict.AddIfNotNull("Limit", query.Limit);

		dict.AddIfNotNull("SortBy", query.SortBy);

		if (query.SortOrder.HasValue)
		{
			dict["sortOrder"] = query.SortOrder.toString();
		}

		if (query.SeriesStatuses != null)
		{
			dict.Add("SeriesStatuses", query.SeriesStatuses.Select(f => f.toString()));
		}

		if (query.Fields != null)
		{
			dict.Add("fields", query.Fields.Select(f => f.toString()));
		}
		if (query.Filters != null)
		{
			dict.Add("Filters", query.Filters.Select(f => f.toString()));
		}
		if (query.ImageTypes != null)
		{
			dict.Add("ImageTypes", query.ImageTypes.Select(f => f.toString()));
		}

		dict.AddIfNotNull("Is3D", query.Is3D);
		if (query.VideoTypes != null)
		{
			dict.Add("VideoTypes", query.VideoTypes.Select(f => f.toString()));
		}
		if (query.AirDays != null)
		{
			dict.Add("AirDays", query.AirDays.Select(f => f.toString()));
		}

		dict.AddIfNotNullOrEmpty("MinOfficialRating", query.MinOfficialRating);
		dict.AddIfNotNullOrEmpty("MaxOfficialRating", query.MaxOfficialRating);

		dict.Add("recursive", query.Recursive);

		dict.AddIfNotNull("MinIndexNumber", query.MinIndexNumber);

		dict.AddIfNotNull("MediaTypes", query.MediaTypes);
		dict.AddIfNotNull("Genres", query.Genres, "|");
		dict.AddIfNotNull("Genres", query.AllGenres, "|");
		dict.AddIfNotNull("Ids", query.Ids);
		dict.AddIfNotNull("Studios", query.Studios, "|");
		dict.AddIfNotNull("ExcludeItemTypes", query.ExcludeItemTypes);
		dict.AddIfNotNull("IncludeItemTypes", query.IncludeItemTypes);
		dict.AddIfNotNull("Artists", query.Artists);

		dict.AddIfNotNull("IsPlayed", query.IsPlayed);
		dict.AddIfNotNull("IsInBoxSet", query.IsInBoxSet);

		dict.AddIfNotNullOrEmpty("Person", query.Person);
		dict.AddIfNotNull("PersonTypes", query.PersonTypes);

		dict.AddIfNotNull("Years", query.Years);

		dict.AddIfNotNull("ParentIndexNumber", query.ParentIndexNumber);
		dict.AddIfNotNull("IsHD", query.IsHD);
		dict.AddIfNotNull("HasParentalRating", query.HasParentalRating);

		dict.AddIfNotNullOrEmpty("SearchTerm", query.SearchTerm);

		dict.AddIfNotNull("MinCriticRating", query.MinCriticRating);
		dict.AddIfNotNull("MinCommunityRating", query.MinCommunityRating);

		dict.AddIfNotNull("MinPlayers", query.MinPlayers);
		dict.AddIfNotNull("MaxPlayers", query.MaxPlayers);
		dict.AddIfNotNullOrEmpty("NameStartsWithOrGreater", query.NameStartsWithOrGreater);
		dict.AddIfNotNullOrEmpty("AlbumArtistStartsWithOrGreater", query.AlbumArtistStartsWithOrGreater);

		if (query.LocationTypes != null && query.LocationTypes.getLength() > 0)
		{
			dict.Add("LocationTypes", query.LocationTypes.Select(f => f.toString()));
		}
		if (query.ExcludeLocationTypes != null && query.ExcludeLocationTypes.getLength() > 0)
		{
			dict.Add("ExcludeLocationTypes", query.ExcludeLocationTypes.Select(f => f.toString()));
		}

		dict.AddIfNotNull("IsMissing", query.IsMissing);
		dict.AddIfNotNull("IsUnaired", query.IsUnaired);
		dict.AddIfNotNull("IsVirtualUnaired", query.IsVirtualUnaired);

		dict.AddIfNotNull("AiredDuringSeason", query.AiredDuringSeason);

		return GetApiUrl("Users/" + query.UserId + "/Items", dict);
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

		QueryStringDictionary dict = new QueryStringDictionary { };

		if (query.Fields != null)
		{
			dict.Add("fields", query.Fields.Select(f => f.toString()));
		}

		dict.AddIfNotNull("Limit", query.Limit);

		dict.AddIfNotNull("StartIndex", query.StartIndex);

		dict.Add("UserId", query.UserId);

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

		QueryStringDictionary dict = new QueryStringDictionary { };

		dict.AddIfNotNull("Limit", query.Limit);
		dict.AddIfNotNullOrEmpty("UserId", query.UserId);

		if (query.Fields != null)
		{
			dict.Add("fields", query.Fields.Select(f => f.toString()));
		}

		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(query.Id))
		{
			throw new IllegalArgumentException("query");
		}

		return GetApiUrl(type + "/" + query.Id + "/Similar", dict);
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

		QueryStringDictionary dict = new QueryStringDictionary { };

		dict.AddIfNotNull("Limit", query.Limit);
		dict.AddIfNotNullOrEmpty("UserId", query.UserId);

		if (query.Fields != null)
		{
			dict.Add("fields", query.Fields.Select(f => f.toString()));
		}

		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(query.Id))
		{
			throw new IllegalArgumentException("query");
		}

		return GetApiUrl(type + "/" + query.Id + "/InstantMix", dict);
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

		QueryStringDictionary dict = new QueryStringDictionary { };

		dict.AddIfNotNull("Limit", query.Limit);
		dict.AddIfNotNullOrEmpty("UserId", query.UserId);

		if (query.Fields != null)
		{
			dict.Add("fields", query.Fields.Select(f => f.toString()));
		}

		return GetApiUrl(type + "/" + GetSlugName(query.Name) + "/InstantMix", dict);
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

		QueryStringDictionary dict = new QueryStringDictionary { };

		dict.AddIfNotNullOrEmpty("ParentId", query.ParentId);

		dict.Add("UserId", query.UserId);
		dict.AddIfNotNull("StartIndex", query.StartIndex);

		dict.AddIfNotNull("Limit", query.Limit);

		dict.AddIfNotNull("SortBy", query.SortBy);

		if (query.SortOrder.HasValue)
		{
			dict["sortOrder"] = query.SortOrder.toString();
		}

		dict.AddIfNotNull("IsPlayed", query.IsPlayed);

		if (query.Fields != null)
		{
			dict.Add("fields", query.Fields.Select(f => f.toString()));
		}

		if (query.Filters != null)
		{
			dict.Add("Filters", query.Filters.Select(f => f.toString()));
		}

		if (query.ImageTypes != null)
		{
			dict.Add("ImageTypes", query.ImageTypes.Select(f => f.toString()));
		}

		dict.Add("recursive", query.Recursive);

		dict.AddIfNotNull("MediaTypes", query.MediaTypes);
		dict.AddIfNotNull("ExcludeItemTypes", query.ExcludeItemTypes);
		dict.AddIfNotNull("IncludeItemTypes", query.IncludeItemTypes);

		dict.AddIfNotNullOrEmpty("NameLessThan", query.NameLessThan);
		dict.AddIfNotNullOrEmpty("NameStartsWithOrGreater", query.NameStartsWithOrGreater);

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

		if (options.ImageIndex.HasValue)
		{
			baseUrl += "/" + options.ImageIndex.Value;
		}

		queryParams.AddIfNotNull("Width", options.Width);
		queryParams.AddIfNotNull("Height", options.Height);
		queryParams.AddIfNotNull("MaxWidth", options.MaxWidth);
		queryParams.AddIfNotNull("MaxHeight", options.MaxHeight);
		queryParams.AddIfNotNull("Quality", (options.Quality != null) ? options.Quality : getImageQuality());

		queryParams.AddIfNotNullOrEmpty("Tag", options.Tag);

		queryParams.AddIfNotNull("CropWhitespace", options.CropWhitespace);
		queryParams.Add("EnableImageEnhancers", options.EnableImageEnhancers);

		if (options.Format != ImageOutputFormat.Original)
		{
			queryParams.Add("Format", options.Format.toString());
		}

		if (options.AddPlayedIndicator)
		{
			queryParams.Add("AddPlayedIndicator", true);
		}
		queryParams.AddIfNotNull("PercentPlayed", options.PercentPlayed);
		queryParams.AddIfNotNullOrEmpty("BackgroundColor", options.BackgroundColor);

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

		options.Tag = GetImageTag(item, options);

		return GetImageUrl(item.Id, options);
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

		options.Tag = item.ImageTags[options.ImageType];

		return GetImageUrl(item.Id, options);
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

		options.Tag = item.ImageTags[options.ImageType];

		return GetImageUrl(item.Id, options);
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

		options.Tag = item.ImageTags[options.ImageType];

		return GetImageUrl(item.Id, options);
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

		String url = "Items/" + itemId + "/Images/" + options.ImageType;

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

		options.Tag = user.PrimaryImageTag;

		return GetUserImageUrl(user.Id, options);
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

		String url = "Users/" + userId + "/Images/" + options.ImageType;

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

		options.Tag = item.PrimaryImageTag;

		return GetPersonImageUrl(item.Name, options);
	}

	/** 
	 Gets the image tag.
	 
	 @param item The item.
	 @param options The options.
	 @return System.String.
	*/
	private String GetImageTag(BaseItemDto item, ImageOptions options)
	{
		if (options.ImageType == ImageType.Backdrop)
		{
			return item.BackdropImageTags[(options.ImageIndex != null) ? options.ImageIndex : 0];
		}

		if (options.ImageType == ImageType.Screenshot)
		{
			return item.ScreenshotImageTags[(options.ImageIndex != null) ? options.ImageIndex : 0];
		}

		if (options.ImageType == ImageType.Chapter)
		{
			return item.Chapters[(options.ImageIndex != null) ? options.ImageIndex : 0].ImageTag;
		}

		return item.ImageTags[options.ImageType];
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

		String url = "Persons/" + GetSlugName(name) + "/Images/" + options.ImageType;

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
		String url = "Years/" + year + "/Images/" + options.ImageType;

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

		String url = "Genres/" + GetSlugName(name) + "/Images/" + options.ImageType;

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

		String url = "MusicGenres/" + GetSlugName(name) + "/Images/" + options.ImageType;

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

		String url = "GameGenres/" + GetSlugName(name) + "/Images/" + options.ImageType;

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

		String url = "Studios/" + GetSlugName(name) + "/Images/" + options.ImageType;

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

		String url = "Artists/" + GetSlugName(name) + "/Images/" + options.ImageType;

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

		options.ImageType = ImageType.Backdrop;

		String backdropItemId;
		java.util.ArrayList<String> backdropImageTags;

		if (item.BackdropCount == 0)
		{
			backdropItemId = item.ParentBackdropItemId;
			backdropImageTags = item.ParentBackdropImageTags;
		}
		else
		{
			backdropItemId = item.Id;
			backdropImageTags = item.BackdropImageTags;
		}

		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(backdropItemId))
		{
			return new String[] { };
		}

		String[] files = new String[backdropImageTags.size()];

		for (var i = 0; i < backdropImageTags.size(); i++)
		{
			options.ImageIndex = i;
			options.Tag = backdropImageTags.get(i);

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

		options.ImageType = ImageType.Logo;

//C# TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java:
		var logoItemId = item.HasLogo ? item.Id : item.ParentLogoItemId;
//C# TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java:
		var imageTag = item.HasLogo ? item.ImageTags[ImageType.Logo] : item.ParentLogoImageTag;

		if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(logoItemId))
		{
			options.Tag = imageTag;

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

		options.ImageType = ImageType.Thumb;

//C# TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java:
		var itemId = item.HasThumb ? item.Id : item.SeriesThumbImageTag != null ? item.SeriesId : item.ParentThumbItemId;
//C# TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java:
		var imageTag = item.HasThumb ? item.ImageTags[ImageType.Thumb] : (item.SeriesThumbImageTag != null) ? item.SeriesThumbImageTag : item.ParentThumbImageTag;

		if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(itemId))
		{
			options.Tag = imageTag;

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

		options.ImageType = ImageType.Art;

//C# TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java:
		var artItemId = item.HasArtImage ? item.Id : item.ParentArtItemId;
//C# TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java:
		var imageTag = item.HasArtImage ? item.ImageTags[ImageType.Art] : item.ParentArtImageTag;

		if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(artItemId))
		{
			options.Tag = imageTag;

			return GetImageUrl(artItemId, options);
		}

		return null;
	}

	/** 
	 Deserializes from stream.
	 
	 <typeparam name="T"></typeparam>
	 @param stream The stream.
	 @return ``0.
	*/
	protected final <T> T DeserializeFromStream(Stream stream)
	{
		return (T)DeserializeFromStream(stream, T.class);
	}

	/** 
	 Deserializes from stream.
	 
	 @param stream The stream.
	 @param type The type.
	 @return System.Object.
	 @exception System.NotImplementedException
	*/
	protected final Object DeserializeFromStream(Stream stream, java.lang.Class type)
	{
		return getJsonSerializer().DeserializeFromStream(stream, type);
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