package MediaBrowser.Model.Providers;

import MediaBrowser.Model.Entities.*;

public class RemoteSearchResult implements IHasProviderIds
{
	/** 
	 Gets or sets the name.
	 
	 <value>The name.</value>
	*/
	private String privateName;
	public final String getName()
	{
		return privateName;
	}
	public final void setName(String value)
	{
		privateName = value;
	}
	/** 
	 Gets or sets the provider ids.
	 
	 <value>The provider ids.</value>
	*/
	private java.util.HashMap<String, String> privateProviderIds;
	public final java.util.HashMap<String, String> getProviderIds()
	{
		return privateProviderIds;
	}
	public final void setProviderIds(java.util.HashMap<String, String> value)
	{
		privateProviderIds = value;
	}
	/** 
	 Gets or sets the year.
	 
	 <value>The year.</value>
	*/
	private Integer privateProductionYear = new Integer();
	public final Integer getProductionYear()
	{
		return privateProductionYear;
	}
	public final void setProductionYear(Integer value)
	{
		privateProductionYear = value;
	}
	private Integer privateIndexNumber = new Integer();
	public final Integer getIndexNumber()
	{
		return privateIndexNumber;
	}
	public final void setIndexNumber(Integer value)
	{
		privateIndexNumber = value;
	}
	private Integer privateIndexNumberEnd = new Integer();
	public final Integer getIndexNumberEnd()
	{
		return privateIndexNumberEnd;
	}
	public final void setIndexNumberEnd(Integer value)
	{
		privateIndexNumberEnd = value;
	}
	private Integer privateParentIndexNumber = new Integer();
	public final Integer getParentIndexNumber()
	{
		return privateParentIndexNumber;
	}
	public final void setParentIndexNumber(Integer value)
	{
		privateParentIndexNumber = value;
	}

	private java.util.Date privatePremiereDate = new java.util.Date();
	public final java.util.Date getPremiereDate()
	{
		return privatePremiereDate;
	}
	public final void setPremiereDate(java.util.Date value)
	{
		privatePremiereDate = value;
	}

	private String privateImageUrl;
	public final String getImageUrl()
	{
		return privateImageUrl;
	}
	public final void setImageUrl(String value)
	{
		privateImageUrl = value;
	}

	private String privateSearchProviderName;
	public final String getSearchProviderName()
	{
		return privateSearchProviderName;
	}
	public final void setSearchProviderName(String value)
	{
		privateSearchProviderName = value;
	}

	private String privateGameSystem;
	public final String getGameSystem()
	{
		return privateGameSystem;
	}
	public final void setGameSystem(String value)
	{
		privateGameSystem = value;
	}

	public RemoteSearchResult()
	{
		setProviderIds(new java.util.HashMap<String, String>(StringComparer.OrdinalIgnoreCase));
	}
}