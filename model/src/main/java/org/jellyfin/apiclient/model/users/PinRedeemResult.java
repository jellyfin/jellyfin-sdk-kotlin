package org.jellyfin.apiclient.model.users;

public class PinRedeemResult
{
	/** 
	 Gets or sets a value indicating whether this <see cref="PinRedeemResult"/> is success.
	 
	 <value><c>true</c> if success; otherwise, <c>false</c>.</value>
	*/
	private boolean Success;
	public final boolean getSuccess()
	{
		return Success;
	}
	public final void setSuccess(boolean value)
	{
		Success = value;
	}
	/** 
	 Gets or sets the users reset.
	 
	 <value>The users reset.</value>
	*/
	private String[] UsersReset;
	public final String[] getUsersReset()
	{
		return UsersReset;
	}
	public final void setUsersReset(String[] value)
	{
		UsersReset = value;
	}
}