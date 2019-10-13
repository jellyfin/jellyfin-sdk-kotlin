package org.jellyfin.apiclient.model.serialization;

import com.google.gson.Gson;
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
    private static final String NEW_JSON = "{\"AccessToken\":\"TOKEN\",\"Address\":\"http://demo.jellyfin.org:80\",\"DateLastAccessed\":\"2019-10-13T02:04:51\",\"Id\":\"SERVER_ID\",\"Name\":\"Test Server\",\"UserId\":\"USER_ID\",\"Version\":\"10.4.0\"}";

    private DateFormat dateFormat;
    private Gson gson;

    @Before
    public void setUp() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .registerTypeAdapter(ServerInfo.class, new ServerInfoDeserializer())
                .create();
    }

    @Test
    public void testLegacyJson() throws Exception {
        ServerInfo info = gson.fromJson(LEGACY_JSON, ServerInfo.class);

        assertEquals("Test Server", info.getName());
        assertEquals("SERVER_ID", info.getId());
        assertNull(info.getVersion());
        assertEquals("http://demo.jellyfin.org:80", info.getAddress());
        assertEquals("USER_ID", info.getUserId());
        assertEquals("TOKEN", info.getAccessToken());
        assertEquals(dateFormat.parse("2019-08-06T18:15:20"), info.getDateLastAccessed());
    }

    @Test
    public void testNewJson() throws Exception {
        ServerInfo info = gson.fromJson(NEW_JSON, ServerInfo.class);

        assertEquals("Test Server", info.getName());
        assertEquals("SERVER_ID", info.getId());
        assertEquals("10.4.0", info.getVersion());
        assertEquals("http://demo.jellyfin.org:80", info.getAddress());
        assertEquals("USER_ID", info.getUserId());
        assertEquals("TOKEN", info.getAccessToken());
        assertEquals(dateFormat.parse("2019-10-13T02:04:51"), info.getDateLastAccessed());
    }
}