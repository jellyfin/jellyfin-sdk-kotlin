package org.jellyfin.apiclient.interaction.websocket;

public class BasicWebSocketMessage {

    private String MessageType;
    public final String getMessageType()
    {
        return MessageType;
    }
    public final void setMessageType(String value)
    {
        MessageType = value;
    }
}
