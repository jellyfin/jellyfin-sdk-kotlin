package mediabrowser.model.entities;

public class SupporterInfo
{
	private String Email;
	public final String getEmail()
	{
		return Email;
	}
	public final void setEmail(String value)
	{
		Email = value;
	}
	private String SupporterKey;
	public final String getSupporterKey()
	{
		return SupporterKey;
	}
	public final void setSupporterKey(String value)
	{
		SupporterKey = value;
	}
	private java.util.Date ExpirationDate = null;
	public final java.util.Date getExpirationDate()
	{
		return ExpirationDate;
	}
	public final void setExpirationDate(java.util.Date value)
	{
		ExpirationDate = value;
	}
	private java.util.Date RegistrationDate = new java.util.Date(0);
	public final java.util.Date getRegistrationDate()
	{
		return RegistrationDate;
	}
	public final void setRegistrationDate(java.util.Date value)
	{
		RegistrationDate = value;
	}
	private String PlanType;
	public final String getPlanType()
	{
		return PlanType;
	}
	public final void setPlanType(String value)
	{
		PlanType = value;
	}
	private boolean IsActiveSupporter;
	public final boolean getIsActiveSupporter()
	{
		return IsActiveSupporter;
	}
	public final void setIsActiveSupporter(boolean value)
	{
		IsActiveSupporter = value;
	}
	private boolean IsExpiredSupporter;
	public final boolean getIsExpiredSupporter()
	{
		return IsExpiredSupporter;
	}
	public final void setIsExpiredSupporter(boolean value)
	{
		IsExpiredSupporter = value;
	}
}