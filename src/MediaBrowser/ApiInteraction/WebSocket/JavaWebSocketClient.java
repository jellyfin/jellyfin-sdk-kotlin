package MediaBrowser.ApiInteraction.WebSocket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.Map;

public class JavaWebSocketClient extends WebSocketClient {

    public JavaWebSocketClient(URI serverURI) {
        super(serverURI);
    }

    public JavaWebSocketClient(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public JavaWebSocketClient(URI serverUri, Draft draft, Map<String, String> headers, int connecttimeout) {
        super(serverUri, draft, headers, connecttimeout);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {

    }

    @Override
    public void onMessage(String s) {

    }

    @Override
    public void onClose(int i, String s, boolean b) {

    }

    @Override
    public void onError(Exception e) {

    }
}
