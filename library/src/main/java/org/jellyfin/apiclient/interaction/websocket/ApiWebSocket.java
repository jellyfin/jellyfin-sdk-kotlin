package org.jellyfin.apiclient.interaction.websocket;

import org.jellyfin.apiclient.interaction.ApiClient;
import org.jellyfin.apiclient.interaction.ApiEventListener;
import org.jellyfin.apiclient.interaction.EmptyResponse;
import org.jellyfin.apiclient.model.apiclient.GeneralCommandEventArgs;
import org.jellyfin.apiclient.model.entities.LibraryUpdateInfo;
import org.jellyfin.apiclient.model.logging.ILogger;
import org.jellyfin.apiclient.model.net.WebSocketMessage;
import org.jellyfin.apiclient.model.serialization.IJsonSerializer;
import org.jellyfin.apiclient.model.session.*;

import java.net.URI;
import java.net.URLEncoder;

public class ApiWebSocket implements ISocketListener {

    private IJsonSerializer jsonSerializer;
    private ILogger logger;
    private ApiEventListener apiEventListener;
    private ApiClient apiClient;

    public ApiWebSocket(IJsonSerializer jsonSerializer, ILogger logger, ApiEventListener apiEventListener, ApiClient apiClient){

        this.jsonSerializer = jsonSerializer;
        this.logger = logger;
        this.apiEventListener = apiEventListener;
        this.apiClient = apiClient;
    }

    public void OpenWebSocket() {

        EnsureWebSocket();
    }

    public void EnsureWebSocket(){

        if (!IsWebSocketOpenOrConnecting()){
            OpenInternal();
        }
    }

    private void OpenInternal(){

        String address = getWebSocketServerAddress();

        URI uri = URI.create(address);

        logger.Debug("Connecting to web socket url: " + address);

        socketClient = new JavaWebSocketClient(logger, uri, this);

        socketClient.connect();
    }

    public void onOpen(){


    }

    private String getWebSocketServerAddress(){

        return apiClient.getApiUrl().replace("http", "ws") + "/embywebsocket?api_key=" + apiClient.getAccessToken() + "&deviceId=" + URLEncoder.encode(apiClient.getDeviceId());
    }

    public void CloseWebSocket(){

        if (IsWebSocketOpen()){

            socketClient.close();
        }
    }

    public void onClose(){


    }

    public void Close(){
        socketClient.close();
    }

    public void SendWebSocketMessage(String name, EmptyResponse response){

        SendWebSocketMessage(name, "", response);
    }

    public void SendWebSocketMessage(String name, Object data, EmptyResponse response){

        logger.Debug("Sending web socket message: " + name);
        WebSocketMessage<Object> msg = new WebSocketMessage<>();

        msg.setMessageType(name);
        msg.setData(data);

        String json = jsonSerializer.SerializeToString(msg);

        SendMessageInternal(json, response);
    }

    private void SendMessageInternal(String message, EmptyResponse response){
        if (IsWebSocketOpen()){

            socketClient.send(message);
            response.onResponse();
        }
        else{
            response.onError(null);
        }
    }

    private JavaWebSocketClient socketClient;
    public boolean IsWebSocketOpen(){

        if (socketClient != null){
            return  socketClient.IsWebSocketOpen();
        }

        return false;
    }

    public boolean IsWebSocketOpenOrConnecting(){

        if (socketClient != null){
            return  socketClient.IsWebSocketOpenOrConnecting();
        }

        return false;
    }

    public void StartReceivingSessionUpdates(int intervalMs)
    {
        SendWebSocketMessage("SessionsStart", intervalMs + "," + intervalMs, new EmptyResponse());
    }

    public void StopReceivingSessionUpdates()
    {
        SendWebSocketMessage("SessionsStop", "", new EmptyResponse());
    }

    public void onMessage(String message){

        String messageType = GetMessageType(message);

        logger.Info("Received web socket message: %s", messageType);

        if (messageType.equalsIgnoreCase("LibraryChanged"))
        {
            LibraryUpdateInfo obj = jsonSerializer.DeserializeFromString(message, LibraryUpdateInfo.class);
            apiEventListener.onLibraryChanged(apiClient, obj);

        }
        else if (messageType.equalsIgnoreCase("RestartRequired"))
        {

        }
        else if (messageType.equalsIgnoreCase("ServerRestarting"))
        {

        }
        else if (messageType.equalsIgnoreCase("ServerShuttingDown"))
        {

        }
        else if (messageType.equalsIgnoreCase("UserDeleted"))
        {

        }
        else if (messageType.equalsIgnoreCase("ScheduledTaskEnded"))
        {

        }
        else if (messageType.equalsIgnoreCase("PackageInstalling"))
        {

        }
        else if (messageType.equalsIgnoreCase("PackageInstallationFailed"))
        {

        }
        else if (messageType.equalsIgnoreCase("PackageInstallationCompleted"))
        {

        }
        else if (messageType.equalsIgnoreCase("PackageInstallationCancelled"))
        {

        }
        else if (messageType.equalsIgnoreCase("UserUpdated"))
        {
            UserDtoMessage obj = jsonSerializer.DeserializeFromString(message, UserDtoMessage.class);
            apiEventListener.onUserUpdated(apiClient, obj.getData());
        }
        else if (messageType.equalsIgnoreCase("UserConfigurationUpdated"))
        {
            UserDtoMessage obj = jsonSerializer.DeserializeFromString(message, UserDtoMessage.class);
            apiEventListener.onUserConfigurationUpdated(apiClient, obj.getData());
        }
        else if (messageType.equalsIgnoreCase("PluginUninstalled"))
        {

        }
        else if (messageType.equalsIgnoreCase("Play"))
        {
            PlayRequestMessage obj = jsonSerializer.DeserializeFromString(message, PlayRequestMessage.class);
            apiEventListener.onPlayCommand(apiClient, obj.getData());
        }
        else if (messageType.equalsIgnoreCase("Playstate"))
        {
            PlaystateRequestMessage obj = jsonSerializer.DeserializeFromString(message, PlaystateRequestMessage.class);
            apiEventListener.onPlaystateCommand(apiClient, obj.getData());
        }
        else if (messageType.equalsIgnoreCase("NotificationAdded"))
        {

        }
        else if (messageType.equalsIgnoreCase("NotificationUpdated"))
        {

        }
        else if (messageType.equalsIgnoreCase("NotificationsMarkedRead"))
        {

        }
        else if (messageType.equalsIgnoreCase("GeneralCommand"))
        {
            OnGeneralCommand(message);
        }
        else if (messageType.equalsIgnoreCase("Sessions"))
        {
            SessionUpdatesEventMessage obj = jsonSerializer.DeserializeFromString(message, SessionUpdatesEventMessage.class);
            apiEventListener.onSessionsUpdated(apiClient, obj.getData());
        }
        else if (messageType.equalsIgnoreCase("UserDataChanged"))
        {
            UserDataChangeMessage obj = jsonSerializer.DeserializeFromString(message, UserDataChangeMessage.class);
            apiEventListener.onUserDataChanged(apiClient, obj.getData());
        }
        else if (messageType.equalsIgnoreCase("SessionEnded"))
        {
            SessionInfoMessage obj = jsonSerializer.DeserializeFromString(message, SessionInfoMessage.class);
            apiEventListener.onSessionEnded(apiClient, obj.getData());
        }
        else if (messageType.equalsIgnoreCase("PlaybackStart"))
        {
            SessionInfoMessage obj = jsonSerializer.DeserializeFromString(message, SessionInfoMessage.class);
            apiEventListener.onPlaybackStart(apiClient, obj.getData());
        }
        else if (messageType.equalsIgnoreCase("PlaybackStopped"))
        {
            SessionInfoMessage obj = jsonSerializer.DeserializeFromString(message, SessionInfoMessage.class);
            apiEventListener.onPlaybackStopped(apiClient, obj.getData());
        }
    }

    private void OnGeneralCommand(String json) throws NumberFormatException
    {
        GeneralCommandEventArgs args = new GeneralCommandEventArgs();

        GeneralCommandMessage obj = jsonSerializer.DeserializeFromString(json, GeneralCommandMessage.class);
        args.setCommand(obj.getData());

        if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(args.getCommand().getName()))
        {
            args.setKnownCommandType(GeneralCommandType.valueOf(args.getCommand().getName()));
        }

        if (args.getKnownCommandType() != null)
        {
            if (args.getKnownCommandType() == GeneralCommandType.DisplayContent)
            {
                String itemId = args.getCommand().getArguments().get("ItemId");
                String itemName = args.getCommand().getArguments().get("ItemName");
                String itemType = args.getCommand().getArguments().get("ItemType");

                BrowseRequest request = new BrowseRequest();
                request.setItemId(itemId);
                request.setItemName(itemName);
                request.setItemType(itemType);

                apiEventListener.onBrowseCommand(apiClient, request);
                return;
            }
            if (args.getKnownCommandType() == GeneralCommandType.DisplayMessage)
            {
                String header = args.getCommand().getArguments().get("Header");
                String text = args.getCommand().getArguments().get("Text");
                String timeoutMs = args.getCommand().getArguments().get("TimeoutMs");

                MessageCommand command = new MessageCommand();
                command.setHeader(header);
                command.setText(text);
                command.setTimeoutMs(Long.parseLong(timeoutMs));

                apiEventListener.onMessageCommand(apiClient, command);
                return;
            }
            if (args.getKnownCommandType() == GeneralCommandType.SetVolume)
            {
                String volume = args.getCommand().getArguments().get("Volume");
                apiEventListener.onSetVolumeCommand(apiClient, Integer.parseInt(volume));
                return;
            }
            if (args.getKnownCommandType() == GeneralCommandType.SetAudioStreamIndex)
            {
                String index = args.getCommand().getArguments().get("Index");
                apiEventListener.onSetAudioStreamIndexCommand(apiClient, Integer.parseInt(index));
                return;
            }
            if (args.getKnownCommandType() == GeneralCommandType.SetSubtitleStreamIndex)
            {
                String index = args.getCommand().getArguments().get("Index");
                apiEventListener.onSetSubtitleStreamIndexCommand(apiClient, Integer.parseInt(index));
                return;
            }
            if (args.getKnownCommandType() == GeneralCommandType.SendString)
            {
                String val = args.getCommand().getArguments().get("String");
                apiEventListener.onSendStringCommand(apiClient, val);
                return;
            }
        }

        apiEventListener.onGeneralCommand(apiClient, args.getCommand());
    }

    private String GetMessageType(String json)
    {
        BasicWebSocketMessage message = jsonSerializer.DeserializeFromString(json, BasicWebSocketMessage.class);
        return message.getMessageType();
    }
}
