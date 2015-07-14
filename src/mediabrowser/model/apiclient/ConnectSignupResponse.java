package mediabrowser.model.apiclient;

public enum ConnectSignupResponse
{
	Failure,
	Success,
	EmailInUse,
	UsernameInUser;

	public int getValue()
	{
		return this.ordinal();
	}

	public static ConnectSignupResponse forValue(int value)
	{
		return values()[value];
	}
}