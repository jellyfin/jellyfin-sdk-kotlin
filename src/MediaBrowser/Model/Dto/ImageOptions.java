package MediaBrowser.Model.Dto;

import MediaBrowser.Model.Drawing.*;
import MediaBrowser.Model.Entities.*;

/** 
 Class ImageOptions
*/
public class ImageOptions
{
	/** 
	 Gets or sets the type of the image.
	 
	 <value>The type of the image.</value>
	*/
	private ImageType privateImageType = getImageType().values()[0];
	public final ImageType getImageType()
	{
		return privateImageType;
	}
	public final void setImageType(ImageType value)
	{
		privateImageType = value;
	}

	/** 
	 Gets or sets the index of the image.
	 
	 <value>The index of the image.</value>
	*/
	private Integer privateImageIndex = new Integer();
	public final Integer getImageIndex()
	{
		return privateImageIndex;
	}
	public final void setImageIndex(Integer value)
	{
		privateImageIndex = value;
	}

	/** 
	 Gets or sets the width.
	 
	 <value>The width.</value>
	*/
	private Integer privateWidth = new Integer();
	public final Integer getWidth()
	{
		return privateWidth;
	}
	public final void setWidth(Integer value)
	{
		privateWidth = value;
	}

	/** 
	 Gets or sets the height.
	 
	 <value>The height.</value>
	*/
	private Integer privateHeight = new Integer();
	public final Integer getHeight()
	{
		return privateHeight;
	}
	public final void setHeight(Integer value)
	{
		privateHeight = value;
	}

	/** 
	 Gets or sets the width of the max.
	 
	 <value>The width of the max.</value>
	*/
	private Integer privateMaxWidth = new Integer();
	public final Integer getMaxWidth()
	{
		return privateMaxWidth;
	}
	public final void setMaxWidth(Integer value)
	{
		privateMaxWidth = value;
	}

	/** 
	 Gets or sets the height of the max.
	 
	 <value>The height of the max.</value>
	*/
	private Integer privateMaxHeight = new Integer();
	public final Integer getMaxHeight()
	{
		return privateMaxHeight;
	}
	public final void setMaxHeight(Integer value)
	{
		privateMaxHeight = value;
	}

	/** 
	 Gets or sets the quality.
	 
	 <value>The quality.</value>
	*/
	private Integer privateQuality = new Integer();
	public final Integer getQuality()
	{
		return privateQuality;
	}
	public final void setQuality(Integer value)
	{
		privateQuality = value;
	}

	/** 
	 Gets or sets the image tag.
	 If set this will result in strong, unconditional response caching
	 
	 <value>The hash.</value>
	*/
	private String privateTag;
	public final String getTag()
	{
		return privateTag;
	}
	public final void setTag(String value)
	{
		privateTag = value;
	}

	/** 
	 Gets or sets a value indicating whether [crop whitespace].
	 
	 <value><c>null</c> if [crop whitespace] contains no value, <c>true</c> if [crop whitespace]; otherwise, <c>false</c>.</value>
	*/
	private Boolean privateCropWhitespace = new Boolean();
	public final Boolean getCropWhitespace()
	{
		return privateCropWhitespace;
	}
	public final void setCropWhitespace(Boolean value)
	{
		privateCropWhitespace = value;
	}

	/** 
	 Gets or sets a value indicating whether [enable image enhancers].
	 
	 <value><c>true</c> if [enable image enhancers]; otherwise, <c>false</c>.</value>
	*/
	private boolean privateEnableImageEnhancers;
	public final boolean getEnableImageEnhancers()
	{
		return privateEnableImageEnhancers;
	}
	public final void setEnableImageEnhancers(boolean value)
	{
		privateEnableImageEnhancers = value;
	}

	/** 
	 Gets or sets the format.
	 
	 <value>The format.</value>
	*/
	private ImageOutputFormat privateFormat = ImageOutputFormat.values()[0];
	public final ImageOutputFormat getFormat()
	{
		return privateFormat;
	}
	public final void setFormat(ImageOutputFormat value)
	{
		privateFormat = value;
	}

	/** 
	 Gets or sets a value indicating whether [add played indicator].
	 
	 <value><c>true</c> if [add played indicator]; otherwise, <c>false</c>.</value>
	*/
	private boolean privateAddPlayedIndicator;
	public final boolean getAddPlayedIndicator()
	{
		return privateAddPlayedIndicator;
	}
	public final void setAddPlayedIndicator(boolean value)
	{
		privateAddPlayedIndicator = value;
	}

	/** 
	 Gets or sets the percent played.
	 
	 <value>The percent played.</value>
	*/
	private Integer privatePercentPlayed = new Integer();
	public final Integer getPercentPlayed()
	{
		return privatePercentPlayed;
	}
	public final void setPercentPlayed(Integer value)
	{
		privatePercentPlayed = value;
	}

	/** 
	 Gets or sets the color of the background.
	 
	 <value>The color of the background.</value>
	*/
	private String privateBackgroundColor;
	public final String getBackgroundColor()
	{
		return privateBackgroundColor;
	}
	public final void setBackgroundColor(String value)
	{
		privateBackgroundColor = value;
	}

	/** 
	 Initializes a new instance of the <see cref="ImageOptions" /> class.
	*/
	public ImageOptions()
	{
		setEnableImageEnhancers(true);

		setFormat(ImageOutputFormat.Original);
	}
}