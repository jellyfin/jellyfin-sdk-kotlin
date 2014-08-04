package MediaBrowser.ApiInteraction;

import MediaBrowser.Model.Serialization.IJsonSerializer;

import java.io.InputStream;

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
    public <T> T DeserializeFromString(String text) {

        // TODO: Need this one
        throw new UnsupportedOperationException();
    }

    @Override
    public Object DeserializeFromStream(InputStream stream, Class type) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object DeserializeFromString(String json, Class type) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String SerializeToString(Object obj) {

        // TODO: Need this one
        throw new UnsupportedOperationException();
    }

    @Override
    public byte[] SerializeToBytes(Object obj) {
        return new byte[0];
    }
}
