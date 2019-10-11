package org.jellyfin.apiclient.model.serialization;

import com.google.gson.GsonBuilder;

import org.jellyfin.apiclient.model.apiclient.ServerInfo;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static org.junit.Assert.*;

public class ServerInfoDeserializerTest {
    private static final String LEGACY_JSON = "{\"AccessToken\":\"TOKEN\",\"DateLastAccessed\":\"2019-08-06T18:15:20\",\"Id\":\"SERVER_ID\",\"LastConnectionMode\":\"Manual\",\"LocalAddress\":\"http://172.19.0.2:8096\",\"ManualAddress\":\"http://demo.jellyfin.org:80\",\"Name\":\"Test Server\",\"RemoteAddress\":\"http://73.147.235.56:8096\",\"UserId\":\"USER_ID\"}";

    private DateFormat dateFormat;
    private GsonBuilder gsonBuilder;

    @Before
    public void setUp() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
        gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .registerTypeAdapter(ServerInfo.class, new ServerInfoDeserializer());
    }

    @Test
    public void testLegacyJson() throws Exception {
        ServerInfo info = gsonBuilder.create().fromJson(LEGACY_JSON, ServerInfo.class);

        assertEquals("Test Server", info.getName());
        assertEquals("SERVER_ID", info.getId());
        assertNull(info.getVersion());
        assertEquals("http://demo.jellyfin.org:80", info.getAddress());
        assertEquals("USER_ID", info.getUserId());
        assertEquals("TOKEN", info.getAccessToken());
        assertEquals(dateFormat.parse("2019-08-06T18:15:20"), info.getDateLastAccessed());
    }
}