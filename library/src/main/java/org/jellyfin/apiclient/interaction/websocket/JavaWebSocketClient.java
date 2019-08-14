package org.jellyfin.apiclient.interaction.websocket;

import org.jellyfin.apiclient.model.logging.ILogger;
import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

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

        _logger.Info("Web socket connection opened.");
        listener.onOpen();
    }

    @Override
    public void onMessage(String s) {

        _logger.Debug("Web socket message received.");
        listener.onMessage(s);
    }

    @Override
    public void onClose(int i, String s, boolean b) {

        _logger.Info("Web socket connection closed.");
        listener.onClose();
    }

    @Override
    public void onError(Exception e) {

        _logger.ErrorException("Web socket error.", e);
    }

    public boolean IsWebSocketOpen() {

        WebSocket.READYSTATE state = getReadyState();

        return  state == WebSocket.READYSTATE.OPEN;
    }

    public boolean IsWebSocketOpenOrConnecting() {

        WebSocket.READYSTATE state = getReadyState();

        return  state == WebSocket.READYSTATE.OPEN || state == WebSocket.READYSTATE.CONNECTING;
    }
}
