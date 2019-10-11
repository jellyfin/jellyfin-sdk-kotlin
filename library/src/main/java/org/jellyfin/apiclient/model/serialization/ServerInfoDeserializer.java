package org.jellyfin.apiclient.model.serialization;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import org.jellyfin.apiclient.model.apiclient.ServerInfo;

import java.lang.reflect.Type;
import java.util.Date;

public class ServerInfoDeserializer implements JsonDeserializer<ServerInfo> {
    @Override
    public ServerInfo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        ServerInfo serverInfo = new ServerInfo();

        if (jsonObject.has("Name")) {
            serverInfo.setName(jsonObject.get("Name").getAsString());
        }

        if (jsonObject.has("Id")) {
            serverInfo.setId(jsonObject.get("Id").getAsString());
        }

        if (jsonObject.has("LastConnectionMode")) {
            String connectionMode = jsonObject.get("LastConnectionMode").getAsString();
            if (connectionMode.equals("Manual") && jsonObject.has("ManualAddress")) {
                serverInfo.setAddress(jsonObject.get("ManualAddress").getAsString());
            } else if (connectionMode.equals("Local") && jsonObject.has("LocalAddress")) {
                serverInfo.setAddress(jsonObject.get("LocalAddress").getAsString());
            } else if (connectionMode.equals("Remote") && jsonObject.has("RemoteAddress")) {
                serverInfo.setAddress(jsonObject.get("RemoteAddress").getAsString());
            }
        } else if (jsonObject.has("Address")) {
            serverInfo.setAddress(jsonObject.get("Address").getAsString());
        }

        if (jsonObject.has("Version")) {
            serverInfo.setVersion(jsonObject.get("Version").getAsString());
        }

        if (jsonObject.has("UserId")) {
            serverInfo.setUserId(jsonObject.get("UserId").getAsString());
        }

        if (jsonObject.has("AccessToken")) {
            serverInfo.setAccessToken(jsonObject.get("AccessToken").getAsString());
        }

        if (jsonObject.has("DateLastAccessed")) {
            serverInfo.setDateLastAccessed(
                    context.deserialize(jsonObject.get("DateLastAccessed"), Date.class));
        }

        return serverInfo;
    }
}
