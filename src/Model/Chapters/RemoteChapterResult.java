package MediaBrowser.Model.Chapters;

public class RemoteChapterResult
{
	/** 
	 Gets or sets the identifier.
	 
	 <value>The identifier.</value>
	*/
	private String privateId;
	public final String getId()
	{
		return privateId;
	}
	public final void setId(String value)
	{
		privateId = value;
	}

	/** 
	 Gets or sets the run time ticks.
	 
	 <value>The run time ticks.</value>
	*/
	private Long privateRunTimeTicks;
	public final Long getRunTimeTicks()
	{
		return privateRunTimeTicks;
	}
	public final void setRunTimeTicks(Long value)
	{
		privateRunTimeTicks = value;
	}

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
	 Gets or sets the community rating.
	 
	 <value>The community rating.</value>
	*/
	private Float privateCommunityRating;
	public final Float getCommunityRating()
	{
		return privateCommunityRating;
	}
	public final void setCommunityRating(Float value)
	{
		privateCommunityRating = value;
	}

	/** 
	 Gets or sets the chapter count.
	 
	 <value>The chapter count.</value>
	*/
	private Integer privateChapterCount;
	public final Integer getChapterCount()
	{
		return privateChapterCount;
	}
	public final void setChapterCount(Integer value)
	{
		privateChapterCount = value;
	}

	/** 
	 Gets or sets the name of the three letter iso language.
	 
	 <value>The name of the three letter iso language.</value>
	*/
	private String privateThreeLetterISOLanguageName;
	public final String getThreeLetterISOLanguageName()
	{
		return privateThreeLetterISOLanguageName;
	}
	public final void setThreeLetterISOLanguageName(String value)
	{
		privateThreeLetterISOLanguageName = value;
	}
}