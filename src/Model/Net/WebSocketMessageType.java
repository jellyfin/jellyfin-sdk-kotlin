package MediaBrowser.Model.Net;

/** 
 Enum WebSocketMessageType
*/
public enum WebSocketMessageType
{
	/** 
	 The text
	*/
	Text,
	/** 
	 The binary
	*/
	Binary,
	/** 
	 The close
	*/
	Close;

	public int getValue()
	{
		return this.ordinal();
	}

	public static WebSocketMessageType forValue(int value)
	{
		return values()[value];
	}
}