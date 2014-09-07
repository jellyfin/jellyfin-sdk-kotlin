package MediaBrowser.Model.Playlists;

public class PlaylistCreationRequest
{
	 private String privateName;
	 public final String getName()
	 {
		 return privateName;
	 }
	 public final void setName(String value)
	 {
		 privateName = value;
	 }

	private java.util.ArrayList<String> privateItemIdList;
	public final java.util.ArrayList<String> getItemIdList()
	{
		return privateItemIdList;
	}
	public final void setItemIdList(java.util.ArrayList<String> value)
	{
		privateItemIdList = value;
	}

	private String privateMediaType;
	public final String getMediaType()
	{
		return privateMediaType;
	}
	public final void setMediaType(String value)
	{
		privateMediaType = value;
	}

	private String privateUserId;
	public final String getUserId()
	{
		return privateUserId;
	}
	public final void setUserId(String value)
	{
		privateUserId = value;
	}

	public PlaylistCreationRequest()
	{
		setItemIdList(new java.util.ArrayList<String>());
	}
}