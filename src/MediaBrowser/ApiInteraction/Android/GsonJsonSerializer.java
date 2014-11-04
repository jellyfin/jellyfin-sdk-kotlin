package MediaBrowser.ApiInteraction.Android;

import MediaBrowser.Model.Serialization.IJsonSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;

public class GsonJsonSerializer implements IJsonSerializer {

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
    public <T> T DeserializeFromString(String json, Class type) {

        Gson gsonBuilder = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        //Type listType = new TypeToken<T>() {}.getType();

        return (T)gsonBuilder.fromJson(json, type);
    }

    @Override
    public Object DeserializeFromStream(InputStream stream, Class type) {

        throw new UnsupportedOperationException();
    }

    @Override
    public String SerializeToString(Object obj) {

        Gson gsonBuilder = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        return gsonBuilder.toJson(obj);
    }
}
