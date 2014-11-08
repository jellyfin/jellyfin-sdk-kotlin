package mediabrowser.apiinteraction.websocket;

public interface ISocketListener {

    void onOpen();
    void onClose();
    void onMessage(String message);
}
