package MediaBrowser.Model.Dlna;

import MediaBrowser.Model.Extensions.*;

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

		if (StringHelper.IndexOfIgnoreCase(search, "upnp:class") != -1 && StringHelper.IndexOfIgnoreCase(search, "derivedfrom") != -1)
		{
			if (StringHelper.IndexOfIgnoreCase(search, "object.item.audioItem") != -1)
			{
				setSearchType(SearchType.Audio);
			}
			else if (StringHelper.IndexOfIgnoreCase(search, "object.item.imageItem") != -1)
			{
				setSearchType(SearchType.Image);
			}
			else if (StringHelper.IndexOfIgnoreCase(search, "object.item.videoItem") != -1)
			{
				setSearchType(SearchType.Video);
			}
			else if (StringHelper.IndexOfIgnoreCase(search, "object.container.playlistContainer") != -1)
			{
				setSearchType(SearchType.Playlist);
			}
		}
	}
}