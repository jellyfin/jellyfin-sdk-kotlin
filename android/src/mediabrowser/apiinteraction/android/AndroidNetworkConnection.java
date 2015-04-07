package mediabrowser.apiinteraction.android;

import mediabrowser.apiinteraction.EmptyResponse;
import mediabrowser.apiinteraction.network.INetworkConnection;
import mediabrowser.model.apiclient.NetworkStatus;
import mediabrowser.model.logging.ILogger;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.*;

public class AndroidNetworkConnection implements INetworkConnection {

    private Context context;
    private ILogger logger;

    public AndroidNetworkConnection(Context context, ILogger logger) {
        this.context = context;
        this.logger = logger;
    }

    @Override
    public void SendWakeOnLan(String macAddress, int port, EmptyResponse response)
    {
        logger.Debug("Sending WakeOnLan over broadcast address. Mac: %s, Port: %d", macAddress, port);

        try {
            byte[] macBytes = getMacBytes(macAddress);
            byte[] bytes = new byte[6 + 16 * macBytes.length];
            for (int i = 0; i < 6; i++) {
                bytes[i] = (byte) 0xff;
            }
            for (int i = 6; i < bytes.length; i += macBytes.length) {
                System.arraycopy(macBytes, 0, bytes, i, macBytes.length);
            }

            DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
            DatagramSocket socket = new DatagramSocket(port);
            socket.setBroadcast(true);
            socket.send(packet);
            socket.close();

            response.onResponse();
        }
        catch (Exception ex){
            response.onError(ex);
        }
    }

    @Override
    public void SendWakeOnLan(String macAddress, String ipAddress, int port, EmptyResponse response)
    {
        logger.Debug("Sending WakeOnLan to %s. Mac: %s, Port: %d", ipAddress, macAddress, port);

        try {
            byte[] macBytes = getMacBytes(macAddress);
            byte[] bytes = new byte[6 + 16 * macBytes.length];
            for (int i = 0; i < 6; i++) {
                bytes[i] = (byte) 0xff;
            }
            for (int i = 6; i < bytes.length; i += macBytes.length) {
                System.arraycopy(macBytes, 0, bytes, i, macBytes.length);
            }

            InetAddress address = InetAddress.getByName(ipAddress);
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, port);
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
            socket.close();

            response.onResponse();
        }
        catch (Exception ex){
            response.onError(ex);
        }
    }

    @Override
    public NetworkStatus getNetworkStatus() {

        NetworkStatus status = new NetworkStatus();

        logger.Debug("Testing local device network connection");

        final ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();

        if (activeNetwork != null && activeNetwork.isConnected()) {

            status.setIsNetworkAvailable(true);

            logger.Debug("Android device is connected to a network");

        } else {

            logger.Debug("Local device is not connected to a network");

            // notify user you are not online
            status.setIsNetworkAvailable(false);
        }

        //NetworkInfo network = conMgr.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET);
        //status.setIsEthernetNetworkAvailable(network != null && network.isConnected());

        //network = conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        //status.setIsWifiNetworkAvailable(network != null && network.isConnected());

        return status;
    }

    private static byte[] getMacBytes(String macStr) throws IllegalArgumentException {
        byte[] bytes = new byte[6];
        String[] hex = macStr.split("(\\:|\\-)");
        if (hex.length != 6) {
            throw new IllegalArgumentException("Invalid MAC address.");
        }
        try {
            for (int i = 0; i < 6; i++) {
                bytes[i] = (byte) Integer.parseInt(hex[i], 16);
            }
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid hex digit in MAC address.");
        }
        return bytes;
    }
}
