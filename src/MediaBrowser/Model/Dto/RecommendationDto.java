package MediaBrowser.Model.Dto;

public class RecommendationDto
{
	private BaseItemDto[] Items;
	public final BaseItemDto[] getItems()
	{
		return Items;
	}
	public final void setItems(BaseItemDto[] value)
	{
		Items = value;
	}

	private RecommendationType RecommendationType = getRecommendationType().values()[0];
	public final RecommendationType getRecommendationType()
	{
		return RecommendationType;
	}
	public final void setRecommendationType(RecommendationType value)
	{
		RecommendationType = value;
	}

	private String BaselineItemName;
	public final String getBaselineItemName()
	{
		return BaselineItemName;
	}
	public final void setBaselineItemName(String value)
	{
		BaselineItemName = value;
	}

	private String CategoryId;
	public final String getCategoryId()
	{
		return CategoryId;
	}
	public final void setCategoryId(String value)
	{
		CategoryId = value;
	}
}