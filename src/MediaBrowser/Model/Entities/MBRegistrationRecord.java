package MediaBrowser.Model.Entities;

public class MBRegistrationRecord
{
	private java.util.Date ExpirationDate = new java.util.Date(0);
	public final java.util.Date getExpirationDate()
	{
		return ExpirationDate;
	}
	public final void setExpirationDate(java.util.Date value)
	{
		ExpirationDate = value;
	}
	private boolean IsRegistered;
	public final boolean getIsRegistered()
	{
		return IsRegistered;
	}
	public final void setIsRegistered(boolean value)
	{
		IsRegistered = value;
	}
	private boolean RegChecked;
	public final boolean getRegChecked()
	{
		return RegChecked;
	}
	public final void setRegChecked(boolean value)
	{
		RegChecked = value;
	}
	private boolean RegError;
	public final boolean getRegError()
	{
		return RegError;
	}
	public final void setRegError(boolean value)
	{
		RegError = value;
	}
	private boolean TrialVersion;
	public final boolean getTrialVersion()
	{
		return TrialVersion;
	}
	public final void setTrialVersion(boolean value)
	{
		TrialVersion = value;
	}
	private boolean IsValid;
	public final boolean getIsValid()
	{
		return IsValid;
	}
	public final void setIsValid(boolean value)
	{
		IsValid = value;
	}
}