package org.jellyfin.apiclient.model.configuration;

import org.jellyfin.apiclient.model.entities.*;

public class ImageOption
{
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
	 Gets or sets the limit.
	 
	 <value>The limit.</value>
	*/
	private int Limit;
	public final int getLimit()
	{
		return Limit;
	}
	public final void setLimit(int value)
	{
		Limit = value;
	}

	/** 
	 Gets or sets the minimum width.
	 
	 <value>The minimum width.</value>
	*/
	private int MinWidth;
	public final int getMinWidth()
	{
		return MinWidth;
	}
	public final void setMinWidth(int value)
	{
		MinWidth = value;
	}

	public ImageOption()
	{
		setLimit(1);
	}
}