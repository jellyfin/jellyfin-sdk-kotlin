package MediaBrowser.ApiInteraction.WebSocket;

import MediaBrowser.Model.Logging.ILogger;
import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.Map;
import java.util.Observable;

public class JavaWebSocketClient extends WebSocketClient {

    private ILogger _logger;

    public JavaWebSocketClient(ILogger logger, URI serverURI) {
        super(serverURI);
        _logger = logger;
    }

    public JavaWebSocketClient(ILogger logger, URI serverUri, Draft draft) {
        super(serverUri, draft);
        _logger = logger;
    }

    public JavaWebSocketClient(ILogger logger, URI serverUri, Draft draft, Map<String, String> headers, int connecttimeout) {
        super(serverUri, draft, headers, connecttimeout);
        _logger = logger;
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {

        _logger.Info("Web socket connection opened.");
    }

    @Override
    public void onMessage(String s) {

        _logger.Debug("Web socket message received.");

        onMessageObservable.notifyObservers(s);
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        _logger.Info("Web socket connection closed.");
    }

    @Override
    public void onError(Exception e) {

        _logger.ErrorException("Web socket error.", e);
    }

    public boolean IsWebSocketOpen(){

        WebSocket.READYSTATE state = getReadyState();

        return  state == WebSocket.READYSTATE.OPEN;
    }

    public boolean IsWebSocketOpenOrConnecting(){

        WebSocket.READYSTATE state = getReadyState();

        return  state == WebSocket.READYSTATE.OPEN || state == WebSocket.READYSTATE.CONNECTING;
    }

    private Observable onMessageObservable = new Observable();
    public Observable getOnMessageObservable(){
        return onMessageObservable;
    }

    private Observable onOpenObservable = new Observable();
    public Observable getOnOpenObservable(){
        return onOpenObservable;
    }
}
