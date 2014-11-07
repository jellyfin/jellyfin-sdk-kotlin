package MediaBrowser.apiinteraction;

public enum ConnectionMode {

    Local(1),
    Remote(2);

    private int intValue;
    private static java.util.HashMap<Integer, ConnectionMode> mappings;
    private static java.util.HashMap<Integer, ConnectionMode> getMappings()
    {
        if (mappings == null)
        {
            synchronized (ConnectionMode.class)
            {
                if (mappings == null)
                {
                    mappings = new java.util.HashMap<Integer, ConnectionMode>();
                }
            }
        }
        return mappings;
    }

    private ConnectionMode(int value)
    {
        intValue = value;
        getMappings().put(value, this);
    }

    public int getValue()
    {
        return intValue;
    }

    public static ConnectionMode forValue(int value)
    {
        return getMappings().get(value);
    }

}
