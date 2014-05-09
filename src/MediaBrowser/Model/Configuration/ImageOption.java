package MediaBrowser.Model.Configuration;

import MediaBrowser.Model.Entities.*;

public class ImageOption
{
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
	 Gets or sets the limit.
	 
	 <value>The limit.</value>
	*/
	private int privateLimit;
	public final int getLimit()
	{
		return privateLimit;
	}
	public final void setLimit(int value)
	{
		privateLimit = value;
	}

	/** 
	 Gets or sets the minimum width.
	 
	 <value>The minimum width.</value>
	*/
	private int privateMinWidth;
	public final int getMinWidth()
	{
		return privateMinWidth;
	}
	public final void setMinWidth(int value)
	{
		privateMinWidth = value;
	}

	public ImageOption()
	{
		setLimit(1);
	}
}