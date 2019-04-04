package org.jellyfin.apiclient.model.session;

import org.jellyfin.apiclient.model.dto.*;

/** 
 Class UserDataChangeInfo
*/
public class UserDataChangeInfo
{
	/** 
	 Gets or sets the user id.
	 
	 <value>The user id.</value>
	*/
	private String UserId;
	public final String getUserId()
	{
		return UserId;
	}
	public final void setUserId(String value)
	{
		UserId = value;
	}

	/** 
	 Gets or sets the user data list.
	 
	 <value>The user data list.</value>
	*/
	private java.util.ArrayList<UserItemDataDto> UserDataList;
	public final java.util.ArrayList<UserItemDataDto> getUserDataList()
	{
		return UserDataList;
	}
	public final void setUserDataList(java.util.ArrayList<UserItemDataDto> value)
	{
		UserDataList = value;
	}
}