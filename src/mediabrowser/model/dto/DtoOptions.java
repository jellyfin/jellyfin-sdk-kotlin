package mediabrowser.model.dto;

import mediabrowser.model.entities.*;
import mediabrowser.model.querying.*;

public class DtoOptions
{
	private java.util.ArrayList<ItemFields> Fields;
	public final java.util.ArrayList<ItemFields> getFields()
	{
		return Fields;
	}
	public final void setFields(java.util.ArrayList<ItemFields> value)
	{
		Fields = value;
	}
	private java.util.ArrayList<ImageType> ImageTypes;
	public final java.util.ArrayList<ImageType> getImageTypes()
	{
		return ImageTypes;
	}
	public final void setImageTypes(java.util.ArrayList<ImageType> value)
	{
		ImageTypes = value;
	}
	private int ImageTypeLimit;
	public final int getImageTypeLimit()
	{
		return ImageTypeLimit;
	}
	public final void setImageTypeLimit(int value)
	{
		ImageTypeLimit = value;
	}
	private boolean EnableImages;
	public final boolean getEnableImages()
	{
		return EnableImages;
	}
	public final void setEnableImages(boolean value)
	{
		EnableImages = value;
	}

	public DtoOptions()
	{
		setFields(new java.util.ArrayList<ItemFields>());
		setImageTypes(new java.util.ArrayList<ImageType>());
		setImageTypeLimit(Integer.MAX_VALUE);
		setEnableImages(true);
	}

	public final int GetImageLimit(ImageType type)
	{
		if (getEnableImages() && getImageTypes().contains(type))
		{
			return getImageTypeLimit();
		}

		return 0;
	}
}