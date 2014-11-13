package mediabrowser.model.dto;

import mediabrowser.model.drawing.*;
import mediabrowser.model.entities.*;

/** 
 Class ImageOptions
*/
public class ImageOptions
{
	/** 
	 Gets or sets the type of the image.
	 
	 <value>The type of the image.</value>
	*/
	private ImageType ImageType = getImageType().values()[0];
	public final ImageType getImageType()
	{
		return ImageType;
	}
	public final void setImageType(ImageType value)
	{
		ImageType = value;
	}

	/** 
	 Gets or sets the index of the image.
	 
	 <value>The index of the image.</value>
	*/
	private Integer ImageIndex;
	public final Integer getImageIndex()
	{
		return ImageIndex;
	}
	public final void setImageIndex(Integer value)
	{
		ImageIndex = value;
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
	 Gets or sets the width of the max.
	 
	 <value>The width of the max.</value>
	*/
	private Integer MaxWidth;
	public final Integer getMaxWidth()
	{
		return MaxWidth;
	}
	public final void setMaxWidth(Integer value)
	{
		MaxWidth = value;
	}

	/** 
	 Gets or sets the height of the max.
	 
	 <value>The height of the max.</value>
	*/
	private Integer MaxHeight;
	public final Integer getMaxHeight()
	{
		return MaxHeight;
	}
	public final void setMaxHeight(Integer value)
	{
		MaxHeight = value;
	}

	/** 
	 Gets or sets the quality.
	 
	 <value>The quality.</value>
	*/
	private Integer Quality;
	public final Integer getQuality()
	{
		return Quality;
	}
	public final void setQuality(Integer value)
	{
		Quality = value;
	}

	/** 
	 Gets or sets the image tag.
	 If set this will result in strong, unconditional response caching
	 
	 <value>The hash.</value>
	*/
	private String Tag;
	public final String getTag()
	{
		return Tag;
	}
	public final void setTag(String value)
	{
		Tag = value;
	}

	/** 
	 Gets or sets a value indicating whether [crop whitespace].
	 
	 <value><c>null</c> if [crop whitespace] contains no value, <c>true</c> if [crop whitespace]; otherwise, <c>false</c>.</value>
	*/
	private Boolean CropWhitespace;
	public final Boolean getCropWhitespace()
	{
		return CropWhitespace;
	}
	public final void setCropWhitespace(Boolean value)
	{
		CropWhitespace = value;
	}

	/** 
	 Gets or sets a value indicating whether [enable image enhancers].
	 
	 <value><c>true</c> if [enable image enhancers]; otherwise, <c>false</c>.</value>
	*/
	private boolean EnableImageEnhancers;
	public final boolean getEnableImageEnhancers()
	{
		return EnableImageEnhancers;
	}
	public final void setEnableImageEnhancers(boolean value)
	{
		EnableImageEnhancers = value;
	}

	/** 
	 Gets or sets the format.
	 
	 <value>The format.</value>
	*/
	private ImageOutputFormat Format;
	public final ImageOutputFormat getFormat()
	{
		return Format;
	}
	public final void setFormat(ImageOutputFormat value)
	{
		Format = value;
	}

	/** 
	 Gets or sets a value indicating whether [add played indicator].
	 
	 <value><c>true</c> if [add played indicator]; otherwise, <c>false</c>.</value>
	*/
	private boolean AddPlayedIndicator;
	public final boolean getAddPlayedIndicator()
	{
		return AddPlayedIndicator;
	}
	public final void setAddPlayedIndicator(boolean value)
	{
		AddPlayedIndicator = value;
	}

	/** 
	 Gets or sets the percent played.
	 
	 <value>The percent played.</value>
	*/
	private Integer PercentPlayed;
	public final Integer getPercentPlayed()
	{
		return PercentPlayed;
	}
	public final void setPercentPlayed(Integer value)
	{
		PercentPlayed = value;
	}

	/** 
	 Gets or sets the color of the background.
	 
	 <value>The color of the background.</value>
	*/
	private String BackgroundColor;
	public final String getBackgroundColor()
	{
		return BackgroundColor;
	}
	public final void setBackgroundColor(String value)
	{
		BackgroundColor = value;
	}

	/** 
	 Initializes a new instance of the <see cref="ImageOptions" /> class.
	*/
	public ImageOptions()
	{
		setEnableImageEnhancers(true);
	}
}