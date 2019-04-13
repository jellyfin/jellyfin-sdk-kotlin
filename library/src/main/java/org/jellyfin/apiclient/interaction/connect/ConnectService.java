package org.jellyfin.apiclient.interaction.connect;

import org.jellyfin.apiclient.interaction.EmptyResponse;
import org.jellyfin.apiclient.interaction.QueryStringDictionary;
import org.jellyfin.apiclient.interaction.Response;
import org.jellyfin.apiclient.interaction.SerializedResponse;
import org.jellyfin.apiclient.interaction.http.HttpRequest;
import org.jellyfin.apiclient.interaction.http.IAsyncHttpClient;
import org.jellyfin.apiclient.model.connect.*;
import org.jellyfin.apiclient.model.logging.ILogger;
import org.jellyfin.apiclient.model.serialization.IJsonSerializer;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class ConnectService {

    public IJsonSerializer JsonSerializer;
    private ILogger _logger;
    private IAsyncHttpClient _httpClient;
    private String appName;
    private String appVersion;

    public ConnectService(IJsonSerializer jsonSerializer, ILogger logger, IAsyncHttpClient httpClient, String appName, String appVersion) {
        JsonSerializer = jsonSerializer;
        _logger = logger;
        _httpClient = httpClient;
        this.appName = appName;
        this.appVersion = appVersion;
    }

    public void Authenticate(String username, String password, final Response<ConnectAuthenticationResult> response) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        QueryStringDictionary args = new QueryStringDictionary();

        args.Add("nameOrEmail", username);
        args.Add("rawpw", password);

        String url = GetConnectUrl("user/authenticate");

        HttpRequest request = new HttpRequest();

        request.setMethod("POST");
        request.setUrl(url);
        request.setPostData(args);

        AddXApplicationName(request);

        _httpClient.Send(request, new SerializedResponse<ConnectAuthenticationResult>(response, JsonSerializer, ConnectAuthenticationResult.class));
    }

    public void CreatePin(String deviceId, final Response<PinCreationResult> response)
    {
        QueryStringDictionary args = new QueryStringDictionary();

        args.Add("deviceId", deviceId);

        String url = GetConnectUrl("pin") + "?" + args.GetQueryString();

        HttpRequest request = new HttpRequest();

        request.setMethod("POST");
        request.setUrl(url);
        request.setPostData(args);

        AddXApplicationName(request);

        _httpClient.Send(request, new SerializedResponse<PinCreationResult>(response, JsonSerializer, PinCreationResult.class));
    }

    public void GetPinStatus(PinCreationResult pin, final Response<PinStatusResult> response)
    {
        QueryStringDictionary dict = new QueryStringDictionary();

        dict.Add("deviceId", pin.getDeviceId());
        dict.Add("pin", pin.getPin());

        String url = GetConnectUrl("pin") + "?" + dict.GetQueryString();

        HttpRequest request = new HttpRequest();

        request.setMethod("GET");
        request.setUrl(url);

        AddXApplicationName(request);

        _httpClient.Send(request, new SerializedResponse<PinStatusResult>(response, JsonSerializer, PinStatusResult.class));
    }

    public void ExchangePin(PinCreationResult pin, final Response<PinExchangeResult> response)
    {
        QueryStringDictionary args = new QueryStringDictionary();

        args.Add("deviceId", pin.getDeviceId());
        args.Add("pin", pin.getPin());

        String url = GetConnectUrl("pin/authenticate");

        HttpRequest request = new HttpRequest();

        request.setMethod("POST");
        request.setUrl(url);
        request.setPostData(args);

        AddXApplicationName(request);

        _httpClient.Send(request, new SerializedResponse<PinExchangeResult>(response, JsonSerializer, PinExchangeResult.class));
    }

    public void GetConnectUser(ConnectUserQuery query, String connectAccessToken, final Response<ConnectUser> response)
    {
        QueryStringDictionary dict = new QueryStringDictionary();

        if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(query.getId()))
        {
            dict.Add("id", query.getId());
        }
        else if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(query.getName()))
        {
            dict.Add("name", query.getName());
        }
        else if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(query.getEmail()))
        {
            dict.Add("email", query.getEmail());
        }
        else if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(query.getNameOrEmail()))
        {
            dict.Add("nameOrEmail", query.getNameOrEmail());
        }
        else
        {
            throw new IllegalArgumentException("Empty ConnectUserQuery");
        }

        String url = GetConnectUrl("user") + "?" + dict.GetQueryString();

        HttpRequest request = new HttpRequest();

        request.setMethod("GET");
        request.setUrl(url);

        AddUserAccessToken(request, connectAccessToken);
        AddXApplicationName(request);

        _httpClient.Send(request, new SerializedResponse<ConnectUser>(response, JsonSerializer, ConnectUser.class));
    }

    public void GetServers(String userId, String connectAccessToken, final Response<ConnectUserServer[]> response)
    {
        QueryStringDictionary dict = new QueryStringDictionary();

        dict.Add("userId", userId);

        String url = GetConnectUrl("servers") + "?" + dict.GetQueryString();

        HttpRequest request = new HttpRequest();

        request.setMethod("GET");
        request.setUrl(url);

        AddUserAccessToken(request, connectAccessToken);
        AddXApplicationName(request);

        _httpClient.Send(request, new SerializedResponse<>(response, JsonSerializer, ConnectUserServer[].class));
    }

    public void DeleteServer(String userId, String connectAccessToken, String serverId, EmptyResponse response)
    {
        QueryStringDictionary dict = new QueryStringDictionary();

        dict.Add("userId", userId);
        dict.Add("serverId", serverId);

        String url = GetConnectUrl("serverAuthorizations") + "?" + dict.GetQueryString();

        HttpRequest request = new HttpRequest();

        request.setMethod("DELETE");
        request.setUrl(url);

        AddUserAccessToken(request, connectAccessToken);
        AddXApplicationName(request);

        _httpClient.Send(request, new Response<String>(response));
    }

    public void Logout(String connectAccessToken, final EmptyResponse response)
    {
        String url = GetConnectUrl("user/logout");

        HttpRequest request = new HttpRequest();

        request.setMethod("POST");
        request.setUrl(url);

        AddUserAccessToken(request, connectAccessToken);
        AddXApplicationName(request);

        _httpClient.Send(request, new Response<String>(response));
    }

    private String GetConnectUrl(String handler)
    {
        return "https://connect.emby.media/service/" + handler;
    }

    private void AddUserAccessToken(HttpRequest request, String accessToken)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(accessToken))
        {
            throw new IllegalArgumentException("accessToken");
        }

        request.getRequestHeaders().put("X-Connect-UserToken", accessToken);
    }

    private void AddXApplicationName(HttpRequest request)
    {
        request.getRequestHeaders().put("X-Application", appName + "/" + appVersion);
    }
}
