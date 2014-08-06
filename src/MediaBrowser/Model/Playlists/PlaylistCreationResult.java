package MediaBrowser.Model.Playlists;

public class PlaylistCreationResult
{
	private String privateId;
	public final String getId()
	{
		return privateId;
	}
	public final void setId(String value)
	{
		privateId = value;
	}
}