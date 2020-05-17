package org.jellyfin.apiclient.interaction.connectionmanager;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class ConnectionManagerTest {

    @Test
    public void testNormalizeAddress() {
        // The normalizeAddress method doesn't use those parameters so it's safe to null everything
        ConnectionManager connectionManager = new ConnectionManager(null, null, null, null, null, null, null, null, null);

        // Tests for urls providing HTTP or HTTPS protocol
        assertArrayEquals(new String[]{"https://demo.jellyfin.org/stable"}, connectionManager.NormalizeAddress("https://demo.jellyfin.org/stable"));
        assertArrayEquals(new String[]{"http://demo.jellyfin.org/stable"}, connectionManager.NormalizeAddress("http://demo.jellyfin.org/stable"));

        // Tests for urls not providing a protocol
        assertArrayEquals(new String[]{"https://demo.jellyfin.org/stable", "http://demo.jellyfin.org/stable"}, connectionManager.NormalizeAddress("demo.jellyfin.org/stable"));
        assertArrayEquals(new String[]{"https://http.jellyfin.org/stable", "http://http.jellyfin.org/stable"}, connectionManager.NormalizeAddress("http.jellyfin.org/stable"));
    }
}
