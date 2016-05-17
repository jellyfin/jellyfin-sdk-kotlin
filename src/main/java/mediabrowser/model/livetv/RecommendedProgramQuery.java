package mediabrowser.model.livetv;

import mediabrowser.model.entities.*;
import mediabrowser.model.querying.*;

public class RecommendedProgramQuery
{
	/** 
	 Fields to return within the items, in addition to basic information
	 
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
	private Boolean EnableImages;
	public final Boolean getEnableImages()
	{
		return EnableImages;
	}
	public final void setEnableImages(Boolean value)
	{
		EnableImages = value;
	}
	private Integer ImageTypeLimit;
	public final Integer getImageTypeLimit()
	{
		return ImageTypeLimit;
	}
	public final void setImageTypeLimit(Integer value)
	{
		ImageTypeLimit = value;
	}
	private ImageType[] EnableImageTypes;
	public final ImageType[] getEnableImageTypes()
	{
		return EnableImageTypes;
	}
	public final void setEnableImageTypes(ImageType[] value)
	{
		EnableImageTypes = value;
	}

	private boolean EnableTotalRecordCount;
	public final boolean getEnableTotalRecordCount()
	{
		return EnableTotalRecordCount;
	}
	public final void setEnableTotalRecordCount(boolean value)
	{
		EnableTotalRecordCount = value;
	}

	public RecommendedProgramQuery()
	{
		setEnableTotalRecordCount(true);
	}

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
	 Gets or sets a value indicating whether this instance is airing.
	 
	 <value><c>true</c> if this instance is airing; otherwise, <c>false</c>.</value>
	*/
	private Boolean IsAiring;
	public final Boolean getIsAiring()
	{
		return IsAiring;
	}
	public final void setIsAiring(Boolean value)
	{
		IsAiring = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance has aired.
	 
	 <value><c>null</c> if [has aired] contains no value, <c>true</c> if [has aired]; otherwise, <c>false</c>.</value>
	*/
	private Boolean HasAired;
	public final Boolean getHasAired()
	{
		return HasAired;
	}
	public final void setHasAired(Boolean value)
	{
		HasAired = value;
	}

	/** 
	 The maximum number of items to return
	 
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
	 Gets or sets a value indicating whether this instance is movie.
	 
	 <value><c>null</c> if [is movie] contains no value, <c>true</c> if [is movie]; otherwise, <c>false</c>.</value>
	*/
	private Boolean IsMovie;
	public final Boolean getIsMovie()
	{
		return IsMovie;
	}
	public final void setIsMovie(Boolean value)
	{
		IsMovie = value;
	}
	/** 
	 Gets or sets a value indicating whether this instance is kids.
	 
	 <value><c>null</c> if [is kids] contains no value, <c>true</c> if [is kids]; otherwise, <c>false</c>.</value>
	*/
	private Boolean IsKids;
	public final Boolean getIsKids()
	{
		return IsKids;
	}
	public final void setIsKids(Boolean value)
	{
		IsKids = value;
	}
	/** 
	 Gets or sets a value indicating whether this instance is sports.
	 
	 <value><c>null</c> if [is sports] contains no value, <c>true</c> if [is sports]; otherwise, <c>false</c>.</value>
	*/
	private Boolean IsSports;
	public final Boolean getIsSports()
	{
		return IsSports;
	}
	public final void setIsSports(Boolean value)
	{
		IsSports = value;
	}
}