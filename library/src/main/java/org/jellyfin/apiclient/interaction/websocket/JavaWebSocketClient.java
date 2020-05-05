package org.jellyfin.apiclient.interaction.websocket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.handshake.ServerHandshake;
import org.jellyfin.apiclient.logging.ILogger;

import java.net.URI;

public class JavaWebSocketClient extends WebSocketClient {

    private ILogger _logger;
    private ISocketListener listener;

    public JavaWebSocketClient(ILogger logger, URI serverURI, ISocketListener listener) {
        super(serverURI);
        _logger = logger;
        this.listener = listener;
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {

        _logger.info("Web socket connection opened.");
        listener.onOpen();
    }

    @Override
    public void onMessage(String s) {

        _logger.debug("Web socket message received.");
        listener.onMessage(s);
    }

    @Override
    public void onClose(int i, String reason, boolean b) {
        _logger.info("Web socket connection closed. Reason: " + reason);
        listener.onClose();
    }

    @Override
    public void onError(Exception e) {

        _logger.error("Web socket error.", e);
    }

    public boolean IsWebSocketOpen() {

        ReadyState state = getReadyState();

        return state == ReadyState.OPEN;
    }

    public boolean IsWebSocketOpenOrConnecting() {

        ReadyState state = getReadyState();

        return state == ReadyState.OPEN || state == ReadyState.NOT_YET_CONNECTED;
    }
}
