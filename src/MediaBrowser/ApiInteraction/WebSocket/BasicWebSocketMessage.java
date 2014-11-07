package MediaBrowser.apiinteraction.websocket;

public class BasicWebSocketMessage {

    private String messageType;
    public final String getMessageType()
    {
        return messageType;
    }
    public final void setMessageType(String value)
    {
        messageType = value;
    }

}
