package MediaBrowser.ApiInteraction;

import MediaBrowser.Model.Logging.ILogger;
import MediaBrowser.Model.Serialization.IJsonSerializer;

import java.util.Observable;

public class ApiWebSocket {

    private IJsonSerializer jsonSerializer;
    private ILogger logger;
    private ServerEventListener serverEventListener;

    public void OpenWebSocket() {

        EnsureWebSocket();
    }

    public void EnsureWebSocket(){

        if (!IsWebSocketOpenOrConnecting()){

        }
    }

    public void CloseWebSocket(){

    }

    public void SendWebSocketMessage(String name){

    }

    public void SendWebSocketMessage(String name, String data){

    }

    public void SendWebSocketMessage(String name, Object data){

    }

    public boolean IsWebSocketOpen(){
        return false;
    }

    public boolean IsWebSocketOpenOrConnecting(){
        return false;
    }

    public void StartReceivingSessionUpdates(int intervalMs)
    {
        SendWebSocketMessage("SessionsStart", intervalMs + "," + intervalMs);
    }

    public void StopReceivingSessionUpdates()
    {
        SendWebSocketMessage("SessionsStop", "");
    }

    private void OnMessageReceived(String message){

    }
}
