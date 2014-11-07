package mediabrowser.model.providers;

/** 
 Class RemoteImageResult.
*/
public class RemoteImageResult
{
	/** 
	 Gets or sets the images.
	 
	 <value>The images.</value>
	*/
	private java.util.ArrayList<RemoteImageInfo> Images;
	public final java.util.ArrayList<RemoteImageInfo> getImages()
	{
		return Images;
	}
	public final void setImages(java.util.ArrayList<RemoteImageInfo> value)
	{
		Images = value;
	}

	/** 
	 Gets or sets the total record count.
	 
	 <value>The total record count.</value>
	*/
	private int TotalRecordCount;
	public final int getTotalRecordCount()
	{
		return TotalRecordCount;
	}
	public final void setTotalRecordCount(int value)
	{
		TotalRecordCount = value;
	}

	/** 
	 Gets or sets the providers.
	 
	 <value>The providers.</value>
	*/
	private java.util.ArrayList<String> Providers;
	public final java.util.ArrayList<String> getProviders()
	{
		return Providers;
	}
	public final void setProviders(java.util.ArrayList<String> value)
	{
		Providers = value;
	}
}