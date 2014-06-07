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
	private Boolean _isInTrial = new Boolean();
	public final boolean getTrialVersion()
	{
		if (_isInTrial == null)
		{
			if (!getRegChecked()) //don't set this until we've successfully obtained exp date
			{
				return false;
			}
			_isInTrial = getExpirationDate().compareTo(new java.util.Date()) > 0;
		}
		return (_isInTrial && !getIsRegistered());
	}
	public final boolean getIsValid()
	{
		return !getRegChecked() || (getIsRegistered() || getTrialVersion());
	}
}