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
	 Gets or sets the URL.
	 
	 <value>The URL.</value>
	*/
	private String Url;
	public final String getUrl()
	{
		return Url;
	}
	public final void setUrl(String value)
	{
		Url = value;
	}

	/** 
	 Gets a url used for previewing a smaller version
	*/
	private String ThumbnailUrl;
	public final String getThumbnailUrl()
	{
		return ThumbnailUrl;
	}
	public final void setThumbnailUrl(String value)
	{
		ThumbnailUrl = value;
	}

	/** 
	 Gets or sets the height.
	 
	 <value>The height.</value>
	*/
	private Integer Height;
	public final Integer getHeight()
	{
		return Height;
	}
	public final void setHeight(Integer value)
	{
		Height = value;
	}

	/** 
	 Gets or sets the width.
	 
	 <value>The width.</value>
	*/
	private Integer Width;
	public final Integer getWidth()
	{
		return Width;
	}
	public final void setWidth(Integer value)
	{
		Width = value;
	}

	/** 
	 Gets or sets the community rating.
	 
	 <value>The community rating.</value>
	*/
	private Double CommunityRating;
	public final Double getCommunityRating()
	{
		return CommunityRating;
	}
	public final void setCommunityRating(Double value)
	{
		CommunityRating = value;
	}

	/** 
	 Gets or sets the vote count.
	 
	 <value>The vote count.</value>
	*/
	private Integer VoteCount;
	public final Integer getVoteCount()
	{
		return VoteCount;
	}
	public final void setVoteCount(Integer value)
	{
		VoteCount = value;
	}

	/** 
	 Gets or sets the language.
	 
	 <value>The language.</value>
	*/
	private String Language;
	public final String getLanguage()
	{
		return Language;
	}
	public final void setLanguage(String value)
	{
		Language = value;
	}

	/** 
	 Gets or sets the type.
	 
	 <value>The type.</value>
	*/
	private ImageType Type = ImageType.values()[0];
	public final ImageType getType()
	{
		return Type;
	}
	public final void setType(ImageType value)
	{
		Type = value;
	}

	/** 
	 Gets or sets the type of the rating.
	 
	 <value>The type of the rating.</value>
	*/
	private RatingType RatingType = getRatingType().values()[0];
	public final RatingType getRatingType()
	{
		return RatingType;
	}
	public final void setRatingType(RatingType value)
	{
		RatingType = value;
	}
}