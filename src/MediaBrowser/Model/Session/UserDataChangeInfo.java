package MediaBrowser.Model.Session;

import MediaBrowser.Model.Dto.*;

/** 
 Class UserDataChangeInfo
*/
public class UserDataChangeInfo
{
	/** 
	 Gets or sets the user id.
	 
	 <value>The user id.</value>
	*/
	private String privateUserId;
	public final String getUserId()
	{
		return privateUserId;
	}
	public final void setUserId(String value)
	{
		privateUserId = value;
	}

	/** 
	 Gets or sets the user data list.
	 
	 <value>The user data list.</value>
	*/
	private java.util.ArrayList<UserItemDataDto> privateUserDataList;
	public final java.util.ArrayList<UserItemDataDto> getUserDataList()
	{
		return privateUserDataList;
	}
	public final void setUserDataList(java.util.ArrayList<UserItemDataDto> value)
	{
		privateUserDataList = value;
	}
}