package MediaBrowser.Model.Providers;

/** 
 Class RemoteImageResult.
*/
public class RemoteImageResult
{
	/** 
	 Gets or sets the images.
	 
	 <value>The images.</value>
	*/
	private java.util.ArrayList<RemoteImageInfo> privateImages;
	public final java.util.ArrayList<RemoteImageInfo> getImages()
	{
		return privateImages;
	}
	public final void setImages(java.util.ArrayList<RemoteImageInfo> value)
	{
		privateImages = value;
	}

	/** 
	 Gets or sets the total record count.
	 
	 <value>The total record count.</value>
	*/
	private int privateTotalRecordCount;
	public final int getTotalRecordCount()
	{
		return privateTotalRecordCount;
	}
	public final void setTotalRecordCount(int value)
	{
		privateTotalRecordCount = value;
	}

	/** 
	 Gets or sets the providers.
	 
	 <value>The providers.</value>
	*/
	private java.util.ArrayList<String> privateProviders;
	public final java.util.ArrayList<String> getProviders()
	{
		return privateProviders;
	}
	public final void setProviders(java.util.ArrayList<String> value)
	{
		privateProviders = value;
	}
}