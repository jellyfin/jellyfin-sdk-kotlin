package MediaBrowser.Model.Querying;

public class EpisodeQuery
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

	private String privateSeasonId;
	public final String getSeasonId()
	{
		return privateSeasonId;
	}
	public final void setSeasonId(String value)
	{
		privateSeasonId = value;
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

	private Integer privateSeasonNumber;
	public final Integer getSeasonNumber()
	{
		return privateSeasonNumber;
	}
	public final void setSeasonNumber(Integer value)
	{
		privateSeasonNumber = value;
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

	public EpisodeQuery()
	{
		setFields(new ItemFields[] { });
	}
}