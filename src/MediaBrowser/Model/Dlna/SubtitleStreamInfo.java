package MediaBrowser.Model.Dlna;

import MediaBrowser.Model.Drawing.*;
import MediaBrowser.Model.Dto.*;
import MediaBrowser.Model.Entities.*;
import MediaBrowser.Model.Extensions.*;
import MediaBrowser.Model.MediaInfo.*;
import MediaBrowser.Model.Session.*;

public class SubtitleStreamInfo
{
	private String privateUrl;
	public final String getUrl()
	{
		return privateUrl;
	}
	public final void setUrl(String value)
	{
		privateUrl = value;
	}
	private String privateLanguage;
	public final String getLanguage()
	{
		return privateLanguage;
	}
	public final void setLanguage(String value)
	{
		privateLanguage = value;
	}
	private String privateName;
	public final String getName()
	{
		return privateName;
	}
	public final void setName(String value)
	{
		privateName = value;
	}
	private boolean privateIsForced;
	public final boolean getIsForced()
	{
		return privateIsForced;
	}
	public final void setIsForced(boolean value)
	{
		privateIsForced = value;
	}
}