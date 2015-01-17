package mediabrowser.model.sync;

import mediabrowser.model.entities.*;

public class ItemFileInfo
{
	/** 
	 Gets or sets the type.
	 
	 <value>The type.</value>
	*/
	private ItemFileType Type = ItemFileType.values()[0];
	public final ItemFileType getType()
	{
		return Type;
	}
	public final void setType(ItemFileType value)
	{
		Type = value;
	}
	/** 
	 Gets or sets the name.
	 
	 <value>The name.</value>
	*/
	private String Name;
	public final String getName()
	{
		return Name;
	}
	public final void setName(String value)
	{
		Name = value;
	}
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
	 Gets or sets the type of the image.
	 
	 <value>The type of the image.</value>
	*/
	private ImageType ImageType;
	public final ImageType getImageType()
	{
		return ImageType;
	}
	public final void setImageType(ImageType value)
	{
		ImageType = value;
	}
	/** 
	 Gets or sets the index.
	 
	 <value>The index.</value>
	*/
	private int Index;
	public final int getIndex()
	{
		return Index;
	}
	public final void setIndex(int value)
	{
		Index = value;
	}
}