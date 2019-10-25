package org.jellyfin.apiclient.model.serialization;

import org.jellyfin.apiclient.model.apiclient.ServerInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;

public class GsonJsonSerializer implements IJsonSerializer {
    private final Gson gson;

    public GsonJsonSerializer() {
        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .registerTypeAdapter(ServerInfo.class, new ServerInfoDeserializer())
                .create();
    }

    @Override
    public void SerializeToStream(Object obj, InputStream stream) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void SerializeToFile(Object obj, String file) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object DeserializeFromFile(Class type, String file) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T DeserializeFromFile(String file) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T DeserializeFromStream(InputStream stream) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T DeserializeFromString(String json, Class<T> type) {
        return gson.fromJson(json, type);
    }

    @Override
    public Object DeserializeFromStream(InputStream stream, Class type) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String SerializeToString(Object obj) {
        return gson.toJson(obj);
    }
}
