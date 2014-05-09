package MediaBrowser.Model.Querying;

public class UserQuery
{
	private Boolean privateIsHidden;
	public final Boolean getIsHidden()
	{
		return privateIsHidden;
	}
	public final void setIsHidden(Boolean value)
	{
		privateIsHidden = value;
	}
	private Boolean privateIsDisabled;
	public final Boolean getIsDisabled()
	{
		return privateIsDisabled;
	}
	public final void setIsDisabled(Boolean value)
	{
		privateIsDisabled = value;
	}
}