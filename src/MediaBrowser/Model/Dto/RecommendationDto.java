package MediaBrowser.Model.Dto;

public class RecommendationDto
{
	private BaseItemDto[] privateItems;
	public final BaseItemDto[] getItems()
	{
		return privateItems;
	}
	public final void setItems(BaseItemDto[] value)
	{
		privateItems = value;
	}

	private RecommendationType privateRecommendationType = getRecommendationType().values()[0];
	public final RecommendationType getRecommendationType()
	{
		return privateRecommendationType;
	}
	public final void setRecommendationType(RecommendationType value)
	{
		privateRecommendationType = value;
	}

	private String privateBaselineItemName;
	public final String getBaselineItemName()
	{
		return privateBaselineItemName;
	}
	public final void setBaselineItemName(String value)
	{
		privateBaselineItemName = value;
	}

	private String privateCategoryId;
	public final String getCategoryId()
	{
		return privateCategoryId;
	}
	public final void setCategoryId(String value)
	{
		privateCategoryId = value;
	}
}