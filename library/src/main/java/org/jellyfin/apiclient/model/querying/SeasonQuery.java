package org.jellyfin.apiclient.model.querying;

public class SeasonQuery
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

	private ItemFields[] Fields;
	public final ItemFields[] getFields()
	{
		return Fields;
	}
	public final void setFields(ItemFields[] value)
	{
		Fields = value;
	}

	private Boolean IsSpecialSeason;
	public final Boolean getIsSpecialSeason()
	{
		return IsSpecialSeason;
	}
	public final void setIsSpecialSeason(Boolean value)
	{
		IsSpecialSeason = value;
	}

	public SeasonQuery()
	{
		setFields(new ItemFields[] { });
	}
}