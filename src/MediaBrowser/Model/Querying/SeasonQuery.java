package MediaBrowser.Model.Querying;

public class SeasonQuery
{
	private String privateUserId;
	public final String getUserId()
	{
		return privateUserId;
	}
	public final void setUserId(String value)
	{
		privateUserId = value;
	}

	private String privateSeriesId;
	public final String getSeriesId()
	{
		return privateSeriesId;
	}
	public final void setSeriesId(String value)
	{
		privateSeriesId = value;
	}

	private Boolean privateIsMissing;
	public final Boolean getIsMissing()
	{
		return privateIsMissing;
	}
	public final void setIsMissing(Boolean value)
	{
		privateIsMissing = value;
	}

	private Boolean privateIsVirtualUnaired;
	public final Boolean getIsVirtualUnaired()
	{
		return privateIsVirtualUnaired;
	}
	public final void setIsVirtualUnaired(Boolean value)
	{
		privateIsVirtualUnaired = value;
	}

	private ItemFields[] privateFields;
	public final ItemFields[] getFields()
	{
		return privateFields;
	}
	public final void setFields(ItemFields[] value)
	{
		privateFields = value;
	}

	private Boolean privateIsSpecialSeason;
	public final Boolean getIsSpecialSeason()
	{
		return privateIsSpecialSeason;
	}
	public final void setIsSpecialSeason(Boolean value)
	{
		privateIsSpecialSeason = value;
	}

	public SeasonQuery()
	{
		setFields(new ItemFields[] { });
	}
}