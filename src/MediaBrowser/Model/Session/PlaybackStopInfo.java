package MediaBrowser.Model.Session;

import MediaBrowser.Model.Entities.*;

/** 
 Class PlaybackStopInfo.
*/
public class PlaybackStopInfo
{
	/** 
	 Gets or sets the item.
	 
	 <value>The item.</value>
	*/
	private BaseItemInfo privateItem;
	public final BaseItemInfo getItem()
	{
		return privateItem;
	}
	public final void setItem(BaseItemInfo value)
	{
		privateItem = value;
	}

	/** 
	 Gets or sets the item identifier.
	 
	 <value>The item identifier.</value>
	*/
	private String privateItemId;
	public final String getItemId()
	{
		return privateItemId;
	}
	public final void setItemId(String value)
	{
		privateItemId = value;
	}

	/** 
	 Gets or sets the session id.
	 
	 <value>The session id.</value>
	*/
	private String privateSessionId;
	public final String getSessionId()
	{
		return privateSessionId;
	}
	public final void setSessionId(String value)
	{
		privateSessionId = value;
	}

	/** 
	 Gets or sets the media version identifier.
	 
	 <value>The media version identifier.</value>
	*/
	private String privateMediaSourceId;
	public final String getMediaSourceId()
	{
		return privateMediaSourceId;
	}
	public final void setMediaSourceId(String value)
	{
		privateMediaSourceId = value;
	}

	/** 
	 Gets or sets the position ticks.
	 
	 <value>The position ticks.</value>
	*/
	private Long privatePositionTicks;
	public final Long getPositionTicks()
	{
		return privatePositionTicks;
	}
	public final void setPositionTicks(Long value)
	{
		privatePositionTicks = value;
	}
}