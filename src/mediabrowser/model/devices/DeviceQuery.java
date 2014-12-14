package mediabrowser.model.devices;

public class DeviceQuery
{
	/** 
	 Gets or sets a value indicating whether [supports content uploading].
	 
	 <value><c>null</c> if [supports content uploading] contains no value, <c>true</c> if [supports content uploading]; otherwise, <c>false</c>.</value>
	*/
	private Boolean SupportsContentUploading;
	public final Boolean getSupportsContentUploading()
	{
		return SupportsContentUploading;
	}
	public final void setSupportsContentUploading(Boolean value)
	{
		SupportsContentUploading = value;
	}
	/** 
	 Gets or sets a value indicating whether [supports unique identifier].
	 
	 <value><c>null</c> if [supports unique identifier] contains no value, <c>true</c> if [supports unique identifier]; otherwise, <c>false</c>.</value>
	*/
	private Boolean SupportsUniqueIdentifier;
	public final Boolean getSupportsUniqueIdentifier()
	{
		return SupportsUniqueIdentifier;
	}
	public final void setSupportsUniqueIdentifier(Boolean value)
	{
		SupportsUniqueIdentifier = value;
	}
}