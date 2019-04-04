package org.jellyfin.apiclient.model.search;

public class SearchQuery
{
	/** 
	 The user to localize search results for
	 
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
	 Gets or sets the search term.
	 
	 <value>The search term.</value>
	*/
	private String SearchTerm;
	public final String getSearchTerm()
	{
		return SearchTerm;
	}
	public final void setSearchTerm(String value)
	{
		SearchTerm = value;
	}

	private String ParentId;
	public final String getParentId()
	{
		return ParentId;
	}
	public final void setParentId(String value)
	{
		ParentId = value;
	}

	/**
	 Skips over a given number of items within the results. Use for paging.
	 
	 <value>The start index.</value>
	*/
	private Integer StartIndex;
	public final Integer getStartIndex()
	{
		return StartIndex;
	}
	public final void setStartIndex(Integer value)
	{
		StartIndex = value;
	}

	/** 
	 The maximum number of items to return
	 
	 <value>The limit.</value>
	*/
	private Integer Limit;
	public final Integer getLimit()
	{
		return Limit;
	}
	public final void setLimit(Integer value)
	{
		Limit = value;
	}

	private boolean IncludePeople;
	public final boolean getIncludePeople()
	{
		return IncludePeople;
	}
	public final void setIncludePeople(boolean value)
	{
		IncludePeople = value;
	}
	private boolean IncludeMedia;
	public final boolean getIncludeMedia()
	{
		return IncludeMedia;
	}
	public final void setIncludeMedia(boolean value)
	{
		IncludeMedia = value;
	}
	private boolean IncludeGenres;
	public final boolean getIncludeGenres()
	{
		return IncludeGenres;
	}
	public final void setIncludeGenres(boolean value)
	{
		IncludeGenres = value;
	}
	private boolean IncludeStudios;
	public final boolean getIncludeStudios()
	{
		return IncludeStudios;
	}
	public final void setIncludeStudios(boolean value)
	{
		IncludeStudios = value;
	}
	private boolean IncludeArtists;
	public final boolean getIncludeArtists()
	{
		return IncludeArtists;
	}
	public final void setIncludeArtists(boolean value)
	{
		IncludeArtists = value;
	}

	private String[] IncludeItemTypes;
	public final String[] getIncludeItemTypes()
	{
		return IncludeItemTypes;
	}
	public final void setIncludeItemTypes(String[] value)
	{
		IncludeItemTypes = value;
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