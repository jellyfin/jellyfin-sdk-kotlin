package MediaBrowser.Model.Providers;

import MediaBrowser.Model.Entities.*;

public class RemoteSearchResult implements IHasProviderIds
{
	/** 
	 Gets or sets the name.
	 
	 <value>The name.</value>
	*/
	private String Name;
	public final String getName()
	{
		return Name;
	}
	public final void setName(String value)
	{
		Name = value;
	}
	/** 
	 Gets or sets the provider ids.
	 
	 <value>The provider ids.</value>
	*/
	private java.util.HashMap<String, String> ProviderIds;
	public final java.util.HashMap<String, String> getProviderIds()
	{
		return ProviderIds;
	}
	public final void setProviderIds(java.util.HashMap<String, String> value)
	{
		ProviderIds = value;
	}
	/** 
	 Gets or sets the year.
	 
	 <value>The year.</value>
	*/
	private Integer ProductionYear = null;
	public final Integer getProductionYear()
	{
		return ProductionYear;
	}
	public final void setProductionYear(Integer value)
	{
		ProductionYear = value;
	}
	private Integer IndexNumber = null;
	public final Integer getIndexNumber()
	{
		return IndexNumber;
	}
	public final void setIndexNumber(Integer value)
	{
		IndexNumber = value;
	}
	private Integer IndexNumberEnd = null;
	public final Integer getIndexNumberEnd()
	{
		return IndexNumberEnd;
	}
	public final void setIndexNumberEnd(Integer value)
	{
		IndexNumberEnd = value;
	}
	private Integer ParentIndexNumber = null;
	public final Integer getParentIndexNumber()
	{
		return ParentIndexNumber;
	}
	public final void setParentIndexNumber(Integer value)
	{
		ParentIndexNumber = value;
	}

	private java.util.Date PremiereDate = null;
	public final java.util.Date getPremiereDate()
	{
		return PremiereDate;
	}
	public final void setPremiereDate(java.util.Date value)
	{
		PremiereDate = value;
	}

	private String ImageUrl;
	public final String getImageUrl()
	{
		return ImageUrl;
	}
	public final void setImageUrl(String value)
	{
		ImageUrl = value;
	}

	private String SearchProviderName;
	public final String getSearchProviderName()
	{
		return SearchProviderName;
	}
	public final void setSearchProviderName(String value)
	{
		SearchProviderName = value;
	}

	private String GameSystem;
	public final String getGameSystem()
	{
		return GameSystem;
	}
	public final void setGameSystem(String value)
	{
		GameSystem = value;
	}

	public RemoteSearchResult()
	{
		setProviderIds(new java.util.HashMap<String, String>());
	}
}