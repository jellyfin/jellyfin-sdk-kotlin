package mediabrowser.model.dto;

import mediabrowser.model.entities.*;

/** 
 Class ImageInfo
*/
public class ImageInfo
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
	 The image tag
	*/
	public String ImageTag;

	/** 
	 Gets or sets the path.
	 
	 <value>The path.</value>
	*/
	private String Path;
	public final String getPath()
	{
		return Path;
	}
	public final void setPath(String value)
	{
		Path = value;
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
	 Gets or sets the size.
	 
	 <value>The size.</value>
	*/
	private long Size;
	public final long getSize()
	{
		return Size;
	}
	public final void setSize(long value)
	{
		Size = value;
	}
}