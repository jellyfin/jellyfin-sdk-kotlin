package mediabrowser.model.querying;

public class UserQuery
{
	private Boolean IsHidden;
	public final Boolean getIsHidden()
	{
		return IsHidden;
	}
	public final void setIsHidden(Boolean value)
	{
		IsHidden = value;
	}
	private Boolean IsDisabled;
	public final Boolean getIsDisabled()
	{
		return IsDisabled;
	}
	public final void setIsDisabled(Boolean value)
	{
		IsDisabled = value;
	}
}