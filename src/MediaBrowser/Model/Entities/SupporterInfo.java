package MediaBrowser.Model.Entities;

public class SupporterInfo
{
	private String privateEmail;
	public final String getEmail()
	{
		return privateEmail;
	}
	public final void setEmail(String value)
	{
		privateEmail = value;
	}
	private String privateSupporterKey;
	public final String getSupporterKey()
	{
		return privateSupporterKey;
	}
	public final void setSupporterKey(String value)
	{
		privateSupporterKey = value;
	}
	private java.util.Date privateExpirationDate = null;
	public final java.util.Date getExpirationDate()
	{
		return privateExpirationDate;
	}
	public final void setExpirationDate(java.util.Date value)
	{
		privateExpirationDate = value;
	}
	private java.util.Date privateRegistrationDate = new java.util.Date(0);
	public final java.util.Date getRegistrationDate()
	{
		return privateRegistrationDate;
	}
	public final void setRegistrationDate(java.util.Date value)
	{
		privateRegistrationDate = value;
	}
	private String privatePlanType;
	public final String getPlanType()
	{
		return privatePlanType;
	}
	public final void setPlanType(String value)
	{
		privatePlanType = value;
	}
	private boolean privateIsActiveSupporter;
	public final boolean getIsActiveSupporter()
	{
		return privateIsActiveSupporter;
	}
	public final void setIsActiveSupporter(boolean value)
	{
		privateIsActiveSupporter = value;
	}
	private boolean privateIsExpiredSupporter;
	public final boolean getIsExpiredSupporter()
	{
		return privateIsExpiredSupporter;
	}
	public final void setIsExpiredSupporter(boolean value)
	{
		privateIsExpiredSupporter = value;
	}
}