package MediaBrowser.Model.Entities;

public class MBRegistrationRecord
{
	private java.util.Date privateExpirationDate = new java.util.Date(0);
	public final java.util.Date getExpirationDate()
	{
		return privateExpirationDate;
	}
	public final void setExpirationDate(java.util.Date value)
	{
		privateExpirationDate = value;
	}
	private boolean privateIsRegistered;
	public final boolean getIsRegistered()
	{
		return privateIsRegistered;
	}
	public final void setIsRegistered(boolean value)
	{
		privateIsRegistered = value;
	}
	private boolean privateRegChecked;
	public final boolean getRegChecked()
	{
		return privateRegChecked;
	}
	public final void setRegChecked(boolean value)
	{
		privateRegChecked = value;
	}
	private boolean privateRegError;
	public final boolean getRegError()
	{
		return privateRegError;
	}
	public final void setRegError(boolean value)
	{
		privateRegError = value;
	}
	private boolean privateTrialVersion;
	public final boolean getTrialVersion()
	{
		return privateTrialVersion;
	}
	public final void setTrialVersion(boolean value)
	{
		privateTrialVersion = value;
	}
	private boolean privateIsValid;
	public final boolean getIsValid()
	{
		return privateIsValid;
	}
	public final void setIsValid(boolean value)
	{
		privateIsValid = value;
	}
}