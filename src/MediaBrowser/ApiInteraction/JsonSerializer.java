package MediaBrowser.ApiInteraction;

import MediaBrowser.Model.Serialization.IJsonSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;

import java.io.InputStream;
import java.lang.reflect.ParameterizedType;

public class JsonSerializer implements IJsonSerializer {
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
    public <T> T DeserializeFromString(String json) {

        Gson gsonBuilder = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    .create();

        Class<T> persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        return gsonBuilder.fromJson(json, persistentClass);
    }

    @Override
    public Object DeserializeFromStream(InputStream stream, Class type) {

        throw new UnsupportedOperationException();
    }

    @Override
    public Object DeserializeFromString(String json, Class type) {

        Gson gsonBuilder = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    .create();

        return gsonBuilder.fromJson(json, type);
    }

    @Override
    public String SerializeToString(Object obj) {

        Gson gsonBuilder = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        return gsonBuilder.toJson(obj);
    }
}
