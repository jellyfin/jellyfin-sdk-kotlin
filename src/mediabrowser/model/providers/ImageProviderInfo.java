package mediabrowser.model.providers;

import mediabrowser.model.entities.*;

/** 
 Class ImageProviderInfo.
*/
public class ImageProviderInfo
{
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

	private java.util.ArrayList<ImageType> SupportedImages;
	public final java.util.ArrayList<ImageType> getSupportedImages()
	{
		return SupportedImages;
	}
	public final void setSupportedImages(java.util.ArrayList<ImageType> value)
	{
		SupportedImages = value;
	}

	public ImageProviderInfo()
	{
		setSupportedImages(new java.util.ArrayList<ImageType>());
	}
}