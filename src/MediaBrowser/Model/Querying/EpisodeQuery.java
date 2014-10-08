package MediaBrowser.Model.Querying;

public class EpisodeQuery
{
	private String UserId;
	public final String getUserId()
	{
		return UserId;
	}
	public final void setUserId(String value)
	{
		UserId = value;
	}

	private String SeasonId;
	public final String getSeasonId()
	{
		return SeasonId;
	}
	public final void setSeasonId(String value)
	{
		SeasonId = value;
	}

	private String SeriesId;
	public final String getSeriesId()
	{
		return SeriesId;
	}
	public final void setSeriesId(String value)
	{
		SeriesId = value;
	}

	private Boolean IsMissing;
	public final Boolean getIsMissing()
	{
		return IsMissing;
	}
	public final void setIsMissing(Boolean value)
	{
		IsMissing = value;
	}

	private Boolean IsVirtualUnaired;
	public final Boolean getIsVirtualUnaired()
	{
		return IsVirtualUnaired;
	}
	public final void setIsVirtualUnaired(Boolean value)
	{
		IsVirtualUnaired = value;
	}

	private Integer SeasonNumber;
	public final Integer getSeasonNumber()
	{
		return SeasonNumber;
	}
	public final void setSeasonNumber(Integer value)
	{
		SeasonNumber = value;
	}

	private ItemFields[] Fields;
	public final ItemFields[] getFields()
	{
		return Fields;
	}
	public final void setFields(ItemFields[] value)
	{
		Fields = value;
	}

	public EpisodeQuery()
	{
		setFields(new ItemFields[] { });
	}
}