package MediaBrowser.ApiInteraction;

import MediaBrowser.Model.Serialization.IJsonSerializer;

import org.boon.Boon;

import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

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
    public <T> T DeserializeFromString(String json, Class type) {

/*        Gson gsonBuilder = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    .create();*/

        return (T) Boon.fromJson(json, type);
    }

    @Override
    public Object DeserializeFromStream(InputStream stream, Class type) {

        throw new UnsupportedOperationException();
    }

    @Override
    public String SerializeToString(Object obj) {

/*        Gson gsonBuilder = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();*/

        return Boon.toJson(obj);
    }
}
