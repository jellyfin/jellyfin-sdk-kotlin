package mediabrowser.apiinteraction.websocket;

import mediabrowser.apiinteraction.ApiClient;
import mediabrowser.apiinteraction.ApiEventListener;
import mediabrowser.apiinteraction.EmptyResponse;
import mediabrowser.model.apiclient.GeneralCommandEventArgs;
import mediabrowser.model.apiclient.SessionUpdatesEventArgs;
import mediabrowser.model.dto.UserDto;
import mediabrowser.model.extensions.IntHelper;
import mediabrowser.model.extensions.LongHelper;
import mediabrowser.model.extensions.StringHelper;
import mediabrowser.model.logging.ILogger;
import mediabrowser.model.net.WebSocketMessage;
import mediabrowser.model.serialization.IJsonSerializer;
import mediabrowser.model.session.*;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class ApiWebSocket implements ISocketListener {

    private IJsonSerializer jsonSerializer;
    private ILogger logger;
    private ApiEventListener apiEventListener;
    private ApiClient apiClient;
    private boolean enableReconnection;

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

        SendIdentificationMessage();
        SendCapabilities();
        enableReconnection = true;
    }

    private void SendIdentificationMessage(){

        SendWebSocketMessage("Identity", GetIdentificationMessage(), new EmptyResponse());
    }

    protected String GetIdentificationMessage()
    {
        return apiClient.getClientName() + "|" + apiClient.getDeviceId() + "|" + apiClient.getApplicationVersion() + "|" + apiClient.getDeviceName();
    }

    private void SendCapabilities(){

        apiClient.ReportCapabilities(apiClient.getCapabilities(), new EmptyResponse());
    }

    private String getWebSocketServerAddress(){

        return apiClient.getApiUrl().replace("http", "ws");
    }

    public void CloseWebSocket(){

        if (IsWebSocketOpen()){

            socketClient.close();
        }
    }

    public void onClose(){

        if (enableReconnection) {
            EnsureWebSocket();
            enableReconnection = false;
        }
    }

    public void Close(){
        enableReconnection = false;
        socketClient.close();
    }

    public void SendWebSocketMessage(String name, EmptyResponse response){

        SendWebSocketMessage(name, "", response);
    }

    public void SendWebSocketMessage(String name, Object data, EmptyResponse response){

        logger.Debug("Sending web socket message: " + name);
        WebSocketMessage msg = new WebSocketMessage<Object>();

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

        logger.Info("Received web socket message: {0}", messageType);

        if (StringHelper.EqualsIgnoreCase(messageType, "LibraryChanged"))
        {


        }
        else if (StringHelper.EqualsIgnoreCase(messageType, "RestartRequired"))
        {

        }
        else if (StringHelper.EqualsIgnoreCase(messageType, "ServerRestarting"))
        {

        }
        else if (StringHelper.EqualsIgnoreCase(messageType, "ServerShuttingDown"))
        {

        }
        else if (StringHelper.EqualsIgnoreCase(messageType, "UserDeleted"))
        {

        }
        else if (StringHelper.EqualsIgnoreCase(messageType, "ScheduledTaskEnded"))
        {

        }
        else if (StringHelper.EqualsIgnoreCase(messageType, "PackageInstalling"))
        {

        }
        else if (StringHelper.EqualsIgnoreCase(messageType, "PackageInstallationFailed"))
        {

        }
        else if (StringHelper.EqualsIgnoreCase(messageType, "PackageInstallationCompleted"))
        {

        }
        else if (StringHelper.EqualsIgnoreCase(messageType, "PackageInstallationCancelled"))
        {

        }
        else if (StringHelper.EqualsIgnoreCase(messageType, "UserUpdated"))
        {
            UserDtoMessage obj = jsonSerializer.DeserializeFromString(message, UserDtoMessage.class);
            apiEventListener.onUserUpdated(apiClient, obj.getData());
        }
        else if (StringHelper.EqualsIgnoreCase(messageType, "UserConfigurationUpdated"))
        {
            UserDtoMessage obj = jsonSerializer.DeserializeFromString(message, UserDtoMessage.class);
            apiEventListener.onUserConfigurationUpdated(apiClient, obj.getData());
        }
        else if (StringHelper.EqualsIgnoreCase(messageType, "PluginUninstalled"))
        {

        }
        else if (StringHelper.EqualsIgnoreCase(messageType, "Play"))
        {
            PlayRequestMessage obj = jsonSerializer.DeserializeFromString(message, PlayRequestMessage.class);
            apiEventListener.onPlayCommand(apiClient, obj.getData());
        }
        else if (StringHelper.EqualsIgnoreCase(messageType, "Playstate"))
        {
            PlaystateRequestMessage obj = jsonSerializer.DeserializeFromString(message, PlaystateRequestMessage.class);
            apiEventListener.onPlaystateCommand(apiClient, obj.getData());
        }
        else if (StringHelper.EqualsIgnoreCase(messageType, "NotificationAdded"))
        {

        }
        else if (StringHelper.EqualsIgnoreCase(messageType, "NotificationUpdated"))
        {

        }
        else if (StringHelper.EqualsIgnoreCase(messageType, "NotificationsMarkedRead"))
        {

        }
        else if (StringHelper.EqualsIgnoreCase(messageType, "GeneralCommand"))
        {
            OnGeneralCommand(message);
        }
        else if (StringHelper.EqualsIgnoreCase(messageType, "Sessions"))
        {
            SessionUpdatesEventMessage obj = jsonSerializer.DeserializeFromString(message, SessionUpdatesEventMessage.class);
            apiEventListener.onSessionsUpdated(apiClient, obj.getData());
        }
        else if (StringHelper.EqualsIgnoreCase(messageType, "UserDataChanged"))
        {
            UserDataChangeMessage obj = jsonSerializer.DeserializeFromString(message, UserDataChangeMessage.class);
            apiEventListener.onUserDataChanged(apiClient, obj.getData());
        }
        else if (StringHelper.EqualsIgnoreCase(messageType, "SessionEnded"))
        {
            SessionInfoMessage obj = jsonSerializer.DeserializeFromString(message, SessionInfoMessage.class);
            apiEventListener.onSessionEnded(apiClient, obj.getData());
        }
        else if (StringHelper.EqualsIgnoreCase(messageType, "PlaybackStart"))
        {
            SessionInfoMessage obj = jsonSerializer.DeserializeFromString(message, SessionInfoMessage.class);
            apiEventListener.onPlaybackStart(apiClient, obj.getData());
        }
        else if (StringHelper.EqualsIgnoreCase(messageType, "PlaybackStopped"))
        {
            SessionInfoMessage obj = jsonSerializer.DeserializeFromString(message, SessionInfoMessage.class);
            apiEventListener.onPlaybackStopped(apiClient, obj.getData());
        }
    }

    private void OnGeneralCommand(String json)
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

                long expected = 0;
                tangible.RefObject<Long> tempRef_expected = new tangible.RefObject<Long>(expected);
                LongHelper.TryParseCultureInvariant(timeoutMs, tempRef_expected);
                expected = tempRef_expected.argValue;

                MessageCommand command = new MessageCommand();

                command.setHeader(header);
                command.setText(text);
                command.setTimeoutMs(expected);

                apiEventListener.onMessageCommand(apiClient, command);
                return;
            }
            if (args.getKnownCommandType() == GeneralCommandType.SetVolume)
            {
                String volume = args.getCommand().getArguments().get("Volume");

                int expected = 0;
                tangible.RefObject<Integer> tempRef_expected = new tangible.RefObject<Integer>(expected);
                boolean tempVar = IntHelper.TryParseCultureInvariant(volume, tempRef_expected);
                expected = tempRef_expected.argValue;

                if (tempVar){
                    apiEventListener.onSetVolumeCommand(apiClient, expected);
                }

                return;
            }
            if (args.getKnownCommandType() == GeneralCommandType.SetAudioStreamIndex)
            {
                String index = args.getCommand().getArguments().get("Index");

                int expected = 0;
                tangible.RefObject<Integer> tempRef_expected = new tangible.RefObject<Integer>(expected);
                boolean tempVar = IntHelper.TryParseCultureInvariant(index, tempRef_expected);
                expected = tempRef_expected.argValue;

                if (tempVar){
                    apiEventListener.onSetAudioStreamIndexCommand(apiClient, expected);
                }

                return;
            }
            if (args.getKnownCommandType() == GeneralCommandType.SetSubtitleStreamIndex)
            {
                String index = args.getCommand().getArguments().get("Index");

                int expected = 0;
                tangible.RefObject<Integer> tempRef_expected = new tangible.RefObject<Integer>(expected);
                boolean tempVar = IntHelper.TryParseCultureInvariant(index, tempRef_expected);
                expected = tempRef_expected.argValue;

                if (tempVar){
                    apiEventListener.onSetSubtitleStreamIndexCommand(apiClient, expected);
                }

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
