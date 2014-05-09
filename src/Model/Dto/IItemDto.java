package MediaBrowser.Model.Dto;

/** 
 Interface IItemDto
*/
public interface IItemDto
{
	/** 
	 Gets or sets the primary image aspect ratio.
	 
	 <value>The primary image aspect ratio.</value>
	*/
	Double getPrimaryImageAspectRatio();
	void setPrimaryImageAspectRatio(Double value);

	/** 
	 Gets or sets the original primary image aspect ratio.
	 
	 <value>The original primary image aspect ratio.</value>
	*/
	Double getOriginalPrimaryImageAspectRatio();
	void setOriginalPrimaryImageAspectRatio(Double value);
}