package MediaBrowser.Model.Chapters;

public class RemoteChapterResult
{
	/** 
	 Gets or sets the identifier.
	 
	 <value>The identifier.</value>
	*/
	private String Id;
	public final String getId()
	{
		return Id;
	}
	public final void setId(String value)
	{
		Id = value;
	}

	/** 
	 Gets or sets the run time ticks.
	 
	 <value>The run time ticks.</value>
	*/
	private Long RunTimeTicks;
	public final Long getRunTimeTicks()
	{
		return RunTimeTicks;
	}
	public final void setRunTimeTicks(Long value)
	{
		RunTimeTicks = value;
	}

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
	 Gets or sets the name of the provider.
	 
	 <value>The name of the provider.</value>
	*/
	private String ProviderName;
	public final String getProviderName()
	{
		return ProviderName;
	}
	public final void setProviderName(String value)
	{
		ProviderName = value;
	}

	/** 
	 Gets or sets the community rating.
	 
	 <value>The community rating.</value>
	*/
	private Float CommunityRating;
	public final Float getCommunityRating()
	{
		return CommunityRating;
	}
	public final void setCommunityRating(Float value)
	{
		CommunityRating = value;
	}

	/** 
	 Gets or sets the chapter count.
	 
	 <value>The chapter count.</value>
	*/
	private Integer ChapterCount;
	public final Integer getChapterCount()
	{
		return ChapterCount;
	}
	public final void setChapterCount(Integer value)
	{
		ChapterCount = value;
	}

	/** 
	 Gets or sets the name of the three letter iso language.
	 
	 <value>The name of the three letter iso language.</value>
	*/
	private String ThreeLetterISOLanguageName;
	public final String getThreeLetterISOLanguageName()
	{
		return ThreeLetterISOLanguageName;
	}
	public final void setThreeLetterISOLanguageName(String value)
	{
		ThreeLetterISOLanguageName = value;
	}
}