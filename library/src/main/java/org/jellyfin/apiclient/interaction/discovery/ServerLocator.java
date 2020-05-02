package org.jellyfin.apiclient.interaction.discovery;

import org.jellyfin.apiclient.interaction.Response;
import org.jellyfin.apiclient.logging.ILogger;
import org.jellyfin.apiclient.model.apiclient.ServerDiscoveryInfo;
import org.jellyfin.apiclient.serialization.IJsonSerializer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Enumeration;

public class ServerLocator implements IServerLocator {
    private final static String DISCOVERY_MESSAGE = "who is JellyfinServer?";

    private ILogger logger;
    private IJsonSerializer jsonSerializer;

    public ServerLocator(ILogger logger, IJsonSerializer jsonSerializer) {
        this.logger = logger;
        this.jsonSerializer = jsonSerializer;
    }

    @Override
    public void FindServers(int timeoutMs, Response<ArrayList<ServerDiscoveryInfo>> response)
    {
        // Find the server using UDP broadcast
        try {
            //Open a random port to send the package
            DatagramSocket c = new DatagramSocket();
            c.setBroadcast(true);

            byte[] sendData = DISCOVERY_MESSAGE.getBytes();

            int port = 7359;

            //Try the 255.255.255.255 first
            try {
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("255.255.255.255"), port);
                c.send(sendPacket);
                logger.debug("%s >>> Request packet sent to: 255.255.255.255 (DEFAULT)", getClass().getName());
            } catch (Exception e) {
                logger.exception("Error sending DatagramPacket", e);
            }

            // Broadcast the message over all the network interfaces
            Enumeration interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = (NetworkInterface)interfaces.nextElement();

                if (networkInterface.isLoopback() || !networkInterface.isUp()) {
                    continue; // Don't want to broadcast to the loopback interface
                }

                for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
                    InetAddress broadcast = interfaceAddress.getBroadcast();
                    if (broadcast == null) {
                        continue;
                    }

                    // Send the broadcast package!
                    try {
                        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, broadcast, port);
                        c.send(sendPacket);
                    } catch (Exception e) {
                        logger.exception("Error sending DatagramPacket", e);
                    }

                    logger.debug("%s >>> Request packet sent to: %s; Interface: %s", getClass().getName(), broadcast.getHostAddress(), networkInterface.getDisplayName());
                }
            }

            logger.debug("%s >>> Done looping over all network interfaces. Now waiting for a reply!", getClass().getName());

            Receive(c, timeoutMs, response);

            //Close the port!
            c.close();

        } catch (Exception ex) {

            logger.exception("Error finding servers", ex);

            response.onError(ex);
        }
    }

    private void Receive(DatagramSocket c, long timeoutMs, Response<ArrayList<ServerDiscoveryInfo>> response) throws IOException {

        ArrayList<ServerDiscoveryInfo> servers = new ArrayList<ServerDiscoveryInfo>();
        ArrayList<String> foundServerIds = new ArrayList<String>();

        while (timeoutMs > 0) {

            long startTime = System.currentTimeMillis();

            // Wait for a response
            byte[] recvBuf = new byte[15000];
            DatagramPacket receivePacket = new DatagramPacket(recvBuf, recvBuf.length);
            c.setSoTimeout((int)timeoutMs);

            try {
                c.receive(receivePacket);
            } catch (SocketTimeoutException e) {
                logger.debug("Server discovery timed out waiting for response.");
                break;
            }

            SocketAddress remoteEndpoint = c.getRemoteSocketAddress();

            // We have a response
            logger.debug("%s >>> Broadcast response from server: %s", getClass().getName(), receivePacket.getAddress().getHostAddress());

            // Check if the message is correct
            String message = new String(receivePacket.getData()).trim();

            logger.debug("%s >>> Broadcast response from server: %s", getClass().getName(), message);

            ServerDiscoveryInfo serverInfo = jsonSerializer.DeserializeFromString(message, ServerDiscoveryInfo.class);

            if (remoteEndpoint != null) {
                serverInfo.setEndpointAddress(remoteEndpoint.toString());
            }

            if (foundServerIds.indexOf(serverInfo.getId()) == -1) {
                foundServerIds.add(serverInfo.getId());
                servers.add(serverInfo);
            }

            long endTime = System.currentTimeMillis();
            timeoutMs = timeoutMs - (endTime - startTime);
        }

        logger.debug("Found %d servers", servers.size());

        response.onResponse(servers);
    }
}
