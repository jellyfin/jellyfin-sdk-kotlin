package mediabrowser.model.devices;

public class DeviceOptions
{
	/** 
	 Gets or sets the name of the custom.
	 
	 <value>The name of the custom.</value>
	*/
	private String CustomName;
	public final String getCustomName()
	{
		return CustomName;
	}
	public final void setCustomName(String value)
	{
		CustomName = value;
	}
	/** 
	 Gets or sets the camera upload path.
	 
	 <value>The camera upload path.</value>
	*/
	private String CameraUploadPath;
	public final String getCameraUploadPath()
	{
		return CameraUploadPath;
	}
	public final void setCameraUploadPath(String value)
	{
		CameraUploadPath = value;
	}
}