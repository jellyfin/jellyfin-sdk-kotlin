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
	private String MessageType;
	public final String getMessageType()
	{
		return MessageType;
	}
	public final void setMessageType(String value)
	{
		MessageType = value;
	}
	/** 
	 Gets or sets the data.
	 
	 <value>The data.</value>
	*/
	private T Data;
	public final T getData()
	{
		return Data;
	}
	public final void setData(T value)
	{
		Data = value;
	}
}