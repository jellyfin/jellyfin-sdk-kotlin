package MediaBrowser.Model.Net;

/** 
 Enum WebSocketState
*/
public enum WebSocketState
{
	/** 
	 The none
	*/
	None,
	/** 
	 The connecting
	*/
	Connecting,
	/** 
	 The open
	*/
	Open,
	/** 
	 The close sent
	*/
	CloseSent,
	/** 
	 The close received
	*/
	CloseReceived,
	/** 
	 The closed
	*/
	Closed,
	/** 
	 The aborted
	*/
	Aborted;

	public int getValue()
	{
		return this.ordinal();
	}

	public static WebSocketState forValue(int value)
	{
		return values()[value];
	}
}