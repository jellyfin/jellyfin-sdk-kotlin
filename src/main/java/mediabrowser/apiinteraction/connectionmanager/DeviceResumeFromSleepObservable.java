package mediabrowser.apiinteraction.connectionmanager;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Luke on 3/23/2015.
 */
public class DeviceResumeFromSleepObservable implements Observer {

    private ConnectionManager connectionManager;

    public DeviceResumeFromSleepObservable(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public void update(Observable observable, Object o)
    {
        connectionManager.WakeAllServers();
    }

}
