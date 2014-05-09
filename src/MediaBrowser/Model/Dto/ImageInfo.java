package MediaBrowser.Model.Dto;

import MediaBrowser.Model.Entities.*;

/** 
 Class ImageInfo
*/
public class ImageInfo
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
	 The image tag
	*/
	public String ImageTag;

	/** 
	 Gets or sets the path.
	 
	 <value>The path.</value>
	*/
	private String privatePath;
	public final String getPath()
	{
		return privatePath;
	}
	public final void setPath(String value)
	{
		privatePath = value;
	}

	/** 
	 Gets or sets the height.
	 
	 <value>The height.</value>
	*/
	private int privateHeight;
	public final int getHeight()
	{
		return privateHeight;
	}
	public final void setHeight(int value)
	{
		privateHeight = value;
	}

	/** 
	 Gets or sets the width.
	 
	 <value>The width.</value>
	*/
	private int privateWidth;
	public final int getWidth()
	{
		return privateWidth;
	}
	public final void setWidth(int value)
	{
		privateWidth = value;
	}

	/** 
	 Gets or sets the size.
	 
	 <value>The size.</value>
	*/
	private long privateSize;
	public final long getSize()
	{
		return privateSize;
	}
	public final void setSize(long value)
	{
		privateSize = value;
	}
}