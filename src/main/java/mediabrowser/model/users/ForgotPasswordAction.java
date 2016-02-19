package mediabrowser.model.users;

public enum ForgotPasswordAction
{
	ContactAdmin(0),
	PinCode(1),
	InNetworkRequired(2);

	private int intValue;
	private static java.util.HashMap<Integer, ForgotPasswordAction> mappings;
	private static java.util.HashMap<Integer, ForgotPasswordAction> getMappings()
	{
		if (mappings == null)
		{
			synchronized (ForgotPasswordAction.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, ForgotPasswordAction>();
				}
			}
		}
		return mappings;
	}

	private ForgotPasswordAction(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static ForgotPasswordAction forValue(int value)
	{
		return getMappings().get(value);
	}
}