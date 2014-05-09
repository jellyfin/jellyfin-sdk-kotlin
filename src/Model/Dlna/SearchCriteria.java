package MediaBrowser.Model.Dlna;

public class SearchCriteria
{
	private SearchType privateSearchType = getSearchType().values()[0];
	public final SearchType getSearchType()
	{
		return privateSearchType;
	}
	public final void setSearchType(SearchType value)
	{
		privateSearchType = value;
	}

	public SearchCriteria(String search)
	{
		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(search))
		{
			throw new IllegalArgumentException("search");
		}

		setSearchType(SearchType.Unknown);

		if (search.indexOf("upnp:class", StringComparison.OrdinalIgnoreCase) != -1 && search.indexOf("derivedfrom", StringComparison.OrdinalIgnoreCase) != -1)
		{
			if (search.indexOf("object.item.audioItem", StringComparison.OrdinalIgnoreCase) != -1)
			{
				setSearchType(SearchType.Audio);
			}
			else if (search.indexOf("object.item.imageItem", StringComparison.OrdinalIgnoreCase) != -1)
			{
				setSearchType(SearchType.Image);
			}
			else if (search.indexOf("object.item.videoItem", StringComparison.OrdinalIgnoreCase) != -1)
			{
				setSearchType(SearchType.Video);
			}
			else if (search.indexOf("object.container.playlistContainer", StringComparison.OrdinalIgnoreCase) != -1)
			{
				setSearchType(SearchType.Playlist);
			}
		}
	}
}