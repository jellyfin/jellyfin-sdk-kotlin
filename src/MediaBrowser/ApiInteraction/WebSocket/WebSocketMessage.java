package MediaBrowser.ApiInteraction.WebSocket;

public class WebSocketMessage<T> {

    private String messageType;
    public final String getMessageType()
    {
        return messageType;
    }
    public final void setMessageType(String value)
    {
        messageType = value;
    }

    private T data;
    public final T getData()
    {
        return data;
    }
    public final void setData(T value)
    {
        data = value;
    }
}
