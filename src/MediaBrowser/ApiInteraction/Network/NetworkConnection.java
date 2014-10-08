package MediaBrowser.ApiInteraction.Network;

import MediaBrowser.ApiInteraction.EmptyResponse;
import MediaBrowser.ApiInteraction.NetworkStatus;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.net.*;

public class NetworkConnection implements INetworkConnection {

    private Context context;

    public NetworkConnection(Context context) {
        this.context = context;
    }

    @Override
    public void SendWakeOnLan(String macAddress, int port, EmptyResponse response) throws IOException
    {
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
    }

    @Override
    public void SendWakeOnLan(String macAddress, String ipAddress, int port, EmptyResponse response)
            throws IOException
    {
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
    }

    @Override
    public NetworkStatus getNetworkStatus() {

        NetworkStatus status = new NetworkStatus();

        final ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();

        if (activeNetwork != null && activeNetwork.isConnected()) {

            status.setIsNetworkAvailable(true);

            int type = activeNetwork.getType();

            if (type == ConnectivityManager.TYPE_MOBILE){
                status.setIsRemoteNetworkAvailable(true);
            }

        } else {

            // notify user you are not online
            status.setIsNetworkAvailable(false);
        }

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
