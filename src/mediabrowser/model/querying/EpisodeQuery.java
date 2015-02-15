package mediabrowser.model.querying;

public class EpisodeQuery
{
	/** 
	 Gets or sets the user identifier.
	 
	 <value>The user identifier.</value>
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
	 Gets or sets the season identifier.
	 
	 <value>The season identifier.</value>
	*/
	private String SeasonId;
	public final String getSeasonId()
	{
		return SeasonId;
	}
	public final void setSeasonId(String value)
	{
		SeasonId = value;
	}
	/** 
	 Gets or sets the series identifier.
	 
	 <value>The series identifier.</value>
	*/
	private String SeriesId;
	public final String getSeriesId()
	{
		return SeriesId;
	}
	public final void setSeriesId(String value)
	{
		SeriesId = value;
	}
	/** 
	 Gets or sets a value indicating whether this instance is missing.
	 
	 <value><c>null</c> if [is missing] contains no value, <c>true</c> if [is missing]; otherwise, <c>false</c>.</value>
	*/
	private Boolean IsMissing;
	public final Boolean getIsMissing()
	{
		return IsMissing;
	}
	public final void setIsMissing(Boolean value)
	{
		IsMissing = value;
	}
	/** 
	 Gets or sets a value indicating whether this instance is virtual unaired.
	 
	 <value><c>null</c> if [is virtual unaired] contains no value, <c>true</c> if [is virtual unaired]; otherwise, <c>false</c>.</value>
	*/
	private Boolean IsVirtualUnaired;
	public final Boolean getIsVirtualUnaired()
	{
		return IsVirtualUnaired;
	}
	public final void setIsVirtualUnaired(Boolean value)
	{
		IsVirtualUnaired = value;
	}
	/** 
	 Gets or sets the season number.
	 
	 <value>The season number.</value>
	*/
	private Integer SeasonNumber;
	public final Integer getSeasonNumber()
	{
		return SeasonNumber;
	}
	public final void setSeasonNumber(Integer value)
	{
		SeasonNumber = value;
	}
	/** 
	 Gets or sets the fields.
	 
	 <value>The fields.</value>
	*/
	private ItemFields[] Fields;
	public final ItemFields[] getFields()
	{
		return Fields;
	}
	public final void setFields(ItemFields[] value)
	{
		Fields = value;
	}
	/** 
	 Gets or sets the start index.
	 
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
	 Gets or sets the limit.
	 
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
	/** 
	 Gets or sets the start item identifier.
	 
	 <value>The start item identifier.</value>
	*/
	private String StartItemId;
	public final String getStartItemId()
	{
		return StartItemId;
	}
	public final void setStartItemId(String value)
	{
		StartItemId = value;
	}

	public EpisodeQuery()
	{
		setFields(new ItemFields[] { });
	}
}