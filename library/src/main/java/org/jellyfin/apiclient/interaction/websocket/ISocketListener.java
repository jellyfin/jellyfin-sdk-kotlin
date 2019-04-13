package org.jellyfin.apiclient.interaction.websocket;

public interface ISocketListener {

    void onOpen();
    void onClose();
    void onMessage(String message);
}
