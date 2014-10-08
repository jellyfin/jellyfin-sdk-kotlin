package MediaBrowser.Model.Playlists;

public class PlaylistCreationRequest
{
	 private String Name;
	 public final String getName()
	 {
		 return Name;
	 }
	 public final void setName(String value)
	 {
		 Name = value;
	 }

	private java.util.ArrayList<String> ItemIdList;
	public final java.util.ArrayList<String> getItemIdList()
	{
		return ItemIdList;
	}
	public final void setItemIdList(java.util.ArrayList<String> value)
	{
		ItemIdList = value;
	}

	private String MediaType;
	public final String getMediaType()
	{
		return MediaType;
	}
	public final void setMediaType(String value)
	{
		MediaType = value;
	}

	private String UserId;
	public final String getUserId()
	{
		return UserId;
	}
	public final void setUserId(String value)
	{
		UserId = value;
	}

	public PlaylistCreationRequest()
	{
		setItemIdList(new java.util.ArrayList<String>());
	}
}