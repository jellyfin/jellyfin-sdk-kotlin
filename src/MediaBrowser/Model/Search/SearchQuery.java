package MediaBrowser.Model.Search;

public class SearchQuery
{
	/** 
	 The user to localize search results for
	 
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
	 Gets or sets the search term.
	 
	 <value>The search term.</value>
	*/
	private String privateSearchTerm;
	public final String getSearchTerm()
	{
		return privateSearchTerm;
	}
	public final void setSearchTerm(String value)
	{
		privateSearchTerm = value;
	}

	/** 
	 Skips over a given number of items within the results. Use for paging.
	 
	 <value>The start index.</value>
	*/
	private Integer privateStartIndex;
	public final Integer getStartIndex()
	{
		return privateStartIndex;
	}
	public final void setStartIndex(Integer value)
	{
		privateStartIndex = value;
	}

	/** 
	 The maximum number of items to return
	 
	 <value>The limit.</value>
	*/
	private Integer privateLimit;
	public final Integer getLimit()
	{
		return privateLimit;
	}
	public final void setLimit(Integer value)
	{
		privateLimit = value;
	}

	private boolean privateIncludePeople;
	public final boolean getIncludePeople()
	{
		return privateIncludePeople;
	}
	public final void setIncludePeople(boolean value)
	{
		privateIncludePeople = value;
	}
	private boolean privateIncludeMedia;
	public final boolean getIncludeMedia()
	{
		return privateIncludeMedia;
	}
	public final void setIncludeMedia(boolean value)
	{
		privateIncludeMedia = value;
	}
	private boolean privateIncludeGenres;
	public final boolean getIncludeGenres()
	{
		return privateIncludeGenres;
	}
	public final void setIncludeGenres(boolean value)
	{
		privateIncludeGenres = value;
	}
	private boolean privateIncludeStudios;
	public final boolean getIncludeStudios()
	{
		return privateIncludeStudios;
	}
	public final void setIncludeStudios(boolean value)
	{
		privateIncludeStudios = value;
	}
	private boolean privateIncludeArtists;
	public final boolean getIncludeArtists()
	{
		return privateIncludeArtists;
	}
	public final void setIncludeArtists(boolean value)
	{
		privateIncludeArtists = value;
	}

	private String[] privateIncludeItemTypes;
	public final String[] getIncludeItemTypes()
	{
		return privateIncludeItemTypes;
	}
	public final void setIncludeItemTypes(String[] value)
	{
		privateIncludeItemTypes = value;
	}

	public SearchQuery()
	{
		setIncludeArtists(true);
		setIncludeGenres(true);
		setIncludeMedia(true);
		setIncludePeople(true);
		setIncludeStudios(true);

		setIncludeItemTypes(new String[] { });
	}
}