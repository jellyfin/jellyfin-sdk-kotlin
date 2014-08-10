package MediaBrowser.ApiInteraction;

import MediaBrowser.Model.ApiClient.ServerDiscoveryInfo;
import MediaBrowser.Model.Logging.ILogger;
import MediaBrowser.Model.Serialization.IJsonSerializer;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

public class ServerDiscovery {

    private ILogger logger;
    private IJsonSerializer jsonSerializer;

    public ServerDiscovery(ILogger logger) {
        this.logger = logger;
        jsonSerializer = new JsonSerializer();
    }

    public void FindServers(ServerDiscoveryResponse response)
    {
        // Find the server using UDP broadcast
        try {
            //Open a random port to send the package
            DatagramSocket c = new DatagramSocket();
            c.setBroadcast(true);

            byte[] sendData = "who is MediaBrowserServer_v2?".getBytes();

            //Try the 255.255.255.255 first
            try {
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("255.255.255.255"), 8888);
                c.send(sendPacket);
                logger.Debug(getClass().getName() + ">>> Request packet sent to: 255.255.255.255 (DEFAULT)");
            } catch (Exception e) {
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
                        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, broadcast, 8888);
                        c.send(sendPacket);
                    } catch (Exception e) {
                    }

                    logger.Debug(getClass().getName() + ">>> Request packet sent to: " + broadcast.getHostAddress() + "; Interface: " + networkInterface.getDisplayName());
                }
            }

            logger.Debug(getClass().getName() + ">>> Done looping over all network interfaces. Now waiting for a reply!");

            //Wait for a response
            byte[] recvBuf = new byte[15000];
            DatagramPacket receivePacket = new DatagramPacket(recvBuf, recvBuf.length);
            c.receive(receivePacket);

            //We have a response
            logger.Debug(getClass().getName() + ">>> Broadcast response from server: " + receivePacket.getAddress().getHostAddress());

            //Check if the message is correct
            String message = new String(receivePacket.getData()).trim();

            ServerDiscoveryInfo serverInfo = jsonSerializer.DeserializeFromString(message);

            ServerDiscoveryInfo[] servers = new ServerDiscoveryInfo[]{ serverInfo};

            response.onResponse(servers);

            //Close the port!
            c.close();

        } catch (IOException ex) {

            logger.ErrorException("Error finding servers", ex);
        }
    }
}
