package MediaBrowser.Model.Providers;

import MediaBrowser.Model.Entities.*;

/** 
 Class ImageProviderInfo.
*/
public class ImageProviderInfo
{
	/** 
	 Gets or sets the name.
	 
	 <value>The name.</value>
	*/
	private String privateName;
	public final String getName()
	{
		return privateName;
	}
	public final void setName(String value)
	{
		privateName = value;
	}

	private java.util.ArrayList<ImageType> privateSupportedImages;
	public final java.util.ArrayList<ImageType> getSupportedImages()
	{
		return privateSupportedImages;
	}
	public final void setSupportedImages(java.util.ArrayList<ImageType> value)
	{
		privateSupportedImages = value;
	}

	public ImageProviderInfo()
	{
		setSupportedImages(new java.util.ArrayList<ImageType>());
	}
}