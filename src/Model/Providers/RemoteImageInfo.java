package MediaBrowser.Model.Providers;

import MediaBrowser.Model.Dto.*;
import MediaBrowser.Model.Entities.*;

/** 
 Class RemoteImageInfo
*/
public class RemoteImageInfo
{
	/** 
	 Gets or sets the name of the provider.
	 
	 <value>The name of the provider.</value>
	*/
	private String privateProviderName;
	public final String getProviderName()
	{
		return privateProviderName;
	}
	public final void setProviderName(String value)
	{
		privateProviderName = value;
	}

	/** 
	 Gets or sets the URL.
	 
	 <value>The URL.</value>
	*/
	private String privateUrl;
	public final String getUrl()
	{
		return privateUrl;
	}
	public final void setUrl(String value)
	{
		privateUrl = value;
	}

	/** 
	 Gets a url used for previewing a smaller version
	*/
	private String privateThumbnailUrl;
	public final String getThumbnailUrl()
	{
		return privateThumbnailUrl;
	}
	public final void setThumbnailUrl(String value)
	{
		privateThumbnailUrl = value;
	}

	/** 
	 Gets or sets the height.
	 
	 <value>The height.</value>
	*/
	private Integer privateHeight;
	public final Integer getHeight()
	{
		return privateHeight;
	}
	public final void setHeight(Integer value)
	{
		privateHeight = value;
	}

	/** 
	 Gets or sets the width.
	 
	 <value>The width.</value>
	*/
	private Integer privateWidth;
	public final Integer getWidth()
	{
		return privateWidth;
	}
	public final void setWidth(Integer value)
	{
		privateWidth = value;
	}

	/** 
	 Gets or sets the community rating.
	 
	 <value>The community rating.</value>
	*/
	private Double privateCommunityRating;
	public final Double getCommunityRating()
	{
		return privateCommunityRating;
	}
	public final void setCommunityRating(Double value)
	{
		privateCommunityRating = value;
	}

	/** 
	 Gets or sets the vote count.
	 
	 <value>The vote count.</value>
	*/
	private Integer privateVoteCount;
	public final Integer getVoteCount()
	{
		return privateVoteCount;
	}
	public final void setVoteCount(Integer value)
	{
		privateVoteCount = value;
	}

	/** 
	 Gets or sets the language.
	 
	 <value>The language.</value>
	*/
	private String privateLanguage;
	public final String getLanguage()
	{
		return privateLanguage;
	}
	public final void setLanguage(String value)
	{
		privateLanguage = value;
	}

	/** 
	 Gets or sets the type.
	 
	 <value>The type.</value>
	*/
	private ImageType privateType = ImageType.values()[0];
	public final ImageType getType()
	{
		return privateType;
	}
	public final void setType(ImageType value)
	{
		privateType = value;
	}

	/** 
	 Gets or sets the type of the rating.
	 
	 <value>The type of the rating.</value>
	*/
	private RatingType privateRatingType = getRatingType().values()[0];
	public final RatingType getRatingType()
	{
		return privateRatingType;
	}
	public final void setRatingType(RatingType value)
	{
		privateRatingType = value;
	}
}