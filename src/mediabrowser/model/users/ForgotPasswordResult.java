package mediabrowser.model.users;

public class ForgotPasswordResult
{
	/** 
	 Gets or sets the action.
	 
	 <value>The action.</value>
	*/
	private ForgotPasswordAction Action = ForgotPasswordAction.values()[0];
	public final ForgotPasswordAction getAction()
	{
		return Action;
	}
	public final void setAction(ForgotPasswordAction value)
	{
		Action = value;
	}
	/** 
	 Gets or sets the pin file.
	 
	 <value>The pin file.</value>
	*/
	private String PinFile;
	public final String getPinFile()
	{
		return PinFile;
	}
	public final void setPinFile(String value)
	{
		PinFile = value;
	}
	/** 
	 Gets or sets the pin expiration date.
	 
	 <value>The pin expiration date.</value>
	*/
	private java.util.Date PinExpirationDate = null;
	public final java.util.Date getPinExpirationDate()
	{
		return PinExpirationDate;
	}
	public final void setPinExpirationDate(java.util.Date value)
	{
		PinExpirationDate = value;
	}
}