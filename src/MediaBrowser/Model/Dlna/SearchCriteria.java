package MediaBrowser.Model.Dlna;

import MediaBrowser.Model.Extensions.*;

public class SearchCriteria
{
	private SearchType SearchType = getSearchType().values()[0];
	public final SearchType getSearchType()
	{
		return SearchType;
	}
	public final void setSearchType(SearchType value)
	{
		SearchType = value;
	}

	public SearchCriteria(String search)
	{
		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(search))
		{
			throw new IllegalArgumentException("search");
		}

		setSearchType(SearchType.Unknown);

		String[] factors = StringHelper.RegexSplit(search, "(and|or)");
		for (String factor : factors)
		{
			String[] subFactors = StringHelper.RegexSplit(tangible.DotNetToJavaStringHelper.trim(tangible.DotNetToJavaStringHelper.trim(factor.trim(), '('), ')').trim(), "\\s", 3);

			if (subFactors.length == 3)
			{

				if (StringHelper.EqualsIgnoreCase("upnp:class", subFactors[0]) && (StringHelper.EqualsIgnoreCase("=", subFactors[1]) || StringHelper.EqualsIgnoreCase("derivedfrom", subFactors[1])))
				{
					if (StringHelper.EqualsIgnoreCase("\"object.item.imageItem\"", subFactors[2]) || StringHelper.EqualsIgnoreCase("\"object.item.imageItem.photo\"", subFactors[2]))
					{
						setSearchType(SearchType.Image);
					}
					else if (StringHelper.EqualsIgnoreCase("\"object.item.videoItem\"", subFactors[2]))
					{
						setSearchType(SearchType.Video);
					}
					else if (StringHelper.EqualsIgnoreCase("\"object.container.playlistContainer\"", subFactors[2]))
					{
						setSearchType(SearchType.Playlist);
					}
					else if (StringHelper.EqualsIgnoreCase("\"object.container.album.musicAlbum\"", subFactors[2]))
					{
						setSearchType(SearchType.MusicAlbum);
					}
				}
			}
		}
	}
}