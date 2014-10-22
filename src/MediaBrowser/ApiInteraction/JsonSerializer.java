package MediaBrowser.ApiInteraction;

import MediaBrowser.Model.Serialization.IJsonSerializer;

import org.boon.Boon;
import org.boon.json.JsonParser;
import org.boon.json.JsonParserAndMapper;
import org.boon.json.JsonParserFactory;
import org.boon.json.JsonSerializerFactory;

import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

public class JsonSerializer implements IJsonSerializer {

    private org.boon.json.JsonSerializer jsonSerializer;
    private JsonParserAndMapper jsonParser;

    public JsonSerializer(){
        ConfigureOptions();
    }

    private void ConfigureOptions(){

        JsonParserFactory jsonParserFactory = new JsonParserFactory();

        JsonSerializerFactory jsonSerializerFactory = new JsonSerializerFactory()
                .setCacheInstances( true ) //turns on caching for immutable objects
        ;

        this.jsonParser = jsonParserFactory.create();
        this.jsonSerializer = jsonSerializerFactory.create();
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
    public <T> T DeserializeFromString(String json, Class type) {

        //Gson gsonBuilder = new GsonBuilder()
                    //.setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    //.create();

        //Type listType = new TypeToken<T>() {}.getType();

        //return (T)gsonBuilder.fromJson(json, listType);

        return (T) jsonParser.parse(type, json);
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

        return jsonSerializer.serialize(obj).toString();
    }
}
