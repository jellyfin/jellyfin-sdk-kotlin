package mediabrowser.model.querying;

public class MovieRecommendationQuery
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
	 Gets or sets the parent identifier.
	 
	 <value>The parent identifier.</value>
	*/
	private String ParentId;
	public final String getParentId()
	{
		return ParentId;
	}
	public final void setParentId(String value)
	{
		ParentId = value;
	}
	/** 
	 Gets or sets the item limit.
	 
	 <value>The item limit.</value>
	*/
	private int ItemLimit;
	public final int getItemLimit()
	{
		return ItemLimit;
	}
	public final void setItemLimit(int value)
	{
		ItemLimit = value;
	}
	/** 
	 Gets or sets the category limit.
	 
	 <value>The category limit.</value>
	*/
	private int CategoryLimit;
	public final int getCategoryLimit()
	{
		return CategoryLimit;
	}
	public final void setCategoryLimit(int value)
	{
		CategoryLimit = value;
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

	public MovieRecommendationQuery()
	{
		setItemLimit(10);
		setCategoryLimit(6);
		setFields(new ItemFields[] { });
	}
}