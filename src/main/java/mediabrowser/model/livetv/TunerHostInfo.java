package mediabrowser.model.livetv;

import mediabrowser.model.dto.*;
import mediabrowser.model.extensions.*;

public class TunerHostInfo
{
	private String Id;
	public final String getId()
	{
		return Id;
	}
	public final void setId(String value)
	{
		Id = value;
	}
	private String Url;
	public final String getUrl()
	{
		return Url;
	}
	public final void setUrl(String value)
	{
		Url = value;
	}
	private String Type;
	public final String getType()
	{
		return Type;
	}
	public final void setType(String value)
	{
		Type = value;
	}
	private String DeviceId;
	public final String getDeviceId()
	{
		return DeviceId;
	}
	public final void setDeviceId(String value)
	{
		DeviceId = value;
	}
	private String FriendlyName;
	public final String getFriendlyName()
	{
		return FriendlyName;
	}
	public final void setFriendlyName(String value)
	{
		FriendlyName = value;
	}
	private boolean ImportFavoritesOnly;
	public final boolean getImportFavoritesOnly()
	{
		return ImportFavoritesOnly;
	}
	public final void setImportFavoritesOnly(boolean value)
	{
		ImportFavoritesOnly = value;
	}
	private boolean AllowHWTranscoding;
	public final boolean getAllowHWTranscoding()
	{
		return AllowHWTranscoding;
	}
	public final void setAllowHWTranscoding(boolean value)
	{
		AllowHWTranscoding = value;
	}
	private boolean EnableTvgId;
	public final boolean getEnableTvgId()
	{
		return EnableTvgId;
	}
	public final void setEnableTvgId(boolean value)
	{
		EnableTvgId = value;
	}

	public TunerHostInfo()
	{
		setAllowHWTranscoding(true);
	}
}