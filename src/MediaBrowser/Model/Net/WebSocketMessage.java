package MediaBrowser.Model.Net;

/** 
 Class WebSocketMessage
 
 <typeparam name="T"></typeparam>
*/
public class WebSocketMessage<T>
{
	/** 
	 Gets or sets the type of the message.
	 
	 <value>The type of the message.</value>
	*/
	private String privateMessageType;
	public final String getMessageType()
	{
		return privateMessageType;
	}
	public final void setMessageType(String value)
	{
		privateMessageType = value;
	}
	/** 
	 Gets or sets the data.
	 
	 <value>The data.</value>
	*/
	private T privateData;
	public final T getData()
	{
		return privateData;
	}
	public final void setData(T value)
	{
		privateData = value;
	}
}